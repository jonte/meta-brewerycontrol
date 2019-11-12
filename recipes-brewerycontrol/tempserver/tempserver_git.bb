LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=acf38d238323e04932295add4a59e827"

inherit systemd

RDEPENDS_${PN} = "python3-connexion     \
                  python3-gpiozero      \
                  python3-simple-pid    \
                  python3-w1thermsensor \
"

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "git://github.com/jonte/tempserver.git;protocol=https"
SRCREV = "01a316a87e945d1c12c61d737e6b700533b835a3"

S = "${WORKDIR}/git"

do_install () {
    mkdir -p ${D}/opt/tempserver \
             ${D}/${systemd_unitdir}/system/
    cp -ap ${S}/* ${D}/opt/tempserver
    chown -R root:root ${D}/opt/tempserver

    cat <<HEREDOC > ${D}${systemd_unitdir}/system/ds2482.service
[Unit]
Description=Load ds2482 driver
Before=basic.target
After=local-fs.target sysinit.target
DefaultDependencies=no

[Service]
Type=oneshot
ExecStart=/sbin/modprobe ds2482
ExecStart=/bin/bash -c '/bin/echo ds2482 0x18 > /sys/bus/i2c/devices/i2c-1/new_device'

[Install]
WantedBy=basic.target
HEREDOC

    cat <<HEREDOC > ${D}${systemd_unitdir}/system/tempserver.service
[Unit]
Description=Temperature server
After=network.target ds2482.service

[Service]
Type=simple
WorkingDirectory=/opt/tempserver
ExecStart=/opt/tempserver/main.py

[Install]
WantedBy=basic.target
HEREDOC
}

SYSTEMD_SERVICE_${PN} = "ds2482.service tempserver.service"

FILES_${PN} = "/opt/tempserver/*"

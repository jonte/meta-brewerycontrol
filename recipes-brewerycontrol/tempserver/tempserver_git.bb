LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=acf38d238323e04932295add4a59e827"

inherit systemd setuptools3

RDEPENDS_${PN} = "                      \
                  python3-apscheduler   \
                  python3-connexion     \
                  python3-gpiozero      \
                  python3-simple-pid    \
                  python3-w1thermsensor \
"

SRC_URI = "git://github.com/jonte/tempserver.git;protocol=https"
SRCREV = "e20c29be4ea29d72dd50a9285f99dd537a658c6d"

S = "${WORKDIR}/git"

TEMPSERVERENV_raspberrypi = ""
TEMPSERVERENV_qemux86-64 = "DUMMY=1"

do_install_append () {
    mkdir -p ${D}/${systemd_unitdir}/system/    \
             ${D}/${sysconfdir}
    install -m 755 ${S}/tempserver.conf.sample ${D}/${sysconfdir}/tempserver.conf

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
Environment=${TEMPSERVERENV}
ExecStart=/usr/bin/tempserver

[Install]
WantedBy=basic.target
HEREDOC
}

SYSTEMD_SERVICE_${PN} = "ds2482.service tempserver.service"

FILES_${PN} += " \
    /opt/tempserver/* \
    ${sysconfdir}/   \
"

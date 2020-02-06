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

RDEPENDS_${PN}_append_raspberrypi3 = "ds2482-service"

SRC_URI = "git://github.com/jonte/tempserver.git;protocol=https"
SRCREV = "a9090aefbff792dc5b39c1eae6d5dc2cf326fb0b"

S = "${WORKDIR}/git"

TEMPSERVERENV_raspberrypi3 = ""
TEMPSERVERENV_qemux86-64 = "DUMMY=1"

do_install_append () {
    mkdir -p ${D}/${systemd_unitdir}/system/    \
             ${D}/${sysconfdir}
    install -m 755 ${S}/tempserver.conf.sample ${D}/${sysconfdir}/tempserver.conf

    cat <<HEREDOC > ${D}${systemd_unitdir}/system/tempserver.service
[Unit]
Description=Temperature server
After=network.target ${@bb.utils.contains("MACHINE", "raspberrypi3", "ds2482.service", "", d)}

[Service]
Type=simple
Environment=${TEMPSERVERENV}
ExecStart=/usr/bin/tempserver

[Install]
WantedBy=basic.target
HEREDOC
}

SYSTEMD_SERVICE_${PN} = "tempserver.service"

FILES_${PN} += "${sysconfdir}"

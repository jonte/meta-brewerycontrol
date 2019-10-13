LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "git://github.com/jonte/brewerycontrol_qt.git;protocol=https"
SRCREV = "71cbf42b743d3bfef88fa686b8c92271dbc69f3a"

DEPENDS = "qtbase qtdeclarative qtcharts"
RDEPENDS_${PN} = "\
    qtdeclarative-qmlplugins \
    qtcharts-qmlplugins \
    qtquickcontrols2-qmlplugins \
"

inherit cmake cmake_qt5 systemd

S = "${WORKDIR}/git"

do_install_append() {
    mkdir -p ${D}${systemd_unitdir}/system/ \
             ${D}${sysconfdir}

    cat <<HEREDOC > ${D}${systemd_unitdir}/system/brewerycontrol_qt.service
[Unit]
Description=User interface
After=network.target
After=tempserver.service

[Service]
Type=simple
ExecStart=/usr/bin/brewerycontrol_qt -platform eglfs
Restart=on-failure

[Install]
WantedBy=multi-user.target
HEREDOC

    cat <<HEREDOC > ${D}${sysconfdir}/brewerycontrol.conf
host = "http://localhost:5000/v1"
maxChartPoints = 500
pointRemoveChunkSize = 1
HEREDOC

}

SYSTEMD_SERVICE_${PN} = "brewerycontrol_qt.service"

FILES_${PN} += " \
    ${systemd_unitdir}/system/brewerycontrol_qt.service \
    ${sysconfdir}/brewerycontrol.conf                   \
"

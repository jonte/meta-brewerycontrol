LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "git://github.com/jonte/brewerycontrol_qt.git;protocol=https"
SRCREV = "157b9258e7e9f0807472f0c478887d89371b232c"

DEPENDS = "qtbase qtdeclarative qtcharts"
RDEPENDS_${PN} = "\
    qtdeclarative-qmlplugins \
    qtcharts-qmlplugins \
    qtquickcontrols2-qmlplugins \
"

inherit cmake cmake_qt5 systemd

S = "${WORKDIR}/git"

PACKAGECONFIG ??= "webgl"
PACKAGECONFIG[webgl] = ",,,qtwebglplugin-plugins"

# Set the display platform based on which target we're building for. For qemu,
# a VNC client must be used to display the UI.
MAIN_QT_QPA_PLATFORM_raspberrypi3 = "eglfs"
MAIN_QT_QPA_PLATFORM_qemux86-64 = "vnc"

USE_WEBGL = ""

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
ExecStart=/usr/bin/brewerycontrol_qt -platform ${MAIN_QT_QPA_PLATFORM}
Restart=on-failure

[Install]
WantedBy=multi-user.target
HEREDOC

    cat <<HEREDOC > ${D}${sysconfdir}/brewerycontrol.conf
host = "http://localhost:5000/v1"
maxChartPoints = 500
pointRemoveChunkSize = 1
HEREDOC

    if [ "${@bb.utils.contains('PACKAGECONFIG', 'webgl', '1', '0', d)}" = "1" ]; then
        cat <<HEREDOC > ${D}${systemd_unitdir}/system/brewerycontrol_qt_webgl.service
[Unit]
Description=User interface (webgl)
After=network.target
After=tempserver.service

[Service]
Type=simple
ExecStart=/usr/bin/brewerycontrol_qt -platform webgl:port=8000
Restart=on-failure

[Install]
WantedBy=multi-user.target
HEREDOC

    fi

}

SYSTEMD_SERVICE_${PN} = "brewerycontrol_qt.service"
SYSTEMD_SERVICE_${PN} += "${@bb.utils.contains('PACKAGECONFIG', 'webgl', 'brewerycontrol_qt_webgl.service', '', d)}"

FILES_${PN} += " \
    ${sysconfdir}/brewerycontrol.conf                   \
"

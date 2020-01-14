inherit systemd

RDEPENDS_${PN} = "kernel-module-ds2482"

# Skip the license check - there's no source.
LICENSE = "CLOSED"

do_install () {
    mkdir -p ${D}/${systemd_unitdir}/system

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
}

SYSTEMD_SERVICE_${PN} = "ds2482.service"

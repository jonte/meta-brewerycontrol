inherit systemd

RDEPENDS_${PN} = "wpa-supplicant"

# Skip the license check - there's no source.
LICENSE = "CLOSED"

BC_WIFI_DEV ?= "wlan0"

python do_check_vars () {
    for var in ["BC_WIFI_SSID", "BC_WIFI_PSK"]:
        if not d.getVar(var):
            bb.error(var + " must be set - perhaps you should set it in local.conf?")
}


do_install () {
    mkdir -p ${D}/${sysconfdir}/wpa_supplicant/     \
             ${D}/${sysconfdir}/systemd/network/    \
             ${D}/${sysconfdir}/systemd/system/

    cat <<HEREDOC > ${D}/${sysconfdir}/wpa_supplicant/wpa_supplicant-wlan0.conf
network={
        ssid="${BC_WIFI_SSID}"
        psk=${BC_WIFI_PSK}
 }
HEREDOC

    cat <<HEREDOC > ${D}/${sysconfdir}/systemd/network/00-wireless-dhcp.network
[Match]
Name=${BC_WIFI_DEV}

[Network]
DHCP=yes
HEREDOC

    ln -s ${systemd_unitdir}/system/wpa_supplicant@.service \
          ${D}/${sysconfdir}/systemd/system/wpa_supplicant@${BC_WIFI_DEV}.service

}

SYSTEMD_SERVICE_${PN} = "wpa_supplicant@${BC_WIFI_DEV}.service"

addtask check_vars before do_install

SUMMARY = "A module to control Raspberry Pi GPIO channels"
HOMEPAGE = "http://sourceforge.net/projects/raspberry-gpio-python/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENCE.txt;md5=9b95630a648966b142f1a0dcea001cb7"
PYPI_SRC_URI = "https://files.pythonhosted.org/packages/source/r/rpi-gpio/RPi.GPIO-${PV}.tar.gz"

do_unpack_append() {
    PV = d.getVar("PV")
    os.system("rmdir rpi-gpio-%s" % PV)
    os.system("mv RPi.GPIO-%s rpi-gpio-%s" % (PV,PV))
}

inherit pypi

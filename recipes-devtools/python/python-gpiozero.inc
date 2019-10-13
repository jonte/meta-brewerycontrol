SUMMARY = "A simple interface to GPIO devices with Raspberry Pi."
HOMEPAGE = "https://github.com/RPi-Distro/python-gpiozero"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=96cd08cadd8a35cf94fc1770ee0743da"

inherit pypi

RDEPENDS_${PN} = "${PYTHON_PN}-colorzero ${PYTHON_PN}-rpi-gpio"

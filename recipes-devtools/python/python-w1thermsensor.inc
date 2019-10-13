SUMMARY = "This little pure python module provides a single class to get the temperature of a w1 sensor"
HOMEPAGE = "http://github.com/timofurrer/w1thermsensor"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

inherit pypi

RDEPENDS_${PN} += "python3-click"

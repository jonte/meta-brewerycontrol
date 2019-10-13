SUMMARY = "Yet another Python color library."
HOMEPAGE = "http://colorzero.readthedocs.io/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=b2854a8defdf90fac461ff4aba9cc0ea"

RDEPENDS_${PN} = "${PYTHON_PN}-image"

inherit pypi

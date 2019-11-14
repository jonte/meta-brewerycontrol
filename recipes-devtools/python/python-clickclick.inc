SUMMARY = "Click utility functions"
HOMEPAGE = "https://github.com/zalando/python-clickclick"

# License file is missing. License is actually Apache-2.0
# Bug: https://github.com/hjacobs/python-clickclick/pull/14
LICENSE = "CLOSED"

inherit pypi

S = "${WORKDIR}/clickclick-${PV}"

RDEPENDS_${PN} = "${PYTHON_PN}-pyyaml ${PYTHON_PN}-click"
DEPENDS = "${PYTHON_PN}-six-native"

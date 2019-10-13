SUMMARY = "swagger_ui_bundle - swagger-ui files in a pip package"
HOMEPAGE = "https://github.com/dtkav/swagger_ui_bundle"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

inherit pypi

PYPI_SRC_URI = "https://files.pythonhosted.org/packages/source/s/swagger_ui_bundle/swagger_ui_bundle-${PV}.tar.gz"

do_unpack_append() {
    PV = d.getVar("PV")
    PN = "swagger-ui-bundle"
    PN_broken = PN.replace("-", "_")

    os.system("rmdir %s-%s" % (PN, PV))
    os.system("echo %s-%s %s-%s" % (PN_broken, PV, PN, PV))
    os.system("mv %s-%s %s-%s" % (PN_broken, PV, PN, PV))
}

RDEPENDS_${PN} += "${PYTHON_PN}-jinja2"

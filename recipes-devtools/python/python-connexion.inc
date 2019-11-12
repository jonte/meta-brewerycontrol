SUMMARY = "Connexion - API first applications with OpenAPI/Swagger and Flask"
HOMEPAGE = "https://github.com/zalando/connexion"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=1a59efe5ec370f84218405ce2e537845"

RDEPENDS_${PN} = "\
    ${PYTHON_PN}-async \
    ${PYTHON_PN}-flask \
    ${PYTHON_PN}-inflection \
    ${PYTHON_PN}-jsonschema \
    ${PYTHON_PN}-openapi-spec-validator \
    ${PYTHON_PN}-pyyaml \
    ${PYTHON_PN}-requests \
"

inherit setuptools3 pypi

PACKAGECONFIG[swagger-ui] = ",,,${PYTHON_PN}-swagger-ui-bundle"

PACKAGECONFIG ??= "swagger-ui"

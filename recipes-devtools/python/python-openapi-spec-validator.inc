SUMMARY = "OpenAPI Spec validator"
HOMEPAGE = "https://github.com/p1c2u/openapi-spec-validator"

inherit pypi

RDEPENDS_${PN} += "${PYTHON_PN}-pyyaml ${PYTHON_PN}-jsonschema ${PYTHON_PN}-six"

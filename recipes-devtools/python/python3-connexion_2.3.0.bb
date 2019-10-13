require python-connexion.inc

SRC_URI[md5sum] = "41031478756ff4849103ddb2e89f6a4b"
SRC_URI[sha256sum] = "52bee0bc60edffa2ee6e0a9efc3d1cb1ea6b93df0147534caade612ac34e8036"

SRC_URI += "file://0001-Disable-flake8.patch"

inherit setuptools3

PACKAGECONFIG ??= "swagger-ui"

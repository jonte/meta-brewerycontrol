require python-swagger-ui-bundle.inc

SRC_URI += " file://0001-Disable-testing.patch"
SRC_URI[md5sum] = "1dff2c0d2aa728530d5f52c0ef655faa"
SRC_URI[sha256sum] = "01ae8fdb1fa4e034933e0874afdda0d433dcb94476fccb231b66fd5f49dac96c"

inherit setuptools3

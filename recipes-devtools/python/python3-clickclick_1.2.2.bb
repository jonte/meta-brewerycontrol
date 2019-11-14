require python-clickclick.inc

SRC_URI += " file://0001-Disable-flake8.patch"
SRC_URI[md5sum] = "77a08b665d1fa381f1853b38aa434ce0"
SRC_URI[sha256sum] = "4a890aaa9c3990cfabd446294eb34e3dc89701101ac7b41c1bff85fc210f6d23"

inherit setuptools3

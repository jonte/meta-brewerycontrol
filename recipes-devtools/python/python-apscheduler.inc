SUMMARY = "In-process task scheduler with Cron-like capabilities"
HOMEPAGE = "https://github.com/agronholm/apscheduler"
LICENSE = "MIT"

S = "${WORKDIR}/APScheduler-${PV}"

DEPENDS = "${PYTHON_PN}-setuptools-scm-native"

RDEPENDS_${PN} = "${PYTHON_PN}-tzlocal ${PYTHON_PN}-pytz"

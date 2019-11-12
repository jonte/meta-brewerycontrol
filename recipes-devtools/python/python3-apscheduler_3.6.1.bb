require python-apscheduler.inc

LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=f0e423eea5c91e7aa21bdb70184b3e53"
SRC_URI = "https://files.pythonhosted.org/packages/21/59/20a05dfa5525df11144f68a972b9b25aeeca4933dcbf23510c07955117e4/APScheduler-${PV}.tar.gz"
SRC_URI[md5sum] = "5144b4a86bc203ef352b554b7ea87637"
SRC_URI[sha256sum] = "529afb7909e08416132891188cbfea5351eb35e4a684b67e983d819e8d01a6b0"

inherit setuptools3

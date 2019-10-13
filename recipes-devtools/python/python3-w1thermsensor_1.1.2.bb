require python-w1thermsensor.inc

SRC_URI = "https://files.pythonhosted.org/packages/04/ce/4d982b4762810756eb76ffa54a03ed19618b6de6c2a2d811dc3672d5829d/w1thermsensor-${PV}.tar.gz"
SRC_URI[md5sum] = "d1b983333a52608246c5b3dab804e778"
SRC_URI[sha256sum] = "dc446102fbc6582f17565f2646622c439c99e8eef5144609873f6660eadf9e13"

inherit setuptools3

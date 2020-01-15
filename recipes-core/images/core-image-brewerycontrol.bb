inherit core-image

EXTRA_IMAGE_FEATURES += " package-management "

IMAGE_INSTALL += "      \
    brewerycontrol-qt   \
    openssh             \
    tempserver          \
    tzdata              \
    wifi-configuration  \
"

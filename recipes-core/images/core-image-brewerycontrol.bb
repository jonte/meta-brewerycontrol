inherit core-image

EXTRA_IMAGE_FEATURES += " package-management "

IMAGE_INSTALL += "      \
    brewerycontrol-qt   \
    connman             \
    openssh             \
    tempserver          \
    tzdata              \
    wpa-supplicant      \
"

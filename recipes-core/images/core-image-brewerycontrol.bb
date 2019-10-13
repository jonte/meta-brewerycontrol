inherit core-image

EXTRA_IMAGE_FEATURES += " package-management "

IMAGE_INSTALL += "\
    openssh         \
    wpa-supplicant  \
"

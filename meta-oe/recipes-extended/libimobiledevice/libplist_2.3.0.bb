SUMMARY = "A library to handle Apple Property List format whereas it's binary or XML"
HOMEPAGE = "https://github.com/libimobiledevice/libplist"
LICENSE = "GPL-2.0-only & LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=ebb5c50ab7cab4baeffba14977030c07 \
                    file://COPYING.LESSER;md5=6ab17b41640564434dda85c06b7124f7"

DEPENDS = "libxml2 glib-2.0 swig python3"

inherit autotools pkgconfig python3native python3targetconfig

SRCREV = "72480212cd8ec0ab8d0b5064df52c146cac603b4"
SRC_URI = "git://github.com/libimobiledevice/libplist;protocol=https;branch=master"

S = "${WORKDIR}/git"

CVE_CHECK_IGNORE += "\
    CVE-2017-5834 \
    CVE-2017-5835 \
    CVE-2017-5836 \
"

do_install:append () {
    if [ -e ${D}${libdir}/python*/site-packages/plist/_plist.so ]; then
        chrpath -d ${D}${libdir}/python*/site-packages/plist/_plist.so
    fi
}

PACKAGES =+ "${PN}-utils \
             ${PN}++ \
             ${PN}-python"

FILES:${PN} = "${libdir}/libplist-2.0${SOLIBS}"
FILES:${PN}++ = "${libdir}/libplist++-2.0${SOLIBS}"
FILES:${PN}-utils = "${bindir}/*"
FILES:${PN}-python = "${libdir}/python*/site-packages/*"

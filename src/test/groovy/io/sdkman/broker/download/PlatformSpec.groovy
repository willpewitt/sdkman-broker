package io.sdkman.broker.download

import spock.lang.Specification

class PlatformSpec extends Specification {

    void "should resolve the appropriate platform on valid identifier"() {
        given:
        def p = Platform.of(id)

        expect:
        p.isPresent()
        p.get() == platform

        where:
        id                    | platform
        "Linux"               | Platform.LINUX_64
        "Linux64"             | Platform.LINUX_64
        "Linux32"             | Platform.LINUX_32
        "LinuxARM64"          | Platform.LINUX_ARM64
        "LinuxARM32"          | Platform.LINUX_ARM32

        "Darwin"              | Platform.MAC_OSX

        "SunOS"               | Platform.SUN_OS
        "FreeBSD"             | Platform.FREE_BSD

        "CYGWIN_NT-6.1"       | Platform.WINDOWS_64
        "CYGWIN_NT-6.1-WOW"   | Platform.WINDOWS_64
        "CYGWIN_NT-6.1-WOW64" | Platform.WINDOWS_64
        "CYGWIN_NT-6.3"       | Platform.WINDOWS_64
        "CYGWIN_NT-6.3-WOW"   | Platform.WINDOWS_64
        "CYGWIN_NT-10.0"      | Platform.WINDOWS_64
        "CYGWIN_NT-10.0-WOW"  | Platform.WINDOWS_64

        "MSYS_NT-6.1"         | Platform.WINDOWS_64
        "MSYS_NT-6.3"         | Platform.WINDOWS_64
        "MSYS_NT-10.0"        | Platform.WINDOWS_64

        "MINGW64_NT-6.1"      | Platform.WINDOWS_64
        "MINGW64_NT-6.3"      | Platform.WINDOWS_64
        "MINGW64_NT-10.0"     | Platform.WINDOWS_64

        "MINGW32_NT-6.1"      | Platform.WINDOWS_32
        "MINGW32_NT-6.1-WOW"  | Platform.WINDOWS_32
        "MINGW32_NT-6.2"      | Platform.WINDOWS_32
    }

    void "should resolve empty on no identifier"() {
        expect:
        Platform.of(null) == Optional.empty()
    }

    void "should resolve empty on invalid identifier"() {
        expect:
        Platform.of("ZXSpectrum") == Optional.empty()
    }
}

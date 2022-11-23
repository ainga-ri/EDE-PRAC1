package uoc.ds.pr.util;

import uoc.ds.pr.SportEvents4ClubImpl;

public class ResourceUtil {
    private final SportEvents4ClubImpl sportEvents4Club;

    public ResourceUtil() {
        sportEvents4Club = new SportEvents4ClubImpl();
    }

    public static byte getFlag(byte ... flagBasicLifeSupport) {
        return flagBasicLifeSupport[0];
    }

    public static boolean hasPublicSecurity(byte resource) {
        return true;
    }

    public static boolean hasPrivateSecurity(byte resource) {
        return true;
    }

    public static boolean hasBasicLifeSupport(byte resource) {
        return true;
    }

    public static boolean hasVolunteers(byte resource) {
        return true;
    }

    public static boolean hasAllOpts(byte resource) {
        return true;
    }
}

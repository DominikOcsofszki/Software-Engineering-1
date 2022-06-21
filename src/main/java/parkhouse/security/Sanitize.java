package parkhouse.security;

public class Sanitize {

    private Sanitize() {}

    public static void sanitizeParams(String[] params) {
        params[0] = params[0].replaceAll("[^0-9]{1,2}+", "");
        params[1] = params[1].replaceAll("[^0-9]{13}+", "");
        params[2] = params[2].replaceAll("[^_0-9]+", "");
        params[3] = params[3].replaceAll("[^_0-9]+", "");
        params[4] = params[4].replaceAll("[^a-z0-9]{32}+", "");
        params[5] = params[5].replaceAll("^#[a-z0-9]{7}+", "");
        params[6] = params[6].replaceAll("[^0-9]+", "");
        params[7] = params[7].replaceAll("[^a-zA-Z]+", "");
        params[8] = params[8].replaceAll("[^a-zA-Z]+", "");
        params[9] = params[9].replaceAll("[^A-Z0-9- ]{6,7}+", "");
        params[10] = params[10].replaceAll("[^0-9]{13}+", "");
    }
}

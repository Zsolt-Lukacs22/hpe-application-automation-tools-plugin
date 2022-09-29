package com.microfocus.application.automation.tools.mc;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Pattern;

public class Oauth2TokenUtil {

    private static String client;
    private static String secret;
    private static String tenant;

    private Oauth2TokenUtil() {
    }

    public static boolean validate(String auth2) {
        String strCleaned = removeQuotes(auth2.trim());
        String[] a = strCleaned.split(Pattern.quote(";"));
        for (String s : a) {
            if (StringUtils.isEmpty(s)) {
                continue;
            }
            if (!extractField(s.trim())) {
                return false;
            }
        }
        return true;
    }

    private static String removeQuotes(final String str) {
        if (str.endsWith("\"") && str.startsWith("\"") && str.length() > 1) {
            return str.substring(1, str.length() - 1);
        }
        return str;
    }

    private static boolean extractField(String str) {
        int pos = str.indexOf('=');
        if (pos < 0) {
            return false;
        }

        String key = str.substring(0, pos).trim();
        String value = str.substring(pos + 1).trim();

        if ("client".equalsIgnoreCase(key)) {
            client = value;
        } else if ("secret".equalsIgnoreCase(key)) {
            secret = value;
        } else if ("tenant".equalsIgnoreCase(key)) {
            tenant = value;
        } else {
            return false;
        }
        return true;
    }

    public static String getClient() {
        return client;
    }

    public static String getSecret() {
        return secret;
    }

    public static String getTenant() {
        return tenant;
    }

}
package shopyourway.platform.client.configurations;

import org.apache.commons.lang3.StringUtils;
import play.Play;

public class ApplicationSettings {
    public static int getAppId() {
        String appIdStr = Play.application().configuration().getString("shopyourway.appid");
        try {
            return Integer.parseInt(appIdStr);
        } catch (Exception e) {
            return 0;
        }
    }

    public static String getSecret() {
        String secret = Play.application().configuration().getString("shopyourway.secret");
        return secret;
    }

    public static String getApiBaseUrl() {
        return Play.application().configuration().getString("shopyourway.apiBaseUrl");
    }

    public static Boolean IsSet() {
        String secret = getSecret();
        int appId = getAppId();

        return !StringUtils.isEmpty(secret) && !StringUtils.isBlank(secret) && appId > 0;
    }

    public static String shopYourWayUrl() {
        return Play.application().configuration().getString("shopyourway.baseUrl");
    }
}


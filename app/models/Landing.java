package models;

import shopyourway.platform.client.configurations.ApplicationSettings;

public class Landing {
    private long appId;
    private String shopYourWayUrl;
    private String loginPageUrl;

    public Landing() {
        shopYourWayUrl = ApplicationSettings.shopYourWayUrl();
        loginPageUrl = shopYourWayUrl + "app/" + ApplicationSettings.getAppId() + "/login";
    }

    public String getLoginPageUrl() {
        return loginPageUrl;
    }

    public String getLandingPageUrl() {
        return shopYourWayUrl + "app/" + ApplicationSettings.getAppId() + "/l";
    }

    public boolean displayProperConfigurationMessage() {
        return ApplicationSettings.getAppId() == 0;
    }
}

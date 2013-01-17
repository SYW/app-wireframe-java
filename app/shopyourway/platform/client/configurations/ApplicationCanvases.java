package shopyourway.platform.client.configurations;

public class ApplicationCanvases {
    public static String getCanvasPageUrl() {
        return ApplicationSettings.shopYourWayUrl() + "/app/" + ApplicationSettings.getAppId() + "/r";
    }
}

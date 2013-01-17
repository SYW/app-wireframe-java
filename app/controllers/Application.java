package controllers;

import models.Home;
import play.mvc.Controller;
import play.mvc.Result;
import shopyourway.domain.client.PlatformClient;
import shopyourway.domain.user.User;
import shopyourway.domain.utils.PlatformUtils;
import shopyourway.platform.client.configurations.ApplicationSettings;
import views.html.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the main page of the application
 */

public class Application extends Controller {

    public static Result index() throws Exception {
        if (!ApplicationSettings.IsSet())
            return redirect("/landing");

        if (!PlatformClient.HasToken()) {
            Home home = new Home(null, null);
            home.setDisplayProperConfigurationMessage(true);

            return ok(index.render("Regular canvas", home));
        }

        User user = GetUser();
        List<User> followers = GetFollowers(user.getId());

        Home home = new Home(user, followers);

        return ok(index.render("Regular canvas", home));
    }

    private static User GetUser() throws Exception {
        return new PlatformClient()
                .get("/users/current", User.class);
    }

    private static List<User> GetFollowers(String id) throws Exception {
        Map<String, String> followersParams = new HashMap<String, String>();
        followersParams.put("userId", id);

        List<Integer> userIds = new PlatformClient().getList("/users/followed-by", followersParams, Integer.class);

        Map<String, String> usersGetParams = new HashMap<String, String>();
        usersGetParams.put("ids", PlatformUtils.toCommaSeparatedString(userIds));

        return new PlatformClient()
                .getList("/users/get", usersGetParams, User.class);
    }
}
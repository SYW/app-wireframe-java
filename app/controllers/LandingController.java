package controllers;

import models.Landing;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.landing;

/**
 * This is the application landing page that is public for app users
 */

public class LandingController extends Controller {
    public static Result landing() {
        return ok(landing.render("Landing", new Landing()));
    }
}

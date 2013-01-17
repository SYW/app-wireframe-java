package controllers;

import models.Mobile;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.mobile;

import views.html.*;
import play.*;
import play.mvc.*;
/**
 * enables mobile views of the app.
 */
public class MobileController extends Controller {

    public static Result mobile(){
        Mobile model = new Mobile();
        if (isUserAgentMobile()){
            model.setMobile(true);
        }
        return ok(mobile.render("Mobile",model));
    }

    private static boolean isUserAgentMobile() {
        String userAgent = Controller.request().headers().get("USER-AGENT")[0];
        if (userAgent.contains("iPhone")||userAgent.contains("iPad")||userAgent.contains("iPod"))  {
            return true;
        }
        return false;
    }
}

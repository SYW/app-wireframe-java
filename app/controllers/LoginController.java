package controllers;

import models.PostLogin;
import play.mvc.Result;
import views.html.login;
import views.html.postLogin;

import static play.mvc.Results.ok;

/**
 * Used in the login screen, to enable customization of the login screen by the app.
 */
public class LoginController {
    public static Result login() {
        return ok(login.render());
    }

    public static Result postLogin() {
        return ok(postLogin.render(new PostLogin()));
    }
}

package controllers;

import models.Member;
import play.mvc.Controller;

public class Admin extends Controller {

  public static void index() {
    Member member = Accounts.getLoggedInMember();
    render("admin.html", member);
  }
}

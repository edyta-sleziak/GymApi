package controllers;

import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

public class Accounts extends Controller {

  public static void signup(){
    render("signup.html");
  }

  public static void login(){
    render("login.html");
  }

  public static void register(String name, String email, String password, String address, String gender, double height, double startWeight){
    Logger.info("Registering new user " + email);
    Member member = new Member(name, email, password, address, gender, height, startWeight);
    member.save();
    redirect("/");
  }

  public static void authenticate(String email, String password){
    Logger.info("Attempting to authenticate with " + email + ":" + password);
    Member member = Member.findByEmail(email);
    Trainer trainer = Trainer.findTrainerByEmail(email);
    if ((member != null) && (member.checkPassword(password))) {
      Logger.info("Authentication successful");
      session.put("logged_in_Memberid", member.id);
      redirect ("/dashboard");
    } else if((trainer != null) && (trainer.checkPassword(password))) {
      Logger.info("Authentication as trainer successful");
      session.put("logged_in_Trainerid", trainer.id);
      redirect ("/dashboard");
    } else {
        Logger.info("Authentication failed");
        redirect("/login");
    }
  }

  public static void logout(){
    session.clear();
    redirect ("/");
  }

  public static Member getLoggedInMember(){
    Member member = null;
    if (session.contains("logged_in_Memberid")) {
      String memberId = session.get("logged_in_Memberid");
      member = Member.findById(Long.parseLong(memberId));
    }
    return member;
  }

  public static Trainer getLoggedInTrainer(){
    Trainer trainer = null;
    if (session.contains("logged_in_Trainerid")) {
      String trainerId = session.get("logged_in_Trainerid");
      trainer = Trainer.findById(Long.parseLong(trainerId));
    }
    return trainer;
  }

  public static void update(String name, String email, String password, String address, String gender, double height, double startWeight){
    Member member = Accounts.getLoggedInMember();
    member.setName(name);
    member.setEmail(email);
    member.setPassword(password);
    member.setAddress(address);
    member.setGender(gender);
    member.setHeight(height);
    member.setStartWeight(startWeight);
    Logger.info("Details updated");
    member.save();
    redirect ("/dashboard");
  }
}
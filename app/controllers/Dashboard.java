package controllers;

import java.util.List;

import models.Assessment;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    Trainer trainer = Accounts.getLoggedInTrainer();
    if(member != null) {
      System.out.println(member.getEmail());
      List<Assessment> assessments = member.assessments;
      render("dashboard.html", member, assessments);
    } else if (trainer != null) {
      List<Member> members = Member.getAllMembers();
      render("trainerdashboard.html", members);
    }
  }

  public void add(double weight, float chest, float thigh, float upperArm, float waist, float hips) {
    Logger.info("Adding a new assessment");
    Member member = Accounts.getLoggedInMember();
    Assessment assessment = new Assessment(weight, chest, thigh, upperArm, waist, hips);
    member.assessments.add(assessment);
    member.setCurrentBMI(weight, member.getHeight());
    member.setCurrentBMICategory(member.getCurrentBMI());
    member.setIdealBodyWeight(member.getHeight(), member.gender);
    member.save();
    redirect ("/dashboard");
  }

  public void showProfile(Long id){
    Member member = Member.findById(id);
    Logger.info("Showing profile of " + member.getName());
    render("membersprofile.html", member);
  }

  public void addComment(Long id, Long aid, String comment){
    Member member = Member.findById(id);
    Assessment assessment = Assessment.findById(aid);
    assessment.addComment(comment);
    Logger.info("Comment added");
    member.save();
    render("membersprofile.html", member);
  }

}

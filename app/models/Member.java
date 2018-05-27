package models;

import play.db.jpa.Model;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends Model {

  public String name;
  public String email;
  public String password;
  public String address;
  public String gender;
  public double height;
  public double startWeight;
  private double currentBMI;
  public String currentBMICategory = "N/A";
  public double currentWeight;
  public double idealBodyWeight;

  @OneToMany(cascade = CascadeType.ALL)
  public List<Assessment> assessments = new ArrayList<Assessment>();

  public Member(String name, String email, String password, String address, String gender, double height, double startWeight) {
    this.name = name;
    this.email = email;
    this.address = address;
    this.password = password;
    this.gender = gender;
    this.height = height;
    this.currentWeight = startWeight;
    this.startWeight = startWeight;
    setIdealBodyWeight(height, gender);
    setCurrentBMI(startWeight,height);
  }

  public static List<Member> getAllMembers() {
    return findAll();
  }

  public static Member findByEmail(String email) {
    return find("email", email).first();
  }

  public boolean checkPassword(String password) {
    return this.password.equals(password);
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public void setStartWeight(double startWeight) {
    this.startWeight = startWeight;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setCurrentBMI(double weight, double height) {
    this.currentBMI = GymUtility.calculateBMI(weight, height) ;
    this.currentWeight = weight;
    setCurrentBMICategory(currentBMI);
  }

  public void setCurrentBMICategory(double bmi){
    this.currentBMICategory = GymUtility.determineBMICategory(bmi);
  }

  public double getCurrentBMI() {
    return currentBMI;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getAddress() {
    return address;
  }

  public String getGender() {
    return gender;
  }

  public String getPassword() {
    return password;
  }

  public double getHeight() {
    return height;
  }

  public String getCurrentBMICategory() {
    return currentBMICategory;
  }

  public int numberOfAssessments(){
    return assessments.size();
  }

  public void setIdealBodyWeight(double height, String gender) {
    this.idealBodyWeight = GymUtility.idealBodyWeight(height, gender);
  }

}

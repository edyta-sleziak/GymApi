package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Assessment extends Model {

  public double weight;
  public float chest;
  public float thigh;
  public float upperArm;
  public float waist;
  public float hips;
  public String comment;
  
  public Assessment(double weight, float chest, float thigh, float upperArm, float waist, float hips){
    this.weight = weight;
    this.chest = chest;
    this.thigh = thigh;
    this.upperArm = upperArm;
    this.waist = waist;
    this.hips = hips;
  }

  public void addComment(String comment){
    this.comment = comment;
  }
}

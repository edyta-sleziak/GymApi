package models;

public class GymUtility {

    public static double calculateBMI(double weight, double height){
        return Math.round(weight / Math.pow(height,2) * 100.0) / 100.0;
    }

    public static String determineBMICategory(double bmiValue) {
        String category;
        if(bmiValue < 16) {
            category = "SEVERELY UNDERWEIGHT";
        } else if (bmiValue >= 16 && bmiValue < 18.5) {
            category = "UNDERWEIGHT";
        } else if (bmiValue >= 18.5 && bmiValue < 25) {
            category = "NORMAL";
        } else if (bmiValue >= 25 && bmiValue < 30) {
            category = "OVERWEIGHT";
        } else if (bmiValue >= 30 && bmiValue < 35) {
            category = "MODERATELY OBESE";
        } else {
            category ="SEVERELY OBESE";
        }
        return category;
    }

    public static double idealBodyWeight(double height, String gender) {
        double baseWeight;
        double idealWeight;
        float increment = 2.3f;
        float fiveFtToInch = 60.0f;
        double inchHeight = heightToInch(height);
        if (gender.equals("M")) {
            baseWeight = 50.0f;
        } else {
            baseWeight = 45.5f;
        }
        if (inchHeight > fiveFtToInch) {
            idealWeight = baseWeight + (increment * (inchHeight - fiveFtToInch));
            idealWeight = Math.round(idealWeight * 10.0) / 10.0;
        } else {
            idealWeight = baseWeight;
        }
        return idealWeight;
    }

    public static double heightToInch(double heightInMeters){
        return Math.round((heightInMeters / 0.0254) * 100.0) / 100.0;
    }
}


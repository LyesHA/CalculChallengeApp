package model;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

public class Operation implements Serializable {
    private int numberOne;
    private int numberTwo;
    private String operator;
    private boolean correct;
    private double answer;

    public double getAnswer() {
        return answer;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Operation() {
        this.numberOne = new Random().nextInt(100);
        this.numberTwo = new Random().nextInt(100);
        this.operator = Operator.getOperator();
        this.correct = false;
    }

    public int getNumberTwo() {
        return numberTwo;
    }

    public void setNumberTwo(int numberTwo) {
        this.numberTwo = numberTwo;
    }

    public int getNumberOne() {
        return numberOne;
    }

    public void setNumberOne(int numberOne) {
        this.numberOne = numberOne;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public double getResultat(){
        double resultat=0;
        switch (operator){
            case "+": resultat = numberOne + numberTwo;break;
            case "-": resultat = numberOne - numberTwo;break;
            case "*": resultat = numberOne * numberTwo;break;
            case "/": resultat = Double.valueOf(numberOne) / Double.valueOf(numberTwo) ;break;
        }
        //DecimalFormat df = new DecimalFormat("#.####");
        //df.setRoundingMode(RoundingMode.CEILING);
        //resultat = Double.valueOf(df.format(resultat));
        return resultat;
    }

    @Override
    public String toString() {
        return  numberOne + " " + operator + " " +  numberTwo + " = ";
    }
}

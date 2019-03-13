package model;

import java.util.Random;

public class  Operator {
    private static String[] operator = {"+","-","*","/"};

    public static String getOperator(){
        int index = new Random().nextInt(operator.length);
        String randomOperator = operator[index];
        return randomOperator;
    }
}

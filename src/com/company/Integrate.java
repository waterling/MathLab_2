package com.company;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import static java.lang.Math.*;
import static java.lang.Math.E;
import static java.lang.Math.abs;

class Integrate {
    private String expression;
    Integrate(){
        expression="";
    }
    Integrate(String expression){
        this.expression=expression;
    }
    private  double inFunction(double x, int numOfFunction) {
        switch (numOfFunction) {
            case 1: {
                return sin(x) * cos(x);
            }
            case 2: {
                return (pow(x, 2) + x + 6);
            }
            case 3: {
                return log(x);
            }
            case 4: {
                return 1 / pow(x, 2);
            }
            case 5: {
                return pow(E, x);
            }
            case 6: {
                Expression ex = new ExpressionBuilder(expression)
                        .variables("pi", "e", "x")
                        .build()
                        .setVariable("pi", PI)
                        .setVariable("e", E)
                        .setVariable("x", x);
                return ex.evaluate();
            }
            default: {
                return 0;
            }
        }
    }

    private  double[] calcIntegral(double fromA, double toB, int exactness, int numOfFunction) {
        double h, tempIntegral,leftIntegral, rightIntegral, middleIntegral;
        middleIntegral=0;
        tempIntegral=0;
        h = (toB - fromA) / exactness;
        for (int i = 0; i < exactness; i++) {
            tempIntegral+=inFunction(fromA+i*h,numOfFunction);
            middleIntegral += inFunction(fromA + h * (i + 0.5), numOfFunction);
            progressPercentage(i + 1, exactness);
        }
        rightIntegral=(tempIntegral-inFunction(fromA,numOfFunction))*h;
        middleIntegral *=h;
        leftIntegral=(tempIntegral-inFunction(fromA+(exactness-1)*h,numOfFunction))*h;



        return new double[]{leftIntegral,middleIntegral,rightIntegral};
    }
    String[] calcResult (double fromA, double toB, int exactness, int numOfFunction){
        double result[] = calcIntegral(fromA,toB,exactness,numOfFunction);
        double result2[] = calcIntegral(fromA,toB,2*exactness,numOfFunction);
        String leftResult = Double.toString(result[0])+" +- "+Double.toString((1f/3f)*abs(result[0]-result2[0]));
        String middleResult =Double.toString(result[1])+" +- "+Double.toString((1f/3f)*abs(result[1]-result2[1]));
        String rightResult = Double.toString(result[2])+" +- "+Double.toString((1f/3f)*abs(result[2]-result2[2]));
        return new String[]{leftResult,middleResult,rightResult};

    }


    private  void progressPercentage(int remain, int total) {
        if ((remain / total * 100) % 100 != 0) {
            return;
        }
        if (remain > total) {
            throw new IllegalArgumentException();
        }
        int maxBareSize = 10; // 10unit for 100%
        int remainPercent = ((100 * remain) / total);
        char defaultChar = '-';
        String icon = "■";
        String bare = new String(new char[maxBareSize]).replace('\0', defaultChar) + "]";
        StringBuilder bareDone = new StringBuilder();
        bareDone.append("[");
        for (int i = 0; i < remainPercent / maxBareSize; i++) {
            bareDone.append(icon);
        }
        String bareRemain = bare.substring(remainPercent / maxBareSize, bare.length());
        System.out.print("\r" + bareDone + bareRemain + " " + remainPercent + "%");
        if (remain == total) {
            System.out.print("\n");
        }
    }
}

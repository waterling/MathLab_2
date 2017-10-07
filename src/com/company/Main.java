package com.company;


import java.util.Scanner;

import static java.lang.Math.*;

public class Main {

    public static void main(String[] args) {
        Integrate integrate = new Integrate();
        Scanner in = new Scanner(System.in);
        while (true){
            System.out.println("Function (1): sin(x)*cos(x)");
            System.out.println("Function (2): x^2+x+6");
            System.out.println("Function (3): ln(x)");
            System.out.println("Function (4): 1/(x^2)");
            System.out.println("Function (5): e^x");
            System.out.println("Your function (6)");
            System.out.print("Choose your function: ");
            int numOfFunction = in.nextInt();
            if (numOfFunction == 6) {
                System.out.print("Enter your function: y = ");
                integrate = new Integrate(in.next());
            }
            System.out.print("From: ");
            double fromA = in.nextDouble();
            System.out.print("To: ");
            double toB = in.nextDouble();
            System.out.print("Exactness (sign>0): ");
            int sign = in.nextInt();
            int exactness = (int) pow(10, sign);
            System.out.println("Wait...");
            String results[];
            results = integrate.calcResult(fromA, toB, exactness, numOfFunction);
            System.out.println("Left: " + results[0]);
            System.out.println("Middle: " + results[1]);
            System.out.println("Right: " + results[2]);
            System.out.println("Steps: " + exactness);
        }
    }
}

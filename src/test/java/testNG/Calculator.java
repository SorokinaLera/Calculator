package testNG;

import org.testng.annotations.Listeners;
@Listeners(TestListener.class)
public class Calculator {

    Calculator calculator;

    public double sum(double a, double b){
        double result = a + b;
        return result;
    }

    public double difference(double a, double b){
        double result = a - b;
        return result;
    }

    public double multiplication(double a, double b){
        double result = a * b;
        return result;
    }

    public double division(double a, double b){
        double result = a / b;
        return result;
    }

}
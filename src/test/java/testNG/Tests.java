package testNG;

import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;


@Listeners(TestListener.class)
public class Tests extends Calculator {
    @BeforeMethod
    public void start() {
        calculator = new Calculator();

    }

    @DataProvider(name = "DataSum")
    public Object[][] asdSum() {
        return new Object[][]{
                {1, 2, 3},
                {5, 10, 15},
                {10, -1, 9},
                {-1, -5, -6},
                {0, -1, -1},
                {-15, 3, -12},

        };
    }


    @Test(dataProvider = "DataSum", groups = {"Писал Юра", "smoke"}, description = "Sum test")
    public void testSum(double a, double b, double result) {
        double methodResult = calculator.sum(a, b);
        Assert.assertEquals(result, methodResult);

    }

    @DataProvider(name = "DataDiff")
    public Object[][] asdDiff() {
        return new Object[][]{
                {1, 2, -1},
                {5, 10, -5},
                {10, -1, 11},
                {-1, -5, 4},
                {0, -1, 1},

        };
    }

    @Test(dataProvider = "DataDiff", groups = {"smoke"}, retryAnalyzer = Retry.class, description = "Difference test", priority = 2)
    public void testDifference(double a, double b, double result) {
        double methodResult = calculator.difference(a, b);
        Assert.assertEquals(methodResult, result);
    }

    @DataProvider(name = "Data")
    public Object[][] asd() {
        return new Object[][]{
                {17, 18},
                {-5, 0},
                {-99, 1},
                {0, -5},
                {0, 1},
        };
    }

    @Test(dataProvider = "Data", groups = {"smoke"}, retryAnalyzer = Retry.class, description = "Multiplication test", priority = 3)
    public void testMultiplication(double a, double b) {
        double methodResult = calculator.multiplication(a, b);
        Assert.assertEquals(methodResult, a * b);
    }

    @Test(dataProvider = "Data", groups = {"smoke"}, retryAnalyzer = Retry.class, description = "Division test", priority = 4)
    public void testDivision(double a, double b) {
        double methodResult = calculator.division(a, b);
    }

    @Parameters({"a", "b"})
    @Test(groups = {"Писал Юра"})
    public void test4(@Optional("100")
                              double a,
                      @Optional("50")
                              double b) {

        System.out.println(calculator.difference(a, b));

    }

    @Test(groups = {"smoke"}, retryAnalyzer = Retry.class)
    public void testDivisionByZero() {
        double methodResult = calculator.division(5, 0);
        System.out.println(methodResult);
    }

    @Test(retryAnalyzer = Retry.class, invocationCount = 4, threadPoolSize = 2)
    public void testRandom() {
        Random r = new Random();
        Random m = new Random();
        double methodResult = calculator.sum(r.nextInt(3), m.nextInt(3));
        Assert.assertEquals(methodResult, r.nextInt(3) + m.nextInt(3));

        System.out.println(methodResult);
    }


    @AfterMethod(alwaysRun = true)
    public void finish() {
        System.out.println("*****");
    }

}

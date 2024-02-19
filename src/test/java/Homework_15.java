import org.testng.annotations.Test;


public class Homework_15 extends BaseTest {
    @Test
    public void homework15() throws Throwable{
        Thread.sleep(2000);
        driver.get("https://testpro.io");
        System.out.println("Homework 15 is done");
    }
}

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{


    public  void provideEmail(String email) {
        WebElement emailField = driver.findElement(By.xpath("//input[@placeholder='Email Address']"));
        emailField.sendKeys(email);
    }

    public void providePassword(String password) {
        WebElement passwordField =  driver.findElement(By.xpath("//input[@placeholder='Password']"));
        passwordField.sendKeys(password);
    }

    public void clickSubmit(){
        WebElement submitButton =  driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();
    }

    @Test
    public void loginToKoelApp() {
        provideEmail("burul.satybaeva@testpro.io");
        providePassword("Lulkerup7710%");
        clickSubmit();
    }
}

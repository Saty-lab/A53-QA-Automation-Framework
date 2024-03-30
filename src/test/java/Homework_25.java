import PageObject.LoginPage;

import org.testng.annotations.Test;

import static java.sql.DriverManager.getDriver;

public class Homework_25 extends BaseTest {
    @Test
    public void loginWithValidCredentials() throws Throwable {
        LoginPage login = new LoginPage(getDriver());
        login.provideEmail("burul.satybaeva@testpro.io");
        login.providePassword("Lulkerup7710%");
        login.clickSubmit();
        login.verifyLogin();
    }

    @Test
    public void loginWithValidCredentials2() throws Throwable {
        LoginPage login = new LoginPage(getDriver());
        login.provideEmail("burul.satybaeva@testpro.io");
        login.providePassword("Lulkerup7710%");
        login.clickSubmit();
        login.verifyLogin();
    }
}

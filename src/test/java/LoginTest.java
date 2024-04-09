import pages.LoginPage;

import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void loginWithValidCredentials() {
        LoginPage login = new LoginPage(getDriver());
        login.provideEmail("burul.satybaeva@testpro.io");
        login.providePassword("Lulkerup7710%");
        login.clickSubmit();
        login.verifyLogin();
        System.out.println("First test");
    }
    
}

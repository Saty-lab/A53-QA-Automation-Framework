import PageObject.LoginPage;
import PageObject.playSong;
import org.testng.annotations.Test;

public class Homework_23 extends BaseTest {
    @Test
    public void playAndPauseSong() throws Throwable {
        LoginPage login = new LoginPage(driver);
        playSong music = new playSong(driver);
        //login with valid credentials
        login.loginWithValidCredentials();
        login.verifyLogin();
        //play song
        music.findMusicButton();
        music.clickPlaySongButton();
        music.clickNextSong();
        music.pauseSong();
    }
}
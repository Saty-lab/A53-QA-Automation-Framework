import Pages.LoginPage;
import Pages.PlaySongPage;
import org.testng.annotations.Test;

public class Homework_22 extends BaseTest {
    @Test
    public void playAndPauseSong() {
        LoginPage login = new LoginPage(driver);
        PlaySongPage music = new PlaySongPage(driver);
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

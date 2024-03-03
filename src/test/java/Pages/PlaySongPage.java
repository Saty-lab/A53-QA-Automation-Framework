package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class PlaySongPage extends BasePage {
    public PlaySongPage(WebDriver givenDriver) {
        super(givenDriver);
    }


    //page Locators
    By music = By.xpath("//span[@class='album-thumb']");
    By musicControl = By.xpath("//span[@title='Play or resume']");
    By playNextSong = By.xpath("//i[@title='Play next song']");
    By pauseButton = By.xpath("//span[@title='Pause']");

    //page Methods

    public void findMusicButton() {
        findElementUsingByLocator(music);
    }
    public void clickPlaySongButton() {
        mouse.moveToElement(findElementUsingByLocator(music)).build().perform();
        findElementUsingByLocator(musicControl).click();
    }
    public void clickNextSong() {
        findElementUsingByLocator(playNextSong).click();
        mouse.moveToElement(findElementUsingByLocator(music)).build().perform();
    }

    public void pauseSong() {
        WebElement pauseButton = findElementUsingByLocator(this.pauseButton);
        boolean status = pauseButton.isDisplayed();
        Assert.assertTrue(status);
        System.out.println("Homework 22 is done");
    }
}




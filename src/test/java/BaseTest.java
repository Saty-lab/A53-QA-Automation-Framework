import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class BaseTest {

    public WebDriver driver;
    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
    protected WebDriverWait wait = null;
    protected Actions actions = null;

    @DataProvider(name = "LoginPositiveTest")
    public Object[][] getDataForPositiveLogin() {
        return new Object[][]{
                {"yelyzaveta.postnova@testpro.io", "YrkeNi92"}
        };
    }

    @DataProvider(name = "LoginNegativeTests")
    public Object[][] getDataForNegativeLogin() {
        return new Object[][]{
                {"yelyzaveta.postnova@testpro.io", "12345678", "https://qa.koel.app/"},
                {"nonperson@testpro.io", "YrkeNi92", "https://qa.koel.app/"},
                {"", "YrkeNi92", "https://qa.koel.app/"},
                {"yelyzaveta.postnova@testpro.io", "", "https://qa.koel.app/"},
                {"", "", "https://qa.koel.app/"}
        };
    }

    @DataProvider(name = "AddPlaylistPositiveTest")
    public Object[][] getDataForAddPlaylist() {
        return new Object[][]{
                {"yelyzaveta.postnova@testpro.io", "YrkeNi92", "New Playlist"}
        };
    }

    @DataProvider(name = "RenamePlaylistPositiveTest")
    public Object[][] getDataForRenamePlaylist() {
        return new Object[][]{
                {"yelyzaveta.postnova@testpro.io", "YrkeNi92", "New Playlist", "Renamed Playlist"}
        };
    }

    @DataProvider(name = "AddSongPositiveTest")
    public Object[][] getDataForAddSong() {
        return new Object[][]{
                {"yelyzaveta.postnova@testpro.io", "YrkeNi92", "New Playlist", "take my hand"}
        };
    }

    @BeforeSuite
    static void setupClass() {

        WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
    }
    public static WebDriver getDriver() {
        return threadDriver.get();
    }
    @BeforeMethod
    @Parameters({"BaseUrl"})
    public void launchBrowser(String BaseUrl) throws MalformedURLException {
        threadDriver.set(pickBrowser(System.getProperty("browser")));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        actions = new Actions(getDriver());
        getDriver().get(BaseUrl);
    }


    public WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridURL = "http://192.168.1.11:4444/";
        switch(browser){
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return driver = new FirefoxDriver();

            case "MicrosoftEdge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                return driver = new EdgeDriver();

            case "grid-edge": // gradle clean test -Dbrowser=grid-edge
                caps.setCapability("browserName", "MicrosoftEdge");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);

            case "grid-firefox": // gradle clean test -Dbrowser=grid-firefox
                caps.setCapability("browserName", "firefox");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);

            case "grid-chrome":
                caps.setCapability("browserName", "chrome");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(),caps);

            case "cloud":
                return lambdaTest();

            default:
                WebDriverManager.chromedriver().clearDriverCache().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                //Manage Browser - wait for 10 seconds before failing/quitting.
                return driver = new ChromeDriver(options);
        }
    }

    public WebDriver lambdaTest() throws MalformedURLException {
        String hubURL = "https://hub.lambdatest.com/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "120.0");

        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "bsatybaeva");
        ltOptions.put("accessKey", "h6vWRSINSiYNco1ZD6KEHMehN5IZLptguQVrPCkDjhHVPsO5O3");
        ltOptions.put("geoLocation", "US");
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("resolution", "2560x1440");
        ltOptions.put("timezone", "Detroit");
        ltOptions.put("build", "Homework25");
        ltOptions.put("project", "Music Player");
        ltOptions.put("name", "Automation");
        ltOptions.put("selenium_version", "4.5.0");
        ltOptions.put("driver_version", "120.0");
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        capabilities.setCapability("LT:Options", ltOptions);

        return new RemoteWebDriver(new URL(hubURL),capabilities);
    }
    @AfterMethod
    public void tearDown() {
        threadDriver.get().close();
        threadDriver.remove();
    }

}
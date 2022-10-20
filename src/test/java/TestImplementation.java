
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

public class TestImplementation {
    static final Logger log = getLogger(lookup().lookupClass());
    private WebDriver driver;
    private String urlHubLocal = "http://localhost:4444/wd/hub";
    private String urlHubJenkins = "http://selenium-hub:4444/wd/hub";

    @BeforeClass
    public static void setupWebdriverChromeDriver() {
        //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/chromedriver");
    }

    @Before
    public void setup() throws MalformedURLException {
        String env = System.getProperty("env", "local");
        System.out.println(String.format(" >>>>>>>>>>>>> setup %s",env));
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new RemoteWebDriver(new URL(env.equals("jenkins") ? urlHubJenkins : urlHubLocal),chromeOptions);
        //driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyGitHubTitle() {
        String sutUrl = "https://www.github.com";
        driver.get(sutUrl);
        String title = driver.getTitle();
        log.debug("The title of {} is {}", sutUrl, title);
        assertThat(driver.getTitle(), containsString("GitHub"));
    }

    // TODO: Test more navigators
    @Test
    public void verifyFacebookTitle() {
        driver.get("https://facebook.com");
        System.out.println(String.format(" >>>>>>>>>>>>> verifyFacebookTitle %s",driver.getTitle()));
        assertThat(driver.getTitle(), containsString("Facebook - log in or sign up"));
    }
}

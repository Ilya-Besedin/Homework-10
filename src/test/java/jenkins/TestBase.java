package jenkins;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static java.lang.System.getProperty;

public class TestBase {

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = System.getProperty("browser", "chrome");

        String browserSize = getProperty("browserSize", "414x896");
        Configuration.browserSize = browserSize;
        //System.out.println(browserSize);

        //Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        String user = getProperty("user", "user2");
        String password = getProperty("password", "1235");
        String remoteBrowser = getProperty("remoteBrowser", "selenoid.autotests.cloud/wd/hub");
        Configuration.remote = "https://" + user + ":" + password + "@" + remoteBrowser;
        //System.out.println(user + password + remoteBrowser);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }

}

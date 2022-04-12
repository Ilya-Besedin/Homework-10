package jenkins;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import helpers.StepsAnnotation;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class UseJenkinsJob {
    public void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1280x800";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    public static final String repository = "Ilya-Besedin/Homework-6";

    @Owner("ibesedin")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Jenkins integration")
    @Story("Try to use Jenkins")
    @DisplayName("Checking Issue tab in user's repository")
    @Description("The test checks availability of the Issue tab in the user's repository")
    @Link(url = "https://github.com")

    @Test
    public void testIssueLabel() {
        StepsAnnotation steps = new StepsAnnotation();
        steps.openMainPage();
        steps.searchForRepository(repository);
        steps.openRepository();
        steps.checkTabName();
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
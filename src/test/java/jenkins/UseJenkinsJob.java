package jenkins;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class UseJenkinsJob {

    @Owner("ibesedin")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Jenkins integration")
    @Story("Try to use Jenkins")
    @DisplayName("Checking Issue tab in user's repository")
    @Description("The test checks availability of the Issue tab in the user's repository")
    @Link(url = "https://github.com")

    @Test
    public void testIssueLabel() {

        open("https://github.com/");
        $("[data-test-selector=nav-search-input]").setValue("Ilya-Besedin/Homework-7").pressEnter();
        $("ul.repo-list li").$("a").click();
        //at line 20 made special mistake in cssSelector (change # to .)
        $("#issues-tab").shouldHave(text("Issues"));
    }
}

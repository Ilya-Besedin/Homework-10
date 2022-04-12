package jenkins;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class UseJenkinsJob {

    @Test
    public void testIssueLabel() {

        open("https://github.com/");
        $("[data-test-selector=nav-search-input]").setValue("Ilya-Besedin/Homework-7").pressEnter();
        $("ul.repo-list li").$("a").click();
        //at line 20 made special mistake in cssSelector (change # to .)
        $("#issues-tab").shouldHave(text("Issues"));
    }
}

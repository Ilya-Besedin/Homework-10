package helpers;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StepsAnnotation {

        @Step("Open Github page")
        public void openMainPage() {
            open("https://github.com");
        }

        @Step("Search repository {repo}")
        public void searchForRepository(String repository) {
            $("[data-test-selector=nav-search-input]").setValue(repository).pressEnter();
        }

        @Step("Open repository {repo}")
        public void openRepository() {
            $("ul.repo-list li").$("a").click();
        }

        @Step("Checking tab name")
        public void checkTabName() {
            $("#issues-tab").shouldHave(text("Issues"));
        }
    }

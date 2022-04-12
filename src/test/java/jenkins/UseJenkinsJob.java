package jenkins;

import helpers.StepsAnnotation;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UseJenkinsJob extends TestBase {

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
}
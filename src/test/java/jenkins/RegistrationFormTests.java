package jenkins;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static jenkins.TestData.*;

public class RegistrationFormTests extends TestBase {

    @Owner("IBesedin")
    @Severity(value = SeverityLevel.CRITICAL)
    @Feature("Форма регистрации")
    @Story("Регистрация пользователя")
    @DisplayName("Проверка формы регистрации")
    @Description("Проверка заполнения формы регистрация ожидаемыми данными")
    @Link(url = "https://demoqa.com")

    @Test
    @Tag("dev_test")
    void successFillTest() {
        step("Открыть форму регистрации", () -> {
            open("/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        });

        step("Заполнить имя, фамилию, email, номер телефона и адрес", () -> {
            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $("#userEmail").setValue(email);
            $("#userNumber").setValue(phone);
            $("#currentAddress").setValue(address);
        });

        step("Выбрать пол, хобби и предмет", () -> {
            $(byText(gender)).click();
            // $(byText("Sports")).click();
            $("#hobbies-checkbox-1").parent().click(); //другой вариант записи строки выше
            $("#subjectsInput").setValue(subject).pressEnter();
        });

        step("Установить дату", () -> {
            $("#dateOfBirthInput").click();
            //выбираем даты
            $(".react-datepicker__year-select").selectOptionByValue(year);
            $(".react-datepicker__month-select").selectOptionContainingText(month);
            $(".react-datepicker__day--014:not(.react-datepicker__day--outside-month)").click(); //исключили повторяющиеся даты вне месяца
        });

        step("Прикрепить файл", () -> $("#uploadPicture").uploadFromClasspath(attach));

        step("Выбрать штат и город", () -> {
            $("#submit").scrollIntoView(true);
            $(byText("Select State")).click();
            $(byText(state)).scrollIntoView(true).click();
            $(byText("Select City")).click();
            $(byText(city)).scrollIntoView(true).click();
        });

        step("Нажать Submit", () -> $("#submit").click());

        step("Проверить введенные данные", () -> {
        $(".modal-header").shouldHave(text(tableHeader));
        $(".table-responsive").$(byText("Student Name"))
                .parent().shouldHave(text(firstName + ' ' + lastName));
        $(".table-responsive").shouldHave(text("Student Email " + email),
                text("Gender " + gender), text("Mobile " + phone), text("Date of Birth " + day + ' ' + month + ',' + year),
                text("Subjects Economics"), text("Hobbies " + hobbies), text("Picture " + attach),
                text("Address " + address), text("State and City " + state + ' ' + city));
        });
    }
}
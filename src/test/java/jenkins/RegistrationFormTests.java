package jenkins;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static jenkins.TestData.*;

public class RegistrationFormTests extends TestBase {

    @Owner("IBesedin")
    @Severity(value = SeverityLevel.CRITICAL)
    @Feature("Registration form")
    @Story("User registration")
    @DisplayName("Verification of the complete filling of the registration form")
    @Description("The test checks filling all fields of the registration form")
    @Link(url = "https://demoqa.com")

    @Test
     void successFillTest() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        //заполняем текстовые формы
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#userNumber").setValue(phone);
        $("#currentAddress").setValue(address);

        //отмечаем радиобаттоны и чекбоксы
        $(byText(gender)).click();
        // $(byText("Sports")).click();
        $("#hobbies-checkbox-1").parent().click(); //другой вариант записи строки выше

        //выбираем изучаемый предмет
        $("#subjectsInput").setValue(subject).pressEnter();

        //дата-пикер
        $("#dateOfBirthInput").click();
        //выбираем даты
        $(".react-datepicker__year-select").selectOptionByValue(year);
        $(".react-datepicker__month-select").selectOptionContainingText(month);
        $(".react-datepicker__day--014:not(.react-datepicker__day--outside-month)").click(); //исключили повторяющиеся даты вне месяца

        //прикрепляем файл
        $("#uploadPicture").uploadFromClasspath(attach);

        //выбираем State and City
        $("#submit").scrollIntoView(true);
        $(byText("Select State")).click();
        $(byText(state)).click();
        $(byText("Select City")).click();
        $(byText(city)).click();

        //кликаем Submit
        $("#submit").click();

        //проверяем форму с результатом заполнения данных
        $(".modal-header").shouldHave(text(tableHeader));
        $(".table-responsive").$(byText("Student Name"))
                .parent().shouldHave(text(firstName + ' ' + lastName));
        $(".table-responsive").shouldHave(text("Student Email " + email),
                text("Gender " + gender), text("Mobile " + phone), text("Date of Birth " + day + ' ' + month + ',' + year),
                text("Subjects Economics"), text("Hobbies " + hobbies), text("Picture " + attach),
                text("Address " + address), text("State and City " + state + ' ' + city));
    }
}
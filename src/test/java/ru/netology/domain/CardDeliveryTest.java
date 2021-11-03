package ru.netology.domain;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static java.time.format.DateTimeFormatter.ofPattern;


public class CardDeliveryTest {
    private RegistrationByCardInfo registrationInfo;

    @BeforeEach
    void setUpAll() {
        registrationInfo = DataGenerator.Registration.generateByCard("ru");
    }

    @Test
    void shouldResheduleDate() {
        open("http://localhost:9999");
        $("[data-test-id = 'city'] .input__control").setValue(registrationInfo.getCity());
        $("[data-test-id = 'date'] .input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id = 'date'] .input__control").setValue(DataGenerator.generateDate(3));
        $("[data-test-id = 'name'] .input__control").setValue(registrationInfo.getName());
        $("[data-test-id = 'phone'] .input__control").setValue(registrationInfo.getPhone().toString());
        $(".checkbox__box").click();
        $(".form-field .button__text").click();
       // $$("span.button__text").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification]").shouldHave(text("Встреча успешно запланирована на " + DataGenerator.generateDate(3))).shouldBe(Condition.visible, Duration.ofSeconds(20));
        $("[data-test-id = 'date'] .input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id = 'date'] .input__control").setValue(DataGenerator.generateDate(5));
        $(".form-field .button__text").click();
        //$$("span.button__text").find(exactText("Запланировать")).click();
        $("[data-test-id=replan-notification]").shouldHave(text("Необходимо подтверждение"));
        //$(withText("Необходимо подтверждение")).shouldBe(Condition.visible, Duration.ofSeconds(20));
        $(".notification .button__text").click();
        //$$("span.button__text").find(exactText("Перепланировать")).click();
        $("[data-test-id=success-notification]").shouldHave(text("Встреча успешно запланирована на " + DataGenerator.generateDate(5))).shouldBe(Condition.visible, Duration.ofSeconds(20));
    }
}
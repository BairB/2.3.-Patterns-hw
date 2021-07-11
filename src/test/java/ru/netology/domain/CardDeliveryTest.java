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
        $(".input__control[placeholder=\"Город\"]").setValue(registrationInfo.getCity());
        $(".input__control[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(Keys.BACK_SPACE);
        $(".input__control[placeholder=\"Дата встречи\"]").setValue(DataGenerator.generateDate(3));
        $(".input__control[name='name']").setValue(registrationInfo.getName());
        $(".input__control[name='phone']").setValue(registrationInfo.getPhone().toString());
        $(".checkbox__box").click();
        $$("span.button__text").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification]").shouldHave(text("Встреча успешно запланирована на " + DataGenerator.generateDate(3))).shouldBe(Condition.visible, Duration.ofSeconds(20));
        $(".input__control[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(Keys.BACK_SPACE);
        $(".input__control[placeholder=\"Дата встречи\"]").setValue(DataGenerator.generateDate(5));
        $$("span.button__text").find(exactText("Запланировать")).click();
        $(withText("Необходимо подтверждение")).shouldBe(Condition.visible, Duration.ofSeconds(20));
        $$("span.button__text").find(exactText("Перепланировать")).click();
        $("[data-test-id=success-notification]").shouldHave(text("Встреча успешно запланирована на " + DataGenerator.generateDate(5))).shouldBe(Condition.visible, Duration.ofSeconds(20));
    }
}
package ru.netology.domain;

import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;

import java.time.LocalDate;
import java.util.Locale;

import static java.time.format.DateTimeFormatter.ofPattern;

public class DataGenerator {

    private DataGenerator() {
    }

    public static class Registration {
        private Registration() {
        }

        public static RegistrationByCardInfo generateByCard(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new RegistrationByCardInfo(
                    faker.address().city(),
                    faker.name().name(),
                    faker.phoneNumber()
            );
        }
    }

    public static String generateDate(int days) {
        String date = LocalDate.now().plusDays(days).format(ofPattern("dd.MM.yyyy"));
        return date;
    }
}

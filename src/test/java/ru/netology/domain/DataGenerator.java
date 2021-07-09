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

    public static String date1() {
        String date = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        return date;
    }

    public static String date2() {
        String date = LocalDate.now().plusDays(7).format(ofPattern("dd.MM.yyyy"));
        return date;
    }
}

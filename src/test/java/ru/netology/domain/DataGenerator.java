package ru.netology.domain;

import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;

import java.util.Locale;

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

}

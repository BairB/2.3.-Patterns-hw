package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.github.javafaker.PhoneNumber;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor


public class RegistrationByCardInfo {
    private final String city;
    private final String name;
    private final PhoneNumber phone;

}

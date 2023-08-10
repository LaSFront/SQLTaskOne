package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {

    private static final Faker faker = new Faker(new Locale("ru"));

    private DataHelper() {
    }

    private static String generateRandomLogin() {
        return faker.name().username();
    }

    private static String generateRandomPassword() {
        return faker.internet().password();
    }

    public static AuthInfo generateRandomUser() {
        return new AuthInfo(generateRandomLogin(), generateRandomPassword());
    }

    public static AuthInfo getUser() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class AuthInfo {
        private String username;
        private String password;
    }

    private static String generateRandomVerificationCode() {
        return faker.numerify("######");
    }

    public static Verification generateVerificationInfo() {
        return new Verification(generateRandomVerificationCode());
    }

    @Value
    public static class Verification {
        private String code;
    }
}

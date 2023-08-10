package ru.netology.web.test;

import org.junit.jupiter.api.AfterAll;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.page.LoginPage;


import static com.codeborne.selenide.Selenide.open;

public class SQLTest {

    @AfterAll
    static void teardown() {
        SQLHelper.cleanDB();
    }

    @Test
    @DisplayName("Should be successful auth and verify")
    void shouldBeSuccessfulAuthAndVerify() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getUser();
        var verificationPage = loginPage.setAuthInfoSuccess(authInfo);
        verificationPage.VerificationPageVisualisation();
        var verifyCode = SQLHelper.getVerificationCode();
        verificationPage.setVerifyInfoSuccess(verifyCode);
    }

    @Test
    @DisplayName("Should be successful auth and error in verify")
    void shouldBeSuccessfulAuthAndErrorInVerify() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getUser();
        var verificationPage = loginPage.setAuthInfoSuccess(authInfo);
        var verifyCode = DataHelper.generateVerificationInfo();
        verificationPage.setVerifyInfo(verifyCode);
        verificationPage.errorVerify();
    }

    @Test
    @DisplayName("Should be error in auth")
    void shouldBeErrorInAuth() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.generateRandomUser();
        loginPage.setAuthInfo(authInfo);
        loginPage.errorAuth();
    }
}

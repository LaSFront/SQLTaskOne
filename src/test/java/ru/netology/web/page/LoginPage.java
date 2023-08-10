package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement loginInput = $("[data-test-id='login'] input");
    private final SelenideElement passwordInput = $("[data-test-id='password'] input ");
    private final SelenideElement buttonLogin = $("[data-test-id='action-login']");
    private final SelenideElement errorNotification = $("[data-test-id='error-notification']");

    public void errorAuth() {
        errorNotification.shouldBe(Condition.visible);
    }

    public void setAuthInfo(DataHelper.AuthInfo info) {
        loginInput.sendKeys(info.getUsername());
        passwordInput.sendKeys(info.getPassword());
        buttonLogin.shouldHave(Condition.exactText("Продолжить")).click();
    }

    public VerificationPage setAuthInfoSuccess(DataHelper.AuthInfo info) {
        setAuthInfo(info);
        return new VerificationPage();
    }
}

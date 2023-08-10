package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.google.common.base.Verify.verify;

public class VerificationPage {
    private final SelenideElement codeField = $("[data-test-id='code'] input").shouldBe(Condition.visible);
    private final SelenideElement verifyButton = $("[data-test-id='action-verify']").shouldHave(Condition.exactText("Продолжить"));
    private final SelenideElement errorNotification = $("[data-test-id='error-notification']");

    public void VerificationPageVisualisation() {
        codeField.shouldBe(Condition.visible);
    }

   public void setVerifyInfo(DataHelper.Verification info) {
        codeField.sendKeys(info.getCode());
        verifyButton.click();
    }

    public PersonalAccount setVerifyInfoSuccess(DataHelper.Verification info) {
        codeField.sendKeys(info.getCode());
        verifyButton.click();
        return new PersonalAccount();
    }

    public void errorVerify() {
        errorNotification.shouldBe(Condition.visible);
    }



}

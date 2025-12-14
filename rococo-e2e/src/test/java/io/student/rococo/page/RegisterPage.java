package io.student.rococo.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {
    private final SelenideElement usernameInput = $("#username");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement submitPasswordInput = $("#passwordSubmit");
    private final SelenideElement registerButton = $("button[type='submit']");
    private final SelenideElement successMessage = $(byText("Добро пожаловать в Ro"));

    public RegisterPage setUsername(String username) {
        usernameInput.should(appear).sendKeys(username);
        return this;
    }

    public RegisterPage setPassword(String password) {
        passwordInput.should(appear).sendKeys(password);
        return this;
    }

    public RegisterPage setPasswordSubmit(String submitPassword) {
        submitPasswordInput.should(appear).sendKeys(submitPassword);
        return this;
    }

    public RegisterPage submitRegistration() {
        registerButton.should(appear).click();
        return this;
    }

    public RegisterPage checkSuccessMessage() {
        successMessage.should(appear);
        return this;
    }

    public RegisterPage checkErrorMessage(String errorMessage) {
        $(byText(errorMessage)).should(appear);
        return this;
    }
}

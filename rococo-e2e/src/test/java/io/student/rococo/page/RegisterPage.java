package io.student.rococo.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {
    private final SelenideElement usernameInput = $("#username");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement submitPasswordInput = $("#passwordSubmit");
    private final SelenideElement registerButton = $("button[type='submit']");
    private final SelenideElement successMessage = $(byText("Добро пожаловать в Ro"));
    private final SelenideElement formError = $(".form__error");

    public RegisterPage setUsername(String username) {
        usernameInput.sendKeys(username);
        return this;
    }

    public RegisterPage setPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public RegisterPage setPasswordSubmit(String submitPassword) {
        submitPasswordInput.sendKeys(submitPassword);
        return this;
    }

    public RegisterPage submitRegistration() {
        registerButton.click();
        return this;
    }

    public RegisterPage checkSuccessMessage() {
        successMessage.should(appear);
        return this;
    }

    public RegisterPage checkErrorMessage(String errorMessage) {
        formError.shouldHave(text(errorMessage));
        return this;
    }
}

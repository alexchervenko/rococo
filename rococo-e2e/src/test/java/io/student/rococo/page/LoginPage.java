package io.student.rococo.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private final SelenideElement loginInput = $("[name='username']");
    private final SelenideElement passwordInput = $("[name='password'");
    private final SelenideElement loginButton = $("[type='submit']");
    private final SelenideElement signUpButton = $x("//a[text()='Зарегистрироваться']");

    public void clickRegisterButton() {
        signUpButton.should(appear).click();
    }

    public LoginPage setUsername(String username) {
        loginInput.should(appear).sendKeys(username);
        return this;
    }

    public LoginPage setPassword(String password) {
        passwordInput.should(appear).sendKeys(password);
        return this;
    }

    public LoginPage submitLogin() {
        loginButton.should(appear).click();
        return this;
    }
}

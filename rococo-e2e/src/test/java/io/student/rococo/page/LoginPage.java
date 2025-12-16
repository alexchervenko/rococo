package io.student.rococo.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private final SelenideElement loginInput = $("[name='username']");
    private final SelenideElement passwordInput = $("[name='password'");
    private final SelenideElement loginButton = $("[type='submit']");
    private final SelenideElement signUpButton = $x("//a[text()='Зарегистрироваться']");

    public RegisterPage clickRegisterButton() {
        signUpButton.click();
        return Selenide.page(RegisterPage.class);
    }

    public LoginPage setUsername(String username) {
        loginInput.sendKeys(username);
        return this;
    }

    public LoginPage setPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public MainPage submitLoginExpectSuccess() {
        loginButton.click();
        return Selenide.page(MainPage.class);
    }
}

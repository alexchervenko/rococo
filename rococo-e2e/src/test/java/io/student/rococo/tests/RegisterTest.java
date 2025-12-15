package io.student.rococo.tests;

import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import io.student.rococo.config.Config;
import io.student.rococo.jupiter.User;
import io.student.rococo.model.UserJson;
import io.student.rococo.page.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static io.student.rococo.test_data.Defaults.DEFAULT_USER_PASSWORD;
import static io.student.rococo.test_data.RegisterErrors.PASSWORDS_ARE_NOT_EQUAL;
import static io.student.rococo.test_data.RegisterErrors.USERNAME_EXISTS;

public class RegisterTest {
    private static final Config CFG = Config.getInstance();
    Faker faker = new Faker();

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    void shouldRegisterNewUser() {
        Selenide.open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton()
                .clickRegisterButton()
                .setUsername(faker.name().username())
                .setPassword(DEFAULT_USER_PASSWORD)
                .setPasswordSubmit(DEFAULT_USER_PASSWORD)
                .submitRegistration()
                .checkSuccessMessage();
    }

    @Test
    @User
    @DisplayName("Ошибка регистрации если пользователь с таким username уже существует")
    void shouldNotRegisterUserWithExistingUsername(UserJson userJson) {
        Selenide.open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton()
                .clickRegisterButton()
                .setUsername(userJson.username())
                .setPassword(DEFAULT_USER_PASSWORD)
                .setPasswordSubmit(DEFAULT_USER_PASSWORD)
                .submitRegistration()
                .checkErrorMessage(USERNAME_EXISTS.formatted(userJson.username()));
    }

    @Test
    @DisplayName("Ошибка регистрации если пароли не совпадают")
    void shouldShowErrorIfPasswordAndSubmitPasswordAreNotEqual() {
        Selenide.open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton()
                .clickRegisterButton()
                .setUsername(faker.name().username())
                .setPassword(DEFAULT_USER_PASSWORD)
                .setPasswordSubmit(DEFAULT_USER_PASSWORD + "123")
                .submitRegistration()
                .checkErrorMessage(PASSWORDS_ARE_NOT_EQUAL);
    }
}

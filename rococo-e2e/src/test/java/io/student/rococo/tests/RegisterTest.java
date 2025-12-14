package io.student.rococo.tests;

import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import io.student.rococo.config.Config;
import io.student.rococo.jupiter.User;
import io.student.rococo.model.UserJson;
import io.student.rococo.page.MainPage;
import org.junit.jupiter.api.Test;

import static io.student.rococo.test_data.Defaults.DEFAULT_USER_PASSWORD;
import static io.student.rococo.test_data.RegisterErrors.PASSWORDS_ARE_NOT_EQUAL;
import static io.student.rococo.test_data.RegisterErrors.USERNAME_EXISTS;

public class RegisterTest extends BaseTest {
    private static final Config CFG = Config.getInstance();
    Faker faker = new Faker();

    @Test
    void shouldRegisterNewUser() {
        Selenide.open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton();
        poManager.getLoginPage()
                .clickRegisterButton();
        poManager.getRegisterPage()
                .setUsername(faker.name().username())
                .setPassword(DEFAULT_USER_PASSWORD)
                .setPasswordSubmit(DEFAULT_USER_PASSWORD)
                .submitRegistration()
                .checkSuccessMessage();
    }

    @Test
    @User
    void shouldNotRegisterUserWithExistingUsername(UserJson userJson) {
        Selenide.open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton();
        poManager.getLoginPage()
                .clickRegisterButton();
        poManager.getRegisterPage()
                .setUsername(userJson.username())
                .setPassword(DEFAULT_USER_PASSWORD)
                .setPasswordSubmit(DEFAULT_USER_PASSWORD)
                .submitRegistration()
                .checkErrorMessage(USERNAME_EXISTS.formatted(userJson.username()));
    }

    @Test
    void shouldShowErrorIfPasswordAndSubmitPasswordAreNotEqual() {
        Selenide.open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton();
        poManager.getLoginPage()
                .clickRegisterButton();
        poManager.getRegisterPage()
                .setUsername(faker.name().username())
                .setPassword(DEFAULT_USER_PASSWORD)
                .setPasswordSubmit(DEFAULT_USER_PASSWORD + "123")
                .submitRegistration()
                .checkErrorMessage(PASSWORDS_ARE_NOT_EQUAL);
    }
}

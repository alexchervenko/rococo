package io.student.rococo.tests;

import com.codeborne.selenide.Selenide;
import io.student.rococo.config.Config;
import io.student.rococo.jupiter.annotation.User;
import io.student.rococo.jupiter.extension.BrowserExtension;
import io.student.rococo.model.UserJson;
import io.student.rococo.page.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.student.rococo.test_data.Defaults.DEFAULT_USER_PASSWORD;

@ExtendWith(BrowserExtension.class)
public class LoginTest {
    private static final Config CFG = Config.getInstance();

    @Test
    @DisplayName("Главная страница должна отображаться без аутентификации")
    void mainPageShouldBeDisplayedWithoutLogin() {
        Selenide.open(CFG.frontUrl(), MainPage.class)
                .checkMainPageElementsAreDisplayed();
    }

    @Test
    @User
    @DisplayName("Главная страница должна отображаться после успешного входа")
    void mainPageShouldBeDisplayedAfterSuccessfulLogin(UserJson userJson) {
        Selenide.open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton()
                .setUsername(userJson.username())
                .setPassword(DEFAULT_USER_PASSWORD)
                .submitLoginExpectSuccess()
                .checkMainPageElementsAreDisplayed()
                .openProfileSettings()
                .checkModalElementsAreDisplayed();
    }

    @Test
    @User
    @DisplayName("Должна отображаться кнопка 'Добавить картину' после входа в систему")
    void shouldDisplayAddPictureButtonAfterLogin(UserJson userJson) {
        Selenide.open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton()
                .setUsername(userJson.username())
                .setPassword(DEFAULT_USER_PASSWORD)
                .submitLoginExpectSuccess()
                .openPicturesCategory()
                .checkAddPictureButtonisDisplayed();

    }

    @Test
    @User
    @DisplayName("Должна отображаться кнопка 'Добавить художника' после входа в систему")
    void shouldDisplayAddArtistButtonAfterLogin(UserJson userJson) {
        Selenide.open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton()
                .setUsername(userJson.username())
                .setPassword(DEFAULT_USER_PASSWORD)
                .submitLoginExpectSuccess()
                .openArtistsCategory()
                .checkAddArtistButtonisDisplayed();
    }

    @Test
    @User
    @DisplayName("Должна отображаться кнопка 'Добавить музей' после входа в систему")
    void shouldDisplayAddMuseumButtonAfterLogin(UserJson userJson) {
        Selenide.open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton()
                .setUsername(userJson.username())
                .setPassword(DEFAULT_USER_PASSWORD)
                .submitLoginExpectSuccess()
                .openMuseumsCategory()
                .checkAddMuseumButtonisDisplayed();
    }
}

package io.student.rococo.tests;

import com.codeborne.selenide.Selenide;
import io.student.rococo.config.Config;
import io.student.rococo.jupiter.User;
import io.student.rococo.model.UserJson;
import io.student.rococo.page.MainPage;
import org.junit.jupiter.api.Test;

import static io.student.rococo.test_data.Defaults.DEFAULT_USER_PASSWORD;

public class LoginTest extends BaseTest {
    private static final Config CFG = Config.getInstance();

    @Test
    void mainPageShouldBeDisplayedWithoutLogin() {
        Selenide.open(CFG.frontUrl(), MainPage.class)
                .checkMainPageElementsAreDisplayed();
    }

    @Test
    @User
    void mainPageShouldBeDisplayedAfterSuccessfulLogin(UserJson userJson) {
        Selenide.open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton();
        poManager.getLoginPage()
                .setUsername(userJson.username())
                .setPassword(DEFAULT_USER_PASSWORD)
                .submitLogin();
        poManager.getMainPage()
                .checkMainPageElementsAreDisplayed()
                .openProfileSettings();
        poManager.getProfileModalComponent()
                .checkModalElementsAreDisplayed();
    }

    @Test
    @User
    void shouldDisplayAddPictureButtonAfterLogin(UserJson userJson) {
        Selenide.open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton();
        poManager.getLoginPage()
                .setUsername(userJson.username())
                .setPassword(DEFAULT_USER_PASSWORD)
                .submitLogin();
        poManager.getMainPage()
                .openPicturesCategory();
        poManager.getPicturesPage()
                .checkAddPictureButtonisDisplayed();

    }

    @Test
    @User
    void shouldDisplayAddArtistButtonAfterLogin(UserJson userJson) {
        Selenide.open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton();
        poManager.getLoginPage()
                .setUsername(userJson.username())
                .setPassword(DEFAULT_USER_PASSWORD)
                .submitLogin();
        poManager.getMainPage()
                .openArtistsCategory();
        poManager.getArtistsPage()
                .checkAddArtistButtonisDisplayed();

    }

    @Test
    @User
    void shouldDisplayAddMuseumButtonAfterLogin(UserJson userJson) {
        Selenide.open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton();
        poManager.getLoginPage()
                .setUsername(userJson.username())
                .setPassword(DEFAULT_USER_PASSWORD)
                .submitLogin();
        poManager.getMainPage()
                .openMuseumsCategory();
        poManager.getMuseumsPage()
                .checkAddMuseumButtonisDisplayed();

    }
}

package io.student.rococo.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.student.rococo.page.component.ProfileModalComponent;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private final SelenideElement header = $("header");
    private final SelenideElement headerPicturesButton = header.find(byText("Картины"));
    private final SelenideElement headerArtistsButton = header.find(byText("Художники"));
    private final SelenideElement headerMuseumsButton = header.find(byText("Музеи"));
    private final SelenideElement lightSwitch = header.find("[role='switch']");
    private final SelenideElement loginButton = header.find(byText("Войти"));

    private final SelenideElement mainContent = $("main");
    private final SelenideElement picturesButton = mainContent.find(byText("Картины"));
    private final SelenideElement artistsButton = mainContent.find(byText("Художники"));
    private final SelenideElement museumsButton = mainContent.find(byText("Музеи"));
    private final SelenideElement userAvatar = $x("//button[.//@data-testid='avatar']");


    public LoginPage clickLoginButton() {
        loginButton.click();
        return Selenide.page(LoginPage.class);
    }

    public MainPage checkUserAvatarIsDisplayed() {
        userAvatar.should(appear);
        return this;
    }

    public MainPage checkMainPageElementsAreDisplayed() {
        headerPicturesButton.should(appear);
        headerArtistsButton.should(appear);
        headerMuseumsButton.should(appear);
        lightSwitch.should(appear);
        picturesButton.should(appear);
        artistsButton.should(appear);
        museumsButton.should(appear);
        return this;
    }

    public ProfileModalComponent openProfileSettings() {
        userAvatar.click();
        return Selenide.page(ProfileModalComponent.class);
    }

    public PicturesPage openPicturesCategory() {
        picturesButton.click();
        return Selenide.page(PicturesPage.class);
    }

    public ArtistsPage openArtistsCategory() {
        artistsButton.click();
        return Selenide.page(ArtistsPage.class);
    }

    public MuseumsPage openMuseumsCategory() {
        museumsButton.click();
        return Selenide.page(MuseumsPage.class);
    }

}

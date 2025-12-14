package io.student.rococo.page;

import io.student.rococo.page.component.ProfileModalComponent;

import java.util.Objects;

public class POManager {
    MainPage mainPage;
    LoginPage loginPage;
    RegisterPage registerPage;
    ProfileModalComponent profileModalComponent;
    PicturesPage picturesPage;
    ArtistsPage artistsPage;
    MuseumsPage museumsPage;

    public MainPage getMainPage() {
        return Objects.requireNonNullElseGet(mainPage, MainPage::new);
    }

    public LoginPage getLoginPage() {
        return Objects.requireNonNullElseGet(loginPage, LoginPage::new);
    }

    public RegisterPage getRegisterPage() {
        return Objects.requireNonNullElseGet(registerPage, RegisterPage::new);
    }

    public ProfileModalComponent getProfileModalComponent() {
        return Objects.requireNonNullElseGet(profileModalComponent, ProfileModalComponent::new);
    }

    public PicturesPage getPicturesPage() {
        return Objects.requireNonNullElseGet(picturesPage, PicturesPage::new);
    }

    public ArtistsPage getArtistsPage() {
        return Objects.requireNonNullElseGet(artistsPage, ArtistsPage::new);
    }

    public MuseumsPage getMuseumsPage() {
        return Objects.requireNonNullElseGet(museumsPage, MuseumsPage::new);
    }
}

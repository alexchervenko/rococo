package io.student.rococo.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ArtistsPage {
    public static final SelenideElement addArtistButton = $(byText("Добавить художника"));

    public ArtistsPage checkAddArtistButtonisDisplayed() {
        addArtistButton.should(appear);
        return this;
    }
}

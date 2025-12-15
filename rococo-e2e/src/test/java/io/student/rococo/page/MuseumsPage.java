package io.student.rococo.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MuseumsPage {
    public final SelenideElement addMuseumButton = $(byText("Добавить музей"));

    public MuseumsPage checkAddMuseumButtonisDisplayed() {
        addMuseumButton.should(appear);
        return this;
    }
}

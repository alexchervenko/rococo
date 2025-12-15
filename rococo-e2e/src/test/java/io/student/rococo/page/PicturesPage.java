package io.student.rococo.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PicturesPage {
    public final SelenideElement addPictureButton = $(byText("Добавить картину"));

    public PicturesPage checkAddPictureButtonisDisplayed() {
        addPictureButton.should(appear);
        return this;
    }
}

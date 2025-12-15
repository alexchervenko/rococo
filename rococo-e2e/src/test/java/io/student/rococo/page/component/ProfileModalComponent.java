package io.student.rococo.page.component;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;

public class ProfileModalComponent {
    private final SelenideElement profileModalWindow = $x("//*[@data-testid='modal-component']");
    private final SelenideElement avatar = $x("//*[@data-testid='avatar']");

    public ProfileModalComponent checkModalElementsAreDisplayed() {
        avatar.should(appear);
        profileModalWindow.shouldHave(text("Профиль"));
        profileModalWindow.shouldHave(text("Обновить фото профиля"));
        profileModalWindow.shouldHave(text("Имя"));
        profileModalWindow.shouldHave(text("Фамилия"));
        return this;
    }
}

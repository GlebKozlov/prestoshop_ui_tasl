package com.broit.task.pages.main;

import com.broit.task.pages.Waitable;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class QuickViewProductCartForm implements Waitable<QuickViewProductCartForm> {

    private final SelenideElement quickViewModal = $(".modal.fade.quickview.in");
    private final SelenideElement addToCardButton = $(".add>button.btn.add-to-cart");
    private final SelenideElement quantityInput = $(By.id("quantity_wanted"));
    private final SelenideElement quantityDown = $(".bootstrap-touchspin-down");


    @Step("Click on Quantity Down button to decrease products")
    public void clickOnQuantityDown() {
        quantityDown.shouldBe(Condition.visible).click();
    }

    @Step("Check quantity of products to add: expected =  {expectedQuantity}")
    public void checkQuantityOfProductToAdd(int expectedQuantity) {
        quantityInput.shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.value(String.valueOf(expectedQuantity)));
    }

    @Step("Click on Add To Cart button on Quick View Product Cart")
    public void clickOnAddToCart() {
        addToCardButton.shouldBe(Condition.visible).click();
        quickViewModal.shouldBe(Condition.hidden, Duration.ofSeconds(3));
    }

    @Override
    public QuickViewProductCartForm waitForLoad() {
        quickViewModal.shouldBe(Condition.visible, Duration.ofSeconds(20));
        return this;
    }
}

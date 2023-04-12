package com.broit.task.pages.main;

import com.broit.task.pages.Waitable;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class BlockCardForm implements Waitable<BlockCardForm> {

    private final SelenideElement proceedToCheckoutButton = $(".cart-content-btn>a.btn.btn-primary");
    private final SelenideElement continueShoppingButton = $(".cart-content-btn>button.btn.btn-secondary");
    private final SelenideElement blockCartModal = $(By.id("blockcart-modal"));
    private final SelenideElement quantityField = $(".product-quantity>strong");

    @Step("Check quantity in Block card: expected = {expectedQuantity}")
    public void checkQuantityFieldValue(int expectedQuantity) {
        quantityField.shouldBe(Condition.visible).shouldHave(Condition.text(String.valueOf(expectedQuantity)));
    }

    @Step("Click on Proceed To Checkout button on Black Card")
    public void clickOnProceedToCheckout() {
        proceedToCheckoutButton.shouldBe(Condition.visible).click();
    }

    @Step("Click on Continue Shopping")
    public void clickOnContinueShopping() {
        continueShoppingButton.shouldBe(Condition.visible).click();
        blockCartModal.shouldBe(Condition.hidden);
    }

    @Override
    public BlockCardForm waitForLoad() {
        blockCartModal.shouldBe(Condition.visible);
        return this;
    }
}
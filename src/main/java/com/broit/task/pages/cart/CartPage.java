package com.broit.task.pages.cart;

import com.broit.task.pages.Waitable;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Slf4j
public class CartPage implements Waitable<CartPage> {

    private final SelenideElement contentWrapper = $(By.id("wrapper"));
    private final SelenideElement proceedToCheckoutButton = $("div.checkout a.btn");
    @Getter
    private final ElementsCollection cartItems = $$("li.cart-item");
    @Getter
    private final By productQuantity = By.cssSelector("input.js-cart-line-product-quantity.form-control");

    @Step("Click on Proceed To Checkout button")
    public void clickOnProceedToCheckout() {
        proceedToCheckoutButton.shouldBe(Condition.visible).click();
    }

    @Override
    public CartPage waitForLoad() {
        contentWrapper.shouldBe(Condition.visible, Duration.ofSeconds(10));
        return this;
    }
}

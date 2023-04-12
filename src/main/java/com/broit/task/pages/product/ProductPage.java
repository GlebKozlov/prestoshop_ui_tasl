package com.broit.task.pages.product;

import com.broit.task.pages.Waitable;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class ProductPage implements Waitable<ProductPage> {

    private final SelenideElement content = $(By.id("wrapper"));
    private final SelenideElement quantityInput = $(By.id("quantity_wanted"));
    private final SelenideElement quantityUp = $(".bootstrap-touchspin-up");
    private final SelenideElement addToCardButton = $(By.cssSelector(".add button.btn.add-to-cart"));

    @Step("Click on Quantity Up button to increase products")
    public void clickOnQuantityUp() {
        quantityUp.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
    }

    @Step("Check quantity of products to add: expected = {expectedQuantity}")
    public void checkQuantityOfProductToAdd(int expectedQuantity) {
        quantityInput.shouldBe(Condition.visible).shouldHave(Condition.value(String.valueOf(expectedQuantity)));
    }

    @Step("Click on Add To Cart button on Product Page")
    public void clickOnAddToCart() {
        addToCardButton.shouldBe(Condition.visible).click();
    }

    @Override
    public ProductPage waitForLoad() {
        content.shouldBe(Condition.visible, Duration.ofSeconds(10));
        return this;
    }
}

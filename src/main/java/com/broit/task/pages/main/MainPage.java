package com.broit.task.pages.main;

import com.broit.task.pages.Waitable;
import com.broit.task.utils.StringUtils;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

@Slf4j
@AllArgsConstructor
public class MainPage implements Waitable<MainPage> {

    private static final String CSS_SELECTOR_ACTIVE_DEVICE_TEMPLATE = "a.change-device.%s.active";
    private static final String CSS_SELECTOR_DEVICE_TEMPLATE = "a.change-device.%s";
    private static final String FRAME_ID = "framelive";

    private final By productQuickViewButtonSelector = By.cssSelector("a.quick-view");
    private final SelenideElement contentWrapper = $(By.id("wrapper"));
    private final SelenideElement mainLoaderImage = $(By.id("loadingMessage"));
    private final SelenideElement blockCartProductQuantity = $("div.blockcart span.cart-products-count");
    private final SelenideElement productsArea = $(".products.row");
    private final ElementsCollection products = $$(".products.row>.js-product.product");

    private final String device;

    @Step("Set up Main page")
    public void setUpPage() {
        selectDevice();
        switchToFrame();
        waitForLoad();
    }

    @Step("Check is Products available")
    public void isProductsAvailable() {
        productsArea.shouldBe(Condition.visible);
    }

    @Step("Click on QuickView of product with {productIndex} index")
    public void clickOnQuickViewOfProduct(int productIndex) {
        products.shouldBe(CollectionCondition.sizeGreaterThanOrEqual(productIndex), Duration.ofSeconds(5));
        SelenideElement targetProduct = products.get(productIndex);
        actions().moveToElement(targetProduct).pause(Duration.ofSeconds(1)).perform();
        SelenideElement quickViewButton = targetProduct.find(productQuickViewButtonSelector);
        quickViewButton.shouldBe(Condition.visible, Duration.ofSeconds(5));
        quickViewButton.click();
    }

    @Step("Click on product with {productIndex} index")
    public void clickOnProduct(int productIndex) {
        products.shouldBe(CollectionCondition.sizeGreaterThanOrEqual(productIndex));
        products.get(productIndex).click();
    }

    @Step("Select device")
    private void selectDevice() {
        if (!$(String.format(CSS_SELECTOR_ACTIVE_DEVICE_TEMPLATE, device)).exists()) {
            $(String.format(CSS_SELECTOR_DEVICE_TEMPLATE, device)).shouldBe(Condition.visible).click();
        }
    }

    @Step("Switch to frame")
    private void switchToFrame() {
        switchTo().defaultContent();
        switchTo().frame(FRAME_ID);
    }

    @Step("Check amount of products in Block card icon: expected = {expectedQuantity}")
    public void checkAmountOfProductsInCartIcon(int expectedQuantity) {
        blockCartProductQuantity.shouldBe(Condition.visible)
                .shouldHave(Condition.match("Count of products", (e) -> StringUtils.getNumbers(e.getText()).equals(expectedQuantity)));
    }

    @Step("Wait fo load Main Page")
    public MainPage waitForLoad() {
        contentWrapper.shouldBe(Condition.visible, Duration.ofSeconds(20));
        mainLoaderImage.shouldBe(Condition.not(Condition.visible), Duration.ofSeconds(10));
        return this;
    }
}

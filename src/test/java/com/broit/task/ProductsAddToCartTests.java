package com.broit.task;

import com.broit.task.pages.cart.CartPage;
import com.broit.task.pages.main.BlockCardForm;
import com.broit.task.pages.main.QuickViewProductCartForm;
import com.broit.task.pages.product.ProductPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("Add some products to cart")
public class ProductsAddToCartTests extends BaseTest {

    private static final int MAIN_PAGE_PRODUCT_INDEX_FIRST = 0;
    private static final int MAIN_PAGE_PRODUCT_INDEX_SECOND = 1;
    private static final int CART_PAGE_PRODUCT_INDEX_FIRST = 0;
    private static final int CART_PAGE_PRODUCT_INDEX_SECOND = 1;
    private static final int EXPECTED_AMOUNT_OF_ONE_ITEM_IN_CART = 1;
    private static final int EXPECTED_INCREASED_QUANTITY = 2;
    private static final int EXPECTED_AMOUNT_OF_TWO_ITEMS_IN_CART = 2;
    private static final int EXPECTED_DEFAULT_QUANTITY = 1;

    @Autowired
    private QuickViewProductCartForm quickViewProductCartForm;
    @Autowired
    private BlockCardForm blockCardForm;
    @Autowired
    private CartPage cartPage;
    @Autowired
    private ProductPage productPage;

    @Tags({
            @Tag("Cart"),
            @Tag("QuickView")
    })
    @Test
    void singleProductAddedToCardViaQuickView() {
        mainPage.isProductsAvailable();
        mainPage.clickOnQuickViewOfProduct(MAIN_PAGE_PRODUCT_INDEX_FIRST);

        quickViewProductCartForm.waitForLoad().checkQuantityOfProductToAdd(EXPECTED_DEFAULT_QUANTITY);

        productPage.waitForLoad().clickOnQuantityUp();
        productPage.clickOnQuantityUp();

        quickViewProductCartForm.waitForLoad().clickOnQuantityDown();
        quickViewProductCartForm.checkQuantityOfProductToAdd(EXPECTED_INCREASED_QUANTITY);
        quickViewProductCartForm.clickOnAddToCart();

        blockCardForm.waitForLoad().checkQuantityFieldValue(EXPECTED_INCREASED_QUANTITY);
        blockCardForm.clickOnProceedToCheckout();

        SoftAssertions.assertSoftly(softly -> {
            ElementsCollection cartItems = cartPage.getCartItems();
            softly.assertThat(cartItems).isNotNull();
            softly.assertThat(cartItems.size()).isEqualTo(EXPECTED_AMOUNT_OF_ONE_ITEM_IN_CART);

            SelenideElement cartItem = cartItems.get(CART_PAGE_PRODUCT_INDEX_FIRST);
            softly.assertThat(cartItem.is(Condition.visible)).isTrue();
            SelenideElement productQuantity = cartItem.find(cartPage.getProductQuantity());
            softly.assertThat(productQuantity.is(Condition.visible)).isTrue();
            softly.assertThat(Integer.valueOf(productQuantity.getValue())).isEqualTo(EXPECTED_INCREASED_QUANTITY);
        });
        cartPage.clickOnProceedToCheckout();
    }

    @Tags({
            @Tag("Cart"),
            @Tag("Product")
    })
    @Test
    void productsAddToCardViaProductPage() {
        mainPage.isProductsAvailable();
        mainPage.clickOnProduct(MAIN_PAGE_PRODUCT_INDEX_FIRST);

        productPage.waitForLoad().clickOnQuantityUp();
        productPage.checkQuantityOfProductToAdd(EXPECTED_INCREASED_QUANTITY);
        productPage.clickOnAddToCart();

        blockCardForm.waitForLoad().checkQuantityFieldValue(EXPECTED_INCREASED_QUANTITY);
        blockCardForm.clickOnProceedToCheckout();

        SoftAssertions.assertSoftly(softly -> {
            ElementsCollection cartItems = cartPage.getCartItems();
            softly.assertThat(cartItems).isNotNull();
            softly.assertThat(cartItems.size()).isEqualTo(EXPECTED_AMOUNT_OF_ONE_ITEM_IN_CART);

            SelenideElement cartItem = cartItems.get(CART_PAGE_PRODUCT_INDEX_FIRST);
            softly.assertThat(cartItem.is(Condition.visible)).isTrue();
            SelenideElement productQuantity = cartItem.find(cartPage.getProductQuantity());
            softly.assertThat(productQuantity.is(Condition.visible)).isTrue();
            softly.assertThat(Integer.valueOf(productQuantity.getValue())).isEqualTo(EXPECTED_INCREASED_QUANTITY);
        });
        cartPage.clickOnProceedToCheckout();
    }

    @Tags({
            @Tag("Cart"),
            @Tag("QuickView")
    })
    @Test
    void differentProductsAddedToCardViaQuickView() {
        mainPage.isProductsAvailable();
        mainPage.clickOnQuickViewOfProduct(MAIN_PAGE_PRODUCT_INDEX_FIRST);

        productPage.waitForLoad().clickOnQuantityUp();
        quickViewProductCartForm.waitForLoad().clickOnAddToCart();
        blockCardForm.waitForLoad().clickOnContinueShopping();

        mainPage.waitForLoad().checkAmountOfProductsInCartIcon(EXPECTED_INCREASED_QUANTITY);
        mainPage.clickOnQuickViewOfProduct(MAIN_PAGE_PRODUCT_INDEX_SECOND);

        productPage.waitForLoad().clickOnQuantityUp();
        quickViewProductCartForm.waitForLoad().clickOnAddToCart();
        blockCardForm.waitForLoad().clickOnProceedToCheckout();

        SoftAssertions.assertSoftly(softly -> {
            ElementsCollection cartItems = cartPage.getCartItems();
            softly.assertThat(cartItems).isNotNull();
            softly.assertThat(cartItems.size()).isEqualTo(EXPECTED_AMOUNT_OF_TWO_ITEMS_IN_CART);

            SelenideElement cartItem = cartItems.get(CART_PAGE_PRODUCT_INDEX_FIRST);
            softly.assertThat(cartItem.is(Condition.visible)).isTrue();
            SelenideElement productQuantity = cartItem.find(cartPage.getProductQuantity());
            softly.assertThat(productQuantity.is(Condition.visible)).isTrue();
            softly.assertThat(Integer.valueOf(productQuantity.getValue())).isEqualTo(EXPECTED_INCREASED_QUANTITY);

            SelenideElement cartItem2 = cartItems.get(CART_PAGE_PRODUCT_INDEX_SECOND);
            softly.assertThat(cartItem2.is(Condition.visible)).isTrue();
            SelenideElement productQuantity2 = cartItem2.find(cartPage.getProductQuantity());
            softly.assertThat(productQuantity2.is(Condition.visible)).isTrue();
            softly.assertThat(Integer.valueOf(productQuantity2.getValue())).isEqualTo(EXPECTED_INCREASED_QUANTITY);
        });
        cartPage.clickOnProceedToCheckout();
    }
}
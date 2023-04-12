package com.broit.task.configuration;

import com.broit.task.pages.cart.CartPage;
import com.broit.task.pages.main.BlockCardForm;
import com.broit.task.pages.main.MainPage;
import com.broit.task.pages.main.QuickViewProductCartForm;
import com.broit.task.pages.product.ProductPage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagesConfiguration {

    @Bean
    public MainPage mainPage(@Value("${device}") String device) {
        return new MainPage(device);
    }

    @Bean
    public CartPage cartPage() {
        return new CartPage();
    }

    @Bean
    public BlockCardForm blockCardForm() {
        return new BlockCardForm();
    }

    @Bean
    public QuickViewProductCartForm quickViewProductCartForm() {
        return new QuickViewProductCartForm();
    }

    @Bean
    public ProductPage productPage() {
        return new ProductPage();
    }
}
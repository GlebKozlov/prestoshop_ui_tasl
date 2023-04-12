package com.broit.task.pages;

import io.qameta.allure.Step;

public interface Waitable<T> {

    @Step("Wait for page loading")
    T waitForLoad();
}
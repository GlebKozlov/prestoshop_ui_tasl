package com.broit.task;

import com.broit.task.configuration.DriverConfigManagerConfigurations;
import com.broit.task.configuration.PagesConfiguration;
import com.broit.task.manager.DriverConfigurationManager;
import com.broit.task.pages.main.MainPage;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static com.codeborne.selenide.Selenide.open;

@SpringBootTest(classes = {
        PagesConfiguration.class,
        DriverConfigManagerConfigurations.class
})
public abstract class BaseTest {

    @Value("${default-url}")
    private String url;
    @Autowired
    private DriverConfigurationManager driverConfigurationManager;
    @Autowired
    protected MainPage mainPage;

    @BeforeEach
    void setUp() {
        setUpAllure();
        driverConfigurationManager.setUp();
        open(url);
        mainPage.setUpPage();
    }

    private void setUpAllure() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }
}

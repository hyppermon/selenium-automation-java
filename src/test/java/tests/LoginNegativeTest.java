package tests;

import base.BaseTest;
import config.ConfigLoader;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginNegativeTest extends BaseTest {

    @Test
    void shouldShowErrorWhenUsernameIsMissing() {
        LoginPage loginPage = new LoginPage(driver)
                .open(ConfigLoader.getBaseUrl());


        loginPage.login("", "secret_sauce");

        assertTrue(loginPage.isErrorVisible(), "Error message should be visible");
        assertTrue(
                loginPage.getErrorMessage().contains("Username is required"),
                "Expected username-required error message"
        );
    }
}

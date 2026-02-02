package tests;

import base.BaseTest;
import config.ConfigLoader;
import org.junit.jupiter.api.Test;
import pages.InventoryPage;
import pages.LoginPage;
import testdata.TestUsers;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginTests extends BaseTest {

    @Test
    void shouldLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver)
                .open(ConfigLoader.getBaseUrl());

        InventoryPage inventoryPage =
                loginPage.loginExpectSuccess(TestUsers.STANDARD_USER, TestUsers.PASSWORD);

        assertTrue(inventoryPage.isLoaded(), "Inventory page should be loaded after login");
    }

    @Test
    void shouldShowErrorWhenUsernameIsMissing() {
        LoginPage loginPage = new LoginPage(driver)
                .open(ConfigLoader.getBaseUrl());


        loginPage.login("", TestUsers.PASSWORD);

        assertTrue(loginPage.isErrorVisible(), "Error message should be visible");
        assertTrue(
                loginPage.getErrorMessage().contains("Username is required"),
                "Expected username-required error message");
    }

    @Test
    void shouldShowErrorWhenUsernameLockedOut() {
        LoginPage loginPage = new LoginPage(driver)
                .open(ConfigLoader.getBaseUrl());


        loginPage.login(TestUsers.LOCKED_OUT_USER, TestUsers.PASSWORD);

        assertTrue(loginPage.isErrorVisible(), "Error message should be visible");
        assertTrue(
                loginPage.getErrorMessage().contains("locked out"),
                "Expected locked out user error message");
    }
}
package tests;

import base.BaseTest;
import config.ConfigLoader;
import org.junit.jupiter.api.Test;
import pages.InventoryPage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginPositiveTest extends BaseTest {

    @Test
    void shouldLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver)
                .open(ConfigLoader.getBaseUrl());

        InventoryPage inventoryPage =
                loginPage.loginExpectSuccess("standard_user", "secret_sauce");

        assertTrue(inventoryPage.isLoaded(), "Inventory page should be loaded after login");
    }
}

package tests;

import base.BaseTest;
import config.ConfigLoader;
import org.junit.jupiter.api.Test;
import pages.InventoryPage;
import pages.LoginPage;
import testdata.TestUsers;
import pages.CartPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CartTests extends BaseTest {

    @Test
    void shouldAddOneProductToCart() {
        InventoryPage inventory = new LoginPage(driver)
                .open(ConfigLoader.getBaseUrl())
                .loginExpectSuccess(TestUsers.STANDARD_USER, TestUsers.PASSWORD);

        inventory.addBackpackToCart();

        assertEquals(1, inventory.getCartBadgeCount(), "Cart badge should show 1 item");

        var cart = inventory.goToCart();
        assertTrue(cart.hasAnyItem(), "Cart should contain at least one item");
    }

    @Test
    void shouldRemoveProductFromCart() {
        InventoryPage inventory = new LoginPage(driver)
                .open(ConfigLoader.getBaseUrl())
                .loginExpectSuccess(TestUsers.STANDARD_USER, TestUsers.PASSWORD);

        CartPage cart = inventory.addBackpackToCart().goToCart();

        assertTrue(cart.hasAnyItem(), "Cart should contain item before removal");

        cart.removeBackpack();

        assertTrue(cart.isEmpty(), "Cart should be empty after removal");
    }
}
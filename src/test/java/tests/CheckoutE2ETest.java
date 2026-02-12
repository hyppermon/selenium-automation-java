package tests;

import base.BaseTest;
import config.ConfigLoader;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import testdata.TestUsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckoutE2ETest extends BaseTest {

    @Test
    void shouldCompleteCheckoutFlow() {
        var inventory = new LoginPage(driver)
                .open(ConfigLoader.getBaseUrl())
                .loginExpectSuccess(TestUsers.STANDARD_USER, TestUsers.PASSWORD);

        var cart = inventory
                .addBackpackToCart()
                .goToCart();

        var stepTwo = cart
                .checkout()
                .fillFormAndContinue("Jan", "Kowalski", "00-001");

        assertTrue(stepTwo.isLoaded(), "Checkout summary page should be loaded");

        var complete = stepTwo.finish();

        assertTrue(complete.isLoaded(), "Checkout complete page should be loaded");
        assertEquals("Thank you for your order!", complete.getHeaderText());
    }
}

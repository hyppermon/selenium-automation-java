package tests;

import base.BaseTest;
import config.ConfigLoader;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import testdata.TestUsers;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckoutTests extends BaseTest {

    @Test
    void shouldProceedToCheckoutSummaryWithValidData() {
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
    }

    @Test
    void shouldShowErrorWhenFirstNameIsMissing() {
        var inventory = new LoginPage(driver)
                .open(ConfigLoader.getBaseUrl())
                .loginExpectSuccess(TestUsers.STANDARD_USER, TestUsers.PASSWORD);

        var cart = inventory
                .addBackpackToCart()
                .goToCart();

        var stepOne = cart.checkout();

        stepOne.fillForm("", "Kowalski", "00-001")
                .clickContinue();

        assertTrue(stepOne.isErrorVisible(), "Error message should be visible");
        assertTrue(
                stepOne.getErrorMessage().contains("First Name is required"),
                "Expected first-name-required validation message"
        );
    }
}


package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage extends BasePage {

    private final By firstName = By.cssSelector("[data-test='firstName']");
    private final By lastName  = By.cssSelector("[data-test='lastName']");
    private final By zipCode   = By.cssSelector("[data-test='postalCode']");
    private final By continueButton = By.cssSelector("[data-test='continue']");

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    public CheckoutStepTwoPage fillFormAndContinue(String fName, String lName, String zip) {
        type(firstName, fName);
        type(lastName, lName);
        type(zipCode, zip);
        click(continueButton);
        return new CheckoutStepTwoPage(driver);
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage extends BasePage {

    private final By firstName = By.cssSelector("[data-test='firstName']");
    private final By lastName  = By.cssSelector("[data-test='lastName']");
    private final By zipCode   = By.cssSelector("[data-test='postalCode']");
    private final By continueButton = By.cssSelector("[data-test='continue']");
    private final By errorMessage = By.cssSelector("[data-test='error']");

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

    public CheckoutStepOnePage fillForm(String fName, String lName, String zip) {
        type(firstName, fName);
        type(lastName, lName);
        type(zipCode, zip);
        return this;
    }

    public CheckoutStepOnePage clickContinue() {
        click(continueButton);
        return this;
    }

    public boolean isErrorVisible() {
        return !driver.findElements(errorMessage).isEmpty() && waitVisible(errorMessage).isDisplayed();
    }

    public String getErrorMessage() {
        return waitVisible(errorMessage).getText();
    }
}

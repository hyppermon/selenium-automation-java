package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage extends BasePage {

    private final By summaryContainer = By.cssSelector("[data-test='checkout-summary-container']");

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return waitVisible(summaryContainer).isDisplayed();
    }

    private final By finishButton = By.cssSelector("[data-test='finish']");

    public CheckoutCompletePage finish() {
        click(finishButton);
        return new CheckoutCompletePage(driver);
    }
}

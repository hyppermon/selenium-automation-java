package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {

    private final By completeHeader = By.cssSelector("[data-test='complete-header']");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getHeaderText() {
        return waitVisible(completeHeader).getText();
    }

    public boolean isLoaded() {
        return waitVisible(completeHeader).isDisplayed();
    }
}

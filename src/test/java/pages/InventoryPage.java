package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InventoryPage extends BasePage {

    private final By inventoryContainer = By.id("inventory_container");
    private final By addBackpackBtn = By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']");
    private final By cartLink = By.cssSelector("[data-test='shopping-cart-link']");
    private final By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        wait.until(ExpectedConditions.urlContains("inventory.html"));
        return waitVisible(inventoryContainer).isDisplayed();
    }

    public InventoryPage addBackpackToCart() {
        click(addBackpackBtn);
        return this;
    }

    public CartPage goToCart() {
        click(cartLink);
        return new CartPage(driver);
    }

    public int getCartBadgeCount() {
        if (driver.findElements(cartBadge).isEmpty()) {
            return 0;
        }
        return Integer.parseInt(waitVisible(cartBadge).getText().trim());
    }
}

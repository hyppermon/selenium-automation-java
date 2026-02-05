package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private final By cartItem = By.cssSelector("[data-test='inventory-item']");
    private final By removeBackpackBtn = By.cssSelector("[class='btn btn_secondary btn_small cart_button']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isEmpty() {
        return wait.until(driver ->
                driver.findElements(cartItem).isEmpty()
        );
    }

    public boolean hasAnyItem() {
        return !driver.findElements(cartItem).isEmpty();
    }

    public void removeBackpack() {
        click(removeBackpackBtn);
        wait.until(driver -> driver.findElements(removeBackpackBtn).isEmpty());
    }

    private final By checkoutButton = By.cssSelector("[data-test='checkout']");

    public CheckoutStepOnePage checkout() {
        click(checkoutButton);
        return new CheckoutStepOnePage(driver);
    }

}
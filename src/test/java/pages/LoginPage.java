package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameInput = By.cssSelector("[data-test='username']");
    private final By passwordInput = By.cssSelector("[data-test='password']");
    private final By loginButton   = By.cssSelector("[data-test='login-button']");
    private final By loginContainer = By.cssSelector("[data-test='login-container']");
    private final By errorContainer = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open(String baseUrl) {
        driver.get(baseUrl);
        return this;
    }

    public boolean isLoaded() {
        return waitVisible(loginContainer).isDisplayed();
    }

    public void login(String username, String password) {
        waitVisible(usernameInput).sendKeys(username);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public boolean isErrorVisible() {
        return waitVisible(errorContainer).isDisplayed();
    }

    public String getErrorMessage() {
        return waitVisible(errorContainer).getText();
    }

    public InventoryPage loginExpectSuccess(String username, String password) {
        login(username, password);
        return new InventoryPage(driver);
    }

}


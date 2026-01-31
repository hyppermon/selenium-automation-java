package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Locale;

public final class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver createDriver(String browser, boolean headless) {
        String b = (browser == null ? "chrome" : browser).toLowerCase(Locale.ROOT);

        return switch (b) {    //switch -> no if for extra Firefox or Edge update
            case "chrome" -> createChrome(headless);
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }

    private static WebDriver createChrome(boolean headless) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        return new ChromeDriver(options);
    }
}

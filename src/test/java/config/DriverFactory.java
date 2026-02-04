package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class DriverFactory {

    private DriverFactory() {}

    public static WebDriver createDriver(String browser, boolean headless) {
        String b = (browser == null ? "chrome" : browser).toLowerCase(Locale.ROOT);

        return switch (b) {
            case "chrome" -> createChrome(headless);
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }

    private static WebDriver createChrome(boolean headless) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        Path profileDir;
        try {
            profileDir = Files.createTempDirectory("selenium-chrome-profile-");
        } catch (IOException e) {
            throw new RuntimeException("Failed to create temp profile dir for Chrome", e);
        }
        options.addArguments("--user-data-dir=" + profileDir.toAbsolutePath());
        options.addArguments("--profile-directory=Default");

        options.addArguments("--disable-features=PasswordManagerRedesign,PasswordLeakDetection,AutofillServerCommunication");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--no-first-run");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--incognito");

        if (headless) {
            options.addArguments("--headless=new");
        }

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        return new ChromeDriver(options);
    }
}

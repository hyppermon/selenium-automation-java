package smoke;

import base.BaseTest;
import config.ConfigLoader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SeleniumSmokeTest extends BaseTest {

    @Test
    void shouldOpenBaseUrl() {
        driver.get(ConfigLoader.getBaseUrl());
        assertTrue(driver.getTitle().contains("Swag Labs"));
    }
}

package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigLoader {

    private static final String CONFIG_FILE = "config.properties";
    private static final Properties PROPS = new Properties();

    static {
        try (InputStream is = ConfigLoader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (is == null) {
                throw new IllegalStateException("Missing " + CONFIG_FILE + " in src/test/resources");
            }
            PROPS.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + CONFIG_FILE, e);
        }
    }

    private ConfigLoader() {}

    public static String getBrowser() {
        return get("browser", "chrome");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(get("headless", "false"));
    }

    public static String getBaseUrl() {
        return get("baseUrl", "https://www.saucedemo.com/");
    }

    private static String get(String key, String defaultValue) {
        String sys = System.getProperty(key);
        if (sys != null && !sys.isBlank()) {
            return sys.trim();
        }
        return PROPS.getProperty(key, defaultValue).trim();
    }
}

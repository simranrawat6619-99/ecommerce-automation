package com.ecommerce.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties props = new Properties();

    static {
        // Use a try-with-resources statement to automatically close the stream
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            
            if (inputStream == null) {
                // This provides a clearer error if the file is truly missing from the classpath
                throw new RuntimeException("config.properties not found in the classpath");
            }

            // Load the properties from the input stream
            props.load(inputStream);

        } catch (Exception e) {
            // This will catch any IOExceptions during loading or the RuntimeException if the file is not found
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}

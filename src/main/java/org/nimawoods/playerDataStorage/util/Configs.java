package org.nimawoods.playerDataStorage.util;

public class Configs {

    // Hibernate properties
    public String hibernate_dialect;
    public String hibernate_connection_driver_class;
    public String hibernate_connection_url;
    public String hibernate_connection_username;
    public String hibernate_connection_password;

    // HikariCP properties
    public int hibernate_hikari_minimumIdle;
    public int hibernate_hikari_maximumPoolSize;
    public int hibernate_hikari_idleTimeout;
    public int hibernate_hikari_maxLifetime;
    public int hibernate_hikari_connectionTimeout;

    /**
     * Loads properties from a Java Util Properties object into this configuration object.
     * Handles type conversion and provides basic error handling.
     *
     * @param props The properties to load
     * @throws IllegalArgumentException if required properties are missing or invalid
     */
    public void load(java.util.Properties props) {
        if (props == null) {
            throw new IllegalArgumentException("Properties object cannot be null");
        }

        try {
            // Required Hibernate properties
            hibernate_dialect = getRequiredString(props, "hibernate.dialect");
            hibernate_connection_driver_class = getRequiredString(props, "hibernate.connection.driver_class");
            hibernate_connection_url = getRequiredString(props, "hibernate.connection.url");
            hibernate_connection_username = getRequiredString(props, "hibernate.connection.username");
            hibernate_connection_password = getRequiredString(props, "hibernate.connection.password");

            // HikariCP properties with defaults
            hibernate_hikari_minimumIdle = getIntProperty(props, "hibernate.hikari.minimumIdle", 5);
            hibernate_hikari_maximumPoolSize = getIntProperty(props, "hibernate.hikari.maximumPoolSize", 20);
            hibernate_hikari_idleTimeout = getIntProperty(props, "hibernate.hikari.idleTimeout", 30000);
            hibernate_hikari_maxLifetime = getIntProperty(props, "hibernate.hikari.maxLifetime", 2000000);
            hibernate_hikari_connectionTimeout = getIntProperty(props, "hibernate.hikari.connectionTimeout", 30000);

        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to load properties: " + e.getMessage(), e);
        }
    }

    private String getRequiredString(java.util.Properties props, String key) {
        String value = props.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Required property '" + key + "' is missing or empty");
        }
        return value.trim();
    }

    private int getIntProperty(java.util.Properties props, String key, int defaultValue) {
        String value = props.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid integer value for property '" + key + "': " + value, e);
        }
    }
}

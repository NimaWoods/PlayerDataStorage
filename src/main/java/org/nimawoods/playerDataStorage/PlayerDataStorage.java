package org.nimawoods.playerDataStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.bukkit.plugin.java.JavaPlugin;
import org.nimawoods.playerDataStorage.connections.Connection;
import org.nimawoods.playerDataStorage.util.Configs;

public final class PlayerDataStorage extends JavaPlugin {

	@Override
	public void onEnable() {
		createDefaultProperties();

		Properties props = loadProperties();

		Configs config = new Configs();
		config.load(props);

		Connection connection = new Connection();
		connection.connect(props);
	}

	private void createDefaultProperties() {
		if (!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		}

		File configFile = new File(getDataFolder(), "config.properties");
		if (!configFile.exists()) {
			saveResource("config.properties", false);
		}
	}

	private Properties loadProperties() {
		Properties properties = new Properties();
		File configFile = new File(getDataFolder(), "config.properties");

		try (FileInputStream fis = new FileInputStream(configFile)) {
			properties.load(fis);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load config.properties", e);
		}

		return properties;
	}
}

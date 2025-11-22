package org.nimawoods.playerDataStorage.connections;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Connection {

	private SessionFactory factory;

	public Connection() {
	}

	public void connect(Properties properties) {
		Configuration configuration = new Configuration();

		configuration = getConfiguration(properties);

		try {
			SessionFactory factory = configuration.buildSessionFactory();
		} catch (HibernateException e) {
			throw new RuntimeException("Failed to initialize database connection.", e);
		}
	}

	private Configuration getConfiguration(Properties properties) {
		Configuration configuration = new Configuration();



		return configuration;
	}
}

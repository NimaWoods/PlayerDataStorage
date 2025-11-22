package org.nimawoods.playerDataStorage.entities;

import org.bukkit.Location;
import org.bukkit.inventory.PlayerInventory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "players")
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String uuid;

	@Column(nullable = false)
	Location BedSpawnLocation;

	@Column(nullable = false)
	PlayerInventory Inventory;
}

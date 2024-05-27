package net.rakheros.hometaker;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.rakheros.hometaker.item.ModItemGroups;
import net.rakheros.hometaker.item.ModItems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hometaker implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "hometaker";
	@Deprecated
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.register();
		ModItemGroups.register();
	}

	public static Identifier makeID (String name) {
		return Identifier.of(MOD_ID, name);
	}
}

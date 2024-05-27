package net.rakheros.hometaker.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.rakheros.hometaker.Hometaker;
import net.rakheros.hometaker.item.custom.HometakerItem;

public class ModItems {
    public static void register () {
        Hometaker.LOGGER.info("test");
    }

    public static final Item HOMETAKER = Registry.register(
            Registries.ITEM,
            Hometaker.makeID("hometaker"),
            new HometakerItem(new FabricItemSettings())
        );
}

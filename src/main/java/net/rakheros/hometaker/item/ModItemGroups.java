package net.rakheros.hometaker.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;

public class ModItemGroups {
    public static void register () {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(ModItems.HOMETAKER);
        });
    }
}

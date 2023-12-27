package net.teamabyssal.item.categories;

import net.minecraft.world.item.Item;
import net.teamabyssal.registry.ItemRegistry;

public class BaseItem extends Item {
    public BaseItem(Properties pProperties) {
        super(pProperties);
        ItemRegistry.DROP_LOOT_ITEMS.add(this);
    }
}

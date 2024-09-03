package dev.itsmeow.whisperwoods.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.itsmeow.whisperwoods.WhisperwoodsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

public class ModCreativeTabs {

    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(WhisperwoodsMod.MODID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> WHISPERWOODS = CREATIVE_MODE_TABS.register("whisperwoods", () ->
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, 7)
                    .title(Component.translatable("itemGroup.whisperwoods.main"))
                    .icon(() -> new ItemStack(ModItems.GHOST_LIGHT_FIERY_ORANGE.get()))
                    .displayItems((itemDisplayParameters, output) -> {
                        for(Map.Entry<ResourceKey<Item>, Item> item : ModItems.getTabItems()){
                            output.accept(item.getValue());
                        }
                        ModEntities.getEntities().values().forEach(cont -> output.accept(new ItemStack(cont.getEggItem().get())));
                    }).build());

    public static void init() {
        CREATIVE_MODE_TABS.register();
    }
}

package dev.itsmeow.whisperwoods.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public abstract class ItemBlockModeledArmor extends ItemBlockArmor {

    public ItemBlockModeledArmor(Block block, ArmorMaterial material, ArmorItem.Type slot, Item.Properties properties) {
        super(block, material, slot, properties);
    }

    // Referenced by ItemModeledArmorMixin and Fabric
    @Environment(EnvType.CLIENT)
    public <T extends LivingEntity, A extends HumanoidModel<T>> A getArmorModel(T entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, A defaultModel) {
        if (itemStack != null) {
            if (itemStack.getItem() instanceof ArmorItem && armorSlot != null) {
                A armorModel = this.getBaseModelInstance();
                armorModel = displays(armorModel, armorSlot);
                if (defaultModel != null) {
                    defaultModel.copyPropertiesTo(armorModel);
                }
                armorModel.setupAnim(entityLiving, 0, 0, 0, 0, 0);
                return armorModel;
            }
        }
        return null;
    }

    @Environment(EnvType.CLIENT)
    protected abstract <A extends HumanoidModel<?>> A getBaseModelInstance();

    @Environment(EnvType.CLIENT)
    protected abstract <A extends HumanoidModel<?>> A displays(A armorModel, EquipmentSlot slot);

}

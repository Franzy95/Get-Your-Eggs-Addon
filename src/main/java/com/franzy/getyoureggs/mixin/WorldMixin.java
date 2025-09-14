package com.franzy.getyoureggs.mixin;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityChicken;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mixin(World.class)
public class WorldMixin {

    private final Set<Integer> announcedEggs = new HashSet<>();

    @Inject(method = "spawnEntityInWorld", at = @At("RETURN"))
    private void onSpawnEntityInWorld(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (!(entity instanceof EntityItem)) return;

        EntityItem item = (EntityItem) entity;

        if (item.getEntityItem() == null) return;
        if (item.getEntityItem().itemID != Item.egg.itemID) return;

        int eggId = item.entityId;
        if (announcedEggs.contains(eggId)) return;

        World world = item.worldObj;
        if (world == null || world.isRemote) return;

        List<EntityChicken> nearby = world.getEntitiesWithinAABB(
                EntityChicken.class,
                item.boundingBox.expand(1.5D, 1.5D, 1.5D)
        );

        if (nearby == null || nearby.isEmpty()) {
            return;
        }

        for (Object pObj : world.playerEntities) {
            if (pObj instanceof EntityPlayer) {
                ((EntityPlayer) pObj).addChatMessage("A chicken laid an egg!");
            }
        }

        announcedEggs.add(eggId);

        if (announcedEggs.size() > 5000) {
            announcedEggs.clear();
        }
    }
}









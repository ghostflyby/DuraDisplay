package com.caedis.duradisplay.overlay;

import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.NotNull;

import com.caedis.duradisplay.config.ConfigCharge;
import com.caedis.duradisplay.config.ConfigDurabilityLike;

import cofh.api.energy.IEnergyContainerItem;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import tconstruct.library.tools.ToolCore;

public class OverlayCharge extends OverlayDurabilityLike {

    public OverlayCharge() {
        super(new ConfigCharge());
        addHandler(IElectricItem.class, OverlayCharge::handleIElectricItem);
        addHandler(ToolCore.class, OverlayCharge::handleToolCore);
        addHandler(IEnergyContainerItem.class, OverlayCharge::handleEnergyContainer);
    }

    @Override
    ConfigDurabilityLike config() {
        return config;
    }

    private static DurabilityLikeInfo handleIElectricItem(@NotNull ItemStack stack) {
        IElectricItem bei = ((IElectricItem) stack.getItem());
        assert bei != null;

        return new DurabilityLikeInfo(ElectricItem.manager.getCharge(stack), bei.getMaxCharge(stack));
    }

    private static DurabilityLikeInfo handleEnergyContainer(@NotNull ItemStack stack) {

        IEnergyContainerItem eci = ((IEnergyContainerItem) stack.getItem());
        assert eci != null;

        return new DurabilityLikeInfo(eci.getEnergyStored(stack), eci.getMaxEnergyStored(stack));
    }

    private static DurabilityLikeInfo handleToolCore(@NotNull ItemStack stack) {

        if (!stack.hasTagCompound() && !stack.getTagCompound()
            .hasKey("Energy")) return null;
        return handleEnergyContainer(stack);
    }
}

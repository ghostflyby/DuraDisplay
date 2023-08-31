package com.caedis.duradisplay.config;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

import com.caedis.duradisplay.Tags;

import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;

@SuppressWarnings("unused")
public class GuiConfigDuraDisplay extends GuiConfig {

    public GuiConfigDuraDisplay(GuiScreen parent) {
        super(
            parent,
            getCategories(),
            Tags.MODID,
            "general",
            false,
            false,
            getAbridgedConfigPath(DuraDisplayConfig.config.toString()));
    }

    private static List<IConfigElement> getCategories() {
        var list = new ArrayList<IConfigElement>();
        list.add(new ConfigElement<>(DuraDisplayConfig.config.getCategory(Configuration.CATEGORY_GENERAL)));
        for (var c : ConfigInfo.getCategories()) {
            list.add(new ConfigElement<>(DuraDisplayConfig.config.getCategory(c)));
        }
        return list;
    }

}

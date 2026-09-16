package troll.client.module.movement;

import net.minecraft.client.Minecraft;
import troll.client.module.Categories;
import troll.client.module.Module;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", Categories.MOVEMENT);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        Minecraft mc = Minecraft.getMinecraft();

        if (mc != null && mc.thePlayer != null) {
            mc.thePlayer.setSprinting(false);
        }
    }

    @Override
    public void onUpdate() {
        Minecraft mc = Minecraft.getMinecraft();

        if (mc != null && mc.thePlayer != null) {
            mc.thePlayer.setSprinting(true);
        }
    }
}
package troll.client.module.movement;

import net.minecraft.client.Minecraft;
import troll.client.module.Categories;
import troll.client.module.Module;

public class Fly extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();

    public Fly() {
        super("Fly", Categories.MOVEMENT);
    }

    @Override
    public void onEnable() {
        if (mc == null || mc.thePlayer == null) {
            return;
        }

        mc.thePlayer.capabilities.isFlying = true;
    }

    @Override
    public void onDisable() {
        if (mc == null || mc.thePlayer == null) {
            return;
        }

        mc.thePlayer.capabilities.isFlying = false;
    }

    @Override
    public void onTick() {
        if (mc == null || mc.thePlayer == null) {
            return;
        }

        mc.thePlayer.capabilities.isFlying = true;
    }
}
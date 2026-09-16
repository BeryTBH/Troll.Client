package troll.client.module.player;

import net.minecraft.client.Minecraft;
import troll.client.module.Categories;
import troll.client.module.Module;

public class AntiAFK extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();

    private int ticks;

    public AntiAFK() {
        super("AntiAFK", Categories.PLAYER);
    }

    @Override
    public void onEnable() {
        ticks = 0;
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onTick() {
        if (mc == null || mc.thePlayer == null) {
            return;
        }

        ticks++;

        if (ticks >= 100) {
            mc.thePlayer.rotationYaw += 5.0F;
            ticks = 0;
        }
    }
}
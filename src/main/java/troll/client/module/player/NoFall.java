package troll.client.module.player;

import net.minecraft.client.Minecraft;
import troll.client.module.Categories;
import troll.client.module.Module;

public class NoFall extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();

    public NoFall() {
        super("NoFall", Categories.PLAYER);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onTick() {
        if (mc == null || mc.thePlayer == null) {
            return;
        }

        if (mc.thePlayer.fallDistance > 2.0F) {
            mc.thePlayer.fallDistance = 0.0F;
        }
    }
}
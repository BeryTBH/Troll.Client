package troll.client.module.player;

import net.minecraft.client.Minecraft;
import troll.client.module.Categories;
import troll.client.module.Module;

public class FastPlace extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();

    public FastPlace() {
        super("FastPlace", Categories.PLAYER);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        if (mc == null) {
            return;
        }

        mc.setRightClickDelayTimer(4);
    }

    @Override
    public void onTick() {
        if (mc == null) {
            return;
        }

        mc.setRightClickDelayTimer(0);
    }
}
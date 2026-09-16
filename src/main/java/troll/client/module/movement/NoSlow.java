package troll.client.module.movement;

import net.minecraft.client.Minecraft;
import troll.client.module.Categories;
import troll.client.module.Module;

public class NoSlow extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();

    public NoSlow() {
        super("NoSlow", Categories.MOVEMENT);
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

        if (mc.thePlayer.isUsingItem()) {
            mc.thePlayer.movementInput.moveStrafe *= 5.0F;
            mc.thePlayer.movementInput.moveForward *= 5.0F;
        }
    }
}
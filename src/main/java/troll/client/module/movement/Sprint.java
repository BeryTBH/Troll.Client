package troll.client.module.movement;

import net.minecraft.client.Minecraft;

import troll.client.module.Categories;
import troll.client.module.Module;

public class Sprint extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();

    public Sprint() {
        super("Sprint", Categories.MOVEMENT);
    }

    @Override
    public void onTick() {
        if (mc.thePlayer == null) {
            return;
        }

        if (mc.thePlayer.movementInput.moveForward > 0.0F) {
            mc.thePlayer.setSprinting(true);
        }
    }

    @Override
    public void onDisable() {
        if (mc.thePlayer != null) {
            mc.thePlayer.setSprinting(false);
        }
    }
}
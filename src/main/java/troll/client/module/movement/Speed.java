package troll.client.module.movement;

import net.minecraft.client.Minecraft;

import troll.client.module.Categories;
import troll.client.module.Module;

public class Speed extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();

    public Speed() {
        super("Speed", Categories.MOVEMENT);
    }

    @Override
    public void onTick() {

        if (mc.thePlayer == null) {
            return;
        }

        // Speed logic goes here.
    }
}
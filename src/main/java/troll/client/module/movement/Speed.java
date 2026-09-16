package troll.client.module.movement;

import net.minecraft.client.Minecraft;

import net.minecraft.client.Minecraft;
import troll.client.module.Categories;
import troll.client.module.Module;

public class Speed extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();

    private static final double SPEED = 0.8D;

    public Speed() {
        super("Speed", Categories.MOVEMENT);
    }

    @Override
    public void onTick() {
        if (mc.thePlayer == null) {
            return;
        }

        if (mc.thePlayer.moveForward == 0.0F &&
            mc.thePlayer.moveStrafing == 0.0F) {
            return;
        }

        double yaw = Math.toRadians(mc.thePlayer.rotationYaw);

        double forward = mc.thePlayer.moveForward;
        double strafe = mc.thePlayer.moveStrafing;

        double length = Math.sqrt(forward * forward + strafe * strafe);

        if (length > 0.0D) {
            forward /= length;
            strafe /= length;
        }

        double motionX = (forward * -Math.sin(yaw) + strafe * Math.cos(yaw)) * SPEED;
        double motionZ = (forward * Math.cos(yaw) + strafe * Math.sin(yaw)) * SPEED;

        mc.thePlayer.motionX = motionX;
        mc.thePlayer.motionZ = motionZ;
    }

    @Override
    public void onDisable() {
        if (mc.thePlayer != null) {
            mc.thePlayer.motionX = 0.0D;
            mc.thePlayer.motionZ = 0.0D;
        }
    }
}
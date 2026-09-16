package troll.client.gui;

import java.io.IOException;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;

import troll.client.managers.ModuleManager;
import troll.client.module.Categories;
import troll.client.module.Module;

public class ClickGUI extends GuiScreen {
    private static final int ACCENT = 0xFFFF0055;
    private static final int BACKGROUND = 0xAA000000;
    private static final int MODULE_BG = 0xDD080808;
    private static final int HOVER = 0xFF202020;
    private static final int ENABLED = 0xFFFF0055;
    private static final int TEXT = 0xFFFFFFFF;
    private static final int SUBTEXT = 0xFFAAAAAA;

    private final Categories[] categories = {
        Categories.MOVEMENT,
        Categories.COMBAT,
        Categories.RENDER,
        Categories.WORLD,
        Categories.PLAYER,
        Categories.MISC
    };

    private final String[] categoryNames = {
        "Movement",
        "Combat",
        "Render",
        "World",
        "Player",
        "Misc"
    };

    private final ItemStack[] categoryIcons = {
        new ItemStack(Items.feather), // Movement
        new ItemStack(Items.diamond_sword), // Combat
        new ItemStack(Items.ender_eye), // Render
        new ItemStack(Items.compass), // World
        new ItemStack(Items.apple), // Player
        new ItemStack(Items.redstone) // Misc
    };

    @Override
    public void initGui() {
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawRect(0, 0, width, height, BACKGROUND);

        ModuleManager manager = mc.moduleManager;

        int panelWidth = 110;
        int moduleHeight = 16;
        int headerHeight = 16;
        int gap = 8;

        int totalWidth = categories.length * panelWidth + (categories.length - 1) * gap;

        int startX = (width - totalWidth) / 2;
        int startY = 25;

        for (int i = 0; i < categories.length; i++) {
            Categories category = categories[i];

            int x = startX + i * (panelWidth + gap);
            int y = startY;

            drawRect(
                x,
                y,
                x + panelWidth,
                y + headerHeight,
                ACCENT
            );

            mc.getRenderItem().renderItemAndEffectIntoGUI(
                categoryIcons[i],
                x + 3,
                y
            );

            drawString(
                fontRendererObj,
                categoryNames[i],
                x + 22,
                y + 4,
                TEXT
            );

            y += headerHeight + 3;

            List<Module> modules =
                manager.getModulesByCategory(category);

            for (Module module : modules) {
                boolean hovered = mouseX >= x && mouseX <= x + panelWidth && mouseY >= y && mouseY <= y + moduleHeight;

                int color;

                if (module.isEnabled()) {
                    color = ENABLED;
                } else if (hovered) {
                    color = HOVER;
                } else {
                    color = MODULE_BG;
                }

                drawRect(
                    x,
                    y,
                    x + panelWidth,
                    y + moduleHeight,
                    color
                );

                drawString(
                    fontRendererObj,
                    module.getName(),
                    x + 4,
                    y + 4,
                    TEXT
                );

                y += moduleHeight + 1;
            }
        }

        drawString(
            fontRendererObj,
            "TROLL CLIENT",
            8,
            8,
            ACCENT
        );

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        ModuleManager manager = mc.moduleManager;

        int panelWidth = 110;
        int moduleHeight = 16;
        int headerHeight = 16;
        int gap = 8;

        int totalWidth = categories.length * panelWidth + (categories.length - 1) * gap;

        int startX = (width - totalWidth) / 2;
        int startY = 25;

        for (Categories category : categories) {
            int categoryIndex = getCategoryIndex(category);

            int x = startX + categoryIndex * (panelWidth + gap);

            int y = startY + headerHeight + 3;

            List<Module> modules = manager.getModulesByCategory(category);

            for (Module module : modules) {
                if (
                    mouseX >= x && mouseX <= x + panelWidth && mouseY >= y && mouseY <= y + moduleHeight) {
                    if (mouseButton == 0) {
                        module.toggle();
                    }

                    return;
                }

                y += moduleHeight + 1;
            }
        }
    }

    private int getCategoryIndex(Categories category) {
        for (int i = 0; i < categories.length; i++) {
            if (categories[i] == category) {
                return i;
            }
        }

        return 0;
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
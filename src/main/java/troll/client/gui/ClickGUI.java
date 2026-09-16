package troll.client.gui;

import java.util.List;

import net.minecraft.client.gui.GuiScreen;

import troll.client.managers.ModuleManager;
import troll.client.module.Categories;
import troll.client.module.Module;

public class ClickGUI extends GuiScreen {
    private static final int RED = 0xFFAA0000;
    private static final int DARK = 0xFF111111;
    private static final int PANEL = 0xFF191919;
    private static final int HOVER = 0xFF292929;
    private static final int ENABLED = 0xFFAA0000;

    private final String[] categories = {
        "Player",
        "Render",
        "Misc",
        "Combat",
        "Movement",
        "World"
    };

    private int selectedCategory = 0;

    @Override
    public void initGui() {
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawRect(
            0,
            0,
            width,
            height,
            0x99000000
        );

        int panelX = 12;
        int panelY = 20;
        int panelW = 600;
        int panelH = 300;

        drawRect(
            panelX,
            panelY,
            panelX + panelW,
            panelY + panelH,
            DARK
        );

        drawRect(
            panelX,
            panelY,
            panelX + panelW,
            panelY + 25,
            RED
        );

        drawCenteredString(
            fontRendererObj,
            "TROLL CLIENT",
            panelX + panelW / 2,
            panelY + 8,
            0xFFFFFFFF
        );

        int catX = panelX + 5;

        for (int i = 0; i < categories.length; i++) {
            int catW = 95;

            boolean hover = mouseX >= catX && mouseX <= catX + catW && mouseY >= panelY + 30 && mouseY <= panelY + 55;

            int color;

            if (i == selectedCategory) {
                color = RED;
            } else if (hover) {
                color = HOVER;
            } else {
                color = PANEL;
            }

            drawRect(
                catX,
                panelY + 30,
                catX + catW,
                panelY + 55,
                color
            );

            drawCenteredString(
                fontRendererObj,
                categories[i],
                catX + catW / 2,
                panelY + 38,
                0xFFFFFFFF
            );

            catX += catW + 2;
        }

        drawModules(mouseX, mouseY, panelX, panelY);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private Categories getSelectedCategory() {
        switch (selectedCategory) {
            case 0:
                return Categories.PLAYER;
            case 1:
                return Categories.RENDER;
            case 2:
                return Categories.MISC;
            case 3:
                return Categories.COMBAT;
            case 4:
                return Categories.MOVEMENT;
            case 5:
                return Categories.WORLD;
            default:
                return Categories.PLAYER;
        }
    }

    private void drawModules(int mouseX, int mouseY, int panelX, int panelY) {
        Categories category = getSelectedCategory();

        ModuleManager manager = mc.moduleManager;

        List<Module> modules = manager.getModulesByCategory(category);

        int x = panelX + 15;
        int y = panelY + 70;

        for (Module module : modules) {

            boolean hover =
                mouseX >= x &&
                mouseX <= x + 250 &&
                mouseY >= y &&
                mouseY <= y + 22;

            int color;

            if (module.isEnabled()) {
                color = ENABLED;
            } else if (hover) {
                color = HOVER;
            } else {
                color = PANEL;
            }

            drawRect(
                x,
                y,
                x + 250,
                y + 22,
                color
            );

            drawString(
                fontRendererObj,
                module.getName(),
                x + 8,
                y + 7,
                0xFFFFFFFF
            );

            y += 26;
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        int panelX = 12;
        int panelY = 20;

        int catX = panelX + 5;

        for (int i = 0; i < categories.length; i++) {

            int catW = 95;

            if (
                mouseX >= catX &&
                mouseX <= catX + catW &&
                mouseY >= panelY + 30 &&
                mouseY <= panelY + 55
            ) {

                selectedCategory = i;
                return;
            }

            catX += catW + 2;
        }

        // Module clicks
        Categories category = getSelectedCategory();

        List<Module> modules =
            mc.moduleManager.getModulesByCategory(category);

        int x = panelX + 15;
        int y = panelY + 70;

        for (Module module : modules) {

            if (
                mouseX >= x &&
                mouseX <= x + 250 &&
                mouseY >= y &&
                mouseY <= y + 22
            ) {

                module.toggle();
                return;
            }

            y += 26;
        }

        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
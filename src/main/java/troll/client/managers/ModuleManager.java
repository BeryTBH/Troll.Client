package troll.client.managers;

import java.util.ArrayList;
import java.util.List;

import troll.client.module.Module;
import troll.client.module.movement.Sprint;
import troll.client.module.movement.Speed;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<Module>();

    public ModuleManager() {
        registerModules();
    }

    private void registerModules() {
        modules.add(new Sprint());
        modules.add(new Speed());
    }

    public void onTick() {
        for (Module module : modules) {
            if (module.isEnabled()) {
                try {
                    module.onTick();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Module> getModules() {
        return modules;
    }

    public Module getModule(String name) {
        for (Module module : modules) {
            if (module.getName().equalsIgnoreCase(name)) {
                return module;
            }
        }

        return null;
    }

    public List<Module> getModulesByCategory(troll.client.module.Categories category) {
        List<Module> result = new ArrayList<Module>();

        for (Module module : modules) {
            if (module.getCategory() == category) {
                result.add(module);
            }
        }

        return result;
    }
}
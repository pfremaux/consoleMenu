package menu.example;

import menu.DynMenu;
import menu.MenufiedAction;
import menu.example.pocsubmenu.SubMenuChoice;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Map;

public class PocSubMenu implements MenufiedAction {

    @Override
    public String title() {
        return "Poc title for submenu";
    }

    @Override
    public void wizardAction(Map<String, Object> context, Map<String, Object> parameters) {
        System.out.println(" wizard menu action");
        final DynMenu dynMenu;
        try {
            dynMenu = new DynMenu(SubMenuChoice.class);
            dynMenu.launchMenu();
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void action(Map<String, Object> context, Map<String, Object> parameters) {
        System.out.println(" menu action");
    }

    @Override
    public Map<String, Object> expectedParameters() {
        return Collections.emptyMap();
    }
}

package menu.main;

import menu.DynMenu;
import menu.MenufiedAction;
import menu.basic.DisplayText;
import menu.file.SaveStepList;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class FileUsage implements MenufiedAction {

    @Override
    public String title() {
        return "File tools";
    }

    @Override
    public void wizardAction(Map<String, Object> context, Map<String, Object> parameters) {
        try {
            final DynMenu dynMenu = new DynMenu(SaveStepList.class);
            dynMenu.launchMenu(true, context);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void action(Map<String, Object> context, Map<String, Object> parameters) {

    }

}

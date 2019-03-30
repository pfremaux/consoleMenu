package menu.main;

import menu.DynMenu;
import menu.MenufiedAction;
import menu.web.GoToUrl;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUsage implements MenufiedAction {

    @Override
    public String title() {
        return "Web tools";
    }

    @Override
    public void wizardAction(Map<String, Object> context, Map<String, Object> parameters) {
        try {
            final DynMenu dynMenu = new DynMenu(GoToUrl.class);
            dynMenu.launchMenu(true, context);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void action(Map<String, Object> context, Map<String, Object> parameters) {

    }

}

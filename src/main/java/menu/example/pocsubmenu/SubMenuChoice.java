package menu.example.pocsubmenu;

import menu.MenufiedAction;

import java.util.Collections;
import java.util.Map;

public class SubMenuChoice implements MenufiedAction {

    @Override
    public String title() {
        return "little choice";
    }

    @Override
    public void wizardAction(Map<String, Object> context, Map<String, Object> parameters) {
        System.out.println("wizard sub menu action");
    }

    @Override
    public void action(Map<String, Object> context, Map<String, Object> parameters) {
        System.out.println("sub menu action");
    }

    @Override
    public Map<String, Object> expectedParameters() {
        return Collections.emptyMap();
    }
}

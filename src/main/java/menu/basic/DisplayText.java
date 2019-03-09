package menu.basic;

import menu.MenufiedAction;
import menu.basic.utils.BasicUtils;
import menu.model.Step;
import menu.utils.ContextUtils;

import java.util.HashMap;
import java.util.Map;

public class DisplayText implements MenufiedAction {

    @Override
    public String title() {
        return "Display a text.";
    }

    @Override
    public void wizardAction(Map<String, Object> context, Map<String, Object> parameters) {
        final Map<String, Object> params = new HashMap<>();
        System.out.println("Which text do you want to display ?");
        final String message = BasicUtils.readString();
        params.put("msg", message);
        ContextUtils.getStepList(context).add(new Step(this.getClass().getName(), params));
    }

    @Override
    public void action(Map<String, Object> context, Map<String, Object> parameters) {
        final String message = (String) parameters.get("msg");
        System.out.println(message);
    }

}

package menu.basic;

import menu.MenufiedAction;
import menu.basic.utils.BasicUtils;
import menu.model.Step;
import menu.utils.ContextUtils;

import java.util.HashMap;
import java.util.Map;

public class AskString implements MenufiedAction {

    @Override
    public String title() {
        return "Ask a text.";
    }

    @Override
    public void wizardAction(Map<String, Object> context, Map<String, Object> parameters) {
        final Map<String, Object> params = new HashMap<>();
        System.out.println("What message do you want to display before ?");
        final String message = BasicUtils.readString();
        params.put("msg", message);
        System.out.println("What key for storing its answer ?");
        final String keyAnswer = BasicUtils.readString();
        params.put("keyAnswer", keyAnswer);
        ContextUtils.getStepList(context).add(new Step(this.getClass().getName(), params));
    }

    @Override
    public void action(Map<String, Object> context, Map<String, Object> parameters) {
        final String message = (String) parameters.get("msg");
        System.out.println(message);
        final String answer = BasicUtils.readString();
        final String keyAnswer = (String) parameters.get("keyAnswer");
        context.put(keyAnswer, answer);
    }

}

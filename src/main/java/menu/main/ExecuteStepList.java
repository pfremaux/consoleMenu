package menu.main;

import menu.MenufiedAction;
import menu.model.Step;
import menu.utils.ContextUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class ExecuteStepList implements MenufiedAction {

    @Override
    public String title() {
        return "Execute all actions stored in memory.";
    }

    @Override
    public void wizardAction(Map<String, Object> context, Map<String, Object> parameters) {
        action(context, parameters);
    }

    @Override
    public void action(Map<String, Object> context, Map<String, Object> parameters) {
        final List<Step> stepList = ContextUtils.getStepList(context);
        boolean failure = false;
        for (Step step : stepList) {
            final String actionName = step.getActionName();
            if (failure && !step.isCanResumePreviousFailure()) {
                continue;
            }
            final Map<String, Object> parameters1 = step.getParameters();
            try {
                final Class<? extends MenufiedAction> aClass = (Class<? extends MenufiedAction>) Class.forName(actionName);
                final Constructor<? extends MenufiedAction> constructor = aClass.getConstructor();
                final MenufiedAction menufiedAction = constructor.newInstance();
                menufiedAction.action(context, parameters1);
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            } catch (Exception e) {
                if (step.isMayFail()) {
                    failure = true;
                } else {
                    throw e;
                }
            }
        }
    }
}

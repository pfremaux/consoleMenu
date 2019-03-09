package menu.model;

import menu.utils.ContextUtils;

import java.util.Map;

public class Step {

    private String actionName;
    private Map<String, Object> parameters;
    private boolean mayFail;
    private boolean canResumePreviousFailure;

    public Step() {

    }

    public Step(String actionName, Map<String, Object> parameters) {
        this.actionName = actionName;
        this.parameters = parameters;
        final boolean mayFail = ContextUtils.booleanFromObject(parameters.get("mayFail"));
        final boolean canResumePreviousFailure = ContextUtils.booleanFromObject(parameters.get("canResumePreviousFailure"));
        this.mayFail = mayFail;
        this.canResumePreviousFailure = canResumePreviousFailure;
        this.parameters.remove("mayFail");
        this.parameters.remove("canResumePreviousFailure");
    }

    public String getActionName() {
        return actionName;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public boolean isMayFail() {
        return mayFail;
    }

    public boolean isCanResumePreviousFailure() {
        return canResumePreviousFailure;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public void setMayFail(boolean mayFail) {
        this.mayFail = mayFail;
    }

    public void setCanResumePreviousFailure(boolean canResumePreviousFailure) {
        this.canResumePreviousFailure = canResumePreviousFailure;
    }
}

package menu.web;

import menu.MenufiedAction;
import menu.model.Step;
import menu.utils.ContextUtils;
import menu.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;

import java.util.Collections;
import java.util.Map;

public class GoToUrl implements MenufiedAction {

    @Override
    public String title() {
        return "Go to a specified URL.";
    }

    @Override
    public void wizardAction(Map<String, Object> context, Map<String, Object> parameters) {
        WebDriver driver = SeleniumUtils.getDriver(context);
        driver.get(parameters.get("fullUrl").toString());
        ContextUtils.getStepList(context).add(new Step(this.getClass().getName(), parameters));
    }

    @Override
    public void action(Map<String, Object> context, Map<String, Object> parameters) {
        WebDriver driver = SeleniumUtils.getDriver(context);
        driver.get(parameters.get("fullUrl").toString());
    }

    @Override
    public Map<String, Object> expectedParameters() {
        return Collections.singletonMap("fullUrl", "string");
    }
}

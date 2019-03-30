package menu.web;

import menu.MenufiedAction;
import menu.model.Step;
import menu.utils.ContextUtils;
import menu.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.Map;

public class ClickWebElement implements MenufiedAction {

    @Override
    public String title() {
        return "Generate a click on a web element. (DEPRECATED ?)";
    }

    @Override
    public void wizardAction(Map<String, Object> context, Map<String, Object> parameters) {
        final WebDriver driver = SeleniumUtils.getDriver(context);
        final WebElement element = SeleniumUtils.getElement(driver, context);
        // parameters.put("variableName", context.get("webElementName"));
        parameters.put(SelectElement.WEB_ELEMENT, context.get(SelectElement.WEB_ELEMENT));
        element.click();
        ContextUtils.getStepList(context).add(new Step(this.getClass().getName(), parameters));
    }

    @Override
    public void action(Map<String, Object> context, Map<String, Object> parameters) {
        final WebDriver driver = SeleniumUtils.getDriver(context);
        final WebElement element = SeleniumUtils.getElement(driver, parameters);
        element.click();
    }

    @Override
    public Map<String, Object> expectedParameters() {
        return Collections.singletonMap(SelectElement.WEB_ELEMENT_NAME, "string");
    }
}

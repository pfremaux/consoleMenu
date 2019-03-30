package menu.web;

import menu.MenufiedAction;
import menu.model.Step;
import menu.utils.ContextUtils;
import menu.utils.SeleniumUtils;
import menu.utils.SeleniumUtils.InterestingAttributes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ListSimilarLinks implements MenufiedAction {

    public static final String WEB_ELEMENT_NAME = "webElementName";
    public static final String WEB_ELEMENT = "webElement";

    @Override
    public String title() {
        return "Save selected web element.";
    }

    @Override
    public void wizardAction(Map<String, Object> context, Map<String, Object> parameters) {
        final WebDriver driver = SeleniumUtils.getDriver(context);
        WebElement currentElement = driver.switchTo().activeElement();

        final Map<String, Object> mapDetailsElement = new HashMap<>();
        for (InterestingAttributes interestingAttribute : InterestingAttributes.values()) {
            final String attributeName = interestingAttribute.name().toLowerCase();
            final String attribute = currentElement.getAttribute(attributeName);
            if (attribute != null && attribute.trim().length() > 0) {
                mapDetailsElement.put(attributeName, attribute);
            }
        }
        mapDetailsElement.put("tag", currentElement.getTagName());
        final String nameChosen = parameters.remove(WEB_ELEMENT_NAME).toString();
        parameters.put(WEB_ELEMENT, mapDetailsElement);
        context.put(nameChosen, mapDetailsElement);
        ContextUtils.getStepList(context).add(new Step(this.getClass().getName(), parameters));

    }

    @Override
    public void action(Map<String, Object> context, Map<String, Object> parameters) {
        final WebDriver driver = SeleniumUtils.getDriver(context);
        WebElement element = SeleniumUtils.getElement(driver, parameters);
        element.click();
    }

    @Override
    public Map<String, Object> expectedParameters() {
        return Collections.singletonMap(WEB_ELEMENT_NAME, "string");
    }
}

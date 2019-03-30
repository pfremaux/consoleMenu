package menu.utils;

import menu.web.ClickWebElement;
import menu.web.SelectElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.Map;

public class SeleniumUtils {

    public static final String WEB_ELELEMT_TAG = "tag";

    public static WebDriver getWebDriver() {
        return new FirefoxDriver();
    }

    public static Object setDriver(Map<String, Object> context, WebDriver webDriver) {
        return context.put("webDriver", webDriver);
    }

    public static WebDriver getDriver(Map<String, Object> context) {
        Object webDriver = context.get("webDriver");
        if (webDriver == null) {
            final WebDriver webDriver1 = getWebDriver();
            webDriver = webDriver1;
            setDriver(context, webDriver1);
        }
        return (WebDriver) webDriver;
    }

    public static void setGeneralizedAnchor(Map<String, Object> context, String value) {
        final String cssSelector = "a[href^=" + value + "]";
        context.put("generalizedCssSelector", cssSelector);
    }

    public static void setUrlsSelected(Map<String, Object> context, List<String> urls) {
        context.put("urlsSelected", urls);
    }

    public static List<String> getUrlsSelected(Map<String, Object> context) {
        return (List<String>) context.get("urlsSelected");
    }

    public enum InterestingAttributes {ID, CLASS, NAME, TYPE, TITLE}

    public static WebElement getElement(WebDriver driver, Map<String, Object> parameters) {
        final WebElement webElement;
        final Map<String, Object> details = (Map<String, Object>) parameters.get(SelectElement.WEB_ELEMENT);

        String startingCssSelector = (String) details.get(WEB_ELELEMT_TAG);
        StringBuilder builder = new StringBuilder(startingCssSelector);
        for (InterestingAttributes interestingAttribute : InterestingAttributes.values()) {
            final String attributeName = interestingAttribute.name().toLowerCase();
            final String s = (String) details.get(attributeName);
            if (s == null) {
                continue;
            }
            builder.append("[");
            builder.append(partialCssSelector(attributeName, s.split(" ")[0]));
            builder.append("]");
        }
        webElement = driver.findElement(By.cssSelector(builder.toString()));

        return webElement;
    }

    public static String partialCssSelector(String name, String value) {
        return name + "^=" + value;
    }
}

package menu;

import java.util.Map;

public interface MenufiedAction {

    String title();

    void wizardAction(Map<String, Object> context, Map<String, Object> parameters);

    void action(Map<String, Object> context, Map<String, Object> parameters);

    Map<String, Object> expectedParameters();

}

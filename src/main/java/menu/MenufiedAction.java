package menu;

import java.util.Collections;
import java.util.Map;

public interface MenufiedAction {

    default String title() {
        return "Choice with a title not overridden.";
    }

    void wizardAction(Map<String, Object> context, Map<String, Object> parameters);

    void action(Map<String, Object> context, Map<String, Object> parameters);

    default Map<String, Object> expectedParameters() {
        return Collections.emptyMap();
    }

}

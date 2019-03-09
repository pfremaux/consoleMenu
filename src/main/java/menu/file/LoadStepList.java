package menu.file;

import menu.MenufiedAction;
import menu.model.Steps;
import menu.utils.ContextUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class LoadStepList implements MenufiedAction {

    @Override
    public String title() {
        return "Load all actions you've done.";
    }

    // TODO https://techgarage.io/index.php/2016/09/17/java-objects-to-yaml/
    @Override
    public void wizardAction(Map<String, Object> context, Map<String, Object> parameters) {
        final String fileName = (String) parameters.get("fileName");
        //final StepConstructor stepConstructor = new StepConstructor();
        final Yaml yaml = new Yaml();
        try {
            final Steps steps = yaml.loadAs(new FileReader(fileName + ".yaml"), Steps.class);
            System.out.println(steps);
            ContextUtils.setStepList(context, steps.getSteps());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void action(Map<String, Object> context, Map<String, Object> parameters) {
        // Nothing to do
    }

    @Override
    public Map<String, Object> expectedParameters() {
        return Collections.singletonMap("fileName", "string");
    }
}

package menu.file;

import menu.MenufiedAction;
import menu.file.StepRepresenter;
import menu.model.Step;
import menu.utils.ContextUtils;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaveStepList implements MenufiedAction {

    @Override
    public String title() {
        return "Save all actions you've done.";
    }

    //TODO https://techgarage.io/index.php/2016/09/17/java-objects-to-yaml/
    @Override
    public void wizardAction(Map<String, Object> context, Map<String, Object> parameters) {
        final String fileName = (String) parameters.get("fileName");
        Representer representer = new StepRepresenter();
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);
        final Yaml yaml = new Yaml(representer, options);
        final Map<String, Object> root = new HashMap<>();
        try {
            List<Step> stepList = ContextUtils.getStepList(context);
            root.put("steps", stepList);
            yaml.dump(root, new FileWriter(fileName + ".yaml"));
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

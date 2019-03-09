package menu.file;

import menu.model.Step;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Represent;
import org.yaml.snakeyaml.representer.Representer;

import java.util.HashMap;
import java.util.Map;

public class StepRepresenter extends Representer {

    private class RepresentInteger implements Represent {
        @Override
        public Node representData(Object data) {
            Integer i = (Integer) data;
            return representScalar(Tag.INT, Integer.toString(i));
        }
    }

    private class RepresentMap implements Represent {
        @Override
        public Node representData(Object data) {
            Map<String, Object> map = (Map<String, Object>) data;
            return representMapping(Tag.MAP, map, null);
        }
    }

    private class RepresentStep implements Represent {
        @Override
        public Node representData(Object o) {
            final Step step = (Step) o;
            final String actionName = step.getActionName();
            final Map<String, Object> parameters = step.getParameters();
            final Map<String, Object> result = new HashMap<>();
            result.put("actionName", actionName);
            result.put("parameters", parameters);
            return representMapping(Tag.MAP, result, null);
        }
    }

    public StepRepresenter() {
        this.representers.put(Map.class, new RepresentMap());
        this.representers.put(Integer.class, new RepresentInteger());
        this.representers.put(Step.class, new RepresentStep());
    }

}

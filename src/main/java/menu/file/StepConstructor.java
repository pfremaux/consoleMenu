package menu.file;

import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;

import java.util.HashMap;
import java.util.Map;

public class StepConstructor extends Constructor {

    private class MapConstruct implements Construct {
        @Override
        public Object construct(Node node) {
            Map<Object, Object> objectObjectMap = constructMapping((MappingNode) node);
            Map<String, Object> result = new HashMap<>();
            for (Map.Entry<Object, Object> entry : objectObjectMap.entrySet()) {
                result.put(entry.getKey().toString(), entry.getValue());
            }
            return result;
        }

        @Override
        public void construct2ndStep(Node node, Object object) {

        }
    }

    public StepConstructor() {
        this.yamlConstructors.put(new Tag("parameters"), new MapConstruct());
    }

}

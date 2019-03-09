package menu.utils;

import menu.model.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContextUtils {

    public static List<Step> setStepList(Map<String, Object> context, List<Step> steps) {
        /*final List<Step> steps = new ArrayList<>();
        for (Step stepFromFile : stepsFromFile) {
            Step step = new Step((String) stepFromFile.get("actionName"), stepFromFile);
            steps.add(step);
        }*/
        context.put("stepList", steps);
        return steps;
    }

    public static List<Step> getStepList(Map<String, Object> context) {
        Object stepList = context.get("stepList");
        if (stepList == null) {
            final List<Step> steps = new ArrayList<>();
            context.put("stepList", steps);
            stepList = steps;
        }
        return (List<Step>) stepList;
    }


    public static void mayFail(Map<String, Object> context) {
        context.put(TechKey.MAY_FAIL.key, Boolean.TRUE);
    }

    public static Boolean isFailureAllowed(Map<String, Object> context) {
        return booleanFromObject(context.get(TechKey.MAY_FAIL.key));
    }

    public static void canResumePreviousFailure(Map<String, Object> context) {
        context.put(TechKey.CAN_RESUME_PREVIOUS_FAILURE.key, Boolean.TRUE);
    }

    public static void clearTechKeys(Map<String, Object> context) {
        for (TechKey techKey : TechKey.values()) {
            context.remove(techKey);
        }
    }

    public static boolean booleanFromObject(Object o) {
        if (o == null || !(o instanceof Boolean)) {
            return false;
        }
        return (Boolean) o;
    }

    enum TechKey {
        MAY_FAIL("mayFail"),
        CAN_RESUME_PREVIOUS_FAILURE("canResumePreviousFailure");
        private final String key;

        TechKey(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

}

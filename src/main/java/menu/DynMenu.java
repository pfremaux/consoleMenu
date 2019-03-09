package menu;

import menu.basic.utils.BasicUtils;
import menu.main.DisplayHelp;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class DynMenu {

    private final List<MenufiedAction> menuChoices = new ArrayList<>();

    public DynMenu(Class<?> aClass) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        final URL resource = aClass.getResource("");
        final String resourcePath = resource.getPath();
        final int indexOf = resourcePath.indexOf("menu");
        final String basePackage = resourcePath.substring(indexOf).replaceAll("/", ".");

        File f = new File(resource.getFile());
        for (File file : f.listFiles()) {
            if (file.isFile()) {
                final String name = file.getName();
                final Class<?> aClass2 = Class.forName(basePackage + name.substring(0, name.length() - 6));
                if (MenufiedAction.class.isAssignableFrom(aClass2) && !aClass2.equals(MenufiedAction.class)) {
                    final Constructor<? extends MenufiedAction> constructor = ((Class<? extends MenufiedAction>) aClass2).getConstructor();
                    final MenufiedAction menufiedAction = constructor.newInstance();
                    menuChoices.add(menufiedAction);
                }
            }
        }
    }

    public static void main(String... s) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        final DynMenu dynMenu = new DynMenu(DisplayHelp.class);
        final Map<String, Object> context = new HashMap<>();
        dynMenu.launchMenu(false, context);
    }

    public void launchMenu(boolean isSubMenu, Map<String, Object> context) {
        int i = 1;
        final Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (choice != -1) {
            for (MenufiedAction menuChoice : menuChoices) {
                System.out.println(i + ". " + menuChoice.title());
                i++;
            }
            if (isSubMenu) {
                System.out.println("0. Back");
            } else {
                System.out.println("0. Exit");
            }
            choice = scanner.nextInt();

            if (choice == 0) {
                break;
            }

            final MenufiedAction menufiedAction = menuChoices.get(choice - 1);
            final Map<String, Object> parameters = new HashMap<>();

            for (Map.Entry<String, Object> entry : menufiedAction.expectedParameters().entrySet()) {
                System.out.println(entry.getKey());
                String input = BasicUtils.readString();
                if ("string".equals(entry.getValue())) {
                    parameters.put(entry.getKey(), input);
                } else if ("url".equals(entry.getValue())) {
                    URL value = null;
                    try {
                        value = new URL(input);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    parameters.put(entry.getKey(), value);
                }
            }
            menufiedAction.wizardAction(context, parameters);
            i = 1;
        }
    }

}

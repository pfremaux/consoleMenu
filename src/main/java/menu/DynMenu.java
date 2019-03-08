package menu;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

public class DynMenu {

    private static List<MenufiedAction> choices = new ArrayList<>();
    private final List<MenufiedAction> menuChoices = new ArrayList<>();

    public DynMenu(Class<?> aClass) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        final URL resource = aClass.getResource("");
        final String resourcePath = resource.getPath();
        int indexOf = resourcePath.indexOf("test");
        String basePackage = resourcePath.substring(indexOf).replaceAll("/", ".");

        //System.out.println(resourcePath);
        File f = new File(resource.getFile());
        for (File file : f.listFiles()) {
            if (file.isFile()) {
                String name = file.getName();
                final Class<?> aClass2 = Class.forName(basePackage + name.substring(0, name.length() - 6));
                //System.out.println(aClass);
                if (MenufiedAction.class.isAssignableFrom(aClass2) && !aClass2.equals(MenufiedAction.class)) {
                    Constructor<? extends MenufiedAction> constructor = ((Class<? extends MenufiedAction>) aClass2).getConstructor();
                    MenufiedAction menufiedAction = constructor.newInstance();
                    menuChoices.add(menufiedAction);
                }
            }
        }
    }

    public static void main(String... s) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        final DynMenu dynMenu = new DynMenu(DynMenu.class);
        dynMenu.launchMenu();
        /*URL resource = DynMenu.class.getResource("");
        String resourcePath = resource.getPath();
        int indexOf = resourcePath.indexOf("test");
        String basePackage = resourcePath.substring(indexOf).replaceAll("/", ".");

        //System.out.println(resourcePath);
        File f = new File(resource.getFile());
        for (File file : f.listFiles()) {
            if (file.isFile()) {
                String name = file.getName();
                final Class<?> aClass = Class.forName(basePackage + name.substring(0, name.length() - 6));
                //System.out.println(aClass);
                if (MenufiedAction.class.isAssignableFrom(aClass) && !aClass.equals(MenufiedAction.class)) {
                    System.out.println("good " + aClass.toString());
                    Constructor<? extends MenufiedAction> constructor = ((Class<? extends MenufiedAction>) aClass).getConstructor();
                    MenufiedAction menufiedAction = constructor.newInstance();
                    choices.add(menufiedAction);
                }
            }
        }
        int i = 1;
        for (MenufiedAction choice : choices) {
            System.out.println(i + ". " + choice.title());
            i++;
        }
        System.out.println("x. Exit");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        final MenufiedAction menufiedAction = choices.get(choice - 1);
        final Map<String, Object> parameters = new HashMap<>();
        final Map<String, Object> context = new HashMap<>();
        menufiedAction.wizardAction(context, parameters);*/
    }

    public void launchMenu() {
        int i = 1;
        for (MenufiedAction choice : menuChoices) {
            System.out.println(i + ". " + choice.title());
            i++;
        }
        System.out.println("x. Exit");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        final MenufiedAction menufiedAction = menuChoices.get(choice - 1);
        final Map<String, Object> parameters = new HashMap<>();
        final Map<String, Object> context = new HashMap<>();
        menufiedAction.wizardAction(context, parameters);
    }

}

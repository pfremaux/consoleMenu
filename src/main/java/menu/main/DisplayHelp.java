package menu.main;

import menu.DynMenu;
import menu.MenufiedAction;

import java.util.Map;

public final class DisplayHelp implements MenufiedAction {

    @Override
    public String title() {
        return "Display help for extending this app.";
    }

    @Override
    public void wizardAction(Map<String, Object> context, Map<String, Object> parameters) {
        final String currentPackage = this.getClass().getPackage().toString();
        final String interfaceName = MenufiedAction.class.getName();
        final String simpleInterfaceName = MenufiedAction.class.getSimpleName();
        final Class<DynMenu> dynMenuClass = DynMenu.class;
        System.out.println();
        System.out.printf("File YourFirstMenu.java\n");
        System.out.printf("package %s;\n", currentPackage);
        System.out.printf("import %s;\n", interfaceName);
        System.out.printf("import another.package.AnySubMenuClassImplementing%s;\n", simpleInterfaceName);
        System.out.printf("public class YourFirstMenu implements %s {\n", simpleInterfaceName);
        System.out.printf("\tpublic void wizardAction(Map<String, Object> context, Map<String, Object> parameters) {\n");
        System.out.printf("\t\t%s dynMenu = new %s(AnySubMenuClassImplementing%s.class);\n", dynMenuClass.getSimpleName(), dynMenuClass.getSimpleName(), simpleInterfaceName);
        System.out.printf("\t\tdynMenu.launchMenu();\n");
        System.out.printf("\t}\n");
        System.out.printf("}\n");
        System.out.println("<EOF>");
        System.out.printf("AnySubMenuClassImplementing%s.java", simpleInterfaceName);
        System.out.println();
        System.out.println("package another.package;");
        System.out.printf("import %s;\n", interfaceName);
        System.out.printf("public class AnySubMenuClassImplementing%s implements %s {\n", simpleInterfaceName, simpleInterfaceName);
        System.out.printf("\tpublic void wizardAction(Map<String, Object> context, Map<String, Object> parameters) {\n");
        System.out.printf("\t\tSystem.out.println(\"Hi!\");\n");
        System.out.printf("\t}\n");
        System.out.printf("}\n");
        System.out.println("<EOF>");
    }

    @Override
    public void action(Map<String, Object> context, Map<String, Object> parameters) {
        // Nothing to do.
    }

}

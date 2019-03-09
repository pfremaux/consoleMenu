package menu.basic.utils;

import java.util.Scanner;

public class BasicUtils {

    public static String readString() {
        final Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}

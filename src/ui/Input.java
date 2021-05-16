package ui;

import java.util.Scanner;

public class Input {

    private static Scanner in = new Scanner(System.in);

    static int getInt(){
        return in.nextInt();
    }

    static String string(){
        return in.next();
    }

    static String line(){
        return in.nextLine();
    }
}

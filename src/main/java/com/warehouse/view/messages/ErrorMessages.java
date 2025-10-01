package com.warehouse.view.messages;

public class ErrorMessages {

    public static final String ERROR = "\033[1;31m", RESET = "\033[0m";

    /*
    <summary>
        I don't like using the System.err because it differs to the normal System.out,
        which means it sometimes breaks the terminal and makes it look ugly
    </summary>
     */
    public static void defaultError(){
        System.out.println(ERROR + "\nChoose a value on the scope." + RESET);
    }

    public static void parseError(){
        System.out.println(ERROR + "\nCannot pass your choice to the designed value!" + RESET);
    }

    public static void cannotConnect(){
        System.out.println(ERROR + "\nThis operation could not be done." + RESET);
    }

    public static void duplicateExists(){
        System.out.println(ERROR + "\nA duplicate with the same infos was found! Please, do not make duplicates." + RESET);
    }

}

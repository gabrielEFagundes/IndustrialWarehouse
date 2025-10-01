package com.warehouse.view.messages;

public class WarnMessages {

    public static final String NOT_FOCUS = "\033[1;37m", RESET = "\033[0m";

    public static void printCaseBufferNotWorking(){
        System.out.println(NOT_FOCUS + "\nPress enter to continue!" + RESET);
    }

}

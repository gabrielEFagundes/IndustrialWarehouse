package com.warehouse.view.messages;

public class SuccessMessages {

    public static final String SUCCESS = "\033[1;32m", RESET = "\033[0m";

    public static void successfulConnection(){
        System.out.println(SUCCESS + "\nSuccessfully signed!" + RESET);
    }

}

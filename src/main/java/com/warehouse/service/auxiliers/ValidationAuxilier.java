package com.warehouse.service.auxiliers;

import com.warehouse.view.messages.ErrorMessages;

import java.util.InputMismatchException;

public class ValidationAuxilier {

    public static int parseInt(String str){
        try{
            return Integer.parseInt(str);

        }catch(InputMismatchException e){
            ErrorMessages.parseError();
            return -1;
        }
    }

    public static double parseDouble(String str){
        try{
            return Double.parseDouble(str);

        }catch(InputMismatchException e){
            ErrorMessages.parseError();
            return -1;
        }
    }

    public static boolean isStringBlank(String... str){
        for(String s : str){
            if(s.isBlank()){
                return true;
            }
        }
        return false;
    }

    public static boolean isStorageGreaterThanZero(double storage){
        return storage > 0;
    }

    public static boolean isStorageGreaterThanAmount(double storage, double amount){ return storage > amount; }

}

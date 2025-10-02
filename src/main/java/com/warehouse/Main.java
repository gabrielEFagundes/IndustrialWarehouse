package com.warehouse;

import com.warehouse.service.MainService;
import com.warehouse.view.MainView;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        MainView view = new MainView();
        MainService service = new MainService();

        while(true){
            try {
                int choice = view.menu();

                service.mainService(choice);

            }catch(SQLException e){
                e.printStackTrace();
            }
            /*
            <summary>
                Actually I'll be using the exception for debugging
            </summary>
             */
        }

    }
}
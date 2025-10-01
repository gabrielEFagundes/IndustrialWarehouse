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

            }catch(SQLException _){ }
            /*
            <summary>
                The '_' is just because I won't really do anything with that exception, so I don't really
                need to name it
            </summary
             */
        }

    }
}
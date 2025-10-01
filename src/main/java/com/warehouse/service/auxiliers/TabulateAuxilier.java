package com.warehouse.service.auxiliers;

import com.warehouse.model.Material;
import com.warehouse.model.Supplier;

import java.util.List;

public class TabulateAuxilier {

    public static void tabulateSuppliers(List<Supplier> s){
        System.out.println("\n+------------+----------------------+----------------------+");
        System.out.printf("| %-10s | %-20s | %-20s |\n", "ID", "Name", "CNPJ");

        s.forEach(Supplier ->
                System.out.printf("| %-10d | %-20s | %-20s |\n", Supplier.getId(), Supplier.getName(), Supplier.getCnpj()));

        System.out.println("+------------+----------------------+----------------------+");
    }

    public static void tabulateMaterials(List<Material> m){
        System.out.println("\n+------------+----------------------+----------------------+------------+");
        System.out.printf("| %-10s | %-20s | %-20s | %-10s |\n", "ID", "Name", "Measurement Way", "Storage");

        m.forEach(Material ->
                System.out.printf("| %-10d | %-20s | %-20s | %-10.2f |",
                        Material.getId(), Material.getName(), Material.getMeasurement(), Material.getStorage()));

        System.out.println("\n+------------+----------------------+----------------------+------------+");
    }

}

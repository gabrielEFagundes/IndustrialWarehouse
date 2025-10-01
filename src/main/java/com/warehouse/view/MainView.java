package com.warehouse.view;

import com.warehouse.model.Material;
import com.warehouse.model.NoteEntrance;
import com.warehouse.model.NoteItemEntrance;
import com.warehouse.model.Supplier;
import com.warehouse.service.auxiliers.TabulateAuxilier;
import com.warehouse.service.auxiliers.ValidationAuxilier;
import com.warehouse.view.messages.WarnMessages;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MainView {

    static Scanner scan = new Scanner(System.in);

    public int menu(){
        System.out.print("""
                \n
                +---- Industrial Warehouse Management System ----+
                | 1- Sign New Supplier                           |
                | 2- Sign New Material                           |
                | 3- Register Entrance Note                      |
                | 4- Create Material Requisition                 |
                | 5- Attend Requisition                          |
                | 6- Cancel Requisition                          |
                +------------------------------------------------+
                """);
        System.out.print("-> ");

        return ValidationAuxilier.parseInt(scan.nextLine());
    }

    public Supplier signSupplier(){
        WarnMessages.printCaseBufferNotWorking();
        scan.nextLine();

        System.out.print("What's the name of the supplier?\n-> ");
        String name = scan.nextLine();

        System.out.print("What's the CNPJ of the supplier?\n-> ");
        String cnpj = scan.nextLine();

        return new Supplier(name, cnpj);
    }

    public Material signMaterial(){
        WarnMessages.printCaseBufferNotWorking();
        scan.nextLine();

        System.out.print("What's the name of the material?\n-> ");
        String name = scan.nextLine();

        System.out.print("What's used to measure the material? (e.g. Kg, m, part, etc)\n-> ");
        String measureUnit = scan.nextLine();

        System.out.print("What's the initial amount on the storage?\n-> ");
        String amount = scan.nextLine();

        return new Material(name,
                            measureUnit,
                            ValidationAuxilier.parseDouble(amount));
    }

    public NoteEntrance signEntranceNote(List<Supplier> suppliers){
        WarnMessages.printCaseBufferNotWorking();
        scan.nextLine();

        TabulateAuxilier.tabulateSuppliers(suppliers);
        System.out.print("\nChoose a supplier by ID\n-> ");
        String idSupplier = scan.nextLine();

        return new NoteEntrance(ValidationAuxilier.parseInt(idSupplier),
                                Date.valueOf(LocalDate.now()));
    }

    public NoteItemEntrance signNoteItemEntrance(List<Material> materials, int idEntNote){
        WarnMessages.printCaseBufferNotWorking();
        scan.nextLine();

        TabulateAuxilier.tabulateMaterials(materials);
        System.out.print("\nChoose a material by ID\n-> ");
        String idMaterial = scan.nextLine();

        System.out.print("How many of these you want?\n-> ");
        String amount = scan.nextLine();

        return new NoteItemEntrance(idEntNote,
                                    ValidationAuxilier.parseInt(idMaterial),
                                    ValidationAuxilier.parseDouble(amount));
    }

    public boolean askToAddMore(){
        System.out.print("\nWould you like to add another item for the entrance note? (y/n)\n-> ");
        String addMore = scan.nextLine();

        return addMore.equalsIgnoreCase("y");
    }

}

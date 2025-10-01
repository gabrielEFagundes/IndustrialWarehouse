package com.warehouse.service;

import com.warehouse.dao.MaterialDao;
import com.warehouse.dao.NoteEntranceDao;
import com.warehouse.dao.NoteItemEntranceDao;
import com.warehouse.dao.SupplierDao;
import com.warehouse.model.Material;
import com.warehouse.model.NoteEntrance;
import com.warehouse.model.NoteItemEntrance;
import com.warehouse.model.Supplier;
import com.warehouse.service.auxiliers.ValidationAuxilier;
import com.warehouse.view.messages.ErrorMessages;
import com.warehouse.view.MainView;

import java.sql.SQLException;
import java.util.List;

public class MainService {

    MainView view = new MainView();

    SupplierDao supplierDao = new SupplierDao();
    MaterialDao materialDao = new MaterialDao();
    NoteEntranceDao noteEntranceDao = new NoteEntranceDao();
    NoteItemEntranceDao noteItemEntranceDao = new NoteItemEntranceDao();

    public void mainService(int choice) throws SQLException {
        switch (choice){
            case 1 -> {
                Supplier supplier = view.signSupplier();

                if(!ValidationAuxilier.isStringBlank(supplier.getName(), supplier.getCnpj()) &&
                    !supplierDao.searchForDuplicate(supplier.getCnpj())){

                    supplierDao.signSupplier(supplier);
                }
            }
            case 2 -> {
                Material material = view.signMaterial();

                if(!ValidationAuxilier.isStringBlank(material.getName()) &&
                    !materialDao.searchForDuplicates(material.getName()) &&
                    ValidationAuxilier.isStorageGreaterThanZero(material.getStorage())){

                    materialDao.signMaterial(material);
                }
            }
            case 3 -> {
                List<Supplier> suppliers = supplierDao.allSuppliers();
                List<Material> materials = materialDao.allMaterials();

                NoteEntrance noteEntrance = view.signEntranceNote(suppliers);
                noteEntranceDao.signNoteEntrance(noteEntrance);

                boolean isStillSigning = true;

                while(isStillSigning){
                    NoteItemEntrance noteItemEntrance = view.signNoteItemEntrance(materials, noteEntrance.getId());
                    noteItemEntranceDao.signNoteItemEntrace(noteItemEntrance);

                    if(!view.askToAddMore()){
                        isStillSigning = false;
                    }
                }
            }
            case 4 -> {

            }
            case 5 -> {

            }
            case 6 -> {

            }
            default -> ErrorMessages.defaultError();
        }
    }

}

package com.warehouse.service;

import com.warehouse.dao.*;
import com.warehouse.model.*;
import com.warehouse.service.auxiliers.InGenerator;
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
    RequisitionDao requisitionDao = new RequisitionDao();
    RequisitionItemDao requisitionItemDao = new RequisitionItemDao();

    int counter = 0;

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
                int entranceId = noteEntranceDao.getEntranceNoteId(noteEntrance.getIdSupplier()); // also not the best, but will do

                boolean isStillSigning = true;

                while (isStillSigning) {
                    NoteItemEntrance noteItemEntrance = view.signNoteItemEntrance(materials, entranceId);
                    noteItemEntranceDao.signNoteItemEntrace(noteItemEntrance);

                    if (!view.askToAddMore()) {
                        isStillSigning = false;
                    }
                }
            }
            case 4 -> {
                List<Material> materials = materialDao.allMaterials();

                Requisition requisition = view.callForRequisition();
                requisitionDao.callRequisition(requisition);
                counter++; // will do IF the code is run once

                RequisitionItem requisitionItem = view.requestItems(materials);
                double materialStorage = materialDao.getMaterialStorage(requisitionItem.getIdMaterial());

                if(ValidationAuxilier.isStorageGreaterThanAmount(materialStorage, requisitionItem.getAmount()))
                    requisitionItemDao.signRequisitionItems(requisitionItem.getIdMaterial(), counter, requisitionItem.getAmount());
            }
            case 5 -> {
                List<Requisition> requisitions = requisitionDao.pendentRequisitions();

                int idReq = view.attendRequisition(requisitions);
                int idMat = requisitionItemDao.getMaterialId(idReq);
                double amount = requisitionItemDao.getAmount(idReq);

                requisitionDao.updateStatusToAttended(idReq);
                materialDao.updateStorage(amount, idMat);
            }
            case 6 -> {
                List<Requisition> requisitions = requisitionDao.allRequisitions();

                int idReq = view.attendRequisition(requisitions);
                requisitionDao.updateStatusToCancelled(idReq);
            }
            case 10 -> {
                int amountToFilter = view.filterIds();
                List<Integer> ids = view.addIdToFilter(amountToFilter);
                String buildedIn = InGenerator.genIn(ids, amountToFilter);

            }
            case 0 -> { System.exit(0); }
            default -> ErrorMessages.defaultError();
        }
    }

}

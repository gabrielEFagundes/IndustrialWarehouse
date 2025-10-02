package com.warehouse.model;

public class RequisitionItem {

    private int idRequisition;
    private int idMaterial;
    private double amount;

    public RequisitionItem(int idRequisition, int idMaterial, double amount) {
        this.idRequisition = idRequisition;
        this.idMaterial = idMaterial;
        this.amount = amount;
    }

    public RequisitionItem(int idMaterial, double amount) {
        this.idMaterial = idMaterial;
        this.amount = amount;
    }

    public int getIdRequisition() {
        return idRequisition;
    }

    public void setIdRequisition(int idRequisition) {
        this.idRequisition = idRequisition;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

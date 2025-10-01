package com.warehouse.model;

public class NoteItemEntrance {

    private int idNoteEntrance;
    private int idMaterial;
    private double amount;

    public NoteItemEntrance(int idNoteEntrance, int idMaterial, double amount) {
        this.idNoteEntrance = idNoteEntrance;
        this.idMaterial = idMaterial;
        this.amount = amount;
    }

    public int getIdNoteEntrance() {
        return idNoteEntrance;
    }

    public void setIdNoteEntrance(int idNoteEntrance) {
        this.idNoteEntrance = idNoteEntrance;
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

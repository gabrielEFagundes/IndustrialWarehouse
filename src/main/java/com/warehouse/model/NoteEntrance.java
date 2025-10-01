package com.warehouse.model;

import java.sql.Date;

public class NoteEntrance {

    private int id;
    private int idSupplier;
    private Date dateEntrance;

    public NoteEntrance(int id, int idSupplier, Date dateEntrance) {
        this.id = id;
        this.idSupplier = idSupplier;
        this.dateEntrance = dateEntrance;
    }

    public NoteEntrance(int idSupplier, Date dateEntrance) {
        this.idSupplier = idSupplier;
        this.dateEntrance = dateEntrance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public Date getDateEntrance() {
        return dateEntrance;
    }

    public void setDateEntrance(Date dateEntrance) {
        this.dateEntrance = dateEntrance;
    }
}

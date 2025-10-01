package com.warehouse.model;

import com.warehouse.model.enums.RequisitionEnum;

import java.sql.Date;

public class Requisition {

    private int id;
    private String session;
    private Date solicitationDate;
    private RequisitionEnum status;

    public Requisition(int id, String session, Date solicitationDate, RequisitionEnum status) {
        this.id = id;
        this.session = session;
        this.solicitationDate = solicitationDate;
        this.status = status;
    }

    public Requisition(String session, Date solicitationDate, RequisitionEnum status) {
        this.session = session;
        this.solicitationDate = solicitationDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public Date getSolicitationDate() {
        return solicitationDate;
    }

    public void setSolicitationDate(Date solicitationDate) {
        this.solicitationDate = solicitationDate;
    }

    public RequisitionEnum getStatus() {
        return status;
    }

    public void setStatus(RequisitionEnum status) {
        this.status = status;
    }
}

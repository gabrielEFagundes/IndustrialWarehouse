package com.warehouse.dao;

import com.warehouse.model.NoteEntrance;
import com.warehouse.util.Connectate;
import com.warehouse.view.messages.ErrorMessages;
import com.warehouse.view.messages.SuccessMessages;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class NoteEntranceDao {

    public void signNoteEntrance(NoteEntrance noteEntrance) throws SQLException {
        String query = "INSERT INTO noteEntrance (idSupplier, dateEntrance) VALUES (?,?)";

        Connection conn = Connectate.begin();
        try(var stmt = conn.prepareStatement(query)){
            conn.setAutoCommit(false);

            stmt.setInt(1, noteEntrance.getIdSupplier());
            stmt.setDate(2, noteEntrance.getDateEntrance());
            stmt.executeUpdate();

            conn.commit();
            SuccessMessages.successfulConnection();

        }catch(SQLException e){
            conn.rollback();
            conn.close();
            ErrorMessages.cannotConnect();

        }finally{
            conn.close();
        }
    }

    /*
        I'm still not sure if I could change this DB requisition to something I already have on the code,
        so - TODO: maybe soon make it so I don't need this method

        this is pretty much the only thing I should really change
     */
    public int getEntranceNoteId(String idSupplier, Date dateEntrance){
        String query = "SELECT id FROM noteEntrance WHERE idSupplier = ? AND dateEntrance = ?";

        // TODO: finish this
    }

}

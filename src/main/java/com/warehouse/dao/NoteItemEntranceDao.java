package com.warehouse.dao;

import com.warehouse.model.NoteItemEntrance;
import com.warehouse.util.Connectate;
import com.warehouse.view.messages.ErrorMessages;
import com.warehouse.view.messages.SuccessMessages;

import java.sql.Connection;
import java.sql.SQLException;

public class NoteItemEntranceDao {

    public void signNoteItemEntrace(NoteItemEntrance noteItemEntrance) throws SQLException {
        String query = "INSERT INTO noteItemEntrance (idNoteEntrance, idMaterial, amount) VALUES (?,?,?)";

        Connection conn = Connectate.begin();
        try(var stmt = conn.prepareStatement(query)){
            conn.setAutoCommit(false);

            stmt.setInt(1, noteItemEntrance.getIdNoteEntrance());
            stmt.setInt(2, noteItemEntrance.getIdMaterial());
            stmt.setDouble(3, noteItemEntrance.getAmount());
            stmt.executeUpdate();

            conn.commit();
            SuccessMessages.successfulConnection();

        }catch(SQLException e){
            conn.rollback();
            conn.close();
            ErrorMessages.cannotConnect();
            e.printStackTrace();

        }finally {
            conn.close();
        }
    }

}

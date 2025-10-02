package com.warehouse.dao;

import com.warehouse.model.NoteEntrance;
import com.warehouse.util.Connectate;
import com.warehouse.view.messages.ErrorMessages;
import com.warehouse.view.messages.SuccessMessages;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
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

    public int getEntranceNoteId(int idSupplier) throws SQLException{
        String query = "SELECT id FROM noteEntrance WHERE idSupplier = ?";

        try(Connection conn = Connectate.begin();
            var stmt = conn.prepareStatement(query)){

            stmt.setInt(1, idSupplier);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return rs.getInt("id");
            }

            return -1;

        }
    }

}

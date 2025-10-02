package com.warehouse.dao;

import com.warehouse.util.Connectate;
import com.warehouse.view.messages.ErrorMessages;
import com.warehouse.view.messages.SuccessMessages;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequisitionItemDao {

    public void signRequisitionItems(int idMaterial, int idRequisition, double amount) throws SQLException {
        String query = "INSERT INTO requisitionItem (idRequisition, idMaterial, amount) VALUES (?,?,?)";

        Connection conn = Connectate.begin();
        try(var stmt = conn.prepareStatement(query)){
            conn.setAutoCommit(false);

            stmt.setInt(1, idRequisition);
            stmt.setInt(2, idMaterial);
            stmt.setDouble(3, amount);
            stmt.executeUpdate();

            conn.commit();
            SuccessMessages.successfulConnection();

        }catch(SQLException e){
            conn.rollback();
            conn.close();
            ErrorMessages.cannotConnect();

        }finally {
            conn.close();
        }
    }

    public double getAmount(int idMaterial) throws SQLException{
        String query = """
                SELECT amount
                FROM requisitionItem
                WHERE idMaterial = ?
                """;

        try(Connection conn = Connectate.begin();
            var stmt = conn.prepareStatement(query)){

            stmt.setInt(1, idMaterial);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return rs.getDouble("amount");
            }

            return -1;
        }
    }

    public int getMaterialId(int idRequisition) throws SQLException{
        String query = """
                SELECT material.id AS id
                FROM requisitionItem
                JOIN material ON requisitionItem.idMaterial = material.id
                WHERE idRequisition = ?
                """;
        // not the best, but will do

        try(Connection conn = Connectate.begin();
            var stmt = conn.prepareStatement(query)){

            stmt.setInt(1, idRequisition);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return rs.getInt("id");
            }

            return -1;
        }
    }
}

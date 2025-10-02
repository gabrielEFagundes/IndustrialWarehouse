package com.warehouse.dao;

import com.warehouse.model.Requisition;
import com.warehouse.model.enums.RequisitionEnum;
import com.warehouse.util.Connectate;
import com.warehouse.view.messages.ErrorMessages;
import com.warehouse.view.messages.SuccessMessages;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequisitionDao {

    public void callRequisition(Requisition requisition) throws SQLException {
        String query = "INSERT INTO requisition (session, solicitationDate, status) VALUES (?,?,?)";

        Connection conn = Connectate.begin();
        try(var stmt = conn.prepareStatement(query)){
            conn.setAutoCommit(false);

            stmt.setString(1, requisition.getSession());
            stmt.setDate(2, requisition.getSolicitationDate());
            stmt.setString(3, String.valueOf(requisition.getStatus()));
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

    public List<Requisition> allRequisitions() throws SQLException{
        String query = "SELECT id, session, solicitationDate FROM requisition";
        List<Requisition> requisitions = new ArrayList<>();

        try(Connection conn = Connectate.begin();
            var stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String session = rs.getString("session");
                Date date = rs.getDate("solicitationDate");

                requisitions.add(new Requisition(id, session, date));
            }
        }
        return requisitions;
    }

    public List<Requisition> pendentRequisitions() throws SQLException{
        String query = "SELECT id, session, solicitationDate FROM requisition WHERE status = 'PENDENT'";
        List<Requisition> requisitions = new ArrayList<>();

        try(Connection conn = Connectate.begin();
            var stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String session = rs.getString("session");
                Date solicitationDate = rs.getDate("solicitationDate");

                requisitions.add(new Requisition(id, session, solicitationDate, RequisitionEnum.PENDENT));
            }
        }
        return requisitions;
    }

    public void updateStatusToAttended(int id) throws SQLException{
        String query = "UPDATE requisition SET status = 'ATTENDED' WHERE id = ?";

        Connection conn = Connectate.begin();
        try(var stmt = conn.prepareStatement(query)){
            conn.setAutoCommit(false);

            stmt.setInt(1, id);
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

    public void updateStatusToCancelled(int id) throws SQLException{
        String query = "UPDATE requisition SET status = 'CANCELLED' WHERE id = ?";

        Connection conn = Connectate.begin();
        try(var stmt = conn.prepareStatement(query)){
            conn.setAutoCommit(false);

            stmt.setInt(1, id);
            stmt.executeUpdate();

            conn.commit();
            conn.close();
            SuccessMessages.successfulConnection();

        }catch(SQLException e){
            conn.rollback();
            conn.close();
            ErrorMessages.cannotConnect();

        }finally{
            conn.close();
        }
    }

}

package com.warehouse.dao;

import com.warehouse.model.Supplier;
import com.warehouse.util.Connectate;
import com.warehouse.view.messages.ErrorMessages;
import com.warehouse.view.messages.SuccessMessages;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDao {

    public boolean searchForDuplicate(String cnpj) throws SQLException {
        String query = "SELECT COUNT(0) AS line FROM supplier WHERE cnpj = ?";

        try(Connection conn = Connectate.begin();
            var stmt = conn.prepareStatement(query)){

            stmt.setString(1, cnpj);

            ResultSet rs = stmt.executeQuery();

            if(rs.next() && rs.getInt("line")>0) {
                ErrorMessages.duplicateExists();
                return true;
            }
        }
        return false;
    }

    public void signSupplier(Supplier supplier) throws SQLException {
        String query = "INSERT INTO supplier (name, cnpj) VALUES (?,?)";

        Connection conn = Connectate.begin();
        try(var stmt = conn.prepareStatement(query)){
            conn.setAutoCommit(false);

            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getCnpj());
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

    public List<Supplier> allSuppliers() throws SQLException{
        String query = "SELECT id, name, cnpj FROM supplier";
        List<Supplier> suppliers = new ArrayList<>();

        try(Connection conn = Connectate.begin();
            var stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String cnpj = rs.getString("cnpj");

                suppliers.add(new Supplier(id, name, cnpj));
            }
        }
        return suppliers;
    }

}

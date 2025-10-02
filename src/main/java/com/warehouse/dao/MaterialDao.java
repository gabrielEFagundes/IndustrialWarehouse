package com.warehouse.dao;

import com.warehouse.model.Material;
import com.warehouse.util.Connectate;
import com.warehouse.view.messages.ErrorMessages;
import com.warehouse.view.messages.SuccessMessages;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialDao {

    public boolean searchForDuplicates(String name) throws SQLException {
        String query = "SELECT COUNT(0) AS line FROM material WHERE name = ?";

        try(Connection conn = Connectate.begin();
            var stmt = conn.prepareStatement(query)){

            stmt.setString(1, name);

            ResultSet rs = stmt.executeQuery();

            if(rs.next() && rs.getInt("line")>0){
                ErrorMessages.duplicateExists();
                return true;
            }
        }
        return false;
    }

    public void signMaterial(Material material) throws SQLException{
        String query = "INSERT INTO material (name, measurement, storage) VALUES (?,?,?)";

        Connection conn = Connectate.begin();
        try(var stmt = conn.prepareStatement(query)){
            conn.setAutoCommit(false);

            stmt.setString(1, material.getName());
            stmt.setString(2, material.getMeasurement());
            stmt.setDouble(3, material.getStorage());
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

    public List<Material> allMaterials() throws SQLException{
        String query = "SELECT id, name, measurement, storage FROM material";
        List<Material> materials = new ArrayList<>();

        try(Connection conn = Connectate.begin();
            var stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String measurement = rs.getString("measurement");
                double storage = rs.getDouble("storage");

                materials.add(new Material(id, name, measurement, storage));
            }
        }
        return materials;
    }

    public double getMaterialStorage(int id) throws SQLException{
        String query = "SELECT storage FROM material WHERE id = ?";

        try(Connection conn = Connectate.begin();
            var stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                return rs.getDouble("storage");
            }

            return -1;

        }
    }

    public void updateStorage(double amount, int id) throws SQLException {
        String query = "UPDATE material SET storage = storage - ? WHERE id = ?";

        Connection conn = Connectate.begin();
        try(var stmt = conn.prepareStatement(query)){
            conn.setAutoCommit(false);

            stmt.setDouble(1, amount);
            stmt.setInt(2, id);
            stmt.executeUpdate();

            conn.commit();
            SuccessMessages.successfulConnection();

        }catch (SQLException e){
            conn.rollback();
            conn.close();
            ErrorMessages.cannotConnect();

        }finally{
            conn.close();
        }
    }

}

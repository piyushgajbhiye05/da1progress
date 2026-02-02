package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Warehouse;

public class WarehouseDAOImpl implements WarehouseDAO{

   public WarehouseDAOImpl() {}

    public int addWarehouse(Warehouse warehouse) throws SQLException {
        Connection con = DatabaseConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO warehouse (supplier_id, warehouse_name, location, capacity) VALUES (?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, warehouse.getSupplierId());
        ps.setString(2, warehouse.getWarehouseName());
        ps.setString(3, warehouse.getLocation());
        ps.setInt(4, warehouse.getCapacity());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            warehouse.setWarehouseId(rs.getInt(1));
            return warehouse.getWarehouseId();
        }
        return -1;
    }

    public Warehouse getWarehouseById(int warehouseId) throws SQLException {
        Connection con = DatabaseConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM warehouse WHERE warehouse_id=?");
        ps.setInt(1, warehouseId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Warehouse w = new Warehouse();
            w.setWarehouseId(rs.getInt("warehouse_id"));
            w.setSupplierId(rs.getInt("supplier_id"));
            w.setWarehouseName(rs.getString("warehouse_name"));
            w.setLocation(rs.getString("location"));
            w.setCapacity(rs.getInt("capacity"));
            return w;
        }
        return null;
    }

    public void updateWarehouse(Warehouse warehouse) throws SQLException {
        Connection con = DatabaseConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement(
                "UPDATE warehouse SET capacity=? WHERE warehouse_id=?");

        ps.setInt(1, warehouse.getCapacity());
        ps.setInt(2, warehouse.getWarehouseId());
        ps.executeUpdate();
    }

    public void deleteWarehouse(int warehouseId) throws SQLException {
        Connection con = DatabaseConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement("DELETE FROM warehouse WHERE warehouse_id=?");
        ps.setInt(1, warehouseId);
        ps.executeUpdate();
    }

    public List<Warehouse> getAllWarehouse() throws SQLException {
        List<Warehouse> list = new ArrayList<>();
        Connection con = DatabaseConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM warehouse");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Warehouse w = new Warehouse();
            w.setWarehouseId(rs.getInt("warehouse_id"));
            w.setSupplierId(rs.getInt("supplier_id"));
            w.setWarehouseName(rs.getString("warehouse_name"));
            w.setLocation(rs.getString("location"));
            w.setCapacity(rs.getInt("capacity"));
            list.add(w);
        }
        return list;
    }

}

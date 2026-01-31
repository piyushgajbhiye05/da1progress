package com.edutech.progressive.dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Warehouse;

public class WarehouseDAOImpl implements WarehouseDAO {
    private Connection connection;
    public WarehouseDAOImpl() {
        this.connection = connection;
    }

    @Override
    public int addWarehouse(Warehouse warehouse) throws SQLException {
        String sql = "INSERT INTO warehouse(supplier_id,warehouse_name,location,capacity) Values(?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql,java.sql.Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, warehouse.getSupplierId());
        ps.setString(2, warehouse.getWarehouseName());
        ps.setString(3, warehouse.getLocation());
        ps.setInt(4, warehouse.getCapacity());
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            return rs.getInt(1);
        }
        return -1;
    }

    @Override
    public Warehouse getWarehouseById(int warehouseId) throws SQLException {
        String sql = "SELECT * FROM warehouse WHERE warehouse_id=?";
        Warehouse warehouse = null;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, warehouseId);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            int id = rs.getInt("warehouse_id");
            int supplier_id = rs.getInt("supplier_id");
            String warehouse_name = rs.getString("warehouse_name");
            String location = rs.getString("location");
            int capacity = rs.getInt("capacity");
            return warehouse = new Warehouse(warehouseId, supplier_id, warehouse_name, location, capacity);
        }
        return null;
    }

    @Override
    public void updateWarehouse(Warehouse warehouse) throws SQLException {
        String sql ="UPDATE warehouse SET supplier_id=?,warehouse_name=?,location=?capacity=? WHERE warehouse_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, warehouse.getSupplierId());
        ps.setString(2, warehouse.getWarehouseName());
        ps.setString(3, warehouse.getLocation());
        ps.setInt(4, warehouse.getCapacity());
        ps.setInt(5, warehouse.getWarehouseId());
        ps.executeUpdate();
    }

    @Override
    public void deleteWarehouse(int warehouseId) throws SQLException{
        String sql = "DELETE FROM warehouse WHERE warehouse_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, warehouseId);
        ps.executeUpdate();
    }
    @Override
    public List<Warehouse> getAllWarehouse() throws SQLException{
        List<Warehouse> wList = new ArrayList<>();
        String sql = "SELECT * FROM warehouse";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int warehouse_id = rs.getInt("warehouse_id");
            int supplier_id = rs.getInt("supplier_id");
            String warehouse_name = rs.getString("warehouse_name");
            String location = rs.getString("location");
            int capacity = rs.getInt("capacity");
            Warehouse warehouse = new Warehouse(warehouse_id, supplier_id, warehouse_name, location, capacity);
            wList.add(warehouse);
            return wList;
        }
        return null;
    }

}

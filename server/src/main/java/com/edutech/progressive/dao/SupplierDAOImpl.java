package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Supplier;

public class SupplierDAOImpl implements SupplierDAO {
    private Connection connection;
    

    public SupplierDAOImpl() {
        this.connection = DatabaseConnectionManager.getConnection();
    }

    @Override
    public int addSupplier(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO supplier(supplier_name,email,username,password,phone,address,role) Values(?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, supplier.getSupplierName());
        ps.setString(2, supplier.getEmail());
        ps.setString(3, supplier.getUsername());
        ps.setString(4, supplier.getPassword());
        ps.setString(5, supplier.getPhone());
        ps.setString(6, supplier.getAddress());
        ps.setString(7, supplier.getRole());
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            return rs.getInt(1);
        }
        return -1;

    }

    @Override
    public Supplier getSupplierById(int supplierId) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE supplier_id=?";
        Supplier supplier = null;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, supplierId);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            int id = rs.getInt("supplier_id");
            String supplier_name = rs.getString("supplier_name");
            String email = rs.getString("email");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String phone = rs.getString("phone");
            String address = rs.getString("address");
            String role = rs.getString("role");
            return supplier = new Supplier(supplierId, supplier_name, email, phone, address, username, password, role);
        }
        return null;

    }

    @Override
    public void updateSupplier(Supplier supplier) throws SQLException {
        String sql = "UPDATE supplier SET supplier_name=?,email=?,username=?,password=?,phone=?,address=?,role=? WHERE supplier_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, supplier.getSupplierName());
        ps.setString(2, supplier.getEmail());
        ps.setString(3, supplier.getUsername());
        ps.setString(4, supplier.getPassword());
        ps.setString(5, supplier.getPhone());
        ps.setString(6, supplier.getAddress());
        ps.setString(7, supplier.getRole());
        ps.setInt(8, supplier.getSupplierId());
        ps.executeUpdate();
    }

    @Override
    public void deleteSupplier(int supplierId) throws SQLException {
        String sql = "DELETE FROM supplier WHERE supplier_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, supplierId);
        ps.executeUpdate();
    }

    @Override
    public List<Supplier> getAllSuppliers() throws SQLException {
        List<Supplier> slist = new ArrayList<>();
        String sql = "SELECT * FROM supplier";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int supplier_id = rs.getInt("supplier_id");
            String supplier_name = rs.getString("supplier_name");
            String email = rs.getString("email");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String phone = rs.getString("phone");
            String address = rs.getString("address");
            String role = rs.getString("role");
            Supplier supplier = new Supplier(supplier_id, supplier_name, email, phone, address, username, password, role);
            slist.add(supplier);
            return slist;
            
        }
        return null;
    }
}

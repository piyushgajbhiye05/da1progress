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

public class SupplierDAOImpl implements SupplierDAO{

    public class SupplierDAOImpl implements SupplierDAO {

    public SupplierDAOImpl() {}

    public int addSupplier(Supplier supplier) throws SQLException {
        Connection con = DatabaseConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO supplier (supplier_name, email, username, password) VALUES (?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, supplier.getSupplierName());
        ps.setString(2, supplier.getEmail());
        ps.setString(3, supplier.getUsername());
        ps.setString(4, supplier.getPassword());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            supplier.setSupplierId(rs.getInt(1));
            return supplier.getSupplierId();
        }
        return -1;
    }

    public Supplier getSupplierById(int supplierId) throws SQLException {
        Connection con = DatabaseConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM supplier WHERE supplier_id=?");
        ps.setInt(1, supplierId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Supplier s = new Supplier();
            s.setSupplierId(rs.getInt("supplier_id"));
            s.setSupplierName(rs.getString("supplier_name"));
            s.setEmail(rs.getString("email"));
            s.setUsername(rs.getString("username"));
            s.setPassword(rs.getString("password"));
            return s;
        }
        return null;
    }

    public void updateSupplier(Supplier supplier) throws SQLException {
        Connection con = DatabaseConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement(
                "UPDATE supplier SET supplier_name=?, email=? WHERE supplier_id=?");

        ps.setString(1, supplier.getSupplierName());
        ps.setString(2, supplier.getEmail());
        ps.setInt(3, supplier.getSupplierId());
        ps.executeUpdate();
    }

    public void deleteSupplier(int supplierId) throws SQLException {
        Connection con = DatabaseConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement("DELETE FROM supplier WHERE supplier_id=?");
        ps.setInt(1, supplierId);
        ps.executeUpdate();
    }

    public List<Supplier> getAllSuppliers() throws SQLException {
        List<Supplier> list = new ArrayList<>();
        Connection con = DatabaseConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM supplier");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Supplier s = new Supplier();
            s.setSupplierId(rs.getInt("supplier_id"));
            s.setSupplierName(rs.getString("supplier_name"));
            s.setEmail(rs.getString("email"));
            s.setUsername(rs.getString("username"));
            s.setPassword(rs.getString("password"));
            list.add(s);
        }
        return list;
    }
}

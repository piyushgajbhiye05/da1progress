package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Product;

public class ProductDAOImpl implements ProductDAO{
 public ProductDAOImpl() {}

    public int addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO product (warehouse_id, product_name, product_description, quantity, price) VALUES (?,?,?,?,?)";
        Connection con = DatabaseConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, product.getWarehouseId());
        ps.setString(2, product.getProductName());
        ps.setString(3, product.getProductDescription());
        ps.setInt(4, product.getQuantity());
        ps.setLong(5, product.getPrice());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            product.setProductId(rs.getInt(1));
            return product.getProductId();
        }
        return -1;
    }

    public Product getProductById(int productId) throws SQLException {
        Connection con = DatabaseConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM product WHERE product_id=?");
        ps.setInt(1, productId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Product p = new Product();
            p.setProductId(rs.getInt("product_id"));
            p.setWarehouseId(rs.getInt("warehouse_id"));
            p.setProductName(rs.getString("product_name"));
            p.setProductDescription(rs.getString("product_description"));
            p.setQuantity(rs.getInt("quantity"));
            p.setPrice(rs.getLong("price"));
            return p;
        }
        return null;
    }

    public void updateProduct(Product product) throws SQLException {
        Connection con = DatabaseConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement(
                "UPDATE product SET product_name=?, product_description=?, quantity=?, price=? WHERE product_id=?");

        ps.setString(1, product.getProductName());
        ps.setString(2, product.getProductDescription());
        ps.setInt(3, product.getQuantity());
        ps.setLong(4, product.getPrice());
        ps.setInt(5, product.getProductId());
        ps.executeUpdate();
    }

    public void deleteProduct(int productId) throws SQLException {
        Connection con = DatabaseConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement("DELETE FROM product WHERE product_id=?");
        ps.setInt(1, productId);
        ps.executeUpdate();
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> list = new ArrayList<>();
        Connection con = DatabaseConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM product");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Product p = new Product();
            p.setProductId(rs.getInt("product_id"));
            p.setWarehouseId(rs.getInt("warehouse_id"));
            p.setProductName(rs.getString("product_name"));
            p.setProductDescription(rs.getString("product_description"));
            p.setQuantity(rs.getInt("quantity"));
            p.setPrice(rs.getLong("price"));
            list.add(p);
        }
        return list;
    }

}

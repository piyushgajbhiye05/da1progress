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

public class ProductDAOImpl implements ProductDAO {
    private Connection connection;
    public ProductDAOImpl() {
        this.connection = DatabaseConnectionManager.getConnection();
    }

    @Override
    public int addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO product(warehouse_id,product_name,product_description,quantity,price) Values(?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, product.getWarehouseId());
        ps.setString(2, product.getProductName());
        ps.setString(3, product.getProductDescription());
        ps.setInt(4, product.getQuantity());
        ps.setDouble(5, product.getPrice());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            int id=rs.getInt(1);
            product.setProductId(id);
            return id;
        }
        return -1;
  
    }

    @Override
    public Product getProductById(int productId)throws SQLException {
        Product product = null;
        String sql = "SELECT * FROM product WHERE product_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, productId);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            int id = rs.getInt("product_id");
            int warehouse_id = rs.getInt("warehouse_id");
            String product_name = rs.getString("product_name");
            String product_description = rs.getString("product_description");
            int quantity = rs.getInt("quantity");
            Long price = rs.getLong("price");
            return product = new Product(id, warehouse_id, product_name, product_description, quantity, price);
        }
        return null;
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        String sql ="UPDATE product SET product_name=?,product_description=?,quantity=?,price=? WHERE product_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, product.getProductName());
        ps.setString(2, product.getProductDescription());
        ps.setInt(3, product.getQuantity());
        ps.setLong(4, product.getPrice());
        ps.setInt(1, product.getProductId());
        ps.executeUpdate();
    }
    @Override
    public void deleteProduct(int productId) throws SQLException {
        String sql ="DELETE FROM product WHERE product_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, productId);
        ps.executeUpdate();
    }

    @Override
    public List<Product> getAllProducts() throws SQLException {
        List<Product> plist = new ArrayList<>();
        String sql = "SELECT * FROM product";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int id = rs.getInt("product_id");
            int warehouse_id = rs.getInt("warehouse_id");
            String product_name = rs.getString("product_name");
            String product_description = rs.getString("product_description");
            int quantity = rs.getInt("quantity");
            Long price = rs.getLong("price");
            Product product = new Product(id, warehouse_id, product_name, product_description, quantity, price);
            plist.add(product);
            
        }
        return plist;
 
    }

}

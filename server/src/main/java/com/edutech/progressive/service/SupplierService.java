package com.edutech.progressive.service;


import com.edutech.progressive.entity.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface SupplierService {
    public List<Supplier> getAllSuppliers() throws SQLException;

    public int addSupplier(Supplier supplier) throws SQLException;

    public List<Supplier> getAllSuppliersSortedByName() throws SQLException;

    public default void emptyArrayList(){
    }

    //Do not implement these methods in SupplierServiceImplArraylist.java class
    public default void updateSupplier(Supplier supplier) throws SQLException{
    }

    public default void deleteSupplier(int supplierId)throws SQLException {
    }

    public default Supplier getSupplierById(int supplierId)throws SQLException {
        return null;
    }

}

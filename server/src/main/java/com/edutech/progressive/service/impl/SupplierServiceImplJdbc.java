package com.edutech.progressive.service.impl;

import java.sql.SQLException;

import java.util.Collections;
import java.util.List;

import com.edutech.progressive.dao.SupplierDAO;

import com.edutech.progressive.entity.Supplier;
import com.edutech.progressive.service.SupplierService;

public class SupplierServiceImplJdbc implements SupplierService {


     private SupplierDAO supplierDAO;

     
    public SupplierServiceImplJdbc(SupplierDAO supplierDAO) {
        this.supplierDAO = supplierDAO;
    }

    
    @Override
    public List<Supplier> getAllSuppliers() throws SQLException{
       
       
       return supplierDAO.getAllSuppliers();
    }

    @Override
    public int addSupplier(Supplier supplier) throws SQLException{
       
       return supplierDAO.addSupplier(supplier);
    }

    @Override
    public List<Supplier> getAllSuppliersSortedByName() throws SQLException{
     
        List<Supplier> list = supplierDAO.getAllSuppliers();
        Collections.sort(list);
        return list;
      
    
    }
    @Override
    public void updateSupplier(Supplier supplier) throws SQLException {
        supplierDAO.updateSupplier(supplier);
    }
    @Override
    public void deleteSupplier(int supplierId) throws SQLException {
        supplierDAO.deleteSupplier(supplierId);
    }
    @Override
    public Supplier getSupplierById(int supplierId) throws SQLException{
        return supplierDAO.getSupplierById(supplierId);
    }
}

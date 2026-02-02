package com.edutech.progressive.service.impl;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import com.edutech.progressive.entity.Supplier;
import com.edutech.progressive.service.SupplierService;

public class SupplierServiceImplArraylist implements SupplierService {
    List<Supplier> suplist = new ArrayList<>();
    @Override
    public List<Supplier> getAllSuppliers() {
        // TODO Auto-generated method stub
       // throw new UnsupportedOperationException("Unimplemented method 'getAllSuppliers'");
       return suplist;
    }

    @Override
    public int addSupplier(Supplier supplier) {
        // TODO Auto-generated method stub
       // throw new UnsupportedOperationException("Unimplemented method 'addSupplier'");
       suplist.add(supplier); 
       return suplist.size();
    }

    @Override
    public List<Supplier> getAllSuppliersSortedByName() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'getAllSuppliersSortedByName'");
        Collections.sort(suplist);
        return suplist;
    
    }
    //public void emptyArray(){
     //   
    //}

}

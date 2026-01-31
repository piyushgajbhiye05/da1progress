package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.edutech.progressive.entity.Supplier;
import com.edutech.progressive.service.SupplierService;

public class SupplierServiceImplArraylist implements SupplierService  {
    List<Supplier> newlist = new ArrayList<>();
    @Override
    public List<Supplier> getAllSuppliers() {
        return newlist;
    }
    @Override
    public int addSupplier(Supplier supplier) {
        newlist.add(supplier);
        return newlist.size();
    }
    @Override
    public List<Supplier> getAllSuppliersSortedByName() {
        Collections.sort(newlist);
        return newlist;
    }

}
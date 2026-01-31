package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.edutech.progressive.entity.Warehouse;
import com.edutech.progressive.service.WarehouseService;

public class WarehouseServiceImplArraylist implements WarehouseService {
    List<Warehouse> wlist = new ArrayList<>();

    @Override
    public List<Warehouse> getAllWarehouses() {
        return wlist;
    }

    @Override
    public int addWarehouse(Warehouse warehouse) {
        wlist.add(warehouse);
        return wlist.size();
    }

    @Override
    public List<Warehouse> getWarehousesSortedByCapacity() {
       Collections.sort(wlist);
       return wlist;

    }

}
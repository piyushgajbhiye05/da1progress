package com.edutech.progressive.service.impl;

import java.sql.SQLException;

import java.util.Collections;
import java.util.List;

import com.edutech.progressive.dao.WarehouseDAO;
import com.edutech.progressive.entity.Warehouse;
import com.edutech.progressive.service.WarehouseService;

public class WarehouseServiceImplJdbc implements WarehouseService {

    private WarehouseDAO warehouseDAO;


    
    public WarehouseServiceImplJdbc(WarehouseDAO warehouseDAO) {
        this.warehouseDAO = warehouseDAO;
    }

    @Override
    public List<Warehouse> getAllWarehouses() throws SQLException{
            return warehouseDAO.getAllWarehouse();
    }

    @Override
    public int addWarehouse(Warehouse warehouse) throws SQLException {
        return warehouseDAO.addWarehouse(warehouse);
    }

    @Override
    public List<Warehouse> getWarehousesSortedByCapacity() throws SQLException{
            List<Warehouse> list = warehouseDAO.getAllWarehouse();
            Collections.sort(list);
            return list;
        }
        @Override
        public void updateWarehouse(Warehouse warehouse) throws SQLException{
            warehouseDAO.updateWarehouse(warehouse);
        }
    @Override
    public void deleteWarehouse(int warehouseId) throws SQLException{
        warehouseDAO.deleteWarehouse(warehouseId);
    }
    @Override
    public Warehouse getWarehouseById(int warehouseId) throws SQLException {
        return warehouseDAO.getWarehouseById(warehouseId);
    }

}

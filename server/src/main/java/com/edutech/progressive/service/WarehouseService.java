package com.edutech.progressive.service;

import com.edutech.progressive.entity.Warehouse;

import java.sql.SQLException;
import java.util.List;

public interface WarehouseService {
    List<Warehouse> getAllWarehouses() throws SQLException;

    int addWarehouse(Warehouse warehouse) throws SQLException;

    List<Warehouse> getWarehousesSortedByCapacity() throws SQLException;

    public default void emptyArrayList() {
    }
    //Do not implement these methods in WarehouseServiceImplArraylist.java class
    public default void updateWarehouse(Warehouse warehouse)throws SQLException{
    }
    public default void deleteWarehouse(int warehouseId)throws SQLException {
    }
    public default Warehouse getWarehouseById(int warehouseId)throws SQLException {
        return null;
    }

    //Do not implement these methods in WarehouseServiceImplArraylist.java and WarehouseServiceImplJdbc.java class
    public default List<Warehouse> getWarehouseBySupplier(int supplierId) {
        return null;
    }
}

package com.example.demojpamultidatasource.dao;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class UserMultiRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return UserDbContext.getCurrentDb();
    }
}

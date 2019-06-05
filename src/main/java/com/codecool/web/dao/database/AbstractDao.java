package com.codecool.web.dao.database;

import java.sql.Connection;

abstract class AbstractDao implements AutoCloseable {
    protected final Connection connection;

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }
}

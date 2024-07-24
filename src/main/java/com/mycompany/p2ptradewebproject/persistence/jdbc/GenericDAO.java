package com.mycompany.p2ptradewebproject.persistence.jdbc;

import com.mycompany.p2ptradewebproject.persistence.connection.AbstractDataSource;
import com.mycompany.p2ptradewebproject.persistence.jdbc.interfaces.IGenericDAO;
import com.mycompany.p2ptradewebproject.persistence.jdbc.mapper.ResultSetMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class GenericDAO<T> implements IGenericDAO<T> {

    private static final Logger LOGGER = Logger.getLogger(GenericDAO.class.getSimpleName());

    private static final int COLUMN_ID = 1;

    private final AbstractDataSource dataSource;

    private final ResultSetMapper<T> mapper;

    public GenericDAO(AbstractDataSource dataSource, ResultSetMapper<T> mapper) {
        this.dataSource = dataSource;
        this.mapper = mapper;
    }


    @Override
    public void create(T t) {

    }

    @Override
    public void update(T t, String[] params) {

    }

    @Override
    public void delete(T t) {

    }

    @Override
    public List<T> findAll() {
        System.out.println(mapper.getClass().getSimpleName());
        return findByDynamicSelect(mapper.getQuerySelectAll());
    }

    @Override
    public Optional<T> findById(long id) {
        return findSingleByDynamicSelect(mapper.getQuerySelectAll() + " WHERE id=?", id);
    }

    public Integer count() {
        try (Statement statement = dataSource.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(mapper.getQueryCount())) {
            if (resultSet.next()) {
                return resultSet.getInt(COLUMN_ID);
            }
        } catch (SQLException e) {
            LOGGER.severe("Error during count operation: " + e.getMessage());
            throw new RuntimeException("Error during count operation. ", e);
        }
        return null;
    }

    private List<T> findByDynamicSelect(String sql, Object... sqlParams) {
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql)) {
            for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
                preparedStatement.setObject(i + 1, sqlParams[i]);
            }
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return fetchMultiResults(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.severe("Error during select operation: " + e.getMessage());
            throw new RuntimeException("Error during select operation. ", e);
        }
    }

    protected Optional<T> findSingleByDynamicSelect(String sql, Object... sqlParams) {
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql)) {
            for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
                preparedStatement.setObject(i + 1, sqlParams[i]);
            }
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return fetchSingleResult(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.severe("Error during select operation: " + e.getMessage());
            throw new RuntimeException("Error during select operation. ", e);
        }
    }

    private List<T> fetchMultiResults(ResultSet resultSet) throws SQLException {
        List<T> resultList = new ArrayList<>();
        while (resultSet.next()) {
            T result = mapper.map(resultSet);
            resultList.add(result);
        }
        return resultList;
    }

    private Optional<T> fetchSingleResult(ResultSet resultSet) throws SQLException {
        T result = null;
        if (resultSet.next()) {
            result = mapper.map(resultSet);
        }
        return Optional.ofNullable(result);
    }

}

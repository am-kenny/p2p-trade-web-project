package com.mycompany.p2ptradewebproject.persistence.jdbc;

import com.mycompany.p2ptradewebproject.persistence.connection.AbstractDataSource;
import com.mycompany.p2ptradewebproject.persistence.jdbc.interfaces.IDAOBankAccount;
import com.mycompany.p2ptradewebproject.persistence.entities.BankAccountEntity;
import com.mycompany.p2ptradewebproject.persistence.jdbc.mapper.ResultSetMapper;

import java.sql.*;
import java.util.logging.Logger;

public class BankAccountDAO extends GenericDAO<BankAccountEntity> implements IDAOBankAccount {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getSimpleName());
    private static BankAccountDAO instance;
    private AbstractDataSource dataSource;
    private ResultSetMapper<BankAccountEntity> mapper;

    private static final String INSERT_BANK = "INSERT INTO bank_account(card_number, bank_id, user_id, currency_id, cardholder_name) VALUE(?,?,?,?,?)";
    private static final String UPDATE_BANK = "UPDATE bank_account SET card_number=?, bank_id=?, user_id=?, currency_id=?, cardholder_name=? WHERE id=?";
    private static final String DELETE_BANK = "DELETE FROM bank_account WHERE id=?";


    private BankAccountDAO(AbstractDataSource dataSource, ResultSetMapper<BankAccountEntity> mapper) {
        super(dataSource, mapper);
        this.dataSource = dataSource;
        this.mapper = mapper;
    }

    public static synchronized BankAccountDAO getInstance(AbstractDataSource dataSource, ResultSetMapper<BankAccountEntity> mapper) {
        if (instance == null) {
            instance = new BankAccountDAO(dataSource, mapper);
        }
        return instance;
    }


    @Override
    public void create(BankAccountEntity bankAccountEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(INSERT_BANK)) {
            ps.setInt(1, bankAccountEntity.getCardNumber());
            ps.setInt(2, bankAccountEntity.getBankId());
            ps.setInt(3, bankAccountEntity.getUserId());
            ps.setInt(4, bankAccountEntity.getCurrencyId());
            ps.setString(5, bankAccountEntity.getCardholderName());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    @Override
    public void update(BankAccountEntity bankAccountEntity, String[] params) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_BANK)) {
            ps.setInt(1, bankAccountEntity.getCardNumber());
            ps.setInt(2, bankAccountEntity.getBankId());
            ps.setInt(3, bankAccountEntity.getUserId());
            ps.setInt(4, bankAccountEntity.getCurrencyId());
            ps.setString(5, bankAccountEntity.getCardholderName());
            ps.setInt(6, bankAccountEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    @Override
    public void delete(BankAccountEntity bankAccountEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(DELETE_BANK)) {
            ps.setInt(1, bankAccountEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

}

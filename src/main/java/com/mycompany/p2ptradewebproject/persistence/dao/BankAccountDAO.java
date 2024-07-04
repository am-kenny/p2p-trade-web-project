package com.mycompany.p2ptradewebproject.persistence.dao;

import com.mycompany.p2ptradewebproject.persistence.connection.DataSource;
import com.mycompany.p2ptradewebproject.persistence.dao.interfaces.IDAOBankAccount;
import com.mycompany.p2ptradewebproject.persistence.entities.BankAccountEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

public class BankAccountDAO implements IDAOBankAccount {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getSimpleName());
    private static BankAccountDAO instance;


    private static final String ID_LABEL = "id";
    private static final String CARD_NUMBER_LABEL = "card_number";
    private static final String BANK_ID_LABEL = "bank_id";
    private static final String USER_ID_LABEL = "user_id";
    private static final String CURRENCY_ID_LABEL = "currency_id";
    private static final String CARDHOLDER_NAME_LABEL = "cardholder_name";


    private static final String INSERT_BANK = "INSERT INTO bank_account(card_number, bank_id, user_id, currency_id, cardholder_name) VALUE(?,?,?,?,?)";
    private static final String SELECT_ALL_BANKS = "SELECT * FROM bank_account";
    private static final String SELECT_BANK_ACCOUNT = "SELECT * FROM bank_account WHERE id=?";
    private static final String UPDATE_BANK = "UPDATE bank_account SET card_number=?, bank_id=?, user_id=?, currency_id=?, cardholder_name=? WHERE id=?";
    private static final String DELETE_BANK = "DELETE FROM bank_account WHERE id=?";

    private DataSource dataSource;

    private BankAccountDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static synchronized BankAccountDAO getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new BankAccountDAO(dataSource);
        }
        return instance;
    }


    @Override
    public Optional<BankAccountEntity> get(long id) {
        Connection connection = dataSource.getConnection();
        try (
                PreparedStatement ps = connection.prepareStatement(SELECT_BANK_ACCOUNT)) {
            ps.setLong(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {

                if (resultSet.next()) {
                    return Optional.of(mapResultSetToBankAccountEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("Error during querying database (find Bank Account)." + e.getMessage());
            throw new RuntimeException("Error during querying database (find Bank Account).", e);
        }
        return Optional.empty();
    }

    @Override
    public List<BankAccountEntity> getAll() {
        List<BankAccountEntity> result = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_ALL_BANKS)) {
            while (resultSet.next()) {
                result.add(mapResultSetToBankAccountEntity(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.severe("Error during querying database (find Bank Accounts)" + e.getMessage());
            throw new RuntimeException("Error during querying database (find Bank Accounts)", e);
        }
        return result;
    }

    @Override
    public void create(BankAccountEntity bankAccountEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(INSERT_BANK)) {
            ps.setInt(1, bankAccountEntity.getCardNumber());
            ps.setInt(2, bankAccountEntity.getBank().getId());
            ps.setInt(3, bankAccountEntity.getUser().getId());
            ps.setInt(4, bankAccountEntity.getCurrency().getId());
            ps.setString(5, bankAccountEntity.getCardholderName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(BankAccountEntity bankAccountEntity, String[] params) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_BANK)) {
            ps.setInt(1, bankAccountEntity.getCardNumber());
            ps.setInt(2, bankAccountEntity.getBank().getId());
            ps.setInt(3, bankAccountEntity.getUser().getId());
            ps.setInt(4, bankAccountEntity.getCurrency().getId());
            ps.setString(5, bankAccountEntity.getCardholderName());
            ps.setInt(6, bankAccountEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(BankAccountEntity bankAccountEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(DELETE_BANK)) {
            ps.setInt(1, bankAccountEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private BankAccountEntity mapResultSetToBankAccountEntity(ResultSet resultSet) throws SQLException, NoSuchElementException {
        return new BankAccountEntity(
                resultSet.getInt(ID_LABEL),
                resultSet.getInt(CARD_NUMBER_LABEL),
                BankDAO.getInstance(dataSource).get(resultSet.getInt(BANK_ID_LABEL)).orElseThrow(),
                UserDAO.getInstance(dataSource).get(resultSet.getInt(USER_ID_LABEL)).orElseThrow(),
                CurrencyDAO.getInstance(dataSource).get(resultSet.getInt(CURRENCY_ID_LABEL)).orElseThrow(),
                resultSet.getString(CARDHOLDER_NAME_LABEL)
        );
    }
}

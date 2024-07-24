package com.mycompany.p2ptradewebproject.persistence;

import com.mycompany.p2ptradewebproject.persistence.jdbc.factory.AbstractDAOFactory;
import com.mycompany.p2ptradewebproject.persistence.jdbc.factory.MySqlDAOFactory;
import com.mycompany.p2ptradewebproject.persistence.jdbc.interfaces.*;
import com.mycompany.p2ptradewebproject.persistence.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Runner {
    public static void main(String[] args) {
        // DAO Factories
        AbstractDAOFactory daoFactory = AbstractDAOFactory.getMySqlDAOFactory();
        IDAOUser userDAO = daoFactory.getUserDAO();
        IDAOCurrency currencyDAO = daoFactory.getCurrencyDAO();
        IDAOBank bankDAO = daoFactory.getBankDAO();
        IDAOTrade tradeDAO = daoFactory.getTradeDAO();
        IDAOFeedback feedbackDAO = daoFactory.getFeedbackDAO();
        IDAOMessage messageDAO = daoFactory.getMessageDAO();

//        UserEntity newUser = new UserEntity("username", "email@example.com", "12345678");
//        userDAO.create(newUser);


//        tradeDAO.create(new TradeEntity(
//                2,
//                true,
//                1,
//                1250f,
//                2,
//                22.3f
//        ));


//        feedbackDAO.create(new FeedbackEntity(
//                userDAO.get(1).orElseThrow(),
//                tradeDAO.get(1).orElseThrow(),
//                true,
//                "Great trade!"
//        ));

//        feedbackDAO.delete(feedbackDAO.get(2).orElseThrow());


        // Separate transactions
        List<UserEntity> userRes = userDAO.findAll();
        Optional<UserEntity> optionalUser = userDAO.findById(1);
        List<CurrencyEntity> currencyRes = currencyDAO.findAll();
        List<BankEntity> bankRes = bankDAO.findAll();
        List<TradeEntity> tradeRes = new ArrayList<>();
        List<FeedbackEntity> feedbackRes = new ArrayList<>();

        // One transaction
        MySqlDAOFactory
                .getMySqlDAOFactory()
                .doInTransaction(() -> {
                    tradeRes.addAll(tradeDAO.findAll());
                    feedbackRes.addAll(feedbackDAO.findAll());
                    return true;
                });


        // Printing results
        System.out.println("Users: ");
        printDaoResults(userRes);
        System.out.println("One user: ");
        printDaoResults(optionalUser);
        System.out.println("Currencies: ");
        printDaoResults(currencyRes);
        System.out.println("Banks: ");
        printDaoResults(bankRes);
        System.out.println("Trades: ");
        printDaoResults(tradeRes);
        System.out.println("Feedbacks: ");
        printDaoResults(feedbackRes);
    }


    // Print results depending on parameter datatype
    public static <T> void printDaoResults(T data) {
        if (data instanceof Optional<?> optional) {

            if (optional.isPresent()) {
                System.out.println(optional.orElseThrow());
            }
            return;
        }
        if (data instanceof List<?> resultList) {
            if (!resultList.isEmpty()) {
                resultList.forEach(System.out::println);
            }
            return;
        }
        throw new RuntimeException("Unsupported type");
    }
}
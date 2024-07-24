package com.mycompany.p2ptradewebproject.service.transaction;

import com.mycompany.p2ptradewebproject.persistence.connection.AbstractDataSource;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TransactionProxy implements InvocationHandler {
    private final Object target;
    private final AbstractDataSource transactionManager;

    public TransactionProxy(final Object target, final AbstractDataSource transactionManager) {
        this.target = target;
        this.transactionManager = transactionManager;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Transactional.class)) {
            return transactionManager.doInTransaction(() -> {
                try {
                    return method.invoke(target, args);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        } else {
            return method.invoke(target, args);
        }
    }


    public static <T> T createProxy(T target, AbstractDataSource transactionManager) {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new TransactionProxy(target, transactionManager)
        );
    }

}

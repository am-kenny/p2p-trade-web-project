package com.mycompany.p2ptradewebproject.presentation.manager;

import java.util.ResourceBundle;

public class MessageProperties {
    private static MessageProperties instance;
    private ResourceBundle resource;
    private static final String BUNDLE_NAME = "messages";
    public static final String LOGIN_ERROR = "LOGIN_ERROR";
    public static final String SERVLET_EXCEPTION = "SERVLET_EXCEPTION";
    public static final String IO_EXCEPTION = "IO_EXCEPTION";



    public static MessageProperties getInstance() {
        if (instance == null) {
            instance = new MessageProperties();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}

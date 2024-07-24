package com.mycompany.p2ptradewebproject.presentation.manager;

import java.util.ResourceBundle;

public class PageProperties {
    private static PageProperties instance;
    private ResourceBundle resource;
    private static final String BUNDLE_NAME = "pages";
    public static final String ERROR_PAGE_PATH = "ERROR";
    public static final String MAIN_PAGE_PATH = "MAIN";
    public static final String LOGIN_PAGE_PATH = "LOGIN";



    public static PageProperties getInstance() {
        if (instance == null) {
            instance = new PageProperties();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}

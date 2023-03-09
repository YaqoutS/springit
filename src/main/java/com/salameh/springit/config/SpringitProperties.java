package com.salameh.springit.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("springit") // now we can prefix our properties with springit
public class SpringitProperties {

    /**
     * This is our welcome message.
     */
    private String welcomeMsg = "Hello, World!";

    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public void setWelcomeMsg(String welcomeMsg) {
        this.welcomeMsg = welcomeMsg;
    }
}

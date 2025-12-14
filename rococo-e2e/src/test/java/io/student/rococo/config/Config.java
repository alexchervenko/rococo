package io.student.rococo.config;

public interface Config {

    static Config getInstance() {
        return LocalConfig.INSTANCE;
    }

    String frontUrl();

    String userJdbcUrl();

    String dbUsername();

    String dbPassword();
}

package com.codecool.web.dao.parser;

public final class DaoParserException extends Exception {

    public DaoParserException() {
        super();
    }

    public DaoParserException(String message) {
        super(message);
    }

    public DaoParserException(Throwable throwable) {
        super(throwable);
    }

    public DaoParserException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

package com.thejaxonhill.mtg.exception;

public class MtgExpressionException extends RuntimeException {
    public MtgExpressionException() {
        super("\"with\" value must be set on expression.");
    }
}

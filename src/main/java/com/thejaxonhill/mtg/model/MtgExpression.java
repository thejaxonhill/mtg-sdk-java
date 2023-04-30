package com.thejaxonhill.mtg.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MtgExpression {
    private final String expression;

    public static class MtgExpressionBuilder {
        StringBuilder expressionBuilder;

        MtgExpressionBuilder(String value) {
            this.expressionBuilder = new StringBuilder(value);
        }

        public MtgExpressionBuilder and(String value) {
            this.expressionBuilder.append("|").append(value);
            return this;
        }

        public MtgExpressionBuilder or(String value) {
            this.expressionBuilder.append(",").append(value);
            return this;
        }

        public MtgExpression build() {
            return new MtgExpression(expressionBuilder.toString());
        }
    }

    public static MtgExpressionBuilder builder(String value) {
        return new MtgExpressionBuilder(value);
    }

}

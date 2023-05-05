package com.thejaxonhill.mtg.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MtgExpression {
    private final String expression;

    public static class MtgExpressionBuilder {
        StringBuilder sb;

        MtgExpressionBuilder(String value) {
            this.sb = new StringBuilder(value);
        }

        public MtgExpressionBuilder and(String value) {
            this.sb.append("|").append(value);
            return this;
        }

        public MtgExpressionBuilder or(String value) {
            this.sb.append(",").append(value);
            return this;
        }

        public MtgExpression build() {
            return new MtgExpression(sb.toString());
        }
    }

    public static MtgExpressionBuilder builder(String value) {
        return new MtgExpressionBuilder(value);
    }

}

package com.thejaxonhill.mtg.model;

import com.thejaxonhill.mtg.exception.MtgExpressionException;
import com.thejaxonhill.mtg.shared.MutableBuilder;

import lombok.Getter;

public class MtgExpression {

    @Getter
    private final String expression;

    private MtgExpression(String expression) {
        this.expression = expression;
    }

    public static class MtgExpressionBuilder implements MutableBuilder<MtgExpressionBuilder> {
        StringBuilder sb;
        String with;

        MtgExpressionBuilder() {
            this.sb = new StringBuilder();
        }

        public MtgExpressionBuilder with(String with) {
            this.with = with;
            return this;
        }

        public MtgExpressionBuilder and(String value) {
            this.sb.append(",").append(value);
            return this;
        }

        public MtgExpressionBuilder or(String value) {
            this.sb.append("|").append(value);
            return this;
        }

        public MtgExpression build() {
            if (!isValid())
                throw new MtgExpressionException();
            return new MtgExpression(sb.insert(0, with).toString());
        }

        private boolean isValid() {
            return this.with != null && !this.with.isEmpty();
        }
    }

    public static MtgExpressionBuilder builder() {
        return new MtgExpressionBuilder();
    }

}

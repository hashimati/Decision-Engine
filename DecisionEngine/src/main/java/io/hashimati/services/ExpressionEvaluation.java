package io.hashimati.services;

import io.hashimati.domains.Decision;
import jakarta.inject.Singleton;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

@Singleton
public class ExpressionEvaluation
{
    public <T> T eval(String expression, Class<T> type)
    {
        return new SpelExpressionParser()
                .parseExpression(expression).getValue(type);
    }
    public Boolean evalBool(String expression){return eval(expression, Boolean.class); }
    public Double evalDouble(String expression){return eval(expression, Double.class); }
    public Long evalLong(String expression){return eval(expression, Long.class); }
    public Integer evalInteger(String expression){return eval(expression, Integer.class); }
    public String evalString(String expression){return eval(expression, String.class); }
}

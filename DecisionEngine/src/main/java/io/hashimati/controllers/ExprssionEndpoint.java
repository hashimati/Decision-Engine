package io.hashimati.controllers;


import io.hashimati.services.ExpressionEvaluation;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import jakarta.inject.Inject;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

@Controller("/evaluate")
public class ExprssionEndpoint {

    @Inject
    private ExpressionEvaluation expressionEvaluation;

    @Get(value = "/boolean", produces = MediaType.TEXT_PLAIN)
    public Boolean evaluateBoolean(@QueryValue("e") String expression)
    {
        return expressionEvaluation.evalBool(expression);
    }


    @Get(value = "/long", produces = MediaType.TEXT_PLAIN)
    public Long evaluateLong(@QueryValue("e") String expression)
    {
        return expressionEvaluation.evalLong(expression);
    }

    @Get(value = "/double", produces = MediaType.TEXT_PLAIN)
    public Double evaluateDouble(@QueryValue("e") String expression)
    {
        return expressionEvaluation.evalDouble(expression);
    }

    @Get(value = "/String", produces = MediaType.TEXT_PLAIN)
    public String evaluateString(@QueryValue("e") String expression)
    {
        return expressionEvaluation.evalString(expression);
    }
}

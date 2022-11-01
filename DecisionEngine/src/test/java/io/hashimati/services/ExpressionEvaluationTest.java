package io.hashimati.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionEvaluationTest {

    ExpressionEvaluation expressionEvaluation = new ExpressionEvaluation();

    @Test
    void evalBool() {
        assertTrue(expressionEvaluation.evalBool("1 == 1"));
        assertFalse(expressionEvaluation.evalBool("1 + 2 > 4"));
        assertTrue(expressionEvaluation.evalBool("'ahmed'.startsWith('ah')"));
        assertTrue(expressionEvaluation.evalBool("'ahmed' == 'ahmed'"));
    }

    @Test
    void evalDouble() {
        assertTrue(expressionEvaluation.evalDouble("1.0 + 2.3") == 3.3);
    }

    @Test
    void evalLong() {

        assertTrue(expressionEvaluation.evalLong("1 + 2") == 3);

    }

    @Test
    void evalInteger() {
        assertTrue(expressionEvaluation.evalInteger("1 + 2") == 3);
        assertTrue(expressionEvaluation.evalInteger("'ahmed'.length") == 5);
    }

    @Test
    void evalString() {
        assertTrue(expressionEvaluation.evalString("'ahmed'.substring(1)").equals("hmed"));
        assertTrue(expressionEvaluation.evalString("'ahmed'.replace('a','m')").equals("mhmed"));
        assertTrue(expressionEvaluation.evalString("'ahmed'.toUpperCase()").equals("AHMED"));
        assertTrue(expressionEvaluation.evalString("'Ahmed'.toLowerCase()").equals("ahmed"));
    }
}
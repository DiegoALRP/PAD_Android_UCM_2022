package es.ucm.fdi.calculator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CalculatorUnitTest {

    @Test
    public void floatNumber() {
        Calculator calculator = new Calculator();

       assertEquals(5.4f, calculator.sum_2_num(2.1f,3.3f),0.1f);
    }
}
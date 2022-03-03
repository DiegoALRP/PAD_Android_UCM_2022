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

    Calculator calculator;

    @Before
    public void setUp() {

        calculator = new Calculator();
    }

    @Test
    public void floatNumber() {

        assertEquals(5.4f, calculator.sum_2_num(2.1f,3.3f),0.1f);
    }

    @Test
    public void floatNumber1() {

        assertEquals(10.67f, calculator.sum_2_num(6.6f,4.07f),0.1f);
    }

    @Test
    public void floatNumber2() {

        assertEquals(23.358f, calculator.sum_2_num(14.1f,9.258f),0.1f);
    }

    @Test
    public void floatNumber3() {

        assertEquals(193.8451f, calculator.sum_2_num(115.8f,78.0451f),0.1f);
    }

    @Test
    public void integerNumber() {

        assertEquals(253, calculator.sum_2_num(200,53),0);
    }

    @Test
    public void integerNumber2() {

        assertEquals(1673, calculator.sum_2_num(1600,73),0);
    }
}
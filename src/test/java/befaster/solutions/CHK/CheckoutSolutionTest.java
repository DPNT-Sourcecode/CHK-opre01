package befaster.solutions.CHK;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutSolutionTest {

    @ParameterizedTest
    @CsvSource(value = {"A:50","AA:100", "B:30", "C:20", "CCC:60", "D:15", "DDDD:60"}, delimiter = ':')
    void testIfGetsTheCorrectOriginalPrice(String input, String expected) {
        CheckoutSolution checkoutSolution = new CheckoutSolution();
        int result = checkoutSolution.checkout(input);
        assertEquals(Integer.parseInt(expected), result);
    }

    @ParameterizedTest
    @CsvSource(value = {"AAA:130", "AAAAAA:260", "BB:45", "BBBB:90"}, delimiter = ':')
    void testIfGetsTheCorrectSpecialOfferPrice(String input, String expected) {
        CheckoutSolution checkoutSolution = new CheckoutSolution();
        int result = checkoutSolution.checkout(input);
        assertEquals(Integer.parseInt(expected), result);
    }

    @ParameterizedTest
    @CsvSource(value = {/*"AAABBC:195", "CCDDD:85",*/"AAA:130", "AAAA:180", "AAAAA:230", "AAAAAA:230", "BBB:75"}, delimiter = ':')
    void testIfGetsTheCorrectPrice(String input, String expected) {
        CheckoutSolution checkoutSolution = new CheckoutSolution();
        int result = checkoutSolution.checkout(input);
        assertEquals(Integer.parseInt(expected), result);
    }

    @Test
    void testIfGetsZeroWhenReceivedNullValue() {
        CheckoutSolution checkoutSolution = new CheckoutSolution();
        int result = checkoutSolution.checkout(null);
        int expected = 0;
        assertEquals(expected, result);
    }

    @Test
    void testIfGetsZeroWhenReceivedEmptyValue() {
        CheckoutSolution checkoutSolution = new CheckoutSolution();
        int result = checkoutSolution.checkout("");
        int expected = 0;
        assertEquals(expected, result);
    }

    @Test
    void testIfGetsNegative1WhenReceivedInvalidInput() {
        CheckoutSolution checkoutSolution = new CheckoutSolution();
        int result = checkoutSolution.checkout("x");
        int expected = -1;
        assertEquals(expected, result);
    }




}






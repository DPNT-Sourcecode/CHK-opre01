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
}


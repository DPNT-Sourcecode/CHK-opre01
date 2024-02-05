package befaster.solutions.CHK;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutSolutionTest {

    @ParameterizedTest
    @CsvSource(value = {"A:50","AA:100", "B:30", "C:20", "CCC:60", "D:15", "DDDD:30"}, delimiter = ':')
    void testIfGetsTheCorrectPrice() {
        CheckoutSolution checkoutSolution = new CheckoutSolution();
        int result = checkoutSolution.checkout("AA");
        int expectedResult = 100;

        assertEquals(expectedResult, result);
    }
}

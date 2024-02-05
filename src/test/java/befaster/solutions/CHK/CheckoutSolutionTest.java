package befaster.solutions.CHK;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutSolutionTest {

    @Test
    void checkout() {
        CheckoutSolution checkoutSolution = new CheckoutSolution();
        int result = checkoutSolution.checkout("AAABBBCCCD");
        int expectedResult = 0;

        assertEquals(expectedResult, result);
    }
}

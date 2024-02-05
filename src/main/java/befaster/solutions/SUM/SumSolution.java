package befaster.solutions.SUM;

import befaster.runner.SolutionNotImplementedException;

@SuppressWarnings("unused")
public class SumSolution {

    public int compute(int x, int y) {
        if(!validateNumberRangeBetweenZeroAndOneHundred(x) && !validateNumberRangeBetweenZeroAndOneHundred(y)) {
            return -1;
        }
        return Integer.sum(x,y);
    }

    private boolean validateNumberRangeBetweenZeroAndOneHundred(int number) {
        return number >= 0 && number <= 100;
    }

}



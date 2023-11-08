package lotto;

import java.util.HashMap;
import java.util.Map;

public class Result {
    private Map<ResultCase, Integer> result;

    public Result() {
        result = new HashMap<>();
    }

    public void changeResult(ResultCase resultCase, int matchLotto) {
        if (result.containsKey(resultCase)) {
            result.put(resultCase, result.get(resultCase) + matchLotto);
            return;
        }
        result.put(resultCase, matchLotto);
    }

    public double getCalculateRateOfReturn(int buyingPrice) {
        double rateOfReturn = ((double) getWinningPrice() / buyingPrice) * 100;
        double refinedRateOfReturn = (double) Math.round(rateOfReturn * Math.pow(10, 2)) / Math.pow(10, 2);
        return refinedRateOfReturn;
    }

    private int getWinningPrice() {
        int winningPrice = 0;
        for (Map.Entry<ResultCase, Integer> entry : result.entrySet()) {
            ResultCase resultCase = entry.getKey();
            Integer matchLotto = entry.getValue();
            winningPrice += resultCase.getPrice() * matchLotto;
        }
        return winningPrice;
    }
}

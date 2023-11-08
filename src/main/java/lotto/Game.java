package lotto;

import static lotto.Constants.*;
import static lotto.ResultCase.*;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

    private BuyingPrice buyingPrice;
    private List<Lotto> lottos;
    private Winning winning;
    private Bonus bonus;
    private Result result;

    public Game() {
        this.buyingPrice = new BuyingPrice();
        this.lottos = new ArrayList<>();
        this.winning = new Winning();
        this.bonus = new Bonus();
        this.result = new Result();
    }

    public void run() {
        saveBuyingPrice();
        printBuyingCount();
        giveLotto();
        saveWinningNumber();
        saveBonusNumber();
        countWinning();
        printWinning();
        printRateOfReturn();
    }

    private void saveBuyingPrice() {
        saveValue(BUYING_PRICE, buyingPrice);
    }

    private void printBuyingCount() {
        buyingPrice.printCount();
    }

    private void giveLotto() {
        for (int i = 0; i < buyingPrice.getBuyingCount(); i++) {
            List<Integer> uniqueNumbers = Randoms.pickUniqueNumbersInRange(MIN_NUMBER, MAX_NUMBER, COUNT);
            lottos.add(new Lotto(uniqueNumbers));
            show(uniqueNumbers);
        }
    }

    private void show(List<Integer> uniqueNumbers) {
        ArrayList<Integer> copyNumbers = new ArrayList<>(uniqueNumbers);
        Collections.sort(copyNumbers);
        String result = copyNumbers.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(result);
    }

    private void saveWinningNumber() {
        saveValue(WINNING_NUMBER, winning);
    }

    private void saveBonusNumber() {
        saveValue(BONUS_NUMBER, bonus);
    }

    private void countWinning() {
        for (ResultCase resultCase : ResultCase.values()) {
            result.save(resultCase, count(resultCase));
        }
    }

    private Integer count(ResultCase resultCase) {
        int matchNumbers = 0;
        for (Lotto lotto : lottos) {
            int equal = lotto.countEqual(winning.getNumbers());
            if (resultCase == FIVE_AND_BONUS_CORRECTNESS) {
                equal = addBonus(lotto, equal);
            }
            if (equal == resultCase.getCorrectness()) {
                matchNumbers++;
            }
        }
        return matchNumbers;
    }

    private Integer addBonus(Lotto lotto, Integer equalNumber) {
        if (lotto.contain(bonus.getNumber())) {
            equalNumber++;
        }
        return equalNumber;
    }

    private void printWinning() {
        System.out.println(WINNING_STATISTICS);
        result.print();
    }

    private void printRateOfReturn() {
        System.out.println(RATE_OF_RETURN + calculateRateOfReturn() + PERCENT);
    }

    private Double calculateRateOfReturn() {
        return result.getCalculateRateOfReturn(buyingPrice.getPrice());
    }

    private void saveValue(String informComment, Input input) {
        Boolean isNotEnded = true;
        while (isNotEnded) {
            try {
                System.out.println(informComment);
                String readLine = Console.readLine();
                input.save(readLine);
                isNotEnded = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

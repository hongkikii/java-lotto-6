package lotto;

import static lotto.Constants.NUMBERS_SIZE_ERROR;
import static lotto.Constants.NUMBER_DUPLICATE_ERROR;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InputNumbers extends Input {

    protected void checkSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(NUMBERS_SIZE_ERROR);
        }
    }

    protected void checkDuplication(List<Integer> numbers) {
        Set<Integer> noDuplicationNumbers = new HashSet<>(numbers);
        if (numbers.size() != noDuplicationNumbers.size()) {
            throw new IllegalArgumentException(NUMBER_DUPLICATE_ERROR);
        }
    }

    protected List<Integer> translateToValueType(String noEmptyReadLine) {
        String[] split = noEmptyReadLine.split(",");
        return Arrays.stream(split)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
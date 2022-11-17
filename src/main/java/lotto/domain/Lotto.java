package lotto.domain;

import lotto.utils.Validator;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        Validator validator = new Validator();
        validator.isValidWinningNumbers(numbers);
    }

    public List<Integer> getLotteryNumbers() {
        return numbers;
    }
    // TODO: 추가 기능 구현
}

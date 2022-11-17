package lotto.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class Validator {

    private static final String SEPARATOR = ",";
    public static final String LENGTH_OVER_NUMBER = "[ERROR] 6개의 숫자를 입력해야 합니다. 숫자 개수를 올바르게 입력해주세요.";
    private static final String INVALID_NUMBER_INPUT = "[ERROR] 숫자만 입력 가능합니다.";
    private static final String INVALID_MONEY_UNIT = "[ERROR] 1000원 단위로 입력이 가능합니다.";
    private static final String INVALID_MONEY_RANGE = "[ERROR] 1000원 이상 금액을 입력해주세요.";
    private static final String DUPLICATION_NUMBER = "[ERROR] 중복된 숫자가 입력되었습니다.";
    private static final String RANGE_OVER_NUMBER = "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.";
    private static final String INVALID_WINNING_NUMBERS_INPUT = "[ERROR] 숫자와 공백 그리고'" + SEPARATOR + "' 만을 입력해주세요.";
    private static final String INVALID_BONUS_NUMBER_INCLUDES_WINNING_NUMBER = "[ERROR] 보너스 번호는 로또번호에 포함되지 않은 값이어야 합니다.";


    private static final int MONEY_UNIT = 1000;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_LENGTH = 6;


    public int refineAmount(String inputRawAmount) {
        int amount = toNumber(inputRawAmount);
        isValidAmount(amount);
        return amount / MONEY_UNIT; // 나눈 값 자체를 리턴
    }

    public List<Integer> refineWinningNumbers(String inputRawWinningNumbers) {
        List<Integer> winningNumbers = toList(inputRawWinningNumbers);
        isValidWinningNumbers(winningNumbers);
        return winningNumbers;
    }

    public int refineBonusNumber(List<Integer> winningNumbers, String inputRawBonusNumber) {
        int bonusNumber = toNumber(inputRawBonusNumber);
        isValidBonusNumber(winningNumbers, bonusNumber);
        return bonusNumber;
    }

    private int toNumber(String rawNumber) {
        try {
            return Integer.parseInt(rawNumber);
        } catch (Exception e) {
            throw new IllegalArgumentException(INVALID_NUMBER_INPUT);
        }
    }

    private void isValidAmount(int amount) {
        if(amount < MONEY_UNIT) {
            throw new IllegalArgumentException(INVALID_MONEY_RANGE);
        }
        if (amount % MONEY_UNIT != 0) {
            throw new IllegalArgumentException(INVALID_MONEY_UNIT);
        }
    }

    public void isValidWinningNumbers(List<Integer> winningNumbers) {
        if (!checkSize(winningNumbers)) {
            throw new IllegalArgumentException(LENGTH_OVER_NUMBER);
        }

        if (!assertEachNumberSatisfyRange(winningNumbers)) {
            throw new IllegalArgumentException(RANGE_OVER_NUMBER);
        }

        if (!hasUniqueNumbers(winningNumbers)) {
            throw new IllegalArgumentException(DUPLICATION_NUMBER);
        }
    }
    private void isValidBonusNumber(List<Integer> winningNumbers, int bonusNumber) {
        if(!isRightRange(bonusNumber)) {
            throw new IllegalArgumentException(RANGE_OVER_NUMBER);
        }
        if(winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(INVALID_BONUS_NUMBER_INCLUDES_WINNING_NUMBER);
        }
    }


    private List<Integer> toList(String InputRawNumbers) {
        try {
            String deletedBlankSpaceString = InputRawNumbers.replaceAll("\\s", "");
            return Arrays.stream(deletedBlankSpaceString.split(SEPARATOR))
                    .map(Integer::parseInt).sorted().collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_WINNING_NUMBERS_INPUT);
        }
    }




    private boolean checkSize(List<Integer> winningNumbers) {
        return winningNumbers.size() == LOTTO_LENGTH;
    }

    private boolean assertEachNumberSatisfyRange(List<Integer> winningNumbers) {
        return winningNumbers.stream().allMatch(this::isRightRange);
    }

    private boolean hasUniqueNumbers(List<Integer> winningNumbers) {
        final Set<Integer> uniques = new HashSet<>();
        for(Integer number: winningNumbers) {
            if(!uniques.add(number)) {
                return false;
            }
        }
        return true;
    }

    private boolean isRightRange(int number) {
        return number <= MAX_LOTTO_NUMBER && number >= MIN_LOTTO_NUMBER;
    }

}

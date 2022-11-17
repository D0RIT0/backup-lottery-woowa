package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.utils.Validator;

import java.util.List;

public class InputView {

    private static final String INPUT_NUMBER = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER = "당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

    private List<Integer> winningNumbers;


    // constructor lottocontroller처럼 외부에서 받아서 오는 형태로
    Validator validator;

    public InputView() {
        this.validator = new Validator();
    }

    public int inputMoney() {
        System.out.println(INPUT_NUMBER);
        String rawAmount = Console.readLine();
        return validator.refineAmount(rawAmount);
    }


    public List<Integer> inputWinningNumbers() {
        System.out.println(INPUT_WINNING_NUMBER);
        String rawWinningNumbers = Console.readLine();
        List<Integer> winningNumbers = validator.refineWinningNumbers(rawWinningNumbers);
        this.winningNumbers = winningNumbers;
        return winningNumbers;
    }

    public int inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER);
        String rawBonusNumber = Console.readLine();
        return validator.refineBonusNumber(getWinningNumbers(), rawBonusNumber);
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }
}

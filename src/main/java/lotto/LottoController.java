package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    private static InputView inputView;
    private static OutputView outputView;

    public LottoController() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void run() {
        try {
            int amount = inputView.inputMoney();
            LottoMachine lottoMachine = new LottoMachine(amount);
            outputView.printLottoCount(lottoMachine.getCount());
            List<Lotto> tickets = lottoMachine.getLotteryTickets();
            outputView.printLottoNumbers(tickets);
            Lotto winningNumbers = new Lotto(inputView.inputWinningNumbers());
            int bonusNumber = inputView.inputBonusNumber();
            lottoMachine.setWinningNumbers(winningNumbers, bonusNumber);
            outputView.printWinningList(lottoMachine.getWinningList());
            outputView.printYield(lottoMachine.getYield());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

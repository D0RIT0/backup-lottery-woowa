package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    private final LottoMachine lottoMachine;

    public LottoController(InputView inputView, OutputView outputView, LottoMachine lottoMachine) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoMachine = lottoMachine;
    }

    public void first() {
        int amountOfTicket = this.inputView.inputMoney();
        lottoMachine.setAmountOfTickets(amountOfTicket);
        this.outputView.printLottoCount(lottoMachine.getCount());
    }

    public void second() {
        lottoMachine.setLottoTickets();
        List<Lotto> tickets = lottoMachine.getLotteryTickets();
        this.outputView.printLottoNumbers(tickets);
    }

    public void third() {
        List<Integer> winningNumbers = this.inputView.inputWinningNumbers(); // 여기는 잘못됨, EachLotto 이런 식으로 만들어서 Lotto 랑은 자료형 다름
        int bonusNumber = this.inputView.inputBonusNumber();
        lottoMachine.setWinningNumbers(winningNumbers, bonusNumber);
        this.outputView.printWinningList(lottoMachine.getWinningList());
        this.outputView.printYield(lottoMachine.getYield());
    }

    public void run() {
        try {
            first();
            second();
            third();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

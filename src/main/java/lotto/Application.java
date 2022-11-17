package lotto;

import lotto.domain.LottoMachine;
import lotto.utils.Validator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoMachine lottoMachine = new LottoMachine();
        LottoController controller = new LottoController(inputView, outputView, lottoMachine);
        controller.run();
        // TODO: 프로그램 구현
    }
}

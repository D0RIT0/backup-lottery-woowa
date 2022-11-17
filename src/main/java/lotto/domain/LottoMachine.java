package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {

    private static final int LOTTO_SIZE = 6;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int RANK_LIST_SIZE = 8;
    private static final int FIVE_COLLECT = 5;
    private static final int FIVE_COLLECT_ALPHA_SCORE = 1;
    private static final int ALL_COLLECT = 6;
    private static final int ALL_COLLECT_ALPHA_SCORE = 2;
    private static final int INITIAL_VALUE = 0;
    private static final int COLLECT_LOTTO = 1;
    private static final double LOTTO_UNIT_MONEY = 1000.0;
    private static final double PERCENTAGE = 100.0;

    private static List<Integer> winningList;

    public List<Lotto> getLotteryTickets() {
        return lotteryTickets;
    }

    private final List<Lotto> lotteryTickets;
    private WinningNumbers winningNumbers;
    private final int count;



    public LottoMachine(int count) {
        this.count = count / 1000;
        this.lotteryTickets = createLottoTickets();
    }


    public int getCount() {
        return count;
    }


    public List<Lotto> createLottoTickets() {
        List<Lotto> lottoTickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Lotto lotto = createRandomNumbers();
            lottoTickets.add(lotto);
        }
        return lottoTickets;
    }

    public void setWinningNumbers(Lotto lotto, int bonusNumber) {
        winningNumbers = new WinningNumbers(lotto, bonusNumber);
    }

    private Lotto createRandomNumbers() {
        List<Integer> randomNumbers = new ArrayList<>(Randoms.pickUniqueNumbersInRange(MIN_LOTTO_NUMBER,
                MAX_LOTTO_NUMBER, LOTTO_SIZE));
        Collections.sort(randomNumbers);
        return new Lotto(randomNumbers);
    }


    public List<Integer> getWinningList() {
        winningList = IntStream.of(new int[RANK_LIST_SIZE])
                .boxed()
                .collect(Collectors.toList());
        getLotteryTickets().forEach(lotto -> {
            int winningCount = getWinningCount(lotto);
            winningList.set(winningCount, winningList.get(winningCount) + COLLECT_LOTTO);
        });
        return winningList;
    }

    private int getWinningCount(Lotto lotto) {
        int winningCount = (int) lotto.getLotteryTicket().stream()
                .filter(this::checkWinningNumber).count();
        if (winningCount == FIVE_COLLECT && lotto.getLotteryTicket().contains(winningNumbers.getBonusNumber())) {
            return winningCount + FIVE_COLLECT_ALPHA_SCORE;
        }
        if (winningCount == ALL_COLLECT) {
            return winningCount + ALL_COLLECT_ALPHA_SCORE;
        }
        return winningCount;
    }

    private boolean checkWinningNumber(int number) {
        Lotto winningLotto = winningNumbers.getWinningNumbers();
        if (winningLotto.getLotteryTicket().contains(number)) {
            return true;
        }
        return false;
    }

    public double getYield() {
        int moneyAll = INITIAL_VALUE;
        for (int i = INITIAL_VALUE; i < RANK_LIST_SIZE; i++) {
            moneyAll += Rank.findMoney(i).getMoney(winningList.get(i));
        }
        return (double) moneyAll / (LOTTO_UNIT_MONEY * (double) count / PERCENTAGE);
    }


}

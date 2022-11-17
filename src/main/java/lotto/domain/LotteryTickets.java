package lotto.domain;

import java.util.List;

public class LotteryTickets {
    private List<Lotto> lotteryTickets;

    public LotteryTickets(List<Lotto> lotteryTickets) {
        this.lotteryTickets = lotteryTickets;
    }

    public List<Lotto> getLotteryTickets() {
        return lotteryTickets;
    }
}

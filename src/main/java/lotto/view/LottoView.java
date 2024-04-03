package lotto.view;

import lotto.domain.Lottos;
import lotto.domain.Profit;
import lotto.enums.Ranking;
import lotto.domain.Result;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LottoView {
    private static final Scanner scan = new Scanner(System.in);

    public long inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String money = scan.nextLine();
        return Long.parseLong(money);
    }

    public void printLottos(Lottos lottos) {
        int size = lottos.getSize();
        System.out.printf("%d개를 구매했습니다.\n", size);

        for (int i = 0; i < size; i++) {
            System.out.println(lottos.get(i));
        }

        System.out.println();
    }

    public List<Integer> inputAnswer() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String answerLotto = scan.nextLine();
        List<Integer> answerAndBonusBall = Arrays.stream(answerLotto.split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        System.out.println("보너스 볼을 입력해 주세요.");
        String bonusBallNumber = scan.nextLine();
        answerAndBonusBall.add(Integer.parseInt(bonusBallNumber));

        System.out.println();
        return answerAndBonusBall;
    }

    public void printResult(Result result) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        List<Ranking> winRanks = Arrays.stream(Ranking.values())
                .filter(rank -> rank != Ranking.FAIL)
                .collect(Collectors.toList());
        for (Ranking rank : winRanks) {
            String desc = getIndividualRankingDesc(rank);
            long price = rank.getReward();
            int count = result.getScore(rank);

            System.out.println(desc + String.format("(%d원)- %d개", price, count));
        }
    }

    public void printProfit(Profit profit) {
        System.out.println("총 수익률은 " + profit.toString() + "입니다.(기준이 1이기 때문에 결과적으로 " + getGainOrLoss(profit) + "라는 의미임)");
    }

    private String getGainOrLoss(Profit profit) {
        long integerPart = profit.getIntegerPart();
        long decimalPart = profit.getDecimalPart();

        if (integerPart == 1 && decimalPart == 0) {
            return "이익도 손해도 아니";
        }

        if (integerPart < 1) {
            return "손해";
        }

        return "이익이";
    }

    private String getIndividualRankingDesc(Ranking rank) {
        if (rank == Ranking.SECOND) {
            return String.format("%d개 일치, 보너스 볼 일치 ", rank.getMatchCount());
        }

        if (rank == Ranking.FAIL) {
            return "";
        }

        return String.format("%d개 일치 ", rank.getMatchCount());
    }
}

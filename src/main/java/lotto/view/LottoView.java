package lotto.view;

import lotto.domain.Lottos;
import lotto.domain.Profit;
import lotto.enums.Ranking;
import lotto.domain.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LottoView {
    private static final Scanner scan = new Scanner(System.in);

    public long inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String money = scan.nextLine();
        System.out.println();
        return Long.parseLong(money);
    }

    public long inputManualCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        String manualCount = scan.nextLine();
        System.out.println();
        return Long.parseLong(manualCount);
    }

    public List<List<Integer>> inputManualLottos(long manualCount) {
        List<List<Integer>> manualLottoNumbers = new ArrayList<>();
        System.out.println("수동으로 구매할 번호를 입력해주세요.");
        for (int i = 0; i < manualCount; i++) {
            String manualLotto = scan.nextLine();
            manualLottoNumbers.add(Arrays.stream(manualLotto.split(", "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList())
            );
        }
        System.out.println();
        return manualLottoNumbers;
    }

    public void printLottos(Lottos manualLottos, Lottos autoLottos) {
        int manualLottoSize = manualLottos.getSize();
        int autoLottoSize = autoLottos.getSize();

        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manualLottoSize, autoLottoSize);

        for (int i = 0; i < manualLottoSize; i++) {
            System.out.println(manualLottos.get(i));
        }
        for (int i = 0; i < autoLottoSize; i++) {
            System.out.println(autoLottos.get(i));
        }

        System.out.println();
    }

    public List<Integer> inputAnswerNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String answerLotto = scan.nextLine();

        return Arrays.stream(answerLotto.split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public Integer inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String bonusBallNumber = scan.nextLine();
        System.out.println();

        return Integer.parseInt(bonusBallNumber);
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

    private String getIndividualRankingDesc(Ranking rank) {
        if (rank == Ranking.SECOND) {
            return String.format("%d개 일치, 보너스 볼 일치 ", rank.getMatchCount());
        }

        if (rank == Ranking.FAIL) {
            return "";
        }

        return String.format("%d개 일치 ", rank.getMatchCount());
    }

    public void printProfit(Profit profit) {
        System.out.println("총 수익률은 " + profit.toString() + "입니다." + getGainOrLoss(profit));
    }

    private String getGainOrLoss(Profit profit) {
        long integerPart = profit.getIntegerPart();

        if (integerPart < 1) {
            return " (기준이 1이기 때문에 결과적으로 손해라는 의미임)";
        }

        return "";
    }
}

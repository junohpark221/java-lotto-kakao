package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class WinningLotto {
    private Lotto winningNumbers;
    private Ball bonusNumber;
    private static final String INVALID_BONUS_BALL_MESSAGE = "보너스볼은 당첨 번호에 포함되어 있으면 안 됩니다.";

    public WinningLotto(List<Integer> answerNumber, int bonusNumber) {
        this(convertToWinningNumber(answerNumber), Ball.of(bonusNumber));
    }

    public WinningLotto(Lotto winningNumbers, Ball bonusNumber) {
        validateBonusNumberNotInWinningNumbers(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumberNotInWinningNumbers(Lotto answerNumbers, Ball bonusNumber){
        boolean isContain = answerNumbers.contain(bonusNumber);
        if (isContain) {
            throw new IllegalArgumentException(INVALID_BONUS_BALL_MESSAGE);
        }
    }

    private static Lotto convertToWinningNumber(List<Integer> answerNumber) {
        List<Ball> answerBalls = answerNumber
                .stream()
                .map(Ball::of)
                .collect(Collectors.toList());


        return new Lotto(answerBalls);
    }

    public Lotto getWinningNumbers() {
        return this.winningNumbers;
    }

    public Ball getBonusNumber() {
        return this.bonusNumber;
    }
}

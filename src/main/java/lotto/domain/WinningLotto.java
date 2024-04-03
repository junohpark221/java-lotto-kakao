package lotto.domain;

public class WinningLotto {
    private Lotto winningNumbers;
    private Ball bonusNumber;

    public WinningLotto(Lotto winningNumbers, Ball bonusNumber) {
        validateBonusNumberNotInWinningNumbers(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumberNotInWinningNumbers(Lotto answerNumbers, Ball bonusNumber){
        boolean isContain = answerNumbers.contain(bonusNumber);
        if (isContain) {
            throw new RuntimeException();
        }
    }

    public Lotto getWinningNumbers() {
        return this.winningNumbers;
    }

    public Ball getBonusNumber() {
        return this.bonusNumber;
    }
}

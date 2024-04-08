package lotto.domain;

public class Money {
    private final static long LOTTO_PRICE = 1000;
    private final long value;

    public Money(long value) {
        this.value = value;
    }

    public void checkManualLottoCount(long manualCount) {
        long totalLottoCount = value / LOTTO_PRICE;
        validateManualCount(totalLottoCount, manualCount);
    }

    private void validateManualCount(long totalCount, long manualCount) {
        if (manualCount > totalCount) {
            throw new IllegalArgumentException("금액보다 많은 로또를 수동으로 구매할 수 없습니다.");
        }
    }

    public long getTotalLottoCount() {
        return value / LOTTO_PRICE;
    }

    public static long getLottoPrice() {
        return LOTTO_PRICE;
    }

    public long getValue() {
        return this.value;
    }
}

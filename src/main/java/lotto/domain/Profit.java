package lotto.domain;

public class Profit {
    private final long integerPart;
    private final long decimalPart;

    public Profit(long reward, long seed) {
        if (seed == 0) {
            this.integerPart = 1;
            this.decimalPart = 0;
            return;
        }
        long result = reward * 100 / seed;
        this.integerPart = result / 100;
        this.decimalPart = result % 100;
    }

    public long getIntegerPart() {
        return this.integerPart;
    }

    public long getDecimalPart() {
        return this.decimalPart;
    }

    @Override
    public String toString() {
        return String.format("%d.%d", this.integerPart, this.decimalPart);
    }
}

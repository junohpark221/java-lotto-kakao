package lotto.domain;

public class Money {
    private final long value;

    public Money(long value) {
        this.value = value;
    }

    public long getValue() {
        return this.value;
    }

    public long divide (Money money) {
        return this.value / money.getValue();
    }
}

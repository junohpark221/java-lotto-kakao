package lotto.domain;

import lotto.domain.Lotto;

import java.util.List;

public class Lottos {
    private final List<Lotto> values;

    public Lottos(List<Lotto> values) {
        this.values = values;
    }

    public int getSize() {
        return this.values.size();
    }

    public Lotto get(int ix) {
        return values.get(ix);
    }
}

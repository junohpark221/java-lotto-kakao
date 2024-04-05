package lotto.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ball implements Comparable<Ball> {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final String INVALID_NUMBER_RANGE_MESSAGE = "로또 번호는 1 이상 45 이하만 가능합니다.";
    private static final List<Ball> ballPool = IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
            .mapToObj(Ball::new)
            .collect(Collectors.toList());

    private final int number;

    private Ball(int number) {
        this.number = number;
    }

    public static Ball of(int number) {
        validateNumber(number);
        return ballPool.get(number - 1);
    }

    public static List<Ball> allBalls() {
        return ballPool;
    }

    private static void validateNumber(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException(INVALID_NUMBER_RANGE_MESSAGE);
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(Ball ball) {
        int ballNumber = ball.getNumber();

        if (this.number > ballNumber) {
            return 1;
        }

        if (this.number < ballNumber) {
            return -1;
        }

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ball ball = (Ball) o;
        return number == ball.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}

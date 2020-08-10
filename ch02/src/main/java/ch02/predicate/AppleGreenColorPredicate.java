package ch02.predicate;

import ch02.domain.Apple;
import ch02.enums.Color;

public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return Color.GREEN.equals(apple.getColor());
    }
}

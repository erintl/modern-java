package ch02.predicate;

import ch02.domain.Apple;

public interface ApplePredicate {
    boolean test(Apple apple);
}

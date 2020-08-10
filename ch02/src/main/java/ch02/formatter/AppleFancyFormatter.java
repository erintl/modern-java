package ch02.formatter;

import ch02.domain.Apple;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AppleFancyFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
        return "A " + characteristic + " " + apple.getColor() + " apple";
    }
}

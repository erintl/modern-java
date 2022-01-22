package com.modernjava.chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.modernjava.chapter2.Apple;
import com.modernjava.chapter2.Color;

import java.util.function.Function;
import java.util.function.IntPredicate;

public class Examples {

  public static void main(String[] args) {
    // Predicate Example
    predicateExample();

    // Consumer example
    consumerExample();

    // Function example
    functionExample();

    // Primitive predicates
    System.out.println("\nPrimative predicate examples:");
    IntPredicate evenNumber = (int i) -> i % 2 == 0;
    System.out.println(evenNumber.test(1000));
    IntPredicate evenNumber2 = Examples::isEven;
    System.out.println(evenNumber2.test(1000));

    // Method references
    System.out.println("\nMethod reference 1:");
    List<String> strs = Arrays.asList("a", "b", "A", "B");
    strs.sort(((s1, s2) -> s1.compareToIgnoreCase(s2)));
    System.out.println(strs);

    System.out.println("\nMethod reference 2:");
    List<String> strs2 = Arrays.asList("a", "b", "A", "B");
    strs2.sort((String::compareToIgnoreCase));
    System.out.println(strs2);

    // Constructor references
    constructorReferenceExamples();

    // Composing functions
    composingFunctions();

    // Integration
    System.out.println("\n" + integrate((double x) -> x + 10, 3, 7));

    // Runnable examples
    runnableExamples();
  }

  public static <T> List<T> filter(List<T> list, Predicate<T> p) {
    List<T> result = new ArrayList<>();
    for (T e : list) {
      if (p.test(e)) {
        result.add(e);
      }
    }
    return result;
  }

  public static <T> void forEach(List<T> list, Consumer<T> c) {
    for (T e : list) {
      c.accept(e);
    }
  }

  public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
    List<R> result = new ArrayList<>();
    for (T e : list) {
      result.add(f.apply(e));
    }
    return result;
  }

  public static boolean isEven(int number) {
    return number % 2 == 0;
  }

  public static void runnable3() {
    System.out.println("Runnable method reference example");
  }

  public static void composingFunctions() {
    Function<Integer, Integer> f = x -> x + 1;
    Function<Integer, Integer> g = x -> x * 2;
    Function<Integer, Integer> h = f.andThen(g); // (g o f)(x)
    Function<Integer, Integer> k = f.compose(g); // (f o g)(x)

    System.out.println("\nComposing Functions:");
    System.out.println(String.format("(g o f)(x)=%s", h.apply(1)));
    System.out.println(String.format("(f o g)(x)=%s", k.apply(1)));

    String testText = "This is a labda letter";
    Function<String, String> addHeader = Letter::addHeader;
    Function<String, String> transformationPipeline = addHeader.andThen(Letter::checkSpelling)
        .andThen(Letter::addFooter);
    System.out.println(transformationPipeline.apply(testText));
  }

  public static double integrate(DoubleUnaryOperator f, double a, double b) {
    return (f.applyAsDouble(a) + f.applyAsDouble(b)) * (b - a) / 2.0;
  }

  public static void process(Runnable r) {
    r.run();
  }

  public static void predicateExample() {
    List<String> strings = Arrays.asList("Test", "", "Test2", "Test3");

    System.out.println("\nPredicate example:");
    Predicate<String> emptyString = String::isEmpty;
    List<String> filteredStrings = filter(strings, emptyString.negate());
    System.out.println(filteredStrings);
  }

  public static void consumerExample() {
    System.out.println("\nConsumer Example:");
    forEach(Arrays.asList(1, 2, 3, 4, 5), System.out::println);
  }

  public static void functionExample() {
    System.out.println("\nFunction example:");
    List<Integer> lengths = map(Arrays.asList("lambdas", "in", "action"), String::length);
    System.out.println(lengths);
  }

  public static void runnableExamples() {
    System.out.println("\nRunnable examples:");
    Runnable r1 = () -> System.out.println("Runnable example 1");
    Runnable r2 = new Runnable() {
      @Override
      public void run() {
        System.out.println("Runnable example 2");
      }
    };

    process(r1);
    process(r2);
    process(Examples::runnable3);
  }

  public static void constructorReferenceExamples() {
    System.out.println("\nConstructor references:");
    Supplier<Apple> s1 = () -> new Apple();
    Apple a1 = s1.get();
    System.out.println(a1);

    Supplier<Apple> s2 = Apple::new;
    Apple a2 = s2.get();
    System.out.println(a2);

    BiFunction<Integer, Color, Apple> bf1 = (Integer w, Color c) -> new Apple(w, c);
    Apple a3 = bf1.apply(27, Color.GREEN);
    System.out.println(a3);

    BiFunction<Integer, Color, Apple> bf2 = Apple::new;
    Apple a4 = bf2.apply(32, Color.RED);
    System.out.println(a4);

    List<Integer> weights = Arrays.asList(7, 3, 4, 10);
    List<Apple> apples = map(weights, Apple::new);
    System.out.println(apples);

    TriFunction<Integer, Integer, Integer, RGB> triFunction1 = (Integer red, Integer green,
        Integer blue) -> new RGB(red, green, blue);
    RGB rgb1 = triFunction1.apply(1, 2, 3);
    System.out.println(rgb1);

    TriFunction<Integer, Integer, Integer, RGB> trifunction2 = RGB::new;
    RGB rgb2 = trifunction2.apply(4, 5, 6);
    System.out.println(rgb2);
  }
}

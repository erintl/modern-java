package com.modernjava.chapter9.chainofresponsibililty;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ChainOfResponsibilityRunner {
  
  public static void main(String[] args) {
    System.out.println("------ Chain Of Responsibility Pattern ------");

    System.out.println("--> Classic Implementation");
    ProcessingObject<String> p1 = new HeaderTextProcessor();
    ProcessingObject<String> p2 = new SpellCheckerProcessing();
    p1.setSuccessor(p2);
    String result1 = p1.handle("Aren't labdas really sexy?!!");
    System.out.println(result1);

    System.out.println("--> Lambda Implementation");
    UnaryOperator<String> headerProcessing = s -> "From Raoul, Mario, and Alan: " + s;
    UnaryOperator<String> spellCheckerProcessing = s -> s.replace("labda", "lambda");
    Function<String, String> processingPipeline = headerProcessing.andThen(spellCheckerProcessing);
    String result2 = processingPipeline.apply("Aren't labdas really sexy?!!");
    System.out.println(result2);
  }
}

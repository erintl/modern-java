package com.modernjava.chapter9.chainofresponsibililty;

public class HeaderTextProcessor extends ProcessingObject<String> {
  
  @Override
  protected String handleWork(String input) {
    return "From Raoul, Mario, and Alan: " + input;
  }
}

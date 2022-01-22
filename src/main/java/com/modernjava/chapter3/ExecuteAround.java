package com.modernjava.chapter3;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

public class ExecuteAround {
  private static final String FILE = ExecuteAround.class
    .getClassLoader().getResource("./data.txt").getFile();

  public static void main(String[] args) throws IOException {
    
    String result1 =  processFileLimited();
    System.out.println(result1);

    String result2 = processFile((br) -> br.readLine() + br.readLine());
    System.out.println(result2);
  }

  public static String processFileLimited() throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
      return br.readLine();
    }
  }

  public static String processFile(BufferedReaderProcessor brp) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
      return brp.process(br);
    }
  }
}

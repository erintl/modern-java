package com.modernjava.chapter3;

import java.io.BufferedReader;
import java.io.IOException;

public interface BufferedReaderProcessor {
  String process(BufferedReader br) throws IOException;
}

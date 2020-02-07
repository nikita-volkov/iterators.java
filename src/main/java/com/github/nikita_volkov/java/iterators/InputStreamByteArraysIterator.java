package com.github.nikita_volkov.java.iterators;

import java.io.*;
import java.util.*;

public class InputStreamByteArraysIterator implements Iterator<byte[]> {

  private final InputStream stream;
  private IOException exception;
  private byte[] bufferArray;
  private int amountRead;

  public InputStreamByteArraysIterator(InputStream stream, int chunkSize) {
    this.stream = stream;
    this.bufferArray = new byte[chunkSize];
    advance();
  }

  public InputStreamByteArraysIterator(InputStream stream) {
    this(stream, 1 << 12);
  }

  public IOException getException() {
    return exception;
  }

  private void advance() {
    try {
      amountRead = stream.read(bufferArray);
    } catch (IOException e) {
      exception = e;
    }
  }

  public boolean hasNext() {
    return exception == null && amountRead != -1;
  }

  public byte[] next() {
    byte[] output = Arrays.copyOf(bufferArray, amountRead);
    advance();
    return output;
  }

}
package com.github.lovasoa.superlogger;

public class App {
  public static void main(String[] args) {
    System.out.println("Agent started");
    while (true) {
      try {
        System.out.println(new SystemInfo());
        Thread.sleep(1000);
      } catch (Throwable e) {
        System.err.println(e);
      }
    }
  }
}

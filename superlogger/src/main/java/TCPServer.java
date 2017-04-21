package com.github.lovasoa.superlogger;

import java.io.*;
import java.net.*;

class TCPServer
{
  static int PORT = 6879;
  public static void main(String argv[]) throws Exception
  {
    ServerSocket socket = new ServerSocket(PORT);
    while (true) {
      System.err.println("Waiting for connections on port " + PORT);
      Socket conn = socket.accept();
      System.err.println("New connection");
      DataOutputStream out = new DataOutputStream(conn.getOutputStream());

      boolean isOpen = true;
      SystemInfo info = new SystemInfo();
      String response;
      while (isOpen) {
        try {
          out.writeBytes(info.toString() + "\n");
        } catch(SocketException e) {
          System.err.println(e);
          isOpen = false;
        }
        Thread.sleep(1000);
      }
      System.err.println("Connection closed");
    }
  }
}

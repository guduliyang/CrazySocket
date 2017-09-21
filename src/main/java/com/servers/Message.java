package com.servers;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Message {
    public static void send(Socket socket,String message) throws IOException {
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        writer.println(message);
        writer.flush();
    }
}

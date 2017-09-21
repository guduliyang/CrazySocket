package com.client;

import com.servers.Message;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    private Socket client;

    public SocketClient(int port){
        try {
            client = new Socket("172.0.0.1",port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SocketClient(String host,int port){
        try {
            client = new Socket(host,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getClient(){
        return client;
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new SocketClient("192.168.100.140",3333).getClient();
        new Thread(new ClientThread(socket)).start();
        Message.send(socket,"我是客户端：我已经连接了！");
        while (true){
            Scanner scanner = new Scanner(System.in);
            Message.send(socket,scanner.next());
        }
    }
}

package com.service;

import com.mode.UserInfo;
import com.servers.Message;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServerSocket {
    private static MyServerSocket myServerSocket;
    private ServerSocket serverSocket;
    public static List<UserInfo> users = new ArrayList<UserInfo>();

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    private MyServerSocket() throws IOException {
        serverSocket = new ServerSocket();
    }

    private MyServerSocket(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    private MyServerSocket(int port, int backlog) throws IOException {
        serverSocket = new ServerSocket(port, backlog);
    }

    private MyServerSocket(int port, int backlog, InetAddress bindAddr) throws IOException {
        serverSocket = new ServerSocket(port, backlog, bindAddr);
    }

    public static MyServerSocket getMyServerSocket() throws IOException {
        if (myServerSocket == null) {
            synchronized (MyServerSocket.class) {
                if (myServerSocket == null) {
                    myServerSocket = new MyServerSocket();
                }
            }
        }
        return myServerSocket;
    }

    public static MyServerSocket getMyServerSocket(int port) throws IOException {
        if (myServerSocket == null) {
            synchronized (MyServerSocket.class) {
                if (myServerSocket == null) {
                    myServerSocket = new MyServerSocket(port);
                }
            }
        }
        return myServerSocket;
    }

    public static MyServerSocket getMyServerSocket(int port, int backlog) throws IOException {
        if (myServerSocket == null) {
            synchronized (MyServerSocket.class) {
                if (myServerSocket == null) {
                    myServerSocket = new MyServerSocket(port, backlog);
                }
            }
        }
        return myServerSocket;
    }

    public static MyServerSocket getMyServerSocket(int port, int backlog, InetAddress bindAddr) throws IOException {
        if (myServerSocket == null) {
            synchronized (MyServerSocket.class) {
                if (myServerSocket == null) {
                    myServerSocket = new MyServerSocket(port, backlog, bindAddr);
                }
            }
        }
        return myServerSocket;
    }

    public void start() throws IOException {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    Socket socket = null;
                    UserInfo user = null;
                    try {
                        socket = serverSocket.accept();
                        user = new UserInfo(socket);
                        user.setName(socket.getInetAddress().getHostName());
                        users.add(user);
                        executorService.execute(new ServerThread(user));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    //向指定用户发送消息
    public void sendMessage(UserInfo user,String message) throws IOException {
        Message.send(user.getSocket(),message);
    }

    //发送广播
    public static void sendBroadcast(String message) throws IOException {
        for (UserInfo user : users){
            Message.send(user.getSocket(),message);
        }
    }

    public static void main(String[] args) throws IOException {
        MyServerSocket serverSocket = MyServerSocket.getMyServerSocket(3333);
        serverSocket.start();
        do {
            Scanner scanner = new Scanner(System.in);
            sendBroadcast(scanner.next());
        } while (true);
    }


}

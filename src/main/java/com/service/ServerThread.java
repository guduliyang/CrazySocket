package com.service;

import com.mode.MessageInfo;
import com.mode.UserInfo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable {
    private Logger logger = LogManager.getLogger(this.getClass());
    private UserInfo user = null;
    private Socket socket = null;
    private BufferedReader br = null;

    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
        logger.info(socket.getInetAddress().getHostAddress() + ":" + socket.getPort()+" is connected");
    }

    public ServerThread(UserInfo user) throws IOException {
        this.user = user;
        socket = user.getSocket();
        br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
        logger.info(user.getName()+" is connected");
    }

    public void run() {
        String content = null;
        while ((content = readFromClient()) != null) {
            System.out.println(user.getName()+" say :"+content);
            //转发消息
            try {
                MessageInfo messageInfo = new MessageInfo(MessageInfo.Client,user.getName(),content);
                MyServerSocket.sendBroadcast(messageInfo.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String readFromClient() {
        String info=null;
        String temp="";
        try {
          return  br.readLine();
        } catch (IOException e) {
            MyServerSocket.users.remove(user);
        }
        return null;
    }

}

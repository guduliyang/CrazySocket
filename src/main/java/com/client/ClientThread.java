package com.client;

import com.mode.MessageInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread implements Runnable {
    private Socket socket = null;
    private BufferedReader br = null;

    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
        br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
        System.out.println("连接到服务器："+socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
    }

    public void run() {
        MessageInfo messageInfo = null;
        String content = null;
        while ((content = readFromClient()) != null) {
            try {
                messageInfo = new MessageInfo(content);
                System.out.println(messageInfo.getName()+" say:"+messageInfo.getContent());
            }catch (Exception e){
                System.out.println("服务器信息："+content);
            }

        }
    }

    private String readFromClient() {
        String info=null;
        String temp="";
        try {
            return  br.readLine();
        } catch (IOException e) {
            e.getMessage();
        }
        return null;
    }
}

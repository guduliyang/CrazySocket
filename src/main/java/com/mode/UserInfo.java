package com.mode;

import java.net.Socket;

public class UserInfo {

    /**
     * name : 姓名
     * host : host
     * port : port
     * socket : socket
     */

    private String name;
    private String host;
    private int port;
    private Socket socket;

    public UserInfo(Socket socket){
        this.socket = socket;
        setHost(socket.getInetAddress().getHostAddress());
        setPort(socket.getPort());
    }

    public UserInfo(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}

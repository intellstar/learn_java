package main.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8801);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                sevice(socket);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void sevice(Socket socket) {
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String boby = "hello,nio1";;
            printWriter.println("Content-Length:" +boby.getBytes().length);
            printWriter.println();
            printWriter.write(boby);
            printWriter.write(boby);
            printWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package TCPServer;

import java.net.*;
import java.io.*;



public class Server {
    private static boolean start;
    
    public static void setStartFalse(){
        start = false;
    }
    
    public static void setStartTrue(){
        start = true;
    }
    
    public static void main(int port){
        try {
            int serverPort = port;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (start == true) {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        }
    }
}

class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    public void run() {
        try { // an echo server
            String data = in.readUTF().toUpperCase();
            out.writeUTF(data);
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                /* close failed */}
        }
    }
}
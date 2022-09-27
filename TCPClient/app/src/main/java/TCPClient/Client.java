/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCPClient;

/**
 *
 * @author UserPC
 */
import java.net.*;
import java.io.*;

public class Client {
    private static String result;
    
    public static String getResult(){
        return result;
    }

    public static void main(int serverPort, String host, String message) {
        // arguments supply message and hostname of destination
        Socket s = null;
        try {
            s = new Socket(host, serverPort);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out
                    = new DataOutputStream(s.getOutputStream());
            out.writeUTF(message); // UTF is a string encoding; see Sec 4.3
            String data = in.readUTF();
            System.out.println("Received: " + data);
            result = data;
        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (s != null) try {
                s.close();
            } catch (IOException e) {/*close failed*/
            }
        }
    }
}

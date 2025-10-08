/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;
/**
 *
 * @author 1117078
 */

public class Client {
    static final double version = 1.0;
    static BufferedReader in;
    static PrintWriter out;
    static Socket socket;
    public static void main(String[] args) {
        try {
            System.out.println("\u001B[33mDavid's Super Cool Chatting System!\u001B[35m");
            
            // network inits
            socket = new Socket("10.172.205.120", 28443); // random port, matches with server
            System.out.println("Connected to server.");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            
            Scanner scan = new Scanner(System.in);
            // send data (perhaps create a method for this?
            String metaData = sendMetaData(version + "");
            if (metaData.equals("Error: Mismatch in server and client version.")) {
                System.out.println("Error: Mismatch in server and client version.");
                in.close();
                out.close();
                socket.close();
                return; 

            }
            System.out.print("What is your name: ");
            metaData = sendMetaData(scan.nextLine());
            // sends a message, then waits for a message from client
            while (true) {
                System.out.print("\u001B[34m>\u001B[0m");
                String sendMessage = scan.nextLine();
                if ("!exit".equals(sendMessage)) {
                    in.close();
                    out.close();
                    socket.close();
                    return; 
                }
                out.println(sendMessage);
                String response = in.readLine();
                if (!response.equals("null")) {
                    System.out.println("\u001B[32mServer\u001B[0m: " + response);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String sendMetaData(String data) {
        try {
             out.println(data);
            String response = in.readLine();
            return response;
        }
        catch (IOException e) {
            e.printStackTrace();
            return "Error";
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.*;
import java.net.*;
import java.util.Scanner();
/**
 *
 * @author 1117078
 */

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("10.172.205.120", 12345);
            System.out.println("Connected to server.");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            Scanner scan = new Scanner(System.in);
            // sends a message, then waits for a message from client
            while (true) {
                System.out.print(">");
                String sendMessage = scan.nextLine();
                if (sendMessage == "!exit") {
                    break;
                }
                out.println(sendMessage);
                String response = in.readLine();
                System.out.println("Server: " + response);
            }

            in.close();
            out.close();
            socket.close();
            }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

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
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started. Waiting for a client...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            
            Scanner scan = new Scanner(System.in);
            // in reads from the socket, out writes to the socket
            // we wait for an input (sent by the client)
            while (true) {
                String message = in.readLine();
                System.out.println("Client: " + message);
                
                String sendMessage = scan.nextLine();
                if (sendMessage == "!exit") {
                    break;
                }
                out.println(sendMessage);
            }

            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
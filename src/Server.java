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
    static String clientName;
    static double version = 1.0;
    static double clientVersion;
    static ServerSocket serverSocket;
    static Socket clientSocket;
    static BufferedReader in;
    static PrintWriter out;
    public static void main(String[] args) {
        try {
            System.out.println("David's Super Cool Chatting System!");
            
            // network inits
            serverSocket = new ServerSocket(28443); // random port, matches with client
            System.out.println("Server started. Waiting for a client...");
            clientSocket = serverSocket.accept();
            System.out.println("Client connected.");
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            
            Scanner scan = new Scanner(System.in);
            
            // get init data (client version, client name)
            String metaData = in.readLine();
            try {
                clientVersion = Double.parseDouble(metaData);
                if (clientVersion != version) {
                    throw new Exception("Invalid Client Version");
                }
                out.println("Matching Versions");
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                if (e.getMessage().equals("Invalid Client Version")) {
                    out.println("Error: Mismatch in server and client version.");
                    return;
                }
            }
            
            clientName = in.readLine();
            out.println("Got Name");
            // in reads from the socket, out writes to the socket
            // we wait for an input (sent by the client)
            while (true) {
                String message = in.readLine();
                if (!message.equals("null")) {
                    System.out.println("\u001B[32m" + clientName + "\u001B[0m: " + message);
                }
                System.out.print("\u001B[34m>\u001B[0m");
                String sendMessage = scan.nextLine();
                if ("!exit".equals(sendMessage)) {
                    in.close();
                    out.close();
                    clientSocket.close();
                    serverSocket.close();
                    return;
                }
                
                out.println(sendMessage);
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
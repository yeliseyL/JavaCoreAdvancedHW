package HW6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        final int PORT = 8806;

        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Server is running");

            try (Socket socket = server.accept()) {
                System.out.println("Client connected");

                try (Scanner input = new Scanner(System.in);
                     DataInputStream in = new DataInputStream(socket.getInputStream());
                     DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

                    new Thread(() -> {
                        try {
                            while (true) {
                                out.writeUTF(input.nextLine());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();

                    while (true) {
                        String str = in.readUTF();
                        if (str.equals("/end")) {
                            break;
                        }
                        System.out.println("Client: " + str);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

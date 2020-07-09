package HW8.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

public class Server {
    private List<ClientHandler> clients;
    private AuthService authService;

    public AuthService getAuthService() {
        return authService;
    }

    public Server() {
        clients = new Vector<>();
        authService = new SimpleAuthService();

        final int PORT = 8806;
        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(PORT);
            System.out.println("Server is running");

            while (true) {
                socket = server.accept();
                System.out.println("Client connected");
                System.out.println("Remote socket address: " + socket.getRemoteSocketAddress());
                System.out.println("Local socket address: " + socket.getLocalSocketAddress());
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void broadcastMessage(String str) {
        for (ClientHandler client : clients) {
            client.sendMessage(str);
        }
    }

    void sendPrivateMessage(String nickname, String message) {
        for (ClientHandler client : clients) {
            if (client.getNickname().equals(nickname)) {
                client.sendMessage(message);
            }
        }
    }

    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }
}



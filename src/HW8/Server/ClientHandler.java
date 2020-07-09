package HW8.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    Server server;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    private String nickname;
    private String login;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    //auth cycle
                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/auth ")) {
                            String[] token = str.split("\\s");
                            if(token.length < 3) {
                                continue;
                            }
                            String newNick = server
                                    .getAuthService()
                                    .getNicknameByLoginAndPassword(token[1], token[2]);
                            if (newNick != null) {
                                sendMessage("/authok " + newNick);
                                nickname = newNick;
                                login = token[1];
                                server.subscribe(this);
                                System.out.printf("Client %s connected %n", nickname);
                                break;
                            } else {
                                sendMessage("Wrong login or password!");
                            }
                        }
                        server.broadcastMessage(str);
                    }
                    //work cycle
                    while (true) {
                        String str = in.readUTF();
                        if (str.equals("/end")) {
                            out.writeUTF("/end");
                            break;
                        }
                        if (str.startsWith("/w ")) {
                            String[] strArr = str.split("\\s+");
                            StringBuilder strSb = new StringBuilder();
                            for (int i = 2; i < strArr.length; i++) {
                                strSb.append(strArr[i] + " ");
                            }
                            String message = strSb.toString();
                            server.sendPrivateMessage(strArr[1], message);
                        } else {
                            server.broadcastMessage(str);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Client offline");
                    server.unsubscribe(this);
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNickname() {
        return nickname;
    }


    void sendMessage(String str) {
        try {
            out.writeUTF(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
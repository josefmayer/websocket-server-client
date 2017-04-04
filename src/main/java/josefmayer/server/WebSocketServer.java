package josefmayer.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.Set;
import java.util.HashSet;


import org.glassfish.tyrus.server.Server;

public class WebSocketServer {

    public static void main(String[] args) {
        runServer();
    }

    public static void runServer() {
        Set<Class<?>> configEndpoints = new HashSet<Class<?>>();
        configEndpoints.add(EchoServerEndpoint.class);
        configEndpoints.add(ReverseServerEndpoint.class);

       Server server = new Server("localhost", 8025, "/websockets", configEndpoints);

        try {
            server.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Please press a key to stop the server.");
            reader.readLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            server.stop();
        }
    }
}
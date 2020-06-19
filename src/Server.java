import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    static Map<String,ClientHandler> users=new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocket server=new ServerSocket(5000);
        while (true){
            System.out.println("waiting...");
            Socket socket=server.accept();
            System.out.println("connected!");
            new Thread(new ClientHandler(socket)).start();
        }
    }
}

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        System.out.println("enter username");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        dataOutputStream.writeUTF(username);

        Thread messageListener = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println("incoming message:\n" + dataInputStream.readUTF());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        messageListener.start();

        while (true) {
            System.out.println("enter target username:");
            String destUser = sc.nextLine();
            dataOutputStream.writeUTF(destUser);
            System.out.println("enter your message:");
            String message = sc.nextLine();
            dataOutputStream.writeUTF(message);
        }

    }
}

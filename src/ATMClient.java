import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ATMClient {
    private static final String SERVER_HOST = "localhost"; // 服务器的IP地址或主机名
    private static final int SERVER_PORT = 2525; // 服务器监听的端口号

    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        Scanner scanner = new Scanner(System.in); // 创建 Scanner 对象用于读取命令行输入

        try {
            // 创建一个套接字并连接到服务器
            socket = new Socket(SERVER_HOST, SERVER_PORT);
            System.out.println("Connected to server at " + SERVER_HOST + " on port " + SERVER_PORT);

            // 创建输入输出流
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 读取并发送 HELO 消息
            System.out.print("Enter user ID (HELO): ");
            String userId = scanner.nextLine();
            out.println("HELO " + userId);
            String response = in.readLine();
            System.out.println("Server response: " + response);

            // 读取并发送 PASS 消息
            System.out.print("Enter password (PASS): ");
            String password = scanner.nextLine();
            out.println("PASS " + password);
            response = in.readLine();
            System.out.println("Server response: " + response);

            // 读取并发送 BALA 消息
            System.out.print("Enter BALA command (BALA): ");
            String balanceCommand = scanner.nextLine();
            out.println(balanceCommand);
            response = in.readLine();
            System.out.println("Server response: " + response);

            // 读取并发送 WDRA 消息
            System.out.print("Enter withdrawal amount (WDRA): ");
            String withdrawalAmount = scanner.nextLine();
            out.println("WDRA " + withdrawalAmount);
            response = in.readLine();
            System.out.println("Server response: " + response);

            // 读取并发送 BYE 消息
            System.out.print("Enter BYE to exit: ");
            String exitCommand = scanner.nextLine();
            out.println(exitCommand);
            response = in.readLine();
            System.out.println("Server response: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (socket != null) socket.close();
                if (scanner != null) scanner.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
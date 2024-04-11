import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ATMServer {
    private static final int SERVER_PORT = 2525; // 服务器监听的端口号

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("ATM Server is listening on port " + SERVER_PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept(); // 等待ATM连接
                System.out.println("ATM connected from " + clientSocket.getInetAddress());

                // 创建一个新的线程来处理ATM的请求
                new Thread(new ServerSendAndAcceptThread(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ServerSendAndAcceptThread implements Runnable {
        private Socket socket;
        private String cid;
        private String pw;
        private int flag=0;
        private String clientIpAddress;
        public ServerSendAndAcceptThread(Socket socket) {
            this.socket = socket;
            this.clientIpAddress = socket.getInetAddress().getHostAddress();
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 Scanner scanner = new Scanner(in)) {

                String message;

                while ((message = scanner.nextLine()) != null) {
                    System.out.println("Received from ATM: " + message);
                    if(message.equals("BYE")){
                        out.println("BYE");
                        break;
                    }
                    handleATMMessage(message, out);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void handleATMMessage(String message, PrintWriter out) {

            String[] parts = message.split("\\s+");

          if(message.startsWith("HELO ")&&flag==0){
              flag = handleHelloMessage(parts[1],out);
              if(flag==1){
                  this.cid=parts[1];
              }
          }
          if (message.startsWith("PASS ") && flag == 1) {
              flag=handlePASSMessage(parts[1],out);
              if(flag==2){
                  this.pw=parts[1];
              }
          }
          if(message.equals("BALA")&&flag==2){
              handleBALAMessage(out);
          }
          if(message.startsWith("WDRA")&&flag==2){
              double value = Double.parseDouble(parts[1]);
              handleWDRAMessage(value,out);
          }

        }
        private int handleHelloMessage(String p, PrintWriter out) {
            int flag = 0;
            try {
                flag = demo.handleHello(p,clientIpAddress); // 调用 handleHello 方法并获取结果
                if (flag == 1) {
                    // 如果 flag 等于 1，表示数据存在，要求 ATM 输入密码
                    out.println("500 AUTH REQUIRED!");
                } else {
                    // 如果 flag 等于 0，表示数据不存在，输出错误信息
                    out.println("401 ERROR!");
                }
            } catch (SQLException e) {
                // 打印 SQL 异常信息
                e.printStackTrace();
                out.println("401 ERROR!");
            }
            return flag;
        }
        private int handlePASSMessage(String p, PrintWriter out) {
            int flag = 1;
            try {
                flag = demo.handlePASS(cid,p,clientIpAddress); // 调用 handleHello 方法并获取结果
                if (flag == 2) {
                    out.println("525 OK!");
                } else {
                    out.println("401 ERROR!");
                }
            } catch (SQLException e) {
                // 打印 SQL 异常信息
                e.printStackTrace();
                out.println("401 ERROR!");
            }
            return flag;
        }
        private void handleBALAMessage(PrintWriter out){
            double money;
            try {
                money = demo.handleBALA(cid,pw,clientIpAddress); // 调用 handleHello 方法并获取结果
                if (money>=0) {
                    out.println("AMNT:" + String.format("%.2f", money));
                } else {
                    out.println("401 ERROR!");
                }
            } catch (SQLException e) {
                // 打印 SQL 异常信息
                e.printStackTrace();
                out.println("401 ERROR!");
            }


        }

        private void handleWDRAMessage(double value,PrintWriter out){

            try {
                int  f1= demo.handleWDRA(cid,pw,value,clientIpAddress); // 调用 handleHello 方法并获取结果
                if (f1==1) {
                    out.println("525 OK!");
                } else {
                    out.println("401 ERROR!");
                }
            } catch (SQLException e) {
                // 打印 SQL 异常信息
                e.printStackTrace();
                out.println("401 ERROR!");
            }


        }
    }
}
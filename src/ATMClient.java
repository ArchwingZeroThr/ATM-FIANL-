import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

public class ATMClient extends JFrame {
    private static final String SERVER_HOST = "10.242.228.90"; // 服务器的IP地址或主机名
    private static final int SERVER_PORT = 2525; // 服务器监听的端口号

    private JTextField useridField;
    private JButton useridButton;

    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;

    public ATMClient() {
        setTitle("ATM登录");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel userLabel = new JLabel("用户名:");
        userLabel.setBounds(30, 70, 80, 40);
        userLabel.setForeground(Color.BLACK); // 设置文字颜色为黑色
        panel.add(userLabel);
        useridField = new JTextField(20);
        useridField.setBounds(130, 60, 165, 40);
        panel.add(useridField);

        useridButton = new JButton("确认");
        useridButton.setBounds(180, 120, 100, 40);
        panel.add(useridButton);

        add(panel);

        // 添加按钮点击事件监听器
        useridButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId = useridField.getText();
                // 建立与服务器的连接
                try {
                    socket = new Socket(SERVER_HOST, SERVER_PORT); // 替换为你的服务器IP和端口
                    out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    // 发送用户名到服务器
                    out.println("HELO " + userId);

                    // 接收服务器响应
                    String response = in.readLine();
                    if (response.startsWith("500")) {
                        // 打开密码输入窗口
                        showPasswordInputDialog();
                        dispose(); // 关闭当前窗口
                    } else if (response.startsWith("401")) {
                        // 提示用户名错误，重新输入
                        JOptionPane.showMessageDialog(null, "用户名输入错误，请重新输入。", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void showPasswordInputDialog() {
        JFrame passwordFrame = new JFrame("密码输入");
        passwordFrame.setSize(400, 300); // 设置窗口大小为400x300
        passwordFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(null);

        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(30, 60, 80, 25);
        passwordPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBounds(130, 50, 165, 40);
        passwordPanel.add(passwordField);

        JButton passwordButton = new JButton("确认");
        passwordButton.setBounds(140, 120, 100, 40);
        passwordPanel.add(passwordButton);

        passwordFrame.add(passwordPanel);
        passwordFrame.setVisible(true);

        // 添加按钮点击事件监听器
        passwordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordField.getPassword());
                try {
                    // 发送密码到服务器
                    out.println("PASS " + password);

                    // 接收服务器响应
                    String response = in.readLine();
                    if (response.startsWith("525")) {
                        // 密码正确，进入客户端界面
                        showMainGUI();
                        passwordFrame.dispose();
                    } else if (response.startsWith("401")) {
                        // 提示密码错误，重新输入
                        JOptionPane.showMessageDialog(null, "密码错误，请重新输入。", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                passwordFrame.dispose(); // 关闭密码输入窗口
            }
        });
    }

    private void showMainGUI() {
        JFrame mainFrame = new JFrame("ATM Main");
        mainFrame.setSize(400, 300); // 设置窗口大小为400x300
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JButton queryAccountButton = new JButton("查询账户");
        queryAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 读取并发送 BALA 消息
                out.println("BALA");
                try {
                    // 接收服务器响应
                    String response = in.readLine();
                    JOptionPane.showMessageDialog(mainFrame, "Server response: " + response, "Response", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainFrame.add(queryAccountButton, gbc);

        JButton withdrawButton = new JButton("取款");
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String withdrawalAmount = JOptionPane.showInputDialog(mainFrame, "请输入取款金额:");
                if (withdrawalAmount != null) {
                    // 读取并发送 WDRA 消息
                    out.println("WDRA " + withdrawalAmount);
                    try {
                        // 接收服务器响应
                        String response = in.readLine();
                        JOptionPane.showMessageDialog(mainFrame, "Server response: " + response, "Response", JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainFrame.add(withdrawButton, gbc);

        JButton exitButton = new JButton("退出 ");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 读取并发送 BYE 消息
                out.println("BYE");
                try {
                    // 接收服务器响应
                    String response = in.readLine();
                    JOptionPane.showMessageDialog(mainFrame, "Server response: " + response, "Response", JOptionPane.INFORMATION_MESSAGE);
                    mainFrame.dispose(); // 关闭当前窗口
                    setVisible(true); // 显示输入ID的窗口
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainFrame.add(exitButton, gbc);

        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ATMClient ATMClient = new ATMClient();
            ATMClient.setVisible(true);
        });
    }
}

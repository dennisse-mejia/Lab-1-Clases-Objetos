package Paquete1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavaLook {
    private static EmailAccount[] accounts = new EmailAccount[50];
    private static EmailAccount currentAccount = null;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(JavaLook::showLoginFrame);
    }

    private static void showLoginFrame() {
        JFrame frame = new JFrame("Login o Crear Cuenta");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Centrar la ventana
        frame.setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBackground(new Color(245, 245, 220)); // Color beige

        JButton loginButton = new JButton("LOGIN");
        JButton createAccountButton = new JButton("CREAR ACCOUNT");

        loginButton.setBackground(new Color(255, 182, 193)); // Color pink
        createAccountButton.setBackground(new Color(255, 182, 193)); // Color pink

        loginButton.addActionListener(e -> showLoginDialog(frame));
        createAccountButton.addActionListener(e -> showCreateAccountDialog(frame));

        panel.add(loginButton);
        panel.add(createAccountButton);
        frame.add(panel);
        frame.setVisible(true);
    }

    private static void showLoginDialog(JFrame parentFrame) {
        JDialog loginDialog = new JDialog(parentFrame, "Login", true);
        loginDialog.setSize(300, 200);
        loginDialog.setLocationRelativeTo(parentFrame); // Centrar la ventana
        loginDialog.setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBackground(new Color(245, 245, 220)); // Color beige

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginButton.setBackground(new Color(255, 182, 193)); // Color pink

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            if (login(email, password)) {
                loginDialog.dispose();
                parentFrame.dispose();
                showMainFrame();
            } else {
                JOptionPane.showMessageDialog(loginDialog, "Email o contraseña incorrectos.");
            }
        });

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        loginDialog.add(panel);
        loginDialog.setVisible(true);
    }

    private static void showCreateAccountDialog(JFrame parentFrame) {
        JDialog createAccountDialog = new JDialog(parentFrame, "Crear Cuenta", true);
        createAccountDialog.setSize(300, 200);
        createAccountDialog.setLocationRelativeTo(parentFrame); // Centrar la ventana
        createAccountDialog.setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBackground(new Color(245, 245, 220)); // Color beige

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel fullNameLabel = new JLabel("Nombre Completo:");
        JTextField fullNameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton createButton = new JButton("Crear");

        createButton.setBackground(new Color(255, 182, 193)); // Color pink

        createButton.addActionListener(e -> {
            String email = emailField.getText();
            String fullName = fullNameField.getText();
            String password = new String(passwordField.getPassword());
            if (createAccount(email, fullName, password)) {
                createAccountDialog.dispose();
                parentFrame.dispose();
                showMainFrame();
            } else {
                JOptionPane.showMessageDialog(createAccountDialog, "No se pudo crear la cuenta. La dirección de email ya está en uso o no hay espacio para más cuentas.");
            }
        });

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(fullNameLabel);
        panel.add(fullNameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(createButton);
        createAccountDialog.add(panel);
        createAccountDialog.setVisible(true);
    }

    private static void showMainFrame() {
        JFrame frame = new JFrame("JavaLook - Menú Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Centrar la ventana
        frame.setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBackground(new Color(245, 245, 220)); // Color beige

        JButton viewInboxButton = new JButton("VER MI INBOX");
        JButton sendEmailButton = new JButton("MANDAR CORREO");
        JButton readEmailButton = new JButton("LEER UN CORREO");
        JButton clearInboxButton = new JButton("LIMPIAR MI INBOX");
        JButton logoutButton = new JButton("CERRAR SESION");

        viewInboxButton.setBackground(new Color(255, 182, 193)); // Color pink
        sendEmailButton.setBackground(new Color(255, 182, 193)); // Color pink
        readEmailButton.setBackground(new Color(255, 182, 193)); // Color pink
        clearInboxButton.setBackground(new Color(255, 182, 193)); // Color pink
        logoutButton.setBackground(new Color(255, 182, 193)); // Color pink

        viewInboxButton.addActionListener(e -> showInboxDialog(frame));
        sendEmailButton.addActionListener(e -> showSendEmailDialog(frame));
        readEmailButton.addActionListener(e -> showReadEmailDialog(frame));
        clearInboxButton.addActionListener(e -> {
            currentAccount.deleteReadEmails();
            JOptionPane.showMessageDialog(frame, "Inbox limpiado.");
        });
        logoutButton.addActionListener(e -> {
            currentAccount = null;
            frame.dispose();
            showLoginFrame();
        });

        panel.add(viewInboxButton);
        panel.add(sendEmailButton);
        panel.add(readEmailButton);
        panel.add(clearInboxButton);
        panel.add(logoutButton);
        frame.add(panel);
        frame.setVisible(true);
    }

    private static void showInboxDialog(JFrame parentFrame) {
        JDialog inboxDialog = new JDialog(parentFrame, "Inbox", true);
        inboxDialog.setSize(500, 400);
        inboxDialog.setLocationRelativeTo(parentFrame); // Centrar la ventana
        inboxDialog.setLayout(new BorderLayout());

        JTextArea inboxTextArea = new JTextArea();
        inboxTextArea.setEditable(false);
        inboxTextArea.setBackground(new Color(245, 245, 220)); // Color beige
        inboxTextArea.setForeground(new Color(255, 182, 193)); // Color pink
        StringBuilder inboxContent = new StringBuilder();
        inboxContent.append("Email: ").append(currentAccount.getEmailAddress()).append("\n");
        inboxContent.append("Nombre Completo: ").append(currentAccount.getFullName()).append("\n");

        int unreadCount = 0;
        int totalCount = 0;

        for (int i = 0; i < currentAccount.getInbox().length; i++) {
            EMAIL email = currentAccount.getInbox()[i];
            if (email != null) {
                totalCount++;
                inboxContent.append((i + 1)).append(" - ")
                        .append(email.getSender()).append(" - ")
                        .append(email.getSubject()).append(" - ")
                        .append(email.isRead() ? "LEIDO" : "SIN LEER")
                        .append("\n");
                if (!email.isRead()) {
                    unreadCount++;
                }
            }
        }

        inboxContent.append("Correos sin leer: ").append(unreadCount).append("\n");
        inboxContent.append("Total de correos: ").append(totalCount).append("\n");

        inboxTextArea.setText(inboxContent.toString());
        inboxDialog.add(new JScrollPane(inboxTextArea), BorderLayout.CENTER);

        inboxDialog.setVisible(true);
    }

    private static void showSendEmailDialog(JFrame parentFrame) {
        JDialog sendEmailDialog = new JDialog(parentFrame, "Mandar Correo", true);
        sendEmailDialog.setSize(400, 300);
        sendEmailDialog.setLocationRelativeTo(parentFrame); // Centrar la ventana
        sendEmailDialog.setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBackground(new Color(245, 245, 220)); // Color beige

        JLabel recipientLabel = new JLabel("Destinatario:");
        JTextField recipientField = new JTextField();
        JLabel subjectLabel = new JLabel("Asunto:");
        JTextField subjectField = new JTextField();
        JLabel contentLabel = new JLabel("Contenido:");
        JTextArea contentArea = new JTextArea();
        JButton sendButton = new JButton("Enviar");

        sendButton.setBackground(new Color(255, 182, 193)); // Color pink

        sendButton.addActionListener(e -> {
            String recipientEmail = recipientField.getText();
            String subject = subjectField.getText();
            String content = contentArea.getText();
            if (sendEmail(recipientEmail, subject, content)) {
                JOptionPane.showMessageDialog(sendEmailDialog, "Correo enviado.");
                sendEmailDialog.dispose();
            } else {
                JOptionPane.showMessageDialog(sendEmailDialog, "No se pudo entregar el correo. La dirección de email no existe o el inbox del destinatario está lleno.");
            }
        });

        panel.add(recipientLabel);
        panel.add(recipientField);
        panel.add(subjectLabel);
        panel.add(subjectField);
        panel.add(contentLabel);
        panel.add(new JScrollPane(contentArea));
        panel.add(sendButton);
        sendEmailDialog.add(panel);
        sendEmailDialog.setVisible(true);
    }

    private static void showReadEmailDialog(JFrame parentFrame) {
        JDialog readEmailDialog = new JDialog(parentFrame, "Leer Correo", true);
        readEmailDialog.setSize(300, 200);
        readEmailDialog.setLocationRelativeTo(parentFrame); // Centrar la ventana
        readEmailDialog.setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBackground(new Color(245, 245, 220)); // Color beige

        JLabel posLabel = new JLabel("Posición del Correo:");
        JTextField posField = new JTextField();
        JButton readButton = new JButton("Leer");

        readButton.setBackground(new Color(255, 182, 193)); // Color pink

        readButton.addActionListener(e -> {
            int pos = Integer.parseInt(posField.getText());
            String emailContent = readEmail(pos);
            JOptionPane.showMessageDialog(readEmailDialog, emailContent);
            readEmailDialog.dispose();
        });

        panel.add(posLabel);
        panel.add(posField);
        panel.add(readButton);
        readEmailDialog.add(panel);
        readEmailDialog.setVisible(true);
    }

    private static boolean login(String email, String password) {
        for (EmailAccount account : accounts) {
            if (account != null && account.getEmailAddress().equals(email) && account.getPassword().equals(password)) {
                currentAccount = account;
                return true;
            }
        }
        return false;
    }

    private static boolean createAccount(String email, String fullName, String password) {
        for (EmailAccount account : accounts) {
            if (account != null && account.getEmailAddress().equals(email)) {
                return false;
            }
        }

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                accounts[i] = new EmailAccount(email, password, fullName);
                currentAccount = accounts[i];
                return true;
            }
        }
        return false;
    }

    private static boolean sendEmail(String recipientEmail, String subject, String content) {
        EMAIL email = new EMAIL(currentAccount.getEmailAddress(), subject, content);

        for (EmailAccount account : accounts) {
            if (account != null && account.getEmailAddress().equals(recipientEmail)) {
                return account.receiveEmail(email);
            }
        }
        return false;
    }

    private static String readEmail(int pos) {
        if (pos < 1 || pos > currentAccount.getInbox().length || currentAccount.getInbox()[pos - 1] == null) {
            return "Correo No Existe";
        } else {
            EMAIL email = currentAccount.getInbox()[pos - 1];
            email.markAsRead();
            return "DE: " + email.getSender() + "\nASUNTO: " + email.getSubject() + "\nCONTENIDO: " + email.getContent();
        }
    }
}




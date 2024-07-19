package Paquete1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavaLook {
    private static EmailAccount[] accounts = new EmailAccount[50];
    private static EmailAccount currentAccount = null;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JavaLook::showLoginFrame);
    }

    private static void showLoginFrame() {
        JFrame frame = new JFrame("Login o Crear Cuenta");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(3, 1));

        JButton loginButton = new JButton("LOGIN");
        JButton createAccountButton = new JButton("CREAR ACCOUNT");

        loginButton.addActionListener(e -> showLoginDialog(frame));
        createAccountButton.addActionListener(e -> showCreateAccountDialog(frame));

        frame.add(loginButton);
        frame.add(createAccountButton);
        frame.setVisible(true);
    }

    private static void showLoginDialog(JFrame parentFrame) {
        JDialog loginDialog = new JDialog(parentFrame, "Login", true);
        loginDialog.setSize(300, 200);
        loginDialog.setLayout(new GridLayout(3, 2));

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

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

        loginDialog.add(emailLabel);
        loginDialog.add(emailField);
        loginDialog.add(passwordLabel);
        loginDialog.add(passwordField);
        loginDialog.add(loginButton);
        loginDialog.setVisible(true);
    }

    private static void showCreateAccountDialog(JFrame parentFrame) {
        JDialog createAccountDialog = new JDialog(parentFrame, "Crear Cuenta", true);
        createAccountDialog.setSize(300, 200);
        createAccountDialog.setLayout(new GridLayout(4, 2));

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel fullNameLabel = new JLabel("Nombre Completo:");
        JTextField fullNameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton createButton = new JButton("Crear");

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

        createAccountDialog.add(emailLabel);
        createAccountDialog.add(emailField);
        createAccountDialog.add(fullNameLabel);
        createAccountDialog.add(fullNameField);
        createAccountDialog.add(passwordLabel);
        createAccountDialog.add(passwordField);
        createAccountDialog.add(createButton);
        createAccountDialog.setVisible(true);
    }

    private static void showMainFrame() {
        JFrame frame = new JFrame("JavaLook - Menú Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 1));

        JButton viewInboxButton = new JButton("VER MI INBOX");
        JButton sendEmailButton = new JButton("MANDAR CORREO");
        JButton readEmailButton = new JButton("LEER UN CORREO");
        JButton clearInboxButton = new JButton("LIMPIAR MI INBOX");
        JButton logoutButton = new JButton("CERRAR SESION");

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

        frame.add(viewInboxButton);
        frame.add(sendEmailButton);
        frame.add(readEmailButton);
        frame.add(clearInboxButton);
        frame.add(logoutButton);
        frame.setVisible(true);
    }

    private static void showInboxDialog(JFrame parentFrame) {
        JDialog inboxDialog = new JDialog(parentFrame, "Inbox", true);
        inboxDialog.setSize(500, 400);
        inboxDialog.setLayout(new BorderLayout());

        JTextArea inboxTextArea = new JTextArea();
        inboxTextArea.setEditable(false);
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
        sendEmailDialog.setLayout(new GridLayout(4, 2));

        JLabel recipientLabel = new JLabel("Destinatario:");
        JTextField recipientField = new JTextField();
        JLabel subjectLabel = new JLabel("Asunto:");
        JTextField subjectField = new JTextField();
        JLabel contentLabel = new JLabel("Contenido:");
        JTextArea contentArea = new JTextArea();
        JButton sendButton = new JButton("Enviar");

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

        sendEmailDialog.add(recipientLabel);
        sendEmailDialog.add(recipientField);
        sendEmailDialog.add(subjectLabel);
        sendEmailDialog.add(subjectField);
        sendEmailDialog.add(contentLabel);
        sendEmailDialog.add(new JScrollPane(contentArea));
        sendEmailDialog.add(sendButton);
        sendEmailDialog.setVisible(true);
    }

    private static void showReadEmailDialog(JFrame parentFrame) {
        JDialog readEmailDialog = new JDialog(parentFrame, "Leer Correo", true);
        readEmailDialog.setSize(300, 200);
        readEmailDialog.setLayout(new GridLayout(2, 2));

        JLabel posLabel = new JLabel("Posición del Correo:");
        JTextField posField = new JTextField();
        JButton readButton = new JButton("Leer");

        readButton.addActionListener(e -> {
            int pos = Integer.parseInt(posField.getText());
            String emailContent = readEmail(pos);
            JOptionPane.showMessageDialog(readEmailDialog, emailContent);
            readEmailDialog.dispose();
        });

        readEmailDialog.add(posLabel);
        readEmailDialog.add(posField);
        readEmailDialog.add(readButton);
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



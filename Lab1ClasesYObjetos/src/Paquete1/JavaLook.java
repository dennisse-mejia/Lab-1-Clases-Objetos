package Paquete1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavaLook {

    private EmailAccount[] accounts = new EmailAccount[50];
    private EmailAccount currentAccount = null;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new JavaLook().showLoginFrame());
    }

    private void showLoginFrame() {
        JFrame frame = new JFrame("Login o Crear Cuenta");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Bienvenido a JavaLook", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        JButton loginButton = new JButton("LOGIN");
        JButton createAccountButton = new JButton("CREAR ACCOUNT");

        loginButton.setFont(new Font("Serif", Font.PLAIN, 18));
        loginButton.setBackground(new Color(255, 182, 193));
        loginButton.setForeground(Color.BLACK);
        createAccountButton.setFont(new Font("Serif", Font.PLAIN, 18));
        createAccountButton.setBackground(new Color(255, 182, 193));
        createAccountButton.setForeground(Color.BLACK);

        loginButton.addActionListener(e -> showLoginDialog(frame));
        createAccountButton.addActionListener(e -> showCreateAccountDialog(frame));

        panel.add(loginButton);
        panel.add(createAccountButton);

        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void showLoginDialog(JFrame parentFrame) {
        JDialog loginDialog = new JDialog(parentFrame, "Login", true);
        loginDialog.setSize(400, 300);
        loginDialog.setLocationRelativeTo(parentFrame);
        loginDialog.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Ingrese sus datos", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginButton.setFont(new Font("Serif", Font.PLAIN, 18));
        loginButton.setBackground(new Color(255, 182, 193));
        loginButton.setForeground(Color.BLACK);

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

        loginDialog.add(titleLabel, BorderLayout.NORTH);
        loginDialog.add(panel, BorderLayout.CENTER);
        loginDialog.setVisible(true);
    }

    private void showCreateAccountDialog(JFrame parentFrame) {
        JDialog createAccountDialog = new JDialog(parentFrame, "Crear Cuenta", true);
        createAccountDialog.setSize(400, 300);
        createAccountDialog.setLocationRelativeTo(parentFrame);
        createAccountDialog.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Crear nueva cuenta", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel fullNameLabel = new JLabel("Nombre Completo:");
        JTextField fullNameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton createButton = new JButton("Crear");

        createButton.setFont(new Font("Serif", Font.PLAIN, 18));
        createButton.setBackground(new Color(255, 182, 193));
        createButton.setForeground(Color.BLACK);

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

        createAccountDialog.add(titleLabel, BorderLayout.NORTH);
        createAccountDialog.add(panel, BorderLayout.CENTER);
        createAccountDialog.setVisible(true);
    }

    private void showMainFrame() {
        JFrame frame = new JFrame("JavaLook - Menú Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Menú Principal", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        JButton viewInboxButton = new JButton("VER MI INBOX");
        JButton sendEmailButton = new JButton("MANDAR CORREO");
        JButton readEmailButton = new JButton("LEER UN CORREO");
        JButton clearInboxButton = new JButton("LIMPIAR MI INBOX");
        JButton logoutButton = new JButton("CERRAR SESION");

        viewInboxButton.setFont(new Font("Serif", Font.PLAIN, 18));
        viewInboxButton.setBackground(new Color(255, 182, 193));
        viewInboxButton.setForeground(Color.BLACK);
        sendEmailButton.setFont(new Font("Serif", Font.PLAIN, 18));
        sendEmailButton.setBackground(new Color(255, 182, 193));
        sendEmailButton.setForeground(Color.BLACK);
        readEmailButton.setFont(new Font("Serif", Font.PLAIN, 18));
        readEmailButton.setBackground(new Color(255, 182, 193));
        readEmailButton.setForeground(Color.BLACK);
        clearInboxButton.setFont(new Font("Serif", Font.PLAIN, 18));
        clearInboxButton.setBackground(new Color(255, 182, 193));
        clearInboxButton.setForeground(Color.BLACK);
        logoutButton.setFont(new Font("Serif", Font.PLAIN, 18));
        logoutButton.setBackground(new Color(255, 182, 193));
        logoutButton.setForeground(Color.BLACK);

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

        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void showInboxDialog(JFrame parentFrame) {
        JDialog inboxDialog = new JDialog(parentFrame, "Inbox", true);
        inboxDialog.setSize(600, 400);
        inboxDialog.setLocationRelativeTo(parentFrame);
        inboxDialog.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Mi Inbox", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        JTextArea inboxTextArea = new JTextArea();
        inboxTextArea.setEditable(false);
        inboxTextArea.setForeground(Color.BLACK);

        StringBuilder inboxContent = new StringBuilder();
        inboxContent.append("Email: ").append(currentAccount.getEmailAddress()).append("\n");
        inboxContent.append("Nombre Completo: ").append(currentAccount.getFullName()).append("\n\n");

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

        inboxContent.append("\nCorreos sin leer: ").append(unreadCount).append("\n");
        inboxContent.append("Total de correos: ").append(totalCount).append("\n");

        inboxTextArea.setText(inboxContent.toString());
        inboxDialog.add(new JScrollPane(inboxTextArea), BorderLayout.CENTER);

        inboxDialog.add(titleLabel, BorderLayout.NORTH);
        inboxDialog.setVisible(true);
    }

    private void showSendEmailDialog(JFrame parentFrame) {
        JDialog sendEmailDialog = new JDialog(parentFrame, "Mandar Correo", true);
        sendEmailDialog.setSize(500, 400);
        sendEmailDialog.setLocationRelativeTo(parentFrame);
        sendEmailDialog.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Enviar Correo", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        JLabel recipientLabel = new JLabel("Destinatario:");
        JTextField recipientField = new JTextField();
        JLabel subjectLabel = new JLabel("Asunto:");
        JTextField subjectField = new JTextField();
        JLabel contentLabel = new JLabel("Contenido:");
        JTextArea contentArea = new JTextArea();
        JButton sendButton = new JButton("Enviar");

        sendButton.setFont(new Font("Serif", Font.PLAIN, 18));
        sendButton.setBackground(new Color(255, 182, 193));
        sendButton.setForeground(Color.BLACK);

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

        sendEmailDialog.add(titleLabel, BorderLayout.NORTH);
        sendEmailDialog.add(panel, BorderLayout.CENTER);
        sendEmailDialog.setVisible(true);
    }

    private void showReadEmailDialog(JFrame parentFrame) {
        JDialog readEmailDialog = new JDialog(parentFrame, "Leer Correo", true);
        readEmailDialog.setSize(400, 300);
        readEmailDialog.setLocationRelativeTo(parentFrame);
        readEmailDialog.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Leer Correo", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        JLabel posLabel = new JLabel("Posición del Correo:");
        JTextField posField = new JTextField();
        JButton readButton = new JButton("Leer");

        readButton.setFont(new Font("Serif", Font.PLAIN, 18));
        readButton.setBackground(new Color(255, 182, 193));
        readButton.setForeground(Color.BLACK);

        readButton.addActionListener(e -> {
            int pos = Integer.parseInt(posField.getText());
            String emailContent = readEmail(pos);
            JOptionPane.showMessageDialog(readEmailDialog, emailContent);
            readEmailDialog.dispose();
        });

        panel.add(posLabel);
        panel.add(posField);
        panel.add(readButton);

        readEmailDialog.add(titleLabel, BorderLayout.NORTH);
        readEmailDialog.add(panel, BorderLayout.CENTER);
        readEmailDialog.setVisible(true);
    }

    private boolean login(String email, String password) {
        email = email.toLowerCase(); // Convertir a minúsculas para la comparación
        for (EmailAccount account : accounts) {
            if (account != null && account.getEmailAddress().equals(email) && account.getPassword().equals(password)) {
                currentAccount = account;
                return true;
            }
        }
        return false;
    }

    private boolean createAccount(String email, String fullName, String password) {
        email = email.toLowerCase(); // Convertir a minúsculas para la comparación y almacenamiento

        // Verificar si el correo electrónico es válido
        if (!isValidEmail(email)) {
            return false;
        }

        // Verificar si el correo electrónico ya está en uso
        for (EmailAccount account : accounts) {
            if (account != null && account.getEmailAddress().equals(email)) {
                return false;
            }
        }

        // Crear una nueva cuenta si hay espacio
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                accounts[i] = new EmailAccount(email, password, fullName);
                currentAccount = accounts[i];
                return true;
            }
        }
        return false;
    }

    private boolean sendEmail(String recipientEmail, String subject, String content) {
        recipientEmail = recipientEmail.toLowerCase(); // Convertir a minúsculas para la comparación
        EMAIL email = new EMAIL(currentAccount.getEmailAddress(), subject, content);

        for (EmailAccount account : accounts) {
            if (account != null && account.getEmailAddress().equals(recipientEmail)) {
                return account.receiveEmail(email);
            }
        }
        return false;
    }

    private String readEmail(int pos) {
        if (pos < 1 || pos > currentAccount.getInbox().length || currentAccount.getInbox()[pos - 1] == null) {
            return "Correo No Existe";
        } else {
            EMAIL email = currentAccount.getInbox()[pos - 1];
            email.markAsRead();
            return "DE: " + email.getSender() + "\nASUNTO: " + email.getSubject() + "\nCONTENIDO: " + email.getContent();
        }
    }

    private boolean isValidEmail(String email) {
        return email.contains("@");
    }
}


package Paquete1;
import java.util.Scanner;

public class JavaLook {
     private static EmailAccount[] accounts = new EmailAccount[50];
    private static EmailAccount currentAccount = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            if (currentAccount == null) {
                showLoginMenu();
            } else {
                showMainMenu();
            }
        }
    }

    private static void showLoginMenu() {
        System.out.println("1. LOGIN");
        System.out.println("2. CREAR ACCOUNT");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                createAccount();
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    private static void login() {
        System.out.println("Ingrese su dirección de email:");
        String email = scanner.nextLine();
        System.out.println("Ingrese su password:");
        String password = scanner.nextLine();

        for (EmailAccount account : accounts) {
            if (account != null && account.getEmailAddress().equals(email) && account.getPassword().equals(password)) {
                currentAccount = account;
                return;
            }
        }
        System.out.println("Email o contraseña incorrectos.");
    }

    private static void createAccount() {
        System.out.println("Ingrese su dirección de email:");
        String email = scanner.nextLine();

        for (EmailAccount account : accounts) {
            if (account != null && account.getEmailAddress().equals(email)) {
                System.out.println("La dirección de email ya está en uso.");
                return;
            }
        }

        System.out.println("Ingrese su nombre completo:");
        String fullName = scanner.nextLine();
        System.out.println("Ingrese su password:");
        String password = scanner.nextLine();

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                accounts[i] = new EmailAccount(email, password, fullName);
                currentAccount = accounts[i];
                return;
            }
        }

        System.out.println("No hay espacio para más cuentas.");
    }

    private static void showMainMenu() {
        System.out.println("1. VER MI INBOX");
        System.out.println("2. MANDAR CORREO");
        System.out.println("3. LEER UN CORREO");
        System.out.println("4. LIMPIAR MI INBOX");
        System.out.println("5. CERRAR SESION");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                viewInbox();
                break;
            case 2:
                sendEmail();
                break;
            case 3:
                readEmail();
                break;
            case 4:
                clearInbox();
                break;
            case 5:
                logout();
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    private static void viewInbox() {
        currentAccount.printInbox();
    }

    private static void sendEmail() {
        System.out.println("Ingrese la dirección del destinatario:");
        String recipientEmail = scanner.nextLine();
        System.out.println("Ingrese el asunto:");
        String subject = scanner.nextLine();
        System.out.println("Ingrese el contenido:");
        String content = scanner.nextLine();

        EMAIL email = new EMAIL(currentAccount.getEmailAddress(), subject, content);

        for (EmailAccount account : accounts) {
            if (account != null && account.getEmailAddress().equals(recipientEmail)) {
                if (account.receiveEmail(email)) {
                    System.out.println("Correo enviado.");
                } else {
                    System.out.println("El inbox del destinatario está lleno.");
                }
                return;
            }
        }
        System.out.println("La dirección de email no existe.");
    }

    private static void readEmail() {
        System.out.println("Ingrese la posición del correo a leer:");
        int pos = scanner.nextInt();
        scanner.nextLine(); // consume newline
        currentAccount.readEmail(pos);
    }

    private static void clearInbox() {
        currentAccount.deleteReadEmails();
        System.out.println("Inbox limpiado.");
    }

    private static void logout() {
        currentAccount = null;
    }

}

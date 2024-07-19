
package Paquete1;
import java.util.Arrays;

public class EmailAccount {
     private String emailAddress;
    private String password;
    private String fullName;
    private EMAIL[] inbox;

    // Constructor
    public EmailAccount(String emailAddress, String password, String fullName) {
        this.emailAddress = emailAddress;
        this.password = password;
        this.fullName = fullName;
        this.inbox = new EMAIL[50]; // Initialize the inbox array
    }

    // Getters
    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    // Function to receive an email
    public boolean receiveEmail(EMAIL em) {
        for (int i = 0; i < inbox.length; i++) {
            if (inbox[i] == null) {
                inbox[i] = em;
                return true;
            }
        }
        return false;
    }

    // Function to print the inbox
    public void printInbox() {
        System.out.println("Email: " + emailAddress);
        System.out.println("Nombre Completo: " + fullName);

        int unreadCount = 0;
        int totalCount = 0;

        for (int i = 0; i < inbox.length; i++) {
            if (inbox[i] != null) {
                totalCount++;
                System.out.println((i + 1) + " - " + inbox[i].getSender() + " - " + inbox[i].getSubject() + " - " + (inbox[i].isRead() ? "LEIDO" : "SIN LEER"));
                if (!inbox[i].isRead()) {
                    unreadCount++;
                }
            }
        }

        System.out.println("Correos sin leer: " + unreadCount);
        System.out.println("Total de correos: " + totalCount);
    }

    // Function to read an email
    public void readEmail(int pos) {
        if (pos < 1 || pos > inbox.length || inbox[pos - 1] == null) {
            System.out.println("Correo No Existe");
        } else {
            inbox[pos - 1].print();
            inbox[pos - 1].markAsRead();
        }
    }

    // Function to delete read emails
    public void deleteReadEmails() {
        for (int i = 0; i < inbox.length; i++) {
            if (inbox[i] != null && inbox[i].isRead()) {
                inbox[i] = null;
            }
        }
    }
}


package Paquete1;


public class EMAIL {
     private String sender;
    private String subject;
    private String content;
    private boolean isRead;

    // Constructor
    public EMAIL(String sender, String subject, String content) {
        this.sender = sender;
        this.subject = subject;
        this.content = content;
        this.isRead = false; // Default value
    }

    // Getters
    public String getSender() {
        return sender;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public boolean isRead() {
        return isRead;
    }

    // Function to mark the email as read
    public void markAsRead() {
        this.isRead = true;
    }

    // Function to print the email details
    public void print() {
        System.out.println("DE: " + sender);
        System.out.println("ASUNTO: " + subject);
        System.out.println("CONTENIDO: " + content);
    }

}

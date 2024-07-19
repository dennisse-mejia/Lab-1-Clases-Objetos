
package Paquete1;


public class EMAIL {
    // String para guardar el asunto
    private String sender;
    private String subject;
    private String content;
    
    // Boolean
    private boolean isRead;

    // Constructor
    public EMAIL(String sender, String subject, String content) {
        this.sender = sender;
        this.subject = subject;
        this.content = content;
        this.isRead = false; // Valor por defecto
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

    // Función para marcar el correo electrónico como leido 
    public void markAsRead() {
        this.isRead = true;
    }

    // Función para imprimir los detalles del correo electrónico, utilizando print()
    public void print() {
        System.out.println("DE: " + sender);
        System.out.println("ASUNTO: " + subject);
        System.out.println("CONTENIDO: " + content);
    }
}
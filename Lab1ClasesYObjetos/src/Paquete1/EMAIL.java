package Paquete1;

public class EMAIL {

    private String sender;
    private String subject;
    private String content;
    private boolean isRead;

   
    public EMAIL(String sender, String subject, String content) {
        this.sender = sender;
        this.subject = subject;
        this.content = content;
        //Valor por default
        this.isRead = false;
    }

   
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

    // Funcion para marcar correo leido
    public void markAsRead() {
        this.isRead = true;
    }

    // Funcion para imprimir los detalles del correo leido
    public void print() {
        System.out.println("DE: " + sender);
        System.out.println("ASUNTO: " + subject);
        System.out.println("CONTENIDO: " + content);
    }

}

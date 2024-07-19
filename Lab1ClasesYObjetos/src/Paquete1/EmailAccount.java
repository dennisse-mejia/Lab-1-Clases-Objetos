package Paquete1;

public class EmailAccount {
    private String emailAddress;
    private String password;
    private String fullName;
    private EMAIL[] inbox;

  
    public EmailAccount(String emailAddress, String password, String fullName) {
        this.emailAddress = emailAddress;
        this.password = password;
        this.fullName = fullName;
        this.inbox = new EMAIL[50]; 
    }

  
    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public EMAIL[] getInbox() {
        return inbox;
    }

    // Funcion para recibir un email
    public boolean receiveEmail(EMAIL em) {
        for (int i = 0; i < inbox.length; i++) {
            if (inbox[i] == null) {
                inbox[i] = em;
                return true;
            }
        }
        return false;
    }

    // Funcion para borrar emails leidos
    public void deleteReadEmails() {
        for (int i = 0; i < inbox.length; i++) {
            if (inbox[i] != null && inbox[i].isRead()) {
                inbox[i] = null;
            }
        }
    }
}

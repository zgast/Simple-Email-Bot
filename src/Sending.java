package at.markus;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Sending {

    public static void sendMail(String email, String subject, String text) {

        final String username = "email@domain.topleveldomain";
        final String password = "password";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "subdomain.domain.topleveldomain");
        prop.put("mail.smtp.port", "Port(e.g. 587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("email@domain.topleveldomain"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
        }
    }

}

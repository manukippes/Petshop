package utilidades;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.logging.log4j.Level;

public class Emailer {
	
	public static Emailer instance;
	
	private Properties props;
	
	public static Emailer getInstance() throws ExcepcionEspecial{
		if (instance==null){
			instance=new Emailer();
		}
		return instance;
	}
	
	private Emailer() throws ExcepcionEspecial {
		
		InputStream inputStream=getClass().getClassLoader().getResourceAsStream("app.properties");
		try {
			props = new Properties();
			props.load(inputStream);
			
			/*
			 * props.put("mail.smtp.auth", "true");
			 * props.put("mail.smtp.starttls.enable", "true");
			 * props.put("mail.smtp.host", "smtp.gmail.com");
			 * props.put("mail.smtp.port", "587");
			 * props.put("mail.username", "somemail@gmail.com");
			 * props.put("mail.password","someRandomwPassword");
			 */
		} catch (IOException e) {
			throw new ExcepcionEspecial("No se ha podido enviar el correo electrónico", Level.WARN);
		}
		
	}
	
	public void send(String to, String subject, String body)throws ExcepcionEspecial{

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				//return new PasswordAuthentication(username, password);
				return new PasswordAuthentication(props.getProperty("mail.username"), props.getProperty("mail.password"));
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(props.getProperty("mail.username")));
			message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(to)); //"ejemplo@gmail.com"
			message.setSubject(subject); //"Destinatario"
			message.setText(body); //"Texto del email"

			Transport.send(message);

		} catch (MessagingException e) {
			throw new ExcepcionEspecial("No se ha podido enviar el correo electrónico", Level.WARN);
		}
	}

}

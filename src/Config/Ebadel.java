
package Config;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import entities.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import javafx.application.Application;



import services.user.LoginAndSignupService;
import services.user.MailSender;
import services.user.UserService;




import com.google.zxing.BarcodeFormat;
import com.google.zxing. EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;





import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Hashtable;

import java.util.Map;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import static services.user.UserService.doHashing;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;




public class Ebadel  extends Application {

    /**
     * @param args the command line arguments
     */
    
    
   
    
    
    public static Stage stg;
    	@Override
	public void start(Stage primaryStage) throws IOException {
            
             Parent root = FXMLLoader.load(getClass().getResource("/view/login_signup/login.fxml"));
        Scene scene = new Scene(root, 1200, 650);
     this.stg=primaryStage;
        primaryStage.setTitle("Welcome to Ebadel!");
        primaryStage.setScene(scene);
        primaryStage.show();
		
	}
    
    
    
    
    public static void main(String[] args) throws Exception, WriterException, IOException,
               NotFoundException{
        // TODO code application logic here
        Datasource data = Datasource.getInstance();
        Datasource data2 = Datasource.getInstance();

        System.out.println(data.hashCode() + "-" + data2.hashCode());

        UserService us = new UserService();
       
        

        
        LoginAndSignupService loginSignup = new LoginAndSignupService();
        
       
   launch(args);              

     
     
    
         } 

         
    
    public static String doHashing(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.update(password.getBytes());

            byte[] resultByteArray = messageDigest.digest();

            StringBuilder sb = new StringBuilder();
 
            for (byte b : resultByteArray) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }

 
}
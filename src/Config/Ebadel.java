
package Config;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.application.Application;



import services.user.LoginAndSignupService;
import services.user.UserService;




import java.io.IOException;
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
package services.user;

import entities.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Config.Datasource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import static services.user.MailSender.sendMail;


public class LoginAndSignupService {
      
    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    public LoginAndSignupService() {
        conn = Datasource.getInstance().getCnx();
    }

      private String Email;
      private String Password;
public boolean login (String email,String password) throws Exception {
    boolean test = false;
        List<user> users = new ArrayList<>();
        String req = "SELECT *  FROM `user` where Email='"+email+"' and Password ='"+doHashing(password)+"'";
        System.out.println(email);
         System.out.println(password);
        try {

       ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
            user u = new user();
                u.setIdu(rs.getInt(1));
                  u.setCin( rs.getString(2));
                u.setNom(rs.getString(3));
                u.setPrenom(rs.getString(4));      
                    u.setTel(rs.getString(5));
            
              u.setEmail( rs.getString(6));
               u.setPassword(rs.getString(7));
                u.setImage(rs.getString(8));
                 u.setRole(rs.getInt(9));
                u.setDateNaissance(rs.getDate(10));
                 users.add(u) ;   
                        
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
  
           try{
               
         if(users.size()==0){
                         return test;
                         
                         }
                         else{
                             test=true;
                           
                         }
           }catch(IndexOutOfBoundsException e){
         
        }
       return test;
    }
  public boolean Signup(user u) throws Exception {
        boolean test = false;
              List<user> users = new ArrayList<>();
      String Email1=u.getEmail();
      String pass1=u.getPassword();
    //  System.out.println(Email1);
          // System.out.println(pass1);
      String password=u.getPassword();
      String req = "SELECT *  FROM `user` where Email='"+Email1+"'";
           try {

       ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
              user u1 = new user();
                u1.setIdu(rs.getInt(1));
                  u1.setCin( rs.getString(2));
                u1.setNom(rs.getString(3));
                u1.setPrenom(rs.getString(4));      
                    u1.setTel(rs.getString(5));
            
              u1.setEmail( rs.getString(6));
               u1.setPassword(rs.getString(7));
                u1.setImage(rs.getString(8));
                u1.setRole(rs.getInt(9));
                u1.setDateNaissance(rs.getDate(10));
                 users.add(u) ; 
                     
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
                    System.out.println("+++++++size++++++++"+users.size());
                                    if(users.size()==0){
                                        
                                        

                                                         String req10 = "INSERT INTO `user` (`Cin`,`Nom`,`Prenom`,`Tel`,`Email`,`Password`,`Image`,`dateNaissance`) VALUES (?,?,?,?,?,?,?,?) ;";
                                                       try {
                                                           pste = conn.prepareStatement(req10);


                                                           pste.setString(1,u.getCin());
                                                           pste.setString(2, u.getNom());
                                                           pste.setString(3, u.getPrenom());
                                                            pste.setString(4,u.getTel());
                                                             pste.setString(5, u.getEmail());
                                                              pste.setString(6, UserService.doHashing(u.getPassword()));
                                                               pste.setString(7, u.getImage());
                                                                 pste.setDate(8, u.getDateNaissance());
                                                           pste.executeUpdate();
                                                           System.out.println("user créée");
                                                           MailSender.sendMail(u);
                                                             test=true;
                                                       } catch (SQLException ex) {
                                                           Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                                                       }
                                                       
                                    }
                                                       else{
                                             System.out.print("signup failed ");
                                            test=false;

                                    }
                    return   test;               
                                       
 



  }      public static String doHashing(String password) {
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
    }}
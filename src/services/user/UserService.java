package services.user;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import Config.Datasource;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

//import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;


import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;





import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.IIOException;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

  
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.scenario.effect.ImageData;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.pdfbox.pdmodel.PDPage;

import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;




/**
 *
 * @author FLAM
 */
public class UserService implements IuserService {
    



    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;
    
    public static int idd;

    public UserService() {
        conn = Datasource.getInstance().getCnx();
    }
 
    public    List<user> getuserbyTel(int Tel)  {
     List<user> users = new ArrayList<>();
      user u = new user();
   
          String req = "SELECT * from `user` where Tel="+Tel+";";
            try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
          
                  
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
            }}
            catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return users;    
    }
 
    @Override
    public void ajouter(user u) {

              List<user> users = new ArrayList<>();
      String Email1=u.getEmail();
      String pass1=u.getPassword();
    //  System.out.println(Email1);
          // System.out.println(pass1);
      String password=u.getPassword();
      String req = "SELECT *  FROM user where Email='"+Email1+"'";
   
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
                                        
                                        

                                                         String req10 = "INSERT INTO user (Cin,`Nom`,`Prenom`,`Tel`,`Email`,`Password`,`Image`,`dateNaissance`) VALUES (?,?,?,?,?,?,?,?) ;";
                                                       try {
                                                           pste = conn.prepareStatement(req10);


                                                           pste.setString(1,u.getCin());
                                                           pste.setString(2, u.getNom());
                                                           pste.setString(3, u.getPrenom());
                                                            pste.setString(4,u.getTel());
                                                             pste.setString(5, u.getEmail());
                                                              pste.setString(6, doHashing(u.getPassword()));
                                                               pste.setString(7, u.getImage());
                                                                 pste.setDate(8, u.getDateNaissance());
                                                           pste.executeUpdate();
                                                           System.out.println("user créée");
                                                       } catch (SQLException ex) {
                                                           Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                                                       }
                                                       
                                    }
                                                       else{
                                             System.out.print("user deja exist ");

                                    }
    }
    

    public int  afficherUserByRole(String Email) {
     
        int Role = 0 ;
        String req = "SELECT`Role` FROM `user` WHERE `Email`='"+Email+"';";
        
        try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
            
                Role =rs.getInt(1);
                                                       
                                                   
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Role;
    }
    
       @Override
    public void modifier( int id ,user u)    {
     
 
        String req = "UPDATE `user` SET "
                +"`Cin`=?,`Nom`=?,`Prenom`=?,`Tel`=?,`Email`=?,`Password`=?,`Image`=?,`dateNaissance`=?"
                + "WHERE idu = '" + id+ "'";
    
        try {
            pste = conn.prepareStatement(req);
           pste.setString(1,u.getCin());
            pste.setString(2, u.getNom());
            pste.setString(3, u.getPrenom());
             pste.setString(4,u.getTel());
              pste.setString(5, u.getEmail());

               pste.setString(6, doHashing(u.getPassword()));
                pste.setString(7, u.getImage());
                pste.setDate(8, u.getDateNaissance());
            pste.executeUpdate();
            System.out.println("user modifie");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    @Override
    public void supprimer(int id )  {
      String delete = "DELETE FROM user  where idu = ?";
        try {
            pste = conn.prepareStatement(delete);
            pste.setInt(1,id);
            pste.executeUpdate();
            System.out.println("user supprimé");
        } catch (SQLException ex) {
                  
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);        
       
        }
    
    }

    @Override
    public List<user> afficher() {
        List<user> users = new ArrayList<>();
        String req = "SELECT * FROM `user`";
        
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
        
        return users;
    }

    @Override
    public int nbUsers() throws SQLException {
    int nb=0;
          String req = "SELECT count(*) from `user` ;";
            try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
            nb=rs.getInt(1);
                                                   
            }}
            catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nb;
    }

   
    
  
    
    

    
    
     
    
    
    
    
    
    
     public user getUserByID(int id)  {
     List<user> users = new ArrayList<>();
      user u = new user();
   
          String req = "SELECT * from `user` where Idu="+id+";";
            try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
          
                  
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
            }}
            catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return u;    
    }
     
      public    List<user> getuserbycin(int cin)  {
     List<user> users = new ArrayList<>();
      user u = new user();
   
          String req = "SELECT * from `user` where cin="+cin+";";
            try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
          
                  
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
            }}
            catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return users;    
    }
      public  List<Integer> gelallID()  {
     List<Integer> ID = new ArrayList<>();
     
   
          String req = "SELECT Idu  from `user`";
            try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
          
                  
            
               
                 ID.add(rs.getInt(1));                                   
            }}
            catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ID;    
    }
    public user getUserByEmail (String Email)  {
     List<user> users = new ArrayList<>();
      user u = new user();
   
          String req = "SELECT * from `user` where Email='"+Email+"';";
            try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
          
                  
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
            }}
            catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return u;    
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
            
         
    public String getByCIN_NOM_PRENOM(int id)
    {
    
       int x=0;
     
        String ch="";
          user u= new user();
               String req = "SELECT idu,cin,nom,prenom FROM `user` WHERE Idu="+id+";";
       
        
        try {

              ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
             
        
                  u.setIdu(rs.getInt(1));
                      u.setCin(rs.getString(2));
                      u.setNom(rs.getString(3));
                      u.setPrenom(rs.getString(4));
         
                                                
                       idd=rs.getInt(1);          
               ch=  rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4);
                 
                                                   
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return ch;
    }

    
    

}
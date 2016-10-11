package com.daria.sprimg.mvc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String userName;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = User.getShaHash(password);
    }

    /**
     * Returns encrypted SHA-256 string with length 64 signs
     */
    public static String getShaHash(String string) {

        if (string.length() > 0) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(string.getBytes());

                byte byteData[] = md.digest();

                //convert the byte to hex format method 1
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < byteData.length; i++) {
                    sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
                }
                return sb.toString();
            } catch (NoSuchAlgorithmException e) {
                System.out.println(e);
            }
        }
        return null;
        /*
        if (!isEmptyAny(string))
        {
            StringBuilder sb = new StringBuilder();
            try
            {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(string.getBytes());


                byte byteData[] = md.digest();


//convert the byte to hex format method 1
                for (int i = 0; i < byteData.length; i++)
                {
                    sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
                }
            }
            catch (NoSuchAlgorithmException e)
            {
                log.error("", e);
            }
            return sb.toString();
        }
        return null;
*/
    }
}

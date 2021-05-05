package dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.codec.binary.Hex;

import conn.Connections;


/**
 * 
 * @author Laura 
 *
 */
public class Dao {
    private Connection conn;
    private Statement statement = null;
    private ResultSet resultSet = null;
   
    
    public Dao() {
		try {
			conn=Connections.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    
	public boolean CheckLogin(String username, String password) throws SQLException,
    ClassNotFoundException, UnsupportedEncodingException, NoSuchAlgorithmException {
			boolean ok = false;
			String hexEncodedText;
			MessageDigest md;
	        byte[] clearTextAsBytes;
		
			String sql = "SELECT * FROM logins WHERE username = ? and pwd = ?";
			try {
				
				clearTextAsBytes = password.getBytes("UTF-8");
	            md = MessageDigest.getInstance( "SHA" );
	            md.reset();
	            md.update( clearTextAsBytes );
	            hexEncodedText = Hex.encodeHexString( md.digest() );
	            
    			PreparedStatement pstmt=conn.prepareStatement(sql);
    			pstmt.setString(1,  username);
    			pstmt.setString(2,  hexEncodedText);
    			ResultSet result = pstmt.executeQuery();
    			ok = result.next();

    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
			return ok;
		
	}
	
    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {

        }
    }

}



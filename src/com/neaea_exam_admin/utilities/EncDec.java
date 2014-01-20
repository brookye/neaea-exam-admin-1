package com.neaea_exam_admin.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author misgana
 * An encryption decryption class
 */
public class EncDec {
	/**
	 * An MD5 ecryptor method
	 * @param rawString
	 * @return an MD5 encrypted string
	 */
   public static String encrypt(String rawString){
	   try {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] passBytes = rawString.getBytes();
	        md.reset();
	        byte[] digested = md.digest(passBytes);
	        StringBuffer sb = new StringBuffer();
	        for(int i=0;i<digested.length;i++){
	            sb.append(Integer.toHexString(0xff & digested[i]));
	        }
	        return sb.toString();
	    } catch (NoSuchAlgorithmException e) {
	    	//TODO whatever to do
	        e.printStackTrace();
	    }
	        return null;
   }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package des.encryption;

import java.util.ArrayList;

/**
 *
 * @author Elliot
 */
public class DESEncryption {
    
    public String arrayToString(ArrayList<String> array){
        StringBuffer sb = new StringBuffer();

        for (String s : array) {
           sb.append(s);
        }
        String str = sb.toString();
        return str;
    }
    
    public String XORing(String plainText, String key){
        ArrayList<String> output = new ArrayList<String>();
        String[] plainChars = plainText.split("");
        String[] keyChars = key.split("");
        for(int i = 0 ; i < key.length() ; i++){
            if((plainChars[i].equals("1") && keyChars[i].equals("0"))  || (plainChars[i].equals("0") && keyChars[i].equals("1")))
                output.add("1");
            
            else
                output.add("0");
        }
        String tmp = arrayToString(output);
        
        return tmp;
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package des.encryption;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Elliot
 */
public class DESEncryption {
    
    public ArrayList<String> readfile(String path) throws IOException {
        File file = new File(path);
        ArrayList<String> temp = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {
            temp.add(st);
        }
        return temp;
    }
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
    
    public ArrayList<String> make64(String path) throws IOException{
        ArrayList<String> chars = new ArrayList<String>();
        ArrayList<String> data = readfile(path);
        String plain = data.get(0);
        String binary = "";
        //System.out.println("im in");
        for(int i = 0 ; i < plain.length() ; i++){
            int num = (int)plain.charAt(i);//get ascii
            String tmp = Integer.toBinaryString(num); //get binary  0 1110101
            while(tmp.length() < 8){//add the missing bits
                tmp = "0" + tmp;
            }
            binary += tmp;
        }
        //System.out.println(binary);
        for(int i = 0 ; i < binary.length() ; i+=64){
            String tmp= "";
            for(int j = i ; j < i+64 ; j++){
                if(j == binary.length())
                    break;
                tmp += binary.charAt(j);
            }
            chars.add(tmp);
        }
        //System.out.println("dklfa");
        if(chars.get(chars.size()-1).length() != 64){
            String tmp2 = chars.get(chars.size()-1);
            while(tmp2.length() < 64){//add the missing bits
                tmp2 = "0" + tmp2;
            }
            chars.set(chars.size()-1, tmp2);
        }
        return chars;
    }
    
    public String encrypt() throws IOException{
        String path = "F:\\college\\third year\\second term\\Computer Network Security\\assignments\\DES-encryption\\DES-encryption\\plain.txt";
        ArrayList<String> chars = make64(path);
        for(int i =0 ; i < chars.size() ; i++){
            String tmp = chars.get(i);
            
            int initial_permutation_table[] ={58,50,42,34,26,18,10,2,60,52,44,36,28,20,12,4,62,54,46,38,30,22,14,6,64,56,48,40,32,24,16,8,57,49,41,33,25,17,9,1,59,51,43,35,27,19,11,3,61,53,45,37,29,21,13,5,63,55,47,39,31,23,15,7}; 
            String perm ="";
            for(int j = 0 ; j < 64 ; j++){
                perm += tmp.charAt(initial_permutation_table[j] -1);
            }
            
            String left = "" , right ="";
            for(int j = 0 ; j < 64 ; j++){
                if(j < 32){
                    left += perm.charAt(j);
                }
                else
                    right += perm.charAt(j);
            }
            System.out.println("left =  " + left);
            System.out.println("right = " + right);
        }
        
        
        return "";
    }
    
    
    
    public static void main(String[] args) throws IOException {
        DESEncryption des = new DESEncryption();
        des.encrypt();
    }
    
}

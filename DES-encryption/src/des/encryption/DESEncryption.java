/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package des.encryption;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Elliot
 */
public class DESEncryption {
    public int sbox1[][] = {{ 14,  4, 13,  1,  2, 15, 11,  8,  3, 10,  6, 12,  5,  9,  0,  7},
                            {0, 15,  7,  4, 14,  2, 13,  1, 10,  6, 12, 11,  9,  5,  3,  8},
                            {4,  1, 14,  8, 13,  6,  2, 11, 15, 12,  9,  7,  3, 10,  5,  0},
                            {15, 12,  8,  2,  4,  9,  1,  7,  5, 11,  3, 14, 10,  0,  6, 13 }};
    
    public int sbox2[][]= {{15,  1,  8, 14,  6, 11,  3,  4,  9,  7,  2, 13, 12,  0,  5, 10},
                            {3, 13,  4,  7, 15,  2,  8, 14, 12,  0,  1, 10,  6,  9, 11,  5},
                            {0, 14,  7, 11, 10,  4, 13,  1,  5,  8, 12,  6,  9,  3,  2, 15},
                            {13,  8, 10,  1,  3, 15,  4,  2, 11,  6,  7, 12,  0,  5, 14,  9 }};
    
    public int sbox3[][]= {{10,  0,  9, 14,  6,  3, 15,  5,  1, 13, 12,  7, 11,  4,  2,  8},
                            {13,  7,  0,  9,  3,  4,  6, 10,  2,  8,  5, 14, 12, 11, 15,  1},
                            {13,  6,  4,  9,  8, 15,  3,  0, 11,  1,  2, 12,  5, 10, 14,  7},
                            {1, 10, 13,  0,  6,  9,  8,  7,  4, 15, 14,  3, 11,  5,  2, 12}} ;
    
    public int sbox4[][]= {{7, 13, 14,  3,  0,  6,  9, 10,  1,  2,  8,  5, 11, 12,  4, 15},
                            {13,  8, 11,  5,  6, 15,  0,  3,  4,  7,  2, 12,  1, 10, 14,  9},
                            {10,  6,  9,  0, 12, 11,  7, 13, 15,  1,  3, 14,  5,  2,  8,  4},
                            {3, 15,  0,  6, 10,  1, 13,  8,  9,  4,  5, 11, 12,  7,  2, 14 }};
    
    public int sbox5[][]={{2, 12,  4,  1,  7, 10, 11,  6,  8,  5,  3, 15, 13,  0, 14,  9},
                            {14, 11,  2, 12,  4,  7, 13,  1,  5,  0, 15, 10,  3,  9,  8,  6},
                            {4,  2,  1, 11, 10, 13,  7,  8, 15,  9, 12,  5,  6,  3,  0, 14},
                            {11,  8, 12,  7,  1, 14,  2, 13,  6, 15,  0,  9, 10,  4,  5,  3 }};
    
    public int sbox6[][]={{12,  1, 10, 15,  9,  2,  6,  8,  0, 13,  3,  4, 14,  7,  5, 11},
                            {10, 15,  4,  2,  7, 12,  9,  5,  6,  1, 13, 14,  0, 11,  3,  8},
                            {9, 14, 15,  5,  2,  8, 12,  3,  7,  0,  4, 10,  1, 13, 11,  6},
                            {4,  3,  2, 12,  9,  5, 15, 10, 11, 14,  1,  7,  6,  0,  8, 13 }};
    
    public int sbox7[][]={{4, 11,  2, 14, 15,  0,  8, 13,  3, 12,  9,  7,  5, 10,  6,  1},
                            {13,  0, 11,  7,  4,  9,  1, 10, 14,  3,  5, 12,  2, 15,  8,  6},
                            {1,  4, 11, 13, 12,  3,  7, 14, 10, 15,  6,  8,  0,  5,  9,  2},
                            {6, 11, 13,  8,  1,  4, 10,  7,  9,  5,  0, 15, 14,  2,  3, 12 }};
    
    public int sbox8[][]={{13,  2,  8,  4,  6, 15, 11,  1, 10,  9,  3, 14,  5,  0, 12,  7},
                            {1, 15, 13,  8, 10,  3,  7,  4, 12,  5,  6, 11,  0, 14,  9,  2},
                            {7, 11,  4,  1,  9, 12, 14,  2,  0,  6, 10, 13, 15,  3,  5,  8},
                            {2,  1, 14,  7,  4, 10,  8, 13, 15, 12,  9,  0,  3,  5,  6, 11 }};

    public int final_perm[] = { 40, 8, 48, 16, 56, 24, 64, 32, 
                                39, 7, 47, 15, 55, 23, 63, 31, 
                                38, 6, 46, 14, 54, 22, 62, 30, 
                                37, 5, 45, 13, 53, 21, 61, 29, 
                                36, 4, 44, 12, 52, 20, 60, 28, 
                                35, 3, 43, 11, 51, 19, 59, 27, 
                                34, 2, 42, 10, 50, 18, 58, 26, 
                                33, 1, 41, 9, 49, 17, 57, 25 }; 
    public int pc1[] = { 57, 49, 41, 33, 25, 
                      17, 9, 1, 58, 50, 42, 34, 26, 
                      18, 10, 2, 59, 51, 43, 35, 27, 
                      19, 11, 3, 60, 52, 44, 36, 63, 
                      55, 47, 39, 31, 23, 15, 7, 62, 
                      54, 46, 38, 30, 22, 14, 6, 61, 
                      53, 45, 37, 29, 21, 13, 5, 28, 
                      20, 12, 4 }; 
    public int pc2[] = {
                14,17,11,24,1,5, 
                3,28,15,6,21,10, 
                23,19,12,4,26,8, 
                16,7,27,20,13,2, 
                41,52,31,37,47,55, 
                30,40,51,45,33,48, 
                44,49,39,56,34,53, 
                46,42,50,36,29,32 
              };
    public int permutation_table[] = {
            16,7,20,21,29,12,28,17, 
            1,15,23,26,5,18,31,10, 
            2,8,24,14,32,27,3,9, 
            19,13,30,6,22,11,4,25 
        };
    
    int initial_permutation_table[] ={58,50,42,34,26,18,10,2,60,52,44,36,28,20,12,4,62,54,46,38,30,22,14,6,64,56,48,40,32,24,16,8,57,49,41,33,25,17,9,1,59,51,43,35,27,19,11,3,61,53,45,37,29,21,13,5,63,55,47,39,31,23,15,7};
    int expansion_table[] = {   
                    32,1,2,3,4,5,4,5, 
                    6,7,8,9,8,9,10,11, 
                    12,13,12,13,14,15,16,17, 
                    16,17,18,19,20,21,20,21, 
                    22,23,24,25,24,25,26,27, 
                    28,29,28,29,30,31,32,1 
                  }; 
    
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


    //mohamed ashraaf

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
    
    public ArrayList<String> make64(String path) throws IOException{//read the data and make it n*64-bit where n is integer > 1
        ArrayList<String> chars = new ArrayList<String>();
        ArrayList<String> data = readfile(path);//read data
        String plain = data.get(0);
        String binary = "";
        //convert plain text to binary
        for(int i = 0 ; i < plain.length() ; i++){
            int num = (int)plain.charAt(i);//get ascii
            String tmp = Integer.toBinaryString(num); //get binary  0 1110101
            while(tmp.length() < 8){//add the missing bits
                tmp = "0" + tmp;
            }
            binary += tmp;
        }
        //cut the binary into 64-bit each and put it in chars
        for(int i = 0 ; i < binary.length() ; i+=64){
            String tmp= "";
            for(int j = i ; j < i+64 ; j++){
                if(j == binary.length())
                    break;
                tmp += binary.charAt(j);
            }
            chars.add(tmp);
        }
        //check if the last one is not equal to 64 if not add zeros to the right
        if(chars.get(chars.size()-1).length() != 64){
            String tmp2 = chars.get(chars.size()-1);
            while(tmp2.length() < 64){//add the missing bits
                tmp2 = "0" + tmp2;
            }
            chars.set(chars.size()-1, tmp2);
        }
        return chars;
    }
    public String readKey() throws IOException{//read key from user and enter it to pc1
        
        String key = "";
        
        while(key.length() != 64)//get input from user and make sure it's exactly 64-bit 
        {
            key = "";
            System.out.println("this  key is too short please enter 8 characters ");
            Scanner scanner = new Scanner(System.in);
            String tmpkey = scanner.nextLine();
            
            for(int i = 0 ;i < tmpkey.length() ; i++){
                int num = (int)tmpkey.charAt(i);//get ascii
                String tmp = Integer.toBinaryString(num); //get binary  0 1110101
                while(tmp.length() < 8){//add the missing bits
                    tmp = "0" + tmp;
                }
                key += tmp;
            }
        }
        
        //commit the key to PC-1 
        String keypc1 = "";
        for(int i = 0 ; i < 56 ; i++){
            keypc1 += key.charAt(pc1[i] - 1);
        }
        
        return keypc1;
    }
    public String rightShift(String data, int num){//circullar right shift by num
        String shift = "";
        if  (num == 1){
            shift += data.charAt(27);
            for (int  i = 0 ; i < 27 ; i++)
            {
                 shift += data.charAt(i);
            }
        
        }
        else{
            shift += data.charAt(26);
            shift += data.charAt(27);
            for (int  i = 0 ; i < 26 ; i++)
             {
                 shift += data.charAt(i);
             }
        }
                
        return shift; 
    }
    public String leftShift(String data, int num)//circullar left shift by num
    {
        String shift = "";
        if  (num == 1){
            for (int  i = 1 ; i < 28 ; i++)
            {
                 shift += data.charAt(i);
            }
        
            shift += data.charAt(0);
        }
        else{
        
            for (int  i = 2 ; i < 28 ; i++)
             {
                 shift += data.charAt(i);
             }
            shift += data.charAt(0);
            shift += data.charAt(1);
        }
                
        return shift; 
    }
    public ArrayList<String> generateKey() throws IOException{//read first key and generate 16 more keys
        ArrayList<String> keys = new ArrayList<String>();
        String key = readKey();//read key 0
        String left = "" , right ="";
        
        for(int i = 1 ; i< 17 ; i++){
            //split the key into two parts
            for(int j = 0 ; j < 56 ; j++){
                if(j < 28){
                    left += key.charAt(j);
                }
                else
                    right += key.charAt(j);
            }
            //check what round we are in so you know the number of shift
            if(i == 1 || i == 2 || i == 9 || i == 16){
                right = leftShift(right,1);
                left = leftShift(left,1);
            }
            else{
                right = leftShift(right,2);
                left = leftShift(left,2);
            }
            key = left + right;
            
            
            String tmp="";
            //addmit key to PC-2
            for(int j = 0 ; j < pc2.length ;j++){
                tmp += key.charAt(pc2[j] - 1);
                
            }
            left ="";right ="";
            
            keys.add(tmp);
            
        }
        
        return keys;
    }
    public ArrayList<String> generateKeyDec() throws IOException{//call key-16 and generate 16 more keys
        ArrayList<String> keys = new ArrayList<String>();
        String key = readKey();
        String left = "" , right ="";
        
        String tmp="";
        //addmit key to PC-2
        for(int j = 0 ; j < pc2.length ;j++){
            tmp += key.charAt(pc2[j] - 1);
        }
        keys.add(tmp);
        
        for(int i = 2 ; i< 17 ; i++){//calculate the rest 15 keys
            //split key
            for(int j = 0 ; j < 56 ; j++){
                if(j < 28){
                    left += key.charAt(j);
                }
                else
                    right += key.charAt(j);
            }
            //check what round we are in so you know the number of shift
            if(i == 1 || i == 2 || i == 9 || i == 16){
                right = rightShift(right,1);
                left = rightShift(left,1);
            }
            else{
                right = rightShift(right,2);
                left = rightShift(left,2);
            }
            key = left + right;
            left = "";right = "";
            
            tmp="";
            //addmit key to PC-2
            for(int j = 0 ; j < pc2.length ;j++){
                tmp += key.charAt(pc2[j] - 1);
            }
            keys.add(tmp);
        }
        
        return keys;
    }
    public int toDesimal(String n){//transform binary in a string to its' desimal number 
        
        String num = n; 
        int dec_value = 0; 
  
        // Initializing base value to 1, 
        // i.e 2^0 
        int base = 1; 
  
        int len = num.length(); 
        for (int i = len - 1; i >= 0; i--) { 
            if (num.charAt(i) == '1') 
                dec_value += base; 
            base = base * 2; 
        }
  
        return dec_value;
    }
    public String S_boxing(String tmp,int round){//take 48-bit string and operate on them to output 32-bit 
        String row = tmp.charAt(5) + "";
        row += tmp.charAt(0);
        String col = tmp.charAt(1)+"" ;
        col += tmp.charAt(2);
        col += tmp.charAt(3);
        col += tmp.charAt(4);
        int indxrow = toDesimal(row);
        int indxcol = toDesimal(col);
        int value ;
        if(round == 1){
            value = sbox1[indxrow][indxcol];
        }
        else if(round == 2){
            value = sbox2[indxrow][indxcol];
        }
        else if(round == 3){
            value = sbox3[indxrow][indxcol];
        }
        else if(round == 4){
            value = sbox4[indxrow][indxcol];
        }
        else if(round == 5){
            value = sbox5[indxrow][indxcol];
        }
        else if(round == 6){
            value = sbox6[indxrow][indxcol];
        }
        else if(round == 7){
            value = sbox7[indxrow][indxcol];
        }
        else{
            value = sbox8[indxrow][indxcol];
        }
        String binary = Integer.toBinaryString(value);
        while(binary.length() < 4){//add the missing bits
            binary = "0" + binary;
        }
        
        return binary;
    }
    //xoring for the expand of the right and the key then transform the output into 32-bit instead of 48-bit
    //then the output goes into permutaion table
    public String F_Function(String expand, String key){  
        String xor = XORing(expand, key);
        ArrayList<String> chars = new ArrayList<String>();
        String func = "";
        for(int i = 0 ; i < xor.length() ; i+=6){//cut the 48-bit into 6-bit each
            String tmp= "";
            for(int j = i ; j < i+6 ; j++){
                if(j == xor.length())
                    break;
                tmp += xor.charAt(j);
            }
            chars.add(tmp);
        }
        for(int i = 1 ; i < 9 ; i++){//addmit each 6-bit to its' right S-Box
            String tmp = S_boxing(chars.get(i-1),i);
            func += tmp;
        }
        //addmit the output into permutaion table
        String perm = "";
        for(int i = 0 ; i< 32 ; i++){
            perm += func.charAt(permutation_table[i] -1);
        }
        return perm;
    }
    
    public String encrypt() throws IOException{//cipher
        String path = "F:\\college\\third year\\second term\\Computer Network Security\\assignments\\DES-encryption\\DES-encryption\\text.txt";
        ArrayList<String> chars = make64(path);//get the plain text as array each index contain 64-bit
        ArrayList<String> keys = generateKey();//generate the 16 keys
        String cipher = "";
        for(int i =0 ; i < chars.size() ; i++){//for each 64-bit in the array
            
            String tmp = chars.get(i);
            
            //addmit it to initial permutation table
            String perm ="";
            for(int j = 0 ; j < 64 ; j++){
                perm += tmp.charAt(initial_permutation_table[j] -1);
            }
            String left = "" , right ="";
            for(int r = 0 ; r < 16 ; r++){
                left = "" ; right = "";
                //split the key
                for(int j = 0 ; j < 64 ; j++){
                    if(j < 32){
                        left += perm.charAt(j);
                    }
                    else
                        right += perm.charAt(j);
                }
                String expand = "";
                
                //get the expantion of the right 
                for(int j = 0 ; j < expansion_table.length ; j++){
                    expand += right.charAt(expansion_table[j] - 1);
                }
                
                String fFun = F_Function(expand, keys.get(r));
                left = XORing(left, fFun);
                //swap the new left and the right
                String swap = left;
                left = right;
                right = swap;
                
                perm = left + right;
                
                
            }
            //do one final swap
            String swap2 = left;
            left = right;
            right = swap2;
            
            perm = left + right;
            
            //addmit on final permutation table
            String finalperm = "";
            for(int j = 0 ; j < final_perm.length ; j++){
                finalperm += perm.charAt(final_perm[j] - 1);     
            }
            cipher += finalperm;
            
        }
        //get the ascci code of the binary output
        String file = "";
        for(int i = 0 ; i < cipher.length() ;i+=8){
            int ascci  = Integer.parseInt(cipher.substring(i, i+8), 2);
            file +=   Integer.toString(ascci) + " ";
        }
        //write it in ciphered.txt
        FileWriter fw=new FileWriter("F:\\college\\third year\\second term\\Computer Network Security\\assignments\\DES-encryption\\DES-encryption\\cipherd.txt");
        for (int i = 0; i < file.length(); i++) 
            fw.write(file.charAt(i));
        fw.close();
        
        return cipher;
    }
    public String decrypt() throws IOException{//decipher with the same operations as the cipher
        String path = "F:\\college\\third year\\second term\\Computer Network Security\\assignments\\DES-encryption\\DES-encryption\\cipherd.txt";
        ArrayList<String> ascii = readfile(path);
        ArrayList<String> keys = generateKeyDec();
        ArrayList<String> chars = new ArrayList<String>();
        String code = arrayToString(ascii);
        String nums[] = code.split(" ");//split each ascii number to convert later
        String ciphered = "";
        for(int i = 0 ; i < nums.length ; i++){//convert to binary
            int num = Integer.parseInt(nums[i]);
            String tmp = Integer.toBinaryString(num); //get binary  0 1110101
            while(tmp.length() < 8){//add the missing bits
                tmp = "0" + tmp;
            }
            ciphered += tmp;
        }
        //cut the binary to 64-bit each
        for(int i = 0 ; i < ciphered.length() ; i+=64){
            String tmp= "";
            for(int j = i ; j < i+64 ; j++){
                if(j == ciphered.length())
                    break;
                tmp += ciphered.charAt(j);
            }
            chars.add(tmp);
        }
        
        String decipher = "";
        for(int i =0 ; i < chars.size() ; i++){
            
            String tmp = chars.get(i);
            String perm ="";
            for(int j = 0 ; j < 64 ; j++){
                perm += tmp.charAt(initial_permutation_table[j] -1);
            }
            String left = "" , right ="";
            for(int r = 0 ; r < 16 ; r++){
                left = "" ; right = "";
                for(int j = 0 ; j < 64 ; j++){
                    if(j < 32){
                        left += perm.charAt(j);
                    }
                    else
                        right += perm.charAt(j);
                }
                String expand = "";
                
                
                for(int j = 0 ; j < expansion_table.length ; j++){
                    expand += right.charAt(expansion_table[j] - 1);
                }
                
                String fFun = F_Function(expand, keys.get(r));
                left = XORing(left, fFun);
                
                String swap = left;
                left = right;
                right = swap;
                
                perm = left + right;
                
                
            }
            String swap2 = left;
            left = right;
            right = swap2;
            
            perm = left + right;
            
            
            String finalperm = "";
            for(int j = 0 ; j < final_perm.length ; j++){
                finalperm += perm.charAt(final_perm[j] - 1);     
            }
            decipher += finalperm;
        
        }
        //convert binary to chars
        String str = "";
        for (int i = 0; i < decipher.length()/8; i++) {
            int a = Integer.parseInt(decipher.substring(8*i,(i+1)*8),2);
            str += (char)(a);
        }

        System.out.println(str);
        return decipher;
    }
    public static void main(String[] args) throws IOException {
        DESEncryption des = new DESEncryption();
        des.encrypt();
        des.decrypt();
    }
    
}

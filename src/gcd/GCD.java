/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcd;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author Andrew Searles
 */

//The purpose of this algorithm is to show that the Searles GCD algorithm takes less steps to operate than that of the Euclidean algorithm
public class GCD { 
    //declare variables for counting and writing
    private static Writer writer;
    private static long n = 0; //for Searles
    private static long m = 0; //for Euclidean
    
    //This is a recursive version of the Searles GCD algorithm 
//    public static void gcd(BigInteger a, BigInteger b) throws IOException {
//
//	if (b.equals(BigInteger.ZERO))  {     
//            writer.write("S,"+a);
//	} else {
//            n++;
//            BigInteger q = a.divide(b);
//            BigInteger r = a.subtract(q.multiply(b));
//            BigInteger l = ((q.add(BigInteger.ONE)).multiply(b)).subtract(a);
//            gcd(b ,r.min(l));
//	}
//    }
    
    //This is an iterative version of the Searles GCD algorithm
        public static void gcd(BigInteger a, BigInteger b) throws IOException {                      
        
            //essentially compares the inverse mod to the regular mod and takes the smaller one allowing for steps to be skipped
	while(b.equals(BigInteger.ZERO) != true)  {  
            n++;
            BigInteger q = a.divide(b);
            BigInteger r = a.subtract(q.multiply(b));
            BigInteger l = ((q.add(BigInteger.ONE)).multiply(b)).subtract(a);
            a=b;
            b = r.min(l);
	}
        writer.write("S,"+a);
    }

        
    //Basic recursive implementation of the Euclidean GCD algorithm
    public static void gcdOriginal(BigInteger a, BigInteger b) throws IOException {
	m++;
	if (b.equals(BigInteger.ZERO)) {
            writer.write("E,"+a);
        } else {
            gcdOriginal(b, a.mod(b));
        }
    }
  
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        //declare variables
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("results.txt"), "utf-8"));
        int aM = 0;
        int aN = 0;
        //format CSV
        writer.write("First,GCD,Steps,Second,GCD,Steps,\n");
        //randomly generated combinations used to test accuracy and steps needed.
        for(int i =0 ; i < 2000; i++) {
                m = 0;
                n = 0;
                BigInteger b = new BigInteger(1024, new Random());
                BigInteger a = new BigInteger(1024, new Random());
                gcdOriginal(a,b);
                writer.write(","+m+",");
                gcd(a,b);
                writer.write(","+n+",\n");
                aM += m;
                aN += n;
                
        } //end for
        //Dont forget to close me ;) -writer
        writer.close();

        //total steps on all operations
        System.out.println("Euclidean: "+aM);
        System.out.println("Searles: "+aN);
        
        //for testing accuracy of results
//        for(int i = 0; i < 1000000; i++){
//                
//               System.out.println(anArray[i]);
//               System.out.println(Array[i]);
//               System.out.println("");
//               if (anArray[i] != Array[i]) {
//                    System.out.println("fail");
//                    break;
//                }
//        }


    }//end main
    
} //end class GCD

//George Navarre
//5/26/2017
//CS 140
//Lab #5 Mad Lib
//This program will allow a user to do mad libs




import java.io.*;
import java.util.*;
public class MadLibs{

//Calls on the appropriate methods through a user menu
   public static void main(String[] args)throws FileNotFoundException{
      Scanner console = new Scanner(System.in);
      String command = "";
      intro();
      while(!command.toLowerCase().equals("q")){
         System.out.println();
         System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
         command = console.nextLine();
         if(command.toLowerCase().equals("c")){
            Scanner read = fileCheck(console);
            System.out.print("Output file name: ");
            File out = new File(console.nextLine());
            PrintStream result = new PrintStream(out);
            replace(result, read);       
         }else if(command.toLowerCase().equals("v")){
            Scanner read = fileCheck(console);
            System.out.println();
            while(read.hasNextLine()){
               System.out.println(read.nextLine());
            } 
         }
      }  
   }

//Prints the introduction   
   public static void intro() {  
   System.out.println("Welcome to the game of Mad Libs");
   System.out.println("I will ask you to provide various words");
   System.out.println("and phrases to fill in a story.");
   System.out.println("The result will be written to an output file.");   
 }
 
 //Finds place holders in a file and replaces them with user entered words
   public static void replace(PrintStream result, Scanner readOut){
      Scanner console = new Scanner(System.in);
      int end = 0;
      int start = 0;      
      while(readOut.hasNextLine()){
         String line = readOut.nextLine();
         Scanner lineScan = new Scanner(line);
         String word = "";
         while(lineScan.hasNext()){
            word = lineScan.next();             
            if(word.contains("<")){
               word = placeholder(word);            
               start = line.indexOf("<");
               end = line.indexOf(">");            
               line = line.replace(line.substring(0, end + 1), " ");            
               System.out.print("Please type " + word + " ");
               word = console.next();
            }
            result.print(word + " ");                          
         }
         result.println();
      }
      System.out.println("Your mad-lib has been created!");
   }
   
//Edits the placeholder and returns it to the replace method as a string   
   public static String placeholder(String word){
      int start = word.indexOf("<");
      int end = word.indexOf(">");            
      word = word.substring(start + 1, end);
      if(word.contains("-")){
         word = word.replace("-", " ");
      }
      if(word.toLowerCase().startsWith("a") || word.toLowerCase().startsWith("i") || word.toLowerCase()
      .startsWith("o") || word.toLowerCase().startsWith("u") || word.toLowerCase().startsWith("e")){
         word = "an " + word.toLowerCase();
      }else{
         word = "a " + word.toLowerCase();
      }
      return word;      
   }
//Checks to see if a file is valad. If it is the method will return a Scanner of the file   
   public static Scanner fileCheck(Scanner console)throws FileNotFoundException{
      System.out.print("Input file name: ");
      String inFileName = console.nextLine();
      File in = new File(inFileName);
      while(!in.canRead()){
         System.out.print("File not found. Try again: ");
         inFileName = console.nextLine();
         in = new File(inFileName); 
      }
      Scanner read = new Scanner(in);
      return read; 
   }
}   
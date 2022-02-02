package Part1_ArrayLIst_FileIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


/**
 * Class FileParser
 */
public class FileParser {

    /**
     * Member Variables
     */


    //ArrayList containing alphabet characters
    ArrayList<String> alphabetArrList = alphabet();



    //Contains all DISTINCT words that don't have the characters: "o", "n", "a".
    // This includes: non-alphanumeric words.
    // They have to be sorted in alphabetic order
    ArrayList<String> obsessive_non_ona_ArrList = new ArrayList<String>();




    //Contains all words repeated 3 times or more
    // They have to be sorted in alphabetic order
    // Contains alphabet
    ArrayList<String> popular_peeps_ArrList = new ArrayList<String>();

    //Contains all non-alphanumeric words.
    ArrayList<String> non_alphanumeric_ArrList = new ArrayList<String>();


    //3 output files Generated
    public final String obsessive_non_ona_FileName = "example_obsessive_non_ona.txt";
    public final String popular_peeps_FileName = "example_popular_peeps.txt";
    public final String non_alphanumeric__FileName = "non_alphanumeric.txt";


     String inputTextFileName;
     ArrayList<String> lowerCaseData; // Use toLowerCaseArrayList(String fileName to initialize it.

    /**
     * Parmetrized Constructor
     * @param filename
     */
    public FileParser(String filename) throws FileNotFoundException {
        inputTextFileName = filename;
        lowerCaseData = toLowerCaseArrayList(inputTextFileName); //Convert file data to lower case and stores it in lower case
        Collections.sort(lowerCaseData); //sorts lowerCaseData excluding alphabet
    }




    public void process() throws FileNotFoundException{




    }


    /**
     * WordCount()
     * Returns number of words in a file
     * @param fileName
     * @return
     */
    public int wordCount(String fileName) throws FileNotFoundException{
        int wordCount=0;

        Scanner input = new Scanner(new FileInputStream(fileName));

            while(input.hasNext()) {
                wordCount++;
                input.nextLine();
            }

        input.close();
        return wordCount;
    }


    /**
     * toLowerCaseArrayList
     * @param fileName
     * @return
     */
    public ArrayList<String> toLowerCaseArrayList(String fileName) throws FileNotFoundException {
        ArrayList<String> lowerCaseData = new <String>ArrayList();

        Scanner input = new Scanner(new FileInputStream(fileName));

            while(input.hasNext()) {
                // if the next is not alpha-numeric
                if(!input.next().contains("[^a-zA-Z]")){
                    lowerCaseData.add(input.next().toLowerCase());// *** Check if it needs another nextLine()
                }
                // if it is alpha numeric, then don't make it lower case
                if (input.next().contains("[^a-zA-Z]")){
                    lowerCaseData.add(input.next());
                }
            }

        input.close();
        return lowerCaseData;
    }

    //Contains all DISTINCT words that don't have the characters: "o", "n", "a".
    // This includes: non-alhpanumeric words.
    // They have to be sorted in alphabetic orde
    public void obsessive_non_ona_Method() {
        ArrayList<String> temp = lowerCaseData; //adds all lower case String data
        temp.addAll(alphabetArrList); // adds all alphabet Strings
        Collections.sort(temp); // Sorts ArrayList
        for (String element : temp) {
            if (element.contains("o") || element.contains("n") || element.contains("a")) {
                temp.remove(element);
            }
        }
        obsessive_non_ona_ArrList = temp;
    }

    /**
     *
     *     //Contains all words repeated 3 times or more
     *     // They have to be sorted in alphabetic order
     */
    public void popular_peeps_ArrList_Method(){
        ArrayList<String> duplicates = new ArrayList<String>(); //Empty Array Containing all duplicates
        ArrayList<String> tempArrayList = lowerCaseData;  //Temporary String Array List that contains all lowerCaseData
        String[] tempArray  = (String[]) tempArrayList.toArray(); //Create String array copy of lowerCaseData, which will used to compare 3 consecutive indexes
        while (tempArrayList.size() != 0){  // Loops through ArrayList
            for (String element : tempArrayList) { // Cycles through ArrayList copy of data
                for (int i = 0; i< tempArray.length; i++) { // Cycles through Array String
                    //check if three consecutive values in Array of string are equivalent
                    if(tempArray[i].equals(tempArray[i+1]) && tempArray[i].equals(tempArray[i+2])){
                        duplicates.add(tempArray[i]); //adds repeated data member to duplicates ArrayList
                    }
                    while (tempArrayList.contains(tempArray[i])){  //removes all elements of containing duplicates
                        for (String element1 : tempArrayList){
                            if (element.equals(tempArray[i])){
                                tempArrayList.remove(i);
                            }
                        }
                    } // end of while loop


                } // end of for-loop of Array String.
            } // end of for-each loop of temp ArrayList. By the end of this loop, ArrayList must empty;
            // And duplicates ArrayList is updated.

        } // end while-loop
        popular_peeps_ArrList = duplicates; //updates popular_peeps_ArrList with all duplicates
        popular_peeps_ArrList.addAll(alphabetArrList);
        Collections.sort(popular_peeps_ArrList);

    } // end of Method


    /**
     * non_alphanumeric_ArrList_Method()
     //Contains all non-alphanumeric words.
     */
    public void non_alphanumeric_ArrList_Method() {
        ArrayList<String> temp = lowerCaseData;
        for (String element: temp) {
            if(lowerCaseData.contains("[^a-zA-Z]")){
                non_alphanumeric_ArrList.add(element);
            }
        }






    }


    public void removeDuplicates(ArrayList<String> arrayList){
        String[] tempArray  = (String[]) arrayList.toArray(); //Create String array copy of lowerCaseData, which will used to compare 3 consecutive indexes
        for (String element: arrayList) {
            for (int i = 0; i< tempArray.length; i++) { // Cycles through Array String
                //check if three consecutive values in Array of string are equivalent
                if(tempArray[i].equals(tempArray[i+1])){
                            arrayList.remove(i);
                }
            } // end of for-loop
        } // end of for-each loop
    }


    //Alphabet ArrayList
    public ArrayList<String> alphabet(){
        ArrayList<String> alphabetArrList= new ArrayList<String>();
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for (char element: alphabet){
            alphabetArrList.add(String.valueOf(element));
        }
        return alphabetArrList;
    }




















        public static void main(String[] args) throws FileNotFoundException {
        Scanner keybd = new Scanner(System.in);
        String filename;
        System.out.print("Enter input filename: ");
        filename = keybd.next();
        FileParser p = new FileParser();
        p.process();
    }
}




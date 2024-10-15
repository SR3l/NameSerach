import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/*
100 points
Homework #2. Chapter 7. PC #13. Name Search (page 492)
Read these instructions for additional requirements carefully.
Write a program that reads the contents of the two files into two separate arrays. The user should be able to enter a name the application will display messages indicating
whether the names were among the most popular.
1. GirlNames.txt and BoyNames.txt contain a list of the 200 most popular names given to girls/boys born in the United States for the years 2000 through 2009.

2. Your application should use an array to hold the names.

3. The program should continue interacting with the user indefinitely unless the user chooses to quit by entering "QUIT" (should be case insensitive).

4. The user should enter a single name and the program will search the name in both lists. The user SHOULD NOT specify whether the search is for
 girls name or boys name. The program is responsible for finding either or both and telling the user where it found it.

5. The program should display one of the following four results (examples):


* The name 'Annabelle' was not found in either list.
* The name 'Xavier' was found in popular boy names list (line 81).
* The name 'Amanda' was found in popular girl names list (line 63).
* The name 'Jordan' was found in both lists: boy names (line 38) and girl names (line 75).
6. The search should be case insensitive, but the resulting message should always display names in properly capitalized format (the first letter uppercase all others lowercase).
7. The files should only be opened/loaded and closed once per program session (at the beginning of the program execution). During the search the files should be closed.
8. If any or both of the files are missing, display a message to the user informing which file is missing or if both files are missing and exit the program.
9. Your program should contain properly defined and used methods. For example, your program should have a method that loads
a file into an array. This method should take one parameter for the file name to be loaded and return an array of Strings.
Another method should take a name and an array reference and perform a search and return results to the calling method.
10. Do not upload input text files.
 */

public class Main {
    public static void main(String[] args) {



        final int myList = 200;


        String[] boyNames = new String[myList];
        String[] girlNames = new String[myList];



        load(boyNames,"BoyNames.txt");
        load(girlNames, "GirlNames.txt");

        Scanner scan = new Scanner(System.in);

        //validate
        while (true) {
            System.out.println("Enter a name to search or type QUIT to exit:");
            String babyName = scan.nextLine();

            if (babyName.equalsIgnoreCase("quit")) {
                break;
            }

            String formattedName = capitalizeName(babyName);

            int boyNamesIndex = getName(boyNames, formattedName);
            int girlGirlsIndex = getName(girlNames, formattedName);

            // if else traversing through names within the file
            if (boyNamesIndex == -1 && girlGirlsIndex == -1) {
                System.out.printf("The name '%s' was not found in either list.\n", formattedName);
            } else if (boyNamesIndex != -1 && girlGirlsIndex == -1) {
                System.out.printf("The name '%s' was found in popular boy names list (line %d).\n",
                        formattedName, boyNamesIndex + 1);
            } else if (boyNamesIndex == -1) {
                System.out.printf("The name '%s' was found in popular girl names list (line %d).\n",
                        formattedName, girlGirlsIndex + 1);
            } else {
                System.out.printf("The name '%s' was found in both lists: boy names (line %d) and girl names (line %d).\n",
                        formattedName, boyNamesIndex + 1, girlGirlsIndex + 1);
            }
        }
        scan.close();
    }


    private static String capitalizeName(String name) {
        if (name.isEmpty()) {
            return name;
        }
        return Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();
    }


    private static void load(String[] names, String fileName) {
        try {
            Scanner fileScan = new Scanner(new File(fileName));  // Corrected
            int count = 0;
            while (fileScan.hasNextLine() && count < names.length) {
                names[count] = fileScan.nextLine();
                count++;
            }
            fileScan.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }



    private static int getName(String[] names, String name) {
        for (int i = 0; i < names.length; i++) {
            if (names[i] != null && names[i].equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }
}

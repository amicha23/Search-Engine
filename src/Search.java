/*
Manage documents
and compute relevance of a given
term within a given directory.
 */

import searchEngine.SearchEngine;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class Search {

    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);
        //Test path
//        Document doc = new Document("C:\\Users\\andre\\IdeaProjects\\TF-IDF Search Engine\\src\\test_files\\lyrics.txt");
//        System.out.println(doc);
//        System.out.println(doc.term_frequency("and"));
//        System.out.println(doc.get_words());
//        System.out.println(doc.get_path());
        main(console);
    }

    public static void main(Scanner console) throws IOException {
        System.out.println("Welcome to 6oog13!!");
        System.out.println("Please enter a the name of a directory: ");
        String directory = console.nextLine();

        System.out.println("Building Search Engine...");
        System.out.println();
        SearchEngine engine = new SearchEngine(directory);

        char answer = 'y';
        while (answer == 'y') {
            System.out.print("Search (enter term to query): ");
            String term = console.nextLine();
            if (term.equals("")) {
                term = console.nextLine();
            }
            List<String> ranking = engine.search(term);
            System.out.println("Showing results for " + "'" + term + "':");
            if (ranking == null) {
                System.out.println("    No results ");
            } else {
                int rank = 1;
                for (String doc : ranking) {
                    System.out.println("    " + rank + ". " + doc);
                    rank += 1;
                }
                System.out.println();
            }
            answer = (char) 0;
            while (answer != 'y' && answer != 'n') {
                System.out.println("Would you like to search for another term (y/n) ");
                answer = console.next().charAt(0);
            }
        }
    }

}

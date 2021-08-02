/*
Search Engine. Manage documents
and compute relevance for a given
term us TF-IDF calculations.
*/

package searchEngine;

import Pair.Pair;
import document.Document;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class SearchEngine {

    private HashMap<String, List<Document>> inverseIndex;
    private double totalDocs;

    public SearchEngine(String directory) throws IOException {
        /*
        Initialize an inverse index dictionary
        pairing words to documents within
        a given directory.
        */
        this.inverseIndex = new HashMap<>();
        this.totalDocs = 0;

        List<Path> pathList = new ArrayList<>();
        System.out.println(directory);

        Files.list(new File(directory).toPath())
                .limit(10)
                .forEach(path -> {
                    System.out.println(path);
                    pathList.add(path);
                });
        System.out.println(pathList);
        for (Path file_name:pathList) {
            this.totalDocs += 1.0;
            String path = "" + file_name;
            Document doc = new Document(path);

            for (String word: doc.get_words()) {
                if (this.inverseIndex.containsKey(word)){
                    this.inverseIndex.get(word).add(doc);
                }
                else {
                    // List<Document> newList = new ArrayList<>();
                    // newList.add(doc);
                    this.inverseIndex.put(word, new ArrayList<>());
                    this.inverseIndex.get(word).add(doc);
                }
            }
        }
        // System.out.println(this.inverseIndex);
        // System.out.println(this.totalDocs);
    }

    private double calculate_idf(String term) {
        /*
        Return the IDF score for a given
        term (disregarding casing and punctuation)
        over all documents in a directory.
        If the term does not appear in the search
        engine, return 0 instead.
        */
        term = term.replaceAll("\\W+", "");
        term = term.toLowerCase();
        if (!this.inverseIndex.containsKey(term)){
            return 0;
        }
        double term_count = this.inverseIndex.get(term).size();
        double score = Math.log(this.totalDocs / term_count);
        return score;
    }

    public List<String> search(String term) {
        /*
        Return a list of document names given
        a search term or terms (disregarding
        casing and punctuation). Rank documents based
        on relevance determined by TF-IDF score
        in descending order. If the search engine
        does not contain the term or all terms given,
        return None.
        */
        double wordOccurance = 0;
        String[] termArray = term.split("//W+");
        List<String> terms = new ArrayList<>();
        HashSet<Document> files_set = new HashSet<>();
        //Return case insensitive terms
        for (String word: termArray) {
            word = word.replaceAll("\\W+", "");
            word = word.toLowerCase();
            terms.add(word);
            // Remove duplicate documents
            if (this.inverseIndex.containsKey(word)) {
                wordOccurance += 1;
                List<Document> select_file = this.inverseIndex.get(word);
                for (Document file_count: select_file) {
                    files_set.add(file_count);
                }
            }
        }
        //check if word / words in inverse index
        if (wordOccurance < 1) {
            return null;
        }
        // List<Document> select_file = list(files_set)
        List<Document> select_file = new ArrayList<>(files_set);
        List<Pair<String, Double>> file_scores = new ArrayList<>();
        double current_score = 0;
        for (Document doc:select_file) {
            //Calculate TF -IDF for each file
            for (String termInput: terms) {
                double idf = this.calculate_idf(termInput);
                double tf = doc.term_frequency(termInput);
                double score = idf * tf;
                current_score += score;
            }
            // Pair<String, Double> newPair = new Pair<>(doc.get_path(), current_score);
            file_scores.add(new Pair<>(doc.get_path(), current_score));
            current_score = 0;
        }

        Comparator<Pair<String, Double>> comparator = new Comparator<>()
        {

            public int compare(Pair<String, Double> tupleA,
                               Pair<String, Double> tupleB)
            {
                return tupleA.x.compareTo(tupleB.x);
            }

        };

        Collections.sort(file_scores, comparator);
        List<String> result = new ArrayList<>();

        for (Pair<String, Double> sorted_file: file_scores) {
            result.add(sorted_file.x);
        }
        return result;
    }




}

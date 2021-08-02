/*
Document Class. Represent a single
file in the search engine and compute
term frequencies in the document.
*/
package document;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Document {
    private List<String> uniqueWords;
    private HashMap<String,Double> termFrequencies;
    private String path;


    public Document(String path) throws IOException {
        /*
        Intialize document and
        compute its term frequencies
        into a dictionary pairing
        term to frequency. Ignore term
        punctuation and casing.
        */
        this.uniqueWords = new ArrayList<>();
        this.termFrequencies = new HashMap<>();
        this.path = path;


        File f = new File(path);
        FileInputStream fis = new FileInputStream(f);
        byte[] data = new byte[(int) f.length()];
        fis.read(data);
        fis.close();

        String content = new String(data, "UTF-8");
        String[] newContent = content.split("\\W+");
        // System.out.println(newContent[0]);
        int num_terms = newContent.length;
        for (String word: newContent) {
            // word = re.sub(r '\W+', '', word).toLowercase();
            word = word.replaceAll("\\W+", "");
            word = word.toLowerCase();
            if (this.termFrequencies.containsKey(word)) {
                double newCount = this.termFrequencies.get(word) + 1.0;
                this.termFrequencies.put(word, newCount);
            }
            else {
                this.uniqueWords.add(word);
                this.termFrequencies.put(word, 1.0);
            }
        }
        for (String key: this.termFrequencies.keySet()) {
            double newVal = termFrequencies.get(key) / (double) num_terms;
            this.termFrequencies.put(key, newVal);
        }
    }

    public double term_frequency(String term) {
        /*
        Return the term frequency of a given word,
        disregarding casing and punctuation.
        If word is not in the document, return 0.
         */

        term = term.replaceAll("\\W+", "");
        term = term.toLowerCase();
        // System.out.println(this.termFrequencies.values());
        if (this.termFrequencies.containsKey(term)){
            return this.termFrequencies.get(term);
        }
        else {
            return 0;
        }
    }

    public List<String> get_words() {
        /*
        Return all non duplicate
        words from the document.
        */
        return this.uniqueWords;
    }

    public String get_path() {
        /*
        Return path of a given file.
        */
        return this.path;
    }
}

# Search Engine

## Goal
Implement a document ranking search engine using a TF-IDF (term frequency-inverse document frequency) algorithm.

The TF-IDF algorithm consists of two main computations.

**Term Frequency (TF)**

The term frequency represents how often a search term appears in a certain document.

**Inverse Document Frequency (IDF)**

As filler words such as “the” or "I" are so common, TF often incorrectly weight documents that use these filler words often. The inverse document frequency is a measure of how often the word appears in the entire set of documents. Use this value minimizes the weight of terms that occur very frequently.

**Term frequency-inverse Document Frequency**
TF-IDF is a statistic that portrays how important a word is to a document. The statistic increases proportionally to the number of times a word appears in the document, but is offset by the number of times the word appears in the entire directory. This helps to control for the fact that some words, such as 'the' or 'and' are generally more common than others.

## Algorithm
1. Compute the TF-IDF score for each document in the given directory that contains the given query. 
2. Sort the documents in descending order of TF-IDF value.
3. Return the document names as a ranked list.

## Classes
- **search.java**: A Java file which will allows a user to run a new TF-IDF search engine at the command line.
- **Document.java**: Represents one document in the search engie, holding its path and words.
- **SearchEngine.java**: use the TF-IDF algorithm to rank the relevance of each document in a directory to a given term.

## How to use
- Enter a directory into the command line

![Search Directory](/img/search.PNG)

- Enter a single term 

![Search Directory](/img/query.PNG)

- A ranked list appears presenting the relevance of each document in the directory to the given query based on the TF-IDF calculation of the term.

- Enter a multi-query

![Search Directory](/img/queries.PNG)

- The search engine will compute the TF-IDF for each individual term in the query and then sum these terms to find the total TF-IDF. Instead of looking at a single query, the search engine looks at all the documents in the directory which contain at least one word in the multi-query. This means if the query is “I love dogs” the TF-IDF should be calculated for all documents in the directory that contain the word “a”, all documents that contain the word “love”, and all documents that contain the word “dogs”. Even if a document only contains the word “a”, it is still included in the ranking; however, it will just be lower because its TF-IDF score for “love” and “dog” will be 0.

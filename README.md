## Search Engine

# Goal
Implement a document ranking search engine using a TF-IDF (term frequency- inverse document frequency) algorithm.

The TF-IDF algorithm consists of two main computations.

**Term Frequency (TF)**

The term frequency represents how often a search term appears in a certain document.

**Inverse Document Frequency (IDF)**

As filler words such as “the” or "I" are so common, TF often incorrectly weight documents that use these filler words often. The inverse document frequency is a measure of how often the word appears in the entire set of documents. Use this value minimizes the weight of terms that occur very frequently.

# Classes
- search.java: A Java file which will allows a user to run a new TF-IDF search engine at the command line.
- Document.java: Represents one document in the search engie, holding its path and words.
- SearchEngine.java: use the TF-IDF algorithm to rank the relevance of each document in a directory to a given term.

# How to use
- Enter a directory into the command line
- Enter a term 
- A ranked list appears presenting the relevance of each document in the directory to that given term

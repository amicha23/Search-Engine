## Search Engine

Implement a document ranking search engine using a TF-IDF (term frequency- inverse document frequency) algorithm.

The TF-IDF algorithm consists of two main computations. The following information describes these computations at a high-level, and the exact equations will be included later in the spec.

# Term Frequency (TF)

The term frequency represents how often a search term appears in a certain document.

# Inverse Document Frequency (IDF)

As filler words such as “the” or "I" are so common, TF often incorrectly weight documents that use these filler words often. The inverse document frequency is a measure of how often the word appears in the entire set of documents. Use this value minimizes the weight of terms that occur very frequently.

package main.java.com.SOProjetoAV2.algorithms;

import main.java.com.SOProjetoAV2.PageReplacementAlgorithm;

import java.util.*;

public class LRUAlgorithm implements PageReplacementAlgorithm {

    // This method implements the LRU (Least Recently Used) page replacement algorithm.
    // It takes a sequence of page requests (pageSequence) and the number of available memory frames (frameCount).
    @Override
    public int execute(List<Integer> pageSequence, int frameCount) {
        // LinkedHashSet is used to store the pages currently in memory (frames) while maintaining insertion order.
        LinkedHashSet<Integer> frames = new LinkedHashSet<>();
        // Counter to track the number of page faults (when a page is not found in memory).
        int pageFaults = 0;

        // Loop through each page in the requested page sequence.
        for (int page : pageSequence) {
            // Check if the current page is not in memory (frames).
            if (!frames.contains(page)) {
                // If the number of pages in memory equals the frame capacity, remove the least recently used page.
                if (frames.size() == frameCount) {
                    // The first element in the LinkedHashSet is the least recently used page.
                    int leastUsed = frames.iterator().next();
                    // Remove the least recently used page from memory (frames).
                    frames.remove(leastUsed);
                }
                // Increment the page fault count because the page was not in memory and had to be loaded.
                pageFaults++;
            } else {
                // If the page is already in memory, remove it from its current position so it can be added again,
                // updating its usage order (making it the most recently used).
                frames.remove(page);
            }
            // Add the current page to the memory (frames), marking it as the most recently used.
            frames.add(page);
        }

        // Return the total number of page faults that occurred.
        return pageFaults;
    }

    // This method returns the name of the algorithm ("LRU").
    @Override
    public String getAlgorithmName() {
        return "LRU";
    }
}

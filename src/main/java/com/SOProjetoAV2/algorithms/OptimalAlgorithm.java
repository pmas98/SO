package main.java.com.SOProjetoAV2.algorithms;

import main.java.com.SOProjetoAV2.PageReplacementAlgorithm;

import java.util.*;

public class OptimalAlgorithm implements PageReplacementAlgorithm {

    // This method implements the Optimal page replacement algorithm.
    // It takes a sequence of page requests (pageSequence) and the number of available memory frames (frameCount).
    @Override
    public int execute(List<Integer> pageSequence, int frameCount) {
        // Set to store the current pages in memory (frames).
        Set<Integer> frames = new HashSet<>();
        // Counter to track the number of page faults (when a page is not found in memory).
        int pageFaults = 0;

        // Loop through each page in the requested page sequence.
        for (int i = 0; i < pageSequence.size(); i++) {
            int page = pageSequence.get(i); // Get the current page.

            // If the page is not already in memory (frames), a page fault occurs.
            if (!frames.contains(page)) {
                // If the frames are full, we need to find a page to remove.
                if (frames.size() == frameCount) {
                    int farthest = -1;         // Variable to track the farthest future use of any page.
                    int pageToRemove = -1;     // Variable to store the page to be removed.

                    // Loop through the current pages in memory to find the one that won't be used for the longest time.
                    for (int frame : frames) {
                        // Check when the current page (frame) will next appear in the remaining page sequence.
                        int nextIndex = pageSequence.subList(i + 1, pageSequence.size()).indexOf(frame);

                        // If the page will not be used again, mark it as the page to remove.
                        if (nextIndex == -1) {
                            pageToRemove = frame;
                            break;  // No need to check further, since this page won't be used again.
                        }

                        // If this page is used farther in the future than the current farthest page, update the farthest page.
                        if (nextIndex > farthest) {
                            farthest = nextIndex;
                            pageToRemove = frame;
                        }
                    }

                    // Remove the page that won't be used for the longest time (or never again).
                    frames.remove(pageToRemove);
                }

                // Add the new page to memory.
                frames.add(page);
                // Increment the page fault count as the new page had to be loaded into memory.
                pageFaults++;
            }
        }

        // Return the total number of page faults that occurred.
        return pageFaults;
    }

    // This method returns the name of the algorithm ("Optimal").
    @Override
    public String getAlgorithmName() {
        return "Optimal";
    }
}

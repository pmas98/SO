package main.java.com.SOProjetoAV2.algorithms;

import main.java.com.SOProjetoAV2.PageReplacementAlgorithm;
import java.util.*;

public class ClockAlgorithm implements PageReplacementAlgorithm {
    @Override
    public int execute(List<Integer> pageSequence, int frameCount) {
        // Array to store the current pages in memory (frames).
        int[] frameArray = new int[frameCount];
        // Array to track whether a page has a "second chance" or not.
        boolean[] secondChance = new boolean[frameCount];
        // Pointer that acts as the "clock hand" to determine the next frame to consider for replacement.
        int pointer = 0;
        // Counter to track the number of page faults (when a page is not found in memory).
        int pageFaults = 0;

        // Loop through each page in the requested page sequence.
        for (int page : pageSequence) {
            boolean found = false; // Flag to indicate if the page is already in memory.
            // Check if the page is already in one of the frames (i.e., memory).
            for (int i = 0; i < frameCount; i++) {
                if (frameArray[i] == page) {
                    // If the page is found, give it a "second chance".
                    secondChance[i] = true;
                    found = true;
                    break;
                }
            }
            // If the page was not found in memory (a page fault occurs).
            if (!found) {
                // Use the Clock algorithm to find a frame to replace.
                // The while loop ensures that we find a frame without a second chance.
                while (secondChance[pointer]) {
                    // If the current frame has a second chance, reset it and move the pointer forward.
                    secondChance[pointer] = false;
                    // Move the pointer forward in a circular manner (mod frameCount to wrap around).
                    pointer = (pointer + 1) % frameCount;
                }
                // Replace the page in the frame pointed to by the clock hand (pointer).
                frameArray[pointer] = page;
                secondChance[pointer] = false;
                // Move the pointer forward to the next frame for future page replacements.
                pointer = (pointer + 1) % frameCount;
                pageFaults++;
            }
        }

        return pageFaults;
    }

    @Override
    public String getAlgorithmName() {
        return "Clock";
    }
}

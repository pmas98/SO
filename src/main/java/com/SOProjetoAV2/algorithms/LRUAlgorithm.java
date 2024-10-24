package main.java.com.SOProjetoAV2.algorithms;

import main.java.com.SOProjetoAV2.SimuladorAlgorithm;

import java.util.*;

public class LRUAlgorithm implements SimuladorAlgorithm {

    // This method implements the LRU (Least Recently Used) page replacement algorithm.
    // It takes a sequence of page requests (pageSequence) and the number of available memory frames (frameCount).
    @Override
    public int execute(List<Integer> pageSequence, int frameCount) {
        LinkedHashSet<Integer> frames = new LinkedHashSet<>();
        int pageFaults = 0;

        for (int page : pageSequence) {
            if (!frames.contains(page)) {
                if (frames.size() == frameCount) {
                    int leastUsed = frames.iterator().next();
                    frames.remove(leastUsed);
                }
                pageFaults++;
            } else {
                frames.remove(page);
            }
            frames.add(page);
        }

        return pageFaults;
    }

    @Override
    public String getAlgorithmName() {
        return "LRU";
    }
}

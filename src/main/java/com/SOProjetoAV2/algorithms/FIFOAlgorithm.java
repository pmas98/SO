package main.java.com.SOProjetoAV2.algorithms;

import main.java.com.SOProjetoAV2.SimuladorAlgorithm;

import java.util.*;

public class FIFOAlgorithm implements SimuladorAlgorithm {
    @Override
    public int execute(List<Integer> pageSequence, int frameCount) {
        Queue<Integer> frames = new LinkedList<>();
        Set<Integer> pageSet = new HashSet<>();
        int pageFaults = 0;

        for (int page : pageSequence) {
            if (!pageSet.contains(page)) {
                if (frames.size() == frameCount) {
                    int removed = frames.poll();
                    pageSet.remove(removed);
                }
                frames.offer(page);
                pageSet.add(page);
                pageFaults++;
            }
        }

        return pageFaults;
    }

    @Override
    public String getAlgorithmName() {
        return "FIFO";
    }
}

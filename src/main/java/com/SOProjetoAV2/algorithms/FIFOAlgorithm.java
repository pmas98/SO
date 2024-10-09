package main.java.com.SOProjetoAV2.algorithms;

import main.java.com.SOProjetoAV2.PageReplacementAlgorithm;

import java.util.*;

public class FIFOAlgorithm implements PageReplacementAlgorithm {
    @Override
    public int execute(List<Integer> pageSequence, int frameCount) {
        Queue<Integer> frames = new LinkedList<>();
        Set<Integer> pageSet = new HashSet<>();
        int pageFaults = 0;

        for (int page : pageSequence) {
            // Se a pagina nao está na memoria
            if (!pageSet.contains(page)) {
                // Se a memória está cheia
                if (frames.size() == frameCount) {
                    // Remova a entrada mais recente
                    int removed = frames.poll();
                    // Remova essa entrada do hash map
                    pageSet.remove(removed);
                }
                // Add the new page to the queue (end of the queue)
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

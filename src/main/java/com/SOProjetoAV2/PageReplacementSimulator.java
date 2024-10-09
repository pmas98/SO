package main.java.com.SOProjetoAV2;

import java.util.*;

public class PageReplacementSimulator {
    private List<Integer> pageSequence;
    private int frameCount;

    public PageReplacementSimulator(List<Integer> pageSequence, int frameCount) {
        this.pageSequence = pageSequence;
        this.frameCount = frameCount;
    }

    public void simulate(PageReplacementAlgorithm algorithm) {
        int pageFaults = algorithm.execute(pageSequence, frameCount);
        System.out.println(algorithm.getAlgorithmName() + " - " + pageFaults + " page faults");
    }

    public static void main(String[] args) {
        List<Integer> pageSequence = Arrays.asList(7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 3, 0, 1, 7, 0, 1, 4, 2, 1, 5);
        int frameCount = 4;

        PageReplacementSimulator simulator = new PageReplacementSimulator(pageSequence, frameCount);
        simulator.simulate(PageReplacementFactory.getAlgorithm(PageReplacementType.FIFO));
        simulator.simulate(PageReplacementFactory.getAlgorithm(PageReplacementType.LRU));
        simulator.simulate(PageReplacementFactory.getAlgorithm(PageReplacementType.OPTIMAL));
        simulator.simulate(PageReplacementFactory.getAlgorithm(PageReplacementType.CLOCK));
    }
}

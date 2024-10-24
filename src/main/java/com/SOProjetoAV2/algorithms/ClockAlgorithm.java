package main.java.com.SOProjetoAV2.algorithms;

import main.java.com.SOProjetoAV2.SimuladorAlgorithm;
import java.util.*;

public class ClockAlgorithm implements SimuladorAlgorithm {
    @Override
    public int execute(List<Integer> pageSequence, int frameCount) {
        int[] frameArray = new int[frameCount];
        boolean[] secondChance = new boolean[frameCount];
        int pointer = 0;
        int pageFaults = 0;

        for (int page : pageSequence) {
            boolean found = false;
            for (int i = 0; i < frameCount; i++) {
                if (frameArray[i] == page) {
                    secondChance[i] = true;
                    found = true;
                    break;
                }
            }
            if (!found) {
                while (secondChance[pointer]) {
                    secondChance[pointer] = false;
                    pointer = (pointer + 1) % frameCount;
                }
                frameArray[pointer] = page;
                secondChance[pointer] = false;
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

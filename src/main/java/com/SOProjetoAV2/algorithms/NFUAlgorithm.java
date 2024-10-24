package main.java.com.SOProjetoAV2.algorithms;

import main.java.com.SOProjetoAV2.SimuladorAlgorithm;

import java.util.*;

public class NFUAlgorithm implements SimuladorAlgorithm {
    @Override
    public int execute(List<Integer> pageSequence, int frameCount) {
        Map<Integer, Integer> pageFrequency = new HashMap<>();
        Set<Integer> pageSet = new HashSet<>();
        List<Integer> frames = new ArrayList<>();
        int pageFaults = 0;

        for (int page : pageSequence) {
            if (!pageSet.contains(page)) {
                if (frames.size() == frameCount) {
                    int lfuPage = findLFUPage(pageFrequency, frames);
                    frames.remove(Integer.valueOf(lfuPage));
                    pageSet.remove(lfuPage);
                }
                frames.add(page);
                pageSet.add(page);
                pageFaults++;
            }
            pageFrequency.put(page, pageFrequency.getOrDefault(page, 0) + 1);
        }

        return pageFaults;
    }

    private int findLFUPage(Map<Integer, Integer> pageFrequency, List<Integer> frames) {
        int lfuPage = frames.get(0);
        int minFreq = pageFrequency.get(lfuPage);

        for (int page : frames) {
            int freq = pageFrequency.get(page);
            if (freq < minFreq) {
                minFreq = freq;
                lfuPage = page;
            }
        }

        return lfuPage;
    }

    @Override
    public String getAlgorithmName() {
        return "NFU";
    }
}

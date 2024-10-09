package main.java.com.SOProjetoAV2;

import java.util.List;

public interface PageReplacementAlgorithm {
    int execute(List<Integer> pageSequence, int frameCount);
    String getAlgorithmName();
}

package main.java.com.SOProjetoAV2;

import java.util.List;

public interface SimuladorAlgorithm {
    int execute(List<Integer> pageSequence, int frameCount);
    String getAlgorithmName();
}

package main.java.com.SOProjetoAV2;

import main.java.com.SOProjetoAV2.algorithms.*;

public class PageReplacementFactory {
    public static PageReplacementAlgorithm getAlgorithm(PageReplacementType type) {
        switch (type) {
            case FIFO:
                return new FIFOAlgorithm();
            case LRU:
                return new LRUAlgorithm();
            case OPTIMAL:
                return new OptimalAlgorithm();
            case CLOCK:
                return new ClockAlgorithm();
            default:
                throw new IllegalArgumentException("Tipo de algoritmo desconhecido");
        }
    }
}

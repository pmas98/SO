package main.java.com.SOProjetoAV2;

import java.util.*;

public class Simulador {
    private List<Integer> pageSequence;
    private int frameCount;

    public Simulador(List<Integer> pageSequence, int frameCount) {
        this.pageSequence = pageSequence;
        this.frameCount = frameCount;
    }

    public void simulate(SimuladorAlgorithm algorithm) {
        int pageFaults = algorithm.execute(pageSequence, frameCount);
        System.out.println(algorithm.getAlgorithmName() + " - " + pageFaults + " page faults");
    }

    public static void main(String[] args) {
        int sequenceSize = 300;
        int maxPageNumber = 20;

        Random random = new Random();
        List<Integer> pageSequence = new ArrayList<>();

//        CASO 1
//        int[] favoritePages = new int[5];
//        for(int i = 0; i < favoritePages.length; i++) {
//            favoritePages[i] = random.nextInt(maxPageNumber);
//        }

//        for(int i = 0; i < sequenceSize; i++) {
//            if(random.nextDouble() < 0.7) { // 70% chance de usar página favorita
//                pageSequence.add(favoritePages[random.nextInt(favoritePages.length)]);
//            } else { // 30% chance de página aleatória
//                pageSequence.add(random.nextInt(maxPageNumber));
//            }
//        }

//         Inserindo Manualmente
//        pageSequence = Arrays.asList(7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 3, 0, 1, 7, 0, 1, 4, 2, 1, 5);

        // CASO 2
        for (int i = 0; i < sequenceSize; i++) {
            pageSequence.add(random.nextInt(maxPageNumber));
        }

        int frameCount = 10;

        Simulador simulation = new Simulador(pageSequence, frameCount);
        simulation.simulate(SimuladorFactory.getAlgorithm(SimuladorType.FIFO));
        simulation.simulate(SimuladorFactory.getAlgorithm(SimuladorType.LRU));
        simulation.simulate(SimuladorFactory.getAlgorithm(SimuladorType.NFU));
        simulation.simulate(SimuladorFactory.getAlgorithm(SimuladorType.CLOCK));
    }
}

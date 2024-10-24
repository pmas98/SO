package main.java.com.SOProjetoAV2.gui;

import main.java.com.SOProjetoAV2.SimuladorFactory;
import main.java.com.SOProjetoAV2.SimuladorType;
import main.java.com.SOProjetoAV2.Simulador;
import main.java.com.SOProjetoAV2.SimuladorAlgorithm;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class PageReplacementGUI extends JFrame {

    private JTextField frameCountField;
    private JTextField pageSequenceField;
    private JButton simulateButton;
    private JPanel chartPanel;

    public PageReplacementGUI() {
        setTitle("Page Replacement Simulator");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Control panel
        JPanel controlPanel = new JPanel(new GridBagLayout());
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Frame count field
        controlPanel.add(new JLabel("Quantidade de frames:"), gbc);
        gbc.gridx++;
        frameCountField = new JTextField(10);
        frameCountField.setText("3");
        controlPanel.add(frameCountField, gbc);

        // Page sequence field
        gbc.gridx = 0;
        gbc.gridy++;
        controlPanel.add(new JLabel("Sequência de páginas (divididas por vírgula):"), gbc);
        gbc.gridx++;
        pageSequenceField = new JTextField(30);
        pageSequenceField.setText("7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0,1,7,0,1");
        controlPanel.add(pageSequenceField, gbc);

        // Simulate button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        simulateButton = new JButton("Simular algoritmos");
        simulateButton.setBackground(new Color(66, 134, 244));
        simulateButton.setForeground(Color.WHITE);
        simulateButton.setFont(new Font("Arial", Font.BOLD, 14));
        controlPanel.add(simulateButton, gbc);

        add(controlPanel, BorderLayout.NORTH);

        // Chart panel
        chartPanel = new JPanel(new BorderLayout());
        chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(chartPanel, BorderLayout.CENTER);

        // Button listener
        simulateButton.addActionListener(e -> runSimulation());
    }

    private void runSimulation() {
        try {
            int frameCount = Integer.parseInt(frameCountField.getText());
            List<Integer> pageSequence = parsePageSequence(pageSequenceField.getText());

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            Simulador simulator = new Simulador(pageSequence, frameCount);

            for (SimuladorType algorithmType : SimuladorType.values()) {
                SimuladorAlgorithm algorithm = SimuladorFactory.getAlgorithm(algorithmType);
                int pageFaults = algorithm.execute(pageSequence, frameCount);
                dataset.addValue(pageFaults, "Page Faults", algorithmType.toString());
            }

            updateChart(dataset);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check your frame count and page sequence.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private List<Integer> parsePageSequence(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }

    private void updateChart(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Comparação entre diferentes algoritmos",
                "Algoritmo",
                "Page Faults",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        chart.setBackgroundPaint(Color.white);
        chart.getTitle().setPaint(Color.darkGray);
        chart.getTitle().setFont(new Font("Arial", Font.BOLD, 18));

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 500));

        this.chartPanel.removeAll();
        this.chartPanel.add(chartPanel, BorderLayout.CENTER);
        this.chartPanel.revalidate();
        this.chartPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PageReplacementGUI gui = new PageReplacementGUI();
            gui.setVisible(true);
        });
    }
}
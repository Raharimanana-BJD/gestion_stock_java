package com.stock.gestion_stock.service;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class StockSwingClient extends JFrame {
    private JTable tableStock;
    private DefaultTableModel tableModel;
    private JTextField txtSearch;
    private final HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build();
    private final String BASE_URL = "http://localhost:8080/api";

    public StockSwingClient() {
        setTitle("Gestion de Stock Client - Swing");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout Principal
        setLayout(new BorderLayout());

        // Top Panel (Recherche & Actions)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtSearch = new JTextField(20);
        JButton btnSearch = new JButton("Rechercher");
        JButton btnRefresh = new JButton("Rafraîchir l'état de stock");

        topPanel.add(new JLabel("Recherche: "));
        topPanel.add(txtSearch);
        topPanel.add(btnSearch);
        topPanel.add(btnRefresh);
        add(topPanel, BorderLayout.NORTH);

        // Table / JTable (ETAT DE STOCK)
        String[] columnNames = { "DÉSIGNATION", "STOCK CURRENT" };
        tableModel = new DefaultTableModel(columnNames, 0);
        tableStock = new JTable(tableModel);
        add(new JScrollPane(tableStock), BorderLayout.CENTER);

        // Listeners
        btnRefresh.addActionListener(e -> chargerEtatStock("/produits"));
        btnSearch.addActionListener(e -> chargerEtatStock("/produits/recherche?q=" + txtSearch.getText().trim()));

        // Initial Load
        chargerEtatStock("/produits");
    }

    private void chargerEtatStock(String endpoint) {
        tableModel.setRowCount(0); // Clear table
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + endpoint))
                    .GET()
                    .build();

            httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(this::parserEtRemplirTable)
                    .exceptionally(ex -> {
                        SwingUtilities.invokeLater(
                                () -> JOptionPane.showMessageDialog(this, "Erreur serveur : " + ex.getMessage()));
                        return null;
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parserEtRemplirTable(String jsonResponseBody) {
        // Parsing manuel basique du JSON pour éviter d'ajouter Jackson Core côté client
        // lourd
        SwingUtilities.invokeLater(() -> {
            try {
                // Nettoyage de tableau JSON rudimentaire pour l'affichage démo JTable
                String clean = jsonResponseBody.replace("[", "").replace("]", "").replace("},{", "}|{");
                if (clean.trim().isEmpty())
                    return;

                String[] items = clean.split("\\|");
                for (String item : items) {
                    String design = extraireValeurJson(item, "design");
                    String stock = extraireValeurJson(item, "stock");
                    tableModel.addRow(new Object[] { design, stock });
                }
            } catch (Exception e) {
                System.out.println("Erreur de rendu JTable");
            }
        });
    }

    private String extraireValeurJson(String json, String key) {
        try {
            int keyIndex = json.indexOf("\"" + key + "\"");
            int colonIndex = json.indexOf(":", keyIndex);
            int commaIndex = json.indexOf(",", colonIndex);
            if (commaIndex == -1)
                commaIndex = json.indexOf("}", colonIndex);

            String val = json.substring(colonIndex + 1, commaIndex).trim();
            return val.replace("\"", "");
        } catch (Exception e) {
            return "N/A";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StockSwingClient().setVisible(true);
        });
    }
}
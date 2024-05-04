package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class InterfazUsuario extends JFrame {
    private JTextField experimentoIdField;
    private JTextField experimentoMedioField;
    private JTextField poblacionNombreField;
    private JTextField poblacionNumInicialField;
    private JTextField poblacionNumActualField;
    private JTextField poblacionTemperaturaField;

    public InterfazUsuario() {
        setTitle("Experimento y Poblacion Bacteria");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        add(new JLabel("Experimento ID:"));
        experimentoIdField = new JTextField();
        add(experimentoIdField);

        add(new JLabel("Experimento Medio:"));
        experimentoMedioField = new JTextField();
        add(experimentoMedioField);

        add(new JLabel("Poblacion Nombre:"));
        poblacionNombreField = new JTextField();
        add(poblacionNombreField);

        add(new JLabel("Poblacion Num Inicial:"));
        poblacionNumInicialField = new JTextField();
        add(poblacionNumInicialField);

        add(new JLabel("Poblacion Temperatura:"));
        poblacionTemperaturaField = new JTextField();
        add(poblacionTemperaturaField);

        JButton button = new JButton("Crear Experimento y Poblacion Bacteria");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int experimentoId = Integer.parseInt(experimentoIdField.getText());
                String experimentoMedio = experimentoMedioField.getText();
                String poblacionNombre = poblacionNombreField.getText();
                int poblacionNumInicial = Integer.parseInt(poblacionNumInicialField.getText());
                int poblacionTemperatura = Integer.parseInt(poblacionTemperaturaField.getText());

                Experimento experimento = new Experimento(experimentoId, experimentoMedio);
                PoblacionBacteria poblacionBacteria = new PoblacionBacteria(poblacionNombre, experimento, null, null, poblacionNumInicial, poblacionNumInicial, poblacionTemperatura, null);
                experimento.addPoblacBacteria(poblacionBacteria);

                try {
                    // Create a new file for each experiment based on the experimentoId
                    FileWriter writer = new FileWriter("data/experimento_" + experimentoId + "_data.txt", true);
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);
                    bufferedWriter.write("Experimento ID: " + experimentoId + ", Experimento Medio: " + experimentoMedio);
                    bufferedWriter.newLine();
                    bufferedWriter.write("Poblacion Nombre: " + poblacionNombre + ", Poblacion Num Inicial: " + poblacionNumInicial + ", Poblacion Num Actual: " + poblacionNumInicial + ", Poblacion Temperatura: " + poblacionTemperatura);
                    bufferedWriter.newLine();
                    bufferedWriter.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "Experimento y Poblacion Bacteria creados y guardados exitosamente!");
            }
        });
        add(button);

        JButton seleccionarArchivo = new JButton("Seleccionar archivo");
        seleccionarArchivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String path = selectedFile.getAbsolutePath();
                    JOptionPane.showMessageDialog(null, "Archivo seleccionado: " + path);

                    // Create a new JFrame to display the file content
                    JFrame frame = new JFrame("File Content");
                    frame.setSize(500, 400);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame when the close button is clicked

                    // Create a JTextArea to display the file content
                    JTextArea textArea = new JTextArea();
                    textArea.setEditable(false); // Make the text area non-editable
                    JScrollPane scrollPane = new JScrollPane(textArea); // Add a scroll pane to the text area
                    frame.add(scrollPane);

                    // Read the file content and display it in the text area
                    try {
                        Scanner scanner = new Scanner(selectedFile);
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            textArea.append(line + "\n");
                        }
                        scanner.close();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }

                    // Show the frame
                    frame.setVisible(true);
                }
            }
        });
        add(seleccionarArchivo);
    }

    public static void main(String[] args) {
        InterfazUsuario interfazUsuario = new InterfazUsuario();
        interfazUsuario.setVisible(true);
    }
}
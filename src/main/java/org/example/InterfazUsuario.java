package org.example;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    private JTextField poblacionTemperaturaField;
    private JDateChooser fechaInicioPicker;
    private JDateChooser fechaFinalPicker;
    private JTextField bacteriaComidaInicialField;
    private JComboBox<String> luminosidadComboBox;

    public InterfazUsuario() {
        setTitle("Experimento y Poblacion Bacteria");
        setSize(1800, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(10, 3));

        add(new JLabel("Experimento ID:"));
        experimentoIdField = new JTextField();
        add(experimentoIdField);

        add(new JLabel("Poblacion Nombre:"));
        poblacionNombreField = new JTextField();
        add(poblacionNombreField);

        add(new JLabel("Poblacion Num Inicial:"));
        poblacionNumInicialField = new JTextField();
        add(poblacionNumInicialField);

        add(new JLabel("Poblacion Temperatura:"));
        poblacionTemperaturaField = new JTextField();
        add(poblacionTemperaturaField);

        add(new JLabel("Luminosidad:"));
        String[] luminosidadOptions = {"Baja", "Media", "Alta"};
        luminosidadComboBox = new JComboBox<>(luminosidadOptions);
        luminosidadComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedLuminosidad = (String) luminosidadComboBox.getSelectedItem();
                System.out.println("Luminosidad: " + selectedLuminosidad);
            }
        });
        add(luminosidadComboBox);

        add(new JLabel("Comida inicial:"));
        bacteriaComidaInicialField = new JTextField();
        add(bacteriaComidaInicialField);

        add(new JLabel("Fecha Inicio:"));
        fechaInicioPicker = new JDateChooser();
        add(fechaInicioPicker);

        add(new JLabel("Fecha Final:"));
        fechaFinalPicker = new JDateChooser();
        add(fechaFinalPicker);



        JButton button = new JButton("Guardar con el ID del experimento");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int experimentoId = Integer.parseInt(experimentoIdField.getText());
                String poblacionNombre = poblacionNombreField.getText();
                int poblacionNumInicial = Integer.parseInt(poblacionNumInicialField.getText());
                int poblacionTemperatura = Integer.parseInt(poblacionTemperaturaField.getText());
                int bacteriaComidaInicial = Integer.parseInt(bacteriaComidaInicialField.getText());
                String luminosidad = (String) luminosidadComboBox.getSelectedItem();
                Date fechaInicio = fechaInicioPicker.getDate();
                Date fechaFinal = fechaFinalPicker.getDate();


                Experimento experimento = new Experimento(experimentoId);
                PoblacionBacteria poblacionBacteria = new PoblacionBacteria(poblacionNombre, experimento, fechaInicio, fechaFinal, poblacionNumInicial, poblacionNumInicial, poblacionTemperatura, luminosidad);
                experimento.addPoblacBacteria(poblacionBacteria);

                try {
                    // Create a new file for each experiment based on the experimentoId
                    FileWriter writer = new FileWriter("data/experimento_" + experimentoId + "_data.txt", true);
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);
                    bufferedWriter.write("Experimento ID: " + experimentoId);
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

        JButton saveButton = new JButton("Guardar como");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file to save");

                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    try {
                        FileWriter writer = new FileWriter(fileToSave);
                        BufferedWriter bufferedWriter = new BufferedWriter(writer);
                        bufferedWriter.write("Experimento ID: " + experimentoIdField.getText());
                        bufferedWriter.newLine();
                        bufferedWriter.write("Poblacion Nombre: " + poblacionNombreField.getText() + ", Poblacion Num Inicial: " + poblacionNumInicialField.getText() + ", Poblacion Num Actual: " + poblacionNumInicialField.getText() + ", Poblacion Temperatura: " + poblacionTemperaturaField.getText());
                        bufferedWriter.newLine();
                        bufferedWriter.close();
                        JOptionPane.showMessageDialog(null, "File saved successfully!");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        add(saveButton);
    }

    public static void main(String[] args) {
        InterfazUsuario interfazUsuario = new InterfazUsuario();
        interfazUsuario.setVisible(true);
    }
}
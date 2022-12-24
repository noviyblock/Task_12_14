package ru.vsu.cs.new_block.task_12_14.swing_app;

import ru.vsu.cs.new_block.task_12_14.main_logic.ArrayUtils;
import ru.vsu.cs.new_block.task_12_14.main_logic.MainLogicClass;
import ru.vsu.cs.new_block.task_12_14.main_logic.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class FrameMain extends JFrame {
    private JTextArea textArea;
    private JTextField textFieldInputData;
    private JButton buttonInput;
    private JButton buttonUpdate;
    private JPanel panelMain;
    private JTextField textFieldInputK;
    private JButton buttonOutput;

    public FrameMain() throws IOException {

        this.setTitle("Main program");
        this.setPreferredSize(new Dimension(500,500));
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();

        JFileChooser fileChooserOpen = new JFileChooser();
        JFileChooser fileChooserSave = new JFileChooser();


        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        JFileChooser finalFileChooserOpen = fileChooserOpen;
        buttonInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (finalFileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[] data = ArrayUtils.readIntArrayFromFile(finalFileChooserOpen.getSelectedFile().getPath());
                        textFieldInputData.setText(ArrayUtils.toString(data));
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    //подготовка данных
                    String answer;
                    int k = Integer.parseInt(textFieldInputK.getText());
                    int[] data = MainLogicClass.convertStrToIntArr(textFieldInputData.getText());
                    if(data==null){
                        answer = "нет данных";
                    } else if (k>0 && k<=data.length) {
                        MainLogicClass.enumOfAllCombsOfArr(data,k);
                    }else{
                        answer = "неверное K";
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        JFileChooser finalFileChooserSave = fileChooserSave;
        buttonOutput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (finalFileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
//                        String path = finalFileChooserSave.getSelectedFile().getPath();
//                        String answer = textAreaOutput.getText();
//                        MainLogicClass.writeUpdateDataInFile(answer,path);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
    }
}

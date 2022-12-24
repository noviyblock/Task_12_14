package ru.vsu.cs.new_block.task_12_14.swing_app;

import java.io.IOException;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws Exception{
        Locale.setDefault(Locale.ROOT);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrameMain().setVisible(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }}
package com.company;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.Math.*;
import java.util.Random;


abstract public class SimulationScreen {
    static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
    static MyJFrame f;

    public void Main() {
        f.setFocusable(true);
    }
    static JComboBox generationType;
    static JComboBox type;
    static JComboBox iterations;
    static JComboBox set;

    public static void main(String[] a) {
        MyJFrame f = new MyJFrame();
        f.setTitle("Simulation");
        if (Main.isFullScreen) {
            device.setFullScreenWindow(f);
        } else {
            f.setBounds(100, 50, 640, 480);
        }

        //p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));

        //p.add(Box.createRigidArea(new Dimension(0,5)));
        String s1[] = {"Escape-Time Functions", "Iterated Function Systems", "Random Fractals"};
        generationType = new JComboBox<>(s1);
        generationType.addItemListener(f);
generationType.setSize(200, 100);


        p.add(generationType);
p.setBounds(480, 0, 200, 480);

        f.add(p);
        f.addKeyListener(f);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    public void init() {
        JPanel p = new JPanel(new GridLayout());
        p.setLayout(new GridLayout(1,2));
        p.setSize(480,480);
    }

    static class MyJFrame extends JFrame implements KeyListener, ItemListener {





        public static void main(String[] args) {

            //f.setLayout(new FlowLayout());




            //f.addActionListener(f);
        }

        public void keyTyped(KeyEvent ke) {
            if (ke.getKeyCode() == KeyEvent.VK_ESCAPE)
                f.dispose();
        }
        public void keyPressed(KeyEvent ke) {}
        public void keyReleased(KeyEvent ke) {
            if (ke.getKeyCode() == KeyEvent.VK_ESCAPE)
                f.dispose();
        }


        /*
        ActionListener generationType = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                String generationType = (String) cb.getSelectedItem();
                //updateMessageLabel(generationType);
            }
        };
        */

        public void paint(Graphics g) {



            double xc = -.5;
            double yc = 0;
            double size = 2;

            int n;   // create n-by-n image
            int m;
            if (Main.isFullScreen) {
                n = 1080;
                m = 1080;

            } else {
                n = 380;
                m = 380;
            }
            int max;   // maximum number of iterations
            //THIS DISPLAYS MANDELBROT FROM START TO FINISH(??)
for(max = 0; max <= 500; max++) {
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            double x0 = xc - size / 2 + size * i / n;
            double y0 = yc - size / 2 + size * j / n;
            ComplexNumber z0 = new ComplexNumber(x0, y0);
            int gray = (max - mand(z0, max))%255;
            Random r = new Random();
            Color c = new Color(gray, gray, gray);
            g.setColor(c);
            g.drawRect(i, n - 1 - j, 1, 1);

                    /*
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                     */
        }
    }
}
//THIS DISPLAYS MANDELBROT IN REVERSE
            for(max = 500; max > 0; max--) {
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        double x0 = xc - size / 2 + size * i / n;
                        double y0 = yc - size / 2 + size * j / n;
                        ComplexNumber z0 = new ComplexNumber(x0, y0);
                        int gray = (max - mand(z0, max))%255;
                        Random r = new Random();
                        Color c = new Color(gray, gray, gray);
                        g.setColor(c);
                        g.drawRect(i, n - 1 - j, 1, 1);

                    /*
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                     */
                    }
                }
            }
        }


        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getSource() == generationType) {
                generationType.getSelectedItem();
            }
            /*
            else if (e.getSource() == type) {

            }
            else if (e.getSource() == iterations) {

            }
            else if (e.getSource() == set) {

            }

             */
        }
    }

    public static int mand(ComplexNumber z0, int max) {
        ComplexNumber z = new ComplexNumber(z0.mod(), 0);
        for (int t = 0; t < max; t++) {
            if (z.mod() > 2.0) return t;
            z.multiply(z, z);
            z.add(z0);
        }
        return max;
    }
}



package com.company;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import java.awt.event.*;
import java.awt.*;
import java.awt.datatransfer.*;
import static javax.swing.GroupLayout.Alignment.*;


public class Calculator extends JFrame
{
    private JMenuBar menuBar;
    private JMenu view;
    private JMenu edit;
    private JMenu help;
    private JMenuItem show;
    private JMenuItem hide;
    private JMenuItem copy;
    private JMenuItem view2;
    private JMenuItem about;

    private JTextArea display;
    private JTextArea display2;

    private JButton cero;
    private JButton uno;
    private JButton dos;
    private JButton tres;
    private JButton cuatro;
    private JButton cinco;
    private JButton seis;
    private JButton siete;
    private JButton ocho;
    private JButton nueve;
    private JButton equals;
    private JButton plus;
    private JButton minus;
    private JButton times;
    private JButton divide;
    private JButton back, sea, see, neg, sqrt, deci;
    private JButton a, b, c, d, e, f, oneX, mod, percent;

    private Document doc;

    private JRadioButton hex, dec, oct, bin;
    private JRadioButton qword, dword, word, bite;

    private Label space = new Label("                ");

    private ButtonGroup b1;
    private ButtonGroup b2;

    private double opNum = 0;
    private boolean[] operator = new boolean[5];

    public static void main(String[] args)
    {
        new Calculator();
    }

    public Calculator()
    {
        super("Calculator");
        setupMenuBar();
        setupDisplay();
        setupRadioButtons();
        setupButtons();
        setupUI(this);
    }

    private void setupMenuBar()
    {
        menuBar = new JMenuBar();
        view = new JMenu(" View ");
        edit = new JMenu(" Edit ");
        help = new JMenu(" Help ");
        show = new JMenuItem(" Show ");
        hide = new JMenuItem(" Hide ");
        copy = new JMenuItem(" Copy ");
        view2 = new JMenuItem(" View Help ");
        about = new JMenuItem(" About Calculator ");
        setJMenuBar(menuBar);
        menuBar.add(view);
        menuBar.add(edit);
        menuBar.add(help);

        copy.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent a)
            {
                String displayedNum = display.getText();
                StringSelection strang = new StringSelection(displayedNum);
                Clipboard sys = Toolkit.getDefaultToolkit().getSystemClipboard();
                sys.setContents(strang, strang);
            }
        });

        view2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent a)
            {
                JOptionPane.showMessageDialog(null, "There is no help here except this link:\nhttps://support.microsoft.com/en-us/products/windows\nIt has been copied to your clipboard.", "Calculator", JOptionPane.OK_OPTION);
                StringSelection strang = new StringSelection("https://support.microsoft.com/en-us/products/windows");
                Clipboard sys = Toolkit.getDefaultToolkit().getSystemClipboard();
                sys.setContents(strang, strang);
            }
        });

        about.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent a)
            {
                JOptionPane.showMessageDialog(null, "Calculator programmed by Chase Kent", "Calculator", JOptionPane.OK_OPTION);
            }
        });

        view.add(show);
        view.add(hide);
        edit.add(copy);
        help.add(view2);
        help.add(about);

    }

    private void setupDisplay()
    {
        display = new JTextArea("0");
        display.setBounds(13, 12, 385, 50);
        display.setEditable(false);
        display.setFont(new Font("Consolas", Font.PLAIN, 44));
        add(display, BorderLayout.NORTH);

        display2 = new JTextArea(" 0000   0000   0000   0000   0000   0000   0000   0000\n 63                          47                     32\n 0000   0000   0000   0000   0000   0000   0000   0000\n 31                          15                      0");
        display2.setBounds(13, 67, 385, 57);
        display2.setEditable(false);
        display2.setFont(new Font("Consolas", Font.PLAIN, 12));
        add(display2, BorderLayout.NORTH);
    }

    private void setupRadioButtons()
    {
        b1 = new ButtonGroup();

        hex = new JRadioButton("Hex");
        dec = new JRadioButton("Dec");
        oct = new JRadioButton("Oct");
        bin = new JRadioButton("Bin");
        b1.add(hex);
        b1.add(dec);
        b1.add(oct);
        b1.add(bin);

        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(hex);
        radioPanel.add(dec);
        radioPanel.add(oct);
        radioPanel.add(bin);
        radioPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        b2 = new ButtonGroup();

        qword = new JRadioButton("Qword");
        dword = new JRadioButton("Dword");
        word = new JRadioButton("Word");
        bite = new JRadioButton("Byte");
        b2.add(qword);
        b2.add(dword);
        b2.add(word);
        b2.add(bite);

        JPanel radioPanel2 = new JPanel(new GridLayout(0, 1));
        radioPanel2.add(qword);
        radioPanel2.add(dword);
        radioPanel2.add(word);
        radioPanel2.add(bite);
        radioPanel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        dec.setSelected(true);
        qword.setSelected(true);


        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(radioPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(radioPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(127)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(radioPanel))
                .addComponent(radioPanel2));
        pack();

        word.setEnabled(false);
        bite.setEnabled(false);
    }

    private void setupButtons()
    {
        cero = new JButton("0");
        uno = new JButton("1");
        dos = new JButton("2");
        tres = new JButton("3");
        cuatro = new JButton("4");
        cinco = new JButton("5");
        seis = new JButton("6");
        siete = new JButton("7");
        ocho = new JButton("8");
        nueve = new JButton("9");
        neg = new JButton("\u00B1");
        deci = new JButton(".");

        deci.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(display.getText().contains("."))
                    return;
                display.append(".");
            }
        });

        neg.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(display.getText().equalsIgnoreCase("0"))
                    return;
                display.setText(Double.toString(Double.parseDouble(display.getText()) * (-1)));
                if(display.getText().endsWith(".0"))
                    display.setText(display.getText().replace(".0", ""));
                if(display.getText().endsWith("-"))
                {
                    display.setText(display.getText().replace("-", ""));
                    display.insert("-", 0);
                }

            }
        });

        cero.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(display.getText().length() > 15)
                    return;
                display.append("0");
            }
        });

        uno.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(display.getText().length() > 15)
                    return;
                if(display.getText().equalsIgnoreCase("0"))
                {
                    display.setText("1");
                    return;
                }
                display.append("1");
            }
        });

        dos.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(display.getText().length() > 15)
                    return;
                if(display.getText().equalsIgnoreCase("0"))
                {
                    display.setText("2");
                    return;
                }
                display.append("2");
            }
        });

        tres.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(display.getText().length() > 15)
                    return;
                if(display.getText().equalsIgnoreCase("0"))
                {
                    display.setText("3");
                    return;
                }
                display.append("3");
            }
        });

        cuatro.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(display.getText().length() > 15)
                    return;
                if(display.getText().equalsIgnoreCase("0"))
                {
                    display.setText("4");
                    return;
                }
                display.append("4");
            }
        });

        cinco.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(display.getText().length() > 15)
                    return;
                if(display.getText().equalsIgnoreCase("0"))
                {
                    display.setText("5");
                    return;
                }
                display.append("5");
            }
        });

        seis.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(display.getText().length() > 15)
                    return;
                if(display.getText().equalsIgnoreCase("0"))
                {
                    display.setText("6");
                    return;
                }
                display.append("6");
            }
        });

        siete.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(display.getText().length() > 15)
                    return;
                if(display.getText().equalsIgnoreCase("0"))
                {
                    display.setText("7");
                    return;
                }
                display.append("7");
            }
        });

        ocho.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(display.getText().length() > 15)
                    return;
                if(display.getText().equalsIgnoreCase("0"))
                {
                    display.setText("8");
                    return;
                }
                display.append("8");
            }
        });

        nueve.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(display.getText().length() > 15)
                    return;
                if(display.getText().equalsIgnoreCase("0"))
                {
                    display.setText("9");
                    return;
                }
                display.append("9");
            }
        });

        equals = new JButton("=");
        plus = new JButton("+");
        minus = new JButton("-");
        times = new JButton("*");
        divide = new JButton("/");
        back = new JButton("\u2190");
        sea = new JButton("C");
        see = new JButton("CE");
        sqrt = new JButton("\u221A");
        percent = new JButton("%");
        mod = new JButton("Mod");
        a = new JButton("A");
        b = new JButton("B");
        c = new JButton("C");
        d = new JButton("D");
        e = new JButton("E");
        f = new JButton("F");
        oneX = new JButton("1/x");
        percent.setEnabled(false);
        oneX.setEnabled(false);
        sqrt.setEnabled(false);
        a.setEnabled(false);
        b.setEnabled(false);
        c.setEnabled(false);
        d.setEnabled(false);
        e.setEnabled(false);
        f.setEnabled(false);
        deci.setEnabled(false);

        back.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(display.getText().equalsIgnoreCase("0"))
                {
                    return;
                }

                if(display.getText().equalsIgnoreCase(""))
                {
                    display.setText("0");
                    return;
                }

                doc = display.getDocument();
                try {
                    doc.remove(doc.getLength() - 1, 1);
                    if(display.getText().equalsIgnoreCase(""))
                    {
                        display.setText("0");
                        return;
                    }
                } catch (BadLocationException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();


                }
            }
        });

        sea.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                display.setText("0");
                setOpNum(0);
            }
        });

        see.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                display.setText("0");
            }
        });

        plus.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                setOpNum(Double.parseDouble(display.getText()));
                display.setText("");
                operator[0] = true;
            }
        });

        minus.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                setOpNum(Double.parseDouble(display.getText()));
                display.setText("");
                operator[1] = true;
            }
        });

        times.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                setOpNum(Double.parseDouble(display.getText()));
                display.setText("");
                operator[2] = true;
            }
        });

        divide.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                setOpNum(Double.parseDouble(display.getText()));
                display.setText("");
                operator[3] = true;
            }
        });

        mod.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                setOpNum(Double.parseDouble(display.getText()));
                display.setText("");
                operator[4] = true;
            }
        });

        equals.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(operator[0])
                {
                    display.setText((Double.toString(getOpNum()+(Double.parseDouble(display.getText())))));
                    setOpNum(0);
                    operator[0] = false;
                }

                if(operator[1])
                {
                    display.setText((Double.toString(getOpNum()-(Double.parseDouble(display.getText())))));
                    setOpNum(0);
                    operator[1] = false;
                }

                if(operator[2])
                {
                    display.setText((Double.toString(getOpNum()*(Double.parseDouble(display.getText())))));
                    setOpNum(0);
                    operator[2] = false;
                }

                if(operator[3])
                {
                    display.setText((Double.toString(getOpNum()/(Double.parseDouble(display.getText())))));
                    setOpNum(0);
                    operator[3] = false;
                }

                if(operator[4])
                {
                    display.setText((Double.toString(getOpNum()%(Double.parseDouble(display.getText())))));
                    setOpNum(0);
                    operator[4] = false;
                }
            }
        });

        hex.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent hey)
            {
                a.setEnabled(true);
                b.setEnabled(true);
                c.setEnabled(true);
                d.setEnabled(true);
                e.setEnabled(true);
                f.setEnabled(true);
                dos.setEnabled(true);
                tres.setEnabled(true);
                cuatro.setEnabled(true);
                cinco.setEnabled(true);
                seis.setEnabled(true);
                siete.setEnabled(true);
                ocho.setEnabled(true);
                nueve.setEnabled(true);
            }
        });

        dec.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent hey)
            {
                a.setEnabled(false);
                b.setEnabled(false);
                c.setEnabled(false);
                d.setEnabled(false);
                e.setEnabled(false);
                f.setEnabled(false);
                dos.setEnabled(true);
                tres.setEnabled(true);
                cuatro.setEnabled(true);
                cinco.setEnabled(true);
                seis.setEnabled(true);
                siete.setEnabled(true);
                ocho.setEnabled(true);
                nueve.setEnabled(true);
            }
        });

        bin.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent hey)
            {
                a.setEnabled(false);
                b.setEnabled(false);
                c.setEnabled(false);
                d.setEnabled(false);
                e.setEnabled(false);
                f.setEnabled(false);
                dos.setEnabled(false);
                tres.setEnabled(false);
                cuatro.setEnabled(false);
                cinco.setEnabled(false);
                seis.setEnabled(false);
                siete.setEnabled(false);
                ocho.setEnabled(false);
                nueve.setEnabled(false);
            }
        });

        oct.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent hey)
            {
                a.setEnabled(false);
                b.setEnabled(false);
                c.setEnabled(false);
                d.setEnabled(false);
                e.setEnabled(false);
                f.setEnabled(false);
                dos.setEnabled(true);
                tres.setEnabled(true);
                cuatro.setEnabled(true);
                cinco.setEnabled(true);
                seis.setEnabled(true);
                siete.setEnabled(true);
                ocho.setEnabled(false);
                nueve.setEnabled(false);
            }
        });

        qword.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent hey)
            {
                display2.setText(" 0000   0000   0000   0000   0000   0000   0000   0000\n 63                          47                     32\n 0000   0000   0000   0000   0000   0000   0000   0000\n 31                          15                      0");
            }
        });

        dword.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent hey)
            {
                display2.setText("\n\n 0000   0000   0000   0000   0000   0000   0000   0000\n 31                          15                      0");
            }
        });







        GroupLayout layout2 = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout2);
        layout2.setAutoCreateGaps(true);
        layout2.setAutoCreateContainerGaps(true);

        layout2.setHorizontalGroup(layout2.createSequentialGroup()
                .addComponent(space, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(a, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(c, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(d, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(e, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(f, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )

                .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mod, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(back, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(siete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cuatro, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(uno, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cero, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )

                .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(see, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ocho, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cinco, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )

                .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(sea, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nueve, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(seis, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tres, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deci, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )

                .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(neg, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(divide, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(times, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(minus, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(plus, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )

                .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(sqrt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(percent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(oneX, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(equals, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )
        );

        layout2.setVerticalGroup(layout2.createSequentialGroup()
                .addGap(135)
                .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(space, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(a, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mod, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )

                .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(b, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(back, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(see, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sea, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(neg, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sqrt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )

                .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(c, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(siete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ocho, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nueve, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(divide, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(percent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )

                .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(d, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cuatro, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cinco, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(seis, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(times, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(oneX, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )

                .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(e, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(uno, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tres, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(minus, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )

                .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(f, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cero, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deci, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(plus, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(equals, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )
        );

        pack();

    }




    private void setupUI(Calculator calc)
    {
        calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calc.setSize(415, 385);
        calc.setResizable(false);
        calc.setLayout(null);
        calc.setLocationRelativeTo(null);
        calc.setVisible(true);

        show.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent a)
            {
                calc.setVisible(true);
            }

        });

        hide.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent a)
            {
                calc.setVisible(false);
            }

        });

    }

    public void setOpNum(double woah)
    {
        opNum = woah;
    }

    public double getOpNum()
    {
        return opNum;
    }

}

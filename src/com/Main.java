package com;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        final Database data = new Database();

        final JFrame frame = new JFrame("aoopt_adapter_proxy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JSplitPane splitPane = new JSplitPane();

        final JList list = new JList(data);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBorder(BorderFactory.createTitledBorder(" Arrays: "));
        splitPane.setLeftComponent(scrollPane);

        JTable table = new JTable();
        scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(" Content: "));
        splitPane.setRightComponent(scrollPane);

        frame.getContentPane().add(splitPane);

        JMenuBar bar = new JMenuBar();
        JButton add = new JButton("Add new array");
        JButton del = new JButton("Remove array");
        JButton copy = new JButton("Copy array");
        bar.add(add);
        bar.add(del);
        bar.add(copy);

        frame.setJMenuBar(bar);

        frame.setSize(600, 450);
        frame.setVisible(true);

        splitPane.setDividerLocation(0.5);

        add.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String value = JOptionPane.showInputDialog(frame,
                        "Give the array size",
                        "Add",
                        JOptionPane.INFORMATION_MESSAGE);
                try{
                    int size = Integer.parseInt(value);
                    data.add(new DataProxy(size));
                } catch(Exception ex) { }
            }
        });
        del.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int idx = list.getSelectedIndex();
                try{
                    data.remove(idx);
                } catch(Exception ex) { }
            }
        });

        // change of selection at the list fires the table refresh
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int idx = list.getSelectedIndex();
                if (idx >= 0) {
                    table.setModel(new DataTableModelAdapter((Data) data.getElementAt(idx)));
                }
            }
        });

        copy.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int idx = list.getSelectedIndex();
                DataProxy dataProxy = (DataProxy) data.getElementAt(idx);
                CopyWriteProxy copyWriteProxy = new CopyWriteProxy(dataProxy);
                dataProxy.add(copyWriteProxy);
                data.add(copyWriteProxy);
            }
        });
    }
}

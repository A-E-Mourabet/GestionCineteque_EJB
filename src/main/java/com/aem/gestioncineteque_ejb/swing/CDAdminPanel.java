package com.aem.gestioncineteque_ejb.swing;

import com.aem.gestioncineteque_ejb.EJB.UserOperations;
import com.aem.gestioncineteque_ejb.entity.CD;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CDAdminPanel extends JPanel {
    private JTable cdTable;
    private DefaultTableModel tableModel;
    private UserOperations userOperations;

    public CDAdminPanel() {
        try {
            InitialContext ctx = new InitialContext();
            userOperations = (UserOperations) ctx.lookup("java:global/your-ear/your-ejb/UserOperationsBean!com.cinetech.ejb.UserOperations");
        } catch (NamingException e) {
            e.printStackTrace();
            return; // Exit if the EJB lookup fails
        }

        setLayout(new BorderLayout());

        // Create table model and table
        String[] columnNames = {"ID", "Title", "Artist", "Available"};
        tableModel = new DefaultTableModel(columnNames, 0);
        cdTable = new JTable(tableModel);
        loadCDs();

        // Create buttons
        JButton addButton = new JButton("Add CD");
        JButton modifyButton = new JButton("Modify CD");
        JButton deleteButton = new JButton("Delete CD");
        JButton refreshButton = new JButton("Refresh");

        addButton.addActionListener(e -> openCDDialog(null));
        modifyButton.addActionListener(e -> {
            int selectedRow = cdTable.getSelectedRow();
            if (selectedRow >= 0) {
                Long cdId = (Long) tableModel.getValueAt(selectedRow, 0);
                openCDDialog(cdId);
            } else {
                showMessage("Please select a CD to modify.");
            }
        });
        deleteButton.addActionListener(e -> {
            int selectedRow = cdTable.getSelectedRow();
            if (selectedRow >= 0) {
                Long cdId = (Long) tableModel.getValueAt(selectedRow, 0);
                deleteCD(cdId);
            } else {
                showMessage("Please select a CD to delete.");
            }
        });
        refreshButton.addActionListener(e -> loadCDs());

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        add(new JScrollPane(cdTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadCDs() {
        tableModel.setRowCount(0); // Clear existing rows
        List<CD> cds = userOperations.browseCDs(); // Call the EJB to get CDs
        for (CD cd : cds) {
            tableModel.addRow(new Object[]{cd.getId(), cd.getTitle(), cd.getArtist(), cd.isAvailable()});
        }
    }

    private void openCDDialog(Long cdId) {
        CDDialog dialog = new CDDialog(this, userOperations, cdId);
        dialog.setVisible(true);
    }

    private void deleteCD(Long cdId) {
        userOperations.deleteCD(cdId);
        loadCDs();
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}


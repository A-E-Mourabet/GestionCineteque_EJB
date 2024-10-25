package com.aem.gestioncineteque_ejb.swing;

import com.aem.gestioncineteque_ejb.EJB.UserOperations;
import com.aem.gestioncineteque_ejb.entity.CD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CDDialog extends JDialog {
    private JTextField titleField;
    private JTextField artistField;
    private JButton saveButton;
    private UserOperations userOperations;
    private Long cdId;

    public CDDialog(Frame owner, UserOperations userOperations, Long cdId) {
        super(owner, cdId == null ? "Add CD" : "Edit CD", true);
        this.userOperations = userOperations;
        this.cdId = cdId;

        setLayout(new GridLayout(3, 2));

        add(new JLabel("Title:"));
        titleField = new JTextField();
        add(titleField);

        add(new JLabel("Artist:"));
        artistField = new JTextField();
        add(artistField);

        saveButton = new JButton(cdId == null ? "Add" : "Save");
        saveButton.addActionListener(new SaveAction());
        add(saveButton);

        if (cdId != null) {
            // Load existing CD data for editing
            CD existingCD = userOperations.findCD(cdId);
            titleField.setText(existingCD.getTitle());
            artistField.setText(existingCD.getArtist());
        }

        pack();
        setLocationRelativeTo(owner); // Center the dialog
    }

    private class SaveAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = titleField.getText();
            String artist = artistField.getText();

            if (cdId == null) {
                // Adding a new CD
                userOperations.addCD(title, artist);
            } else {
                // Modifying an existing CD
                userOperations.updateCD(cdId, title, artist);
            }

            dispose(); // Close the dialog
        }
    }
}


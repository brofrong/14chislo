package programm.ui;

import programm.ui.abstractFrame.BaseFrame;
import programm.database.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * окно обновления/добавления
 */

public class AddUpdateDataFrame extends BaseFrame {
    private JButton sendBtn;

    private JTextField title;
    private JTextField cost;
    private JTextField durationInSeconds;
    private JTextField discount;

    private JPanel addPanel;
    private JPanel idPanel;
    private JTextField idField;
    private JButton changeBtn;

    AddUpdateDataFrame(MainFrame app, Database database, String id) {
        if (!id.equals("")) {
            loadData(database, id);
            idPanel.setVisible(true);
            changeBtn.setVisible(true);
            sendBtn.setVisible(false);
        }

        setContentPane(addPanel);

        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                database.insertIntoTable(title.getText(),cost.getText(),durationInSeconds.getText(),discount.getText());
                app.updateTable("");
                dispose();
            }
        });

        changeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                database.updateTable(title.getText(),cost.getText(), durationInSeconds.getText(), discount.getText(), idField.getText());
                app.updateTable("");
                dispose();
            }

        });
    }

    private void loadData(Database database, String id) {
        String[] fields = database.getById(id);
        idField.setText(fields[0]);
        title.setText(fields[1]);
        cost.setText(fields[2]);
        durationInSeconds.setText(fields[3]);
        discount.setText(fields[4]);
    }
}

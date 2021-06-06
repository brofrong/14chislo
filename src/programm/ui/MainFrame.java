package programm.ui;

import programm.utils.Utils;
import programm.ui.abstractFrame.BaseFrame;
import programm.database.Database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;

/**
 * рабочая область
 */

public class MainFrame extends BaseFrame {

    private JTable table1;
    private JButton addBtn;
    private JButton deleteBtn;
    private JButton editBtn;
    private JTextField filter;
    private JPanel adminPanel;
    private JLabel rowsCount;
    private JPanel mainPanel;
    private JButton backButton;
    private JButton ForwardButton;
    private JLabel pageNumber;

    private int maxElemtetsPerPage = 20;
    private int page = 0;

    public Database database;
    public static MainFrame app;

    public MainFrame() {
        setContentPane(mainPanel);
        app = this;
        database = new Database();
        updateTable("");
        initListners();
    }

    private void initListners() {
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUpdateDataFrame insertServiceForm = new AddUpdateDataFrame(app, database, "");
                insertServiceForm.setVisible(true);
            }
        });
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectRowIndex = table1.getSelectedRow();
                //table1.
                if (selectRowIndex != -1) {
                    AddUpdateDataFrame insertServiceForm = new AddUpdateDataFrame(app, database, table1.getValueAt(selectRowIndex,0).toString());
                    insertServiceForm.setVisible(true);
                } else {
                    Utils.showError(app, "Выберете поле в таблице");
                }

            }
        });
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectRowIndex = table1.getSelectedRow();
                if (selectRowIndex != -1 && Utils.showOption(null, "Точно удалить?")) {
                    String id = table1.getValueAt(selectRowIndex, 0).toString();
                    database.deleteById(id);
                    updateTable("");
                } else {
                    Utils.showError(app, "Выберете поле в таблице");
                }
            }
        });

        filter.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                updateTable(filter.getText());
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(page > 0){
                    page--;
                }
                updatePageNumber();
                updateTable(filter.getText());
            }
        });
        ForwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page++;
                updatePageNumber();
                updateTable(filter.getText());
            }
        });
    }


    /**
     * Запрос к базе данных, загрузка актуальной информации в таблицу
     * @param filer
     */
    public void updateTable(String filer) {
        DefaultTableModel serviceModel = new DefaultTableModel();
        serviceModel.setColumnIdentifiers(new String[]{"ID", "Название", "Стоимость", "Длительность", "Скидка"});
        Iterator it = database.getAll(filer).iterator();

        int counter = 0; //

        int toSkip = page * maxElemtetsPerPage;

        while (it.hasNext() && counter < toSkip){
            it.next();
            counter++;
        }
        counter = 0;
        while (it.hasNext() && counter < maxElemtetsPerPage){
            counter++;
            serviceModel.addRow((String[]) it.next());
        }

        table1.setModel(serviceModel);
        table1.setAutoCreateRowSorter(true);
    }

    private void updatePageNumber(){
        pageNumber.setText(String.valueOf(page));
    }
}


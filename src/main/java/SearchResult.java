import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;

public class SearchResult implements ActionListener {
    JFrame frame, frame1;
    JTextField textbox;
    JTextField gost_id, soba_id, rezervira_od, rezervira_do, zaposlenik_id;
    JLabel label;
    JButton button, button2, button3, button4, button5;
    JPanel panel;
    static JTable table;
    JComboBox<String> cb;

    String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String url = "jdbc:sqlserver://bp2pepelnik.database.windows.net; databaseName= BP2_Karlo_Pepelnik; user= admin2; password= baze2pepelnik+";
    String[] columnNames;

    public void createUI() {
        frame = new JFrame("SQL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        String[] choices = { "zaposlenik","vrsta_zaposlenika", "vrsta_placanja","gost","usluga","soba", "hotel", "vrsta_sobe"};
        cb = new JComboBox<>(choices);
        textbox = new JTextField();
        textbox.setPreferredSize(new Dimension(150, 20));
        //textbox.setBounds(120, 30, 150, 20);
        label = new JLabel("Odaberite tablicu za prikaz: ");
        label.setBounds(10, 30, 100, 20);
        button = new JButton(new AbstractAction("Prikaz tablice") {
            @Override
            public void actionPerformed(ActionEvent e) {
                button = (JButton) e.getSource();
                System.out.println("Showing Table Data.......");
                showTableData();
            }
        });
        button2 = new JButton(new AbstractAction("Unos rezervacije") {
            @Override
            public void actionPerformed(ActionEvent e) {
                button = (JButton) e.getSource();
                System.out.println("Insert dialog.");
                showInsertDialog();
            }
        });
        button3 = new JButton(new AbstractAction("Pregled zaposlenika") {
            @Override
            public void actionPerformed(ActionEvent e) {
                button = (JButton) e.getSource();
                frame1 = new JFrame("Database Search Result");
                frame1.setLayout(new BorderLayout());
                DefaultTableModel model = new DefaultTableModel();
                table = new JTable() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                table.setModel(model);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                table.setFillsViewportHeight(true);
                JScrollPane scroll = new JScrollPane(table);
                scroll.setHorizontalScrollBarPolicy(
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scroll.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                columnNames = new String[]{"Zaposlenik", "Radno Mjesto"};
                model.setColumnIdentifiers(columnNames);
                try {
                    Class.forName(driverName);
                    Connection con = DriverManager.getConnection(url);
                    String sql = "SELECT CONCAT(z.ime, ' ', z.prezime) as 'zaposlenik', opis_vrste as 'r_m' from zaposlenik z inner join vrsta_zaposlenika vz on vz.vrsta_zaposlenika_id=z.vrsta_zaposlenika_id";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    String first;
                    String second;
                    while (rs.next()) {
                        first = rs.getString("zaposlenik");
                        second = rs.getString("r_m");
                        model.addRow(new Object[]{first, second});
                    }
                    frame1.add(scroll);
                    frame1.setVisible(true);
                    frame1.setSize(700, 500);
                }
                catch(Exception asd){
                    System.out.println(asd.toString());
                }
            }
        });
        button4 = new JButton(new AbstractAction("Pregled gostiju") {
            @Override
            public void actionPerformed(ActionEvent e) {
                button = (JButton) e.getSource();
                frame1 = new JFrame("Database Search Result");
                frame1.setLayout(new BorderLayout());
                DefaultTableModel model = new DefaultTableModel();
                table = new JTable() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                table.setModel(model);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                table.setFillsViewportHeight(true);
                JScrollPane scroll = new JScrollPane(table);
                scroll.setHorizontalScrollBarPolicy(
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scroll.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                columnNames = new String[]{"Gost", "Nacin placanja"};
                model.setColumnIdentifiers(columnNames);
                try {
                    Class.forName(driverName);
                    Connection con = DriverManager.getConnection(url);
                    String sql = "SELECT CONCAT(g.ime,' ',g.prezime) as Gost, naziv_placanja as 'n_p' FROM gost g inner join vrsta_placanja vp on vp.vrsta_placanja_id = g.vrsta_placanja_id;";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    String first;
                    String second;
                    while (rs.next()) {
                        first = rs.getString("Gost");
                        second = rs.getString("n_p");
                        model.addRow(new Object[]{first, second});
                    }
                    frame1.add(scroll);
                    frame1.setVisible(true);
                    frame1.setSize(700, 500);
                }
                catch(Exception asd){
                    System.out.println(asd.toString());
                }
            }

        });

        button5 = new JButton(new AbstractAction("Pregled soba") {
            @Override
            public void actionPerformed(ActionEvent e) {
                button = (JButton) e.getSource();
                frame1 = new JFrame("Database Search Result");
                frame1.setLayout(new BorderLayout());
                DefaultTableModel model = new DefaultTableModel();
                table = new JTable() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                table.setModel(model);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                table.setFillsViewportHeight(true);
                JScrollPane scroll = new JScrollPane(table);
                scroll.setHorizontalScrollBarPolicy(
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scroll.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                columnNames = new String[]{"Vrsta Sobe", "Kat", "Cijena"};
                model.setColumnIdentifiers(columnNames);
                try {
                    Class.forName(driverName);
                    Connection con = DriverManager.getConnection(url);
                    String sql = "SELECT v.opis_sobe, kat, v.cijena_sobe from vrsta_sobe v inner join soba s on v.vrsta_sobe_id = s.vrsta_sobe_id;";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    String first;
                    String second;
                    String third;
                    while (rs.next()) {
                        first = rs.getString("opis_sobe");
                        second = rs.getString("kat");
                        third = rs.getString("cijena_sobe");
                        model.addRow(new Object[]{first, second, third});
                    }
                    frame1.add(scroll);
                    frame1.setVisible(true);
                    frame1.setSize(700, 500);
                }
                catch(Exception asd){
                    System.out.println(asd.toString());
                }
            }
        });

        button2.setBounds(120, 130, 150, 20);
        button.setBounds(120, 130, 150, 20);
        button3.setBounds(120, 130, 150, 20);
        button4.setBounds(120, 130, 150, 20);
        button5.setBounds(120, 130, 150, 20);

        //button2.addActionListener(this);
        //button.addActionListener(this);

        frame.add(label);
        frame.add(cb);
        frame.add(button);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);
        frame.setVisible(true);
        frame.setSize(500, 500);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


    public void showTableData() {

        frame1 = new JFrame("Rezultati pretrage");
        frame1.setLayout(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel();

        table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        String first;
        String second;
        String third;
        String fourth;
        String fifth;
        String sixth;
        try {
            Class.forName(driverName);
            Connection con = DriverManager.getConnection(url);
            String sql = "select * from " + Objects.requireNonNull(cb.getSelectedItem()).toString();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            switch (cb.getSelectedIndex()) {
                case 0:
                    columnNames = new String[]{"Zaposlenik ID", "Ime", "Prezime", "OIB", "Hotel ID", "Vrsta Zaposlenika ID"};
                    model.setColumnIdentifiers(columnNames);
                    while (rs.next()) {
                        first = rs.getString("zaposlenik_id");
                        second = rs.getString("ime");
                        third = rs.getString("prezime");
                        fourth = rs.getString("oib");
                        fifth = rs.getString("hotel_id");
                        sixth = rs.getString("vrsta_zaposlenika_id");

                        model.addRow(new Object[]{first, second, third, fourth, fifth, sixth});
                        i++;
                    }
                    break;

                case 1:
                    columnNames = new String[]{"Vrsta Zaposlenika ID", "Opis Vrste", "Placa"};
                    model.setColumnIdentifiers(columnNames);
                    while (rs.next()) {
                        first = rs.getString("vrsta_zaposlenika_id");
                        second = rs.getString("opis_vrste");
                        third = rs.getString("placa");

                        model.addRow(new Object[]{first, second, third});
                        i++;
                    }
                    break;

                case 2:
                    columnNames = new String[]{"Vrsta Placanja ID", "Naziv Placanja", "Popust"};
                    model.setColumnIdentifiers(columnNames);
                    while (rs.next()) {
                        first = rs.getString("vrsta_placanja_id");
                        second = rs.getString("naziv_placanja");
                        third = rs.getString("popust");

                        model.addRow(new Object[]{first, second, third});
                        i++;
                    }
                    break;

                case 3:
                    columnNames = new String[]{"Gost ID", "Ime", "Prezime", "OIB", "Kontakt", "Vrsta Placanja ID"};
                    model.setColumnIdentifiers(columnNames);
                    while (rs.next()) {
                        first = rs.getString("gost_id");
                        second = rs.getString("ime");
                        third = rs.getString("prezime");
                        fourth = rs.getString("OIB");
                        fifth = rs.getString("kontakt");
                        sixth = rs.getString("vrsta_placanja_id");

                        model.addRow(new Object[]{first, second, third, fourth, fifth, sixth});
                        i++;
                    }
                    break;

                case 4:
                    columnNames = new String[]{"Usluga ID", "Opis Usluge", "Cijena Usluge", "Zaposlenik ID"};
                    model.setColumnIdentifiers(columnNames);
                    while (rs.next()) {
                        first = rs.getString("usluga_id");
                        second = rs.getString("opis_usluge");
                        third = rs.getString("cijena_usluge");
                        fourth = rs.getString("zaposlenik_id");
                        model.addRow(new Object[]{first, second, third, fourth});
                        i++;
                    }
                    break;

                case 5:
                    columnNames = new String[]{"Soba ID", "Kat", "Vrsta Sobe ID"};
                    model.setColumnIdentifiers(columnNames);
                    while (rs.next()) {
                        first = rs.getString("soba_id");
                        second = rs.getString("kat");
                        third = rs.getString("vrsta_sobe_id");

                        model.addRow(new Object[]{first, second, third});
                        i++;
                    }
                    break;

                case 6:
                    columnNames = new String[]{"Hotel ID", "Naziv Hotela", "Adresa", "Kontakt", "Web Stranica", "Broj Zvjezdica"};
                    model.setColumnIdentifiers(columnNames);
                    while (rs.next()) {
                        first = rs.getString("hotel_id");
                        second = rs.getString("naziv_hotela");
                        third = rs.getString("adresa");
                        fourth = rs.getString("kontakt");
                        fifth = rs.getString("web_stranica");
                        sixth = rs.getString("broj_zvjezdica");

                        model.addRow(new Object[]{first, second, third, fourth, fifth, sixth});
                        i++;
                    }
                    break;
                case 7:
                    columnNames = new String[]{"Vrsta Sobe ID", "Opis Sobe", "Cijena Sobe"};
                    model.setColumnIdentifiers(columnNames);
                    while (rs.next()) {
                        first = rs.getString("vrsta_sobe_id");
                        second = rs.getString("opis_sobe");
                        third = rs.getString("cijena_sobe");

                        model.addRow(new Object[]{first, second, third});
                        i++;
                    }
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Tablica s tim imenom ne postoji", "Error",
                            JOptionPane.ERROR_MESSAGE);

            }


            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            if (i == 1) {
                System.out.println(i + " Record Found");
            } else {
                System.out.println(i + " Records Found");
            }
            frame1.add(scroll);
            frame1.setVisible(true);
            frame1.setSize(700, 500);
        } catch (Exception ignored) {
            System.out.println(cb.getSelectedIndex());
            JOptionPane.showMessageDialog(null, "Tablica s tim imenom ne postoji", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }


    public void showInsertDialog() {
        frame1 = new JFrame("Rezervacija");
        panel = new JPanel();
        frame1.setLayout(new BoxLayout(frame1.getContentPane(), BoxLayout.Y_AXIS));


        gost_id = new JTextField();
        JLabel gost_idLabel = new JLabel("Šifra Gosta");
        gost_idLabel.setLabelFor(gost_id);
        gost_id.setMaximumSize(new Dimension(400, 20));
        gost_idLabel.setFont(new Font("Verdana", Font.PLAIN, 11));

        soba_id = new JTextField();
        JLabel soba_idLabel = new JLabel("Šifra sobe");
        soba_idLabel.setLabelFor(soba_id);
        soba_id.setMaximumSize(new Dimension(400, 20));
        soba_idLabel.setFont(new Font("Verdana", Font.PLAIN, 11));

        rezervira_od = new JTextField();
        JLabel rezervira_odLabel = new JLabel("Od");
        rezervira_odLabel.setLabelFor(rezervira_od);
        rezervira_od.setMaximumSize(new Dimension(400, 20));
        rezervira_odLabel.setFont(new Font("Verdana", Font.PLAIN, 11));

        rezervira_do = new JTextField();
        JLabel rezervira_doLabel = new JLabel("Do");
        rezervira_doLabel.setLabelFor(rezervira_do);
        rezervira_do.setMaximumSize(new Dimension(400, 20));
        rezervira_doLabel.setFont(new Font("Verdana", Font.PLAIN, 11));

        zaposlenik_id = new JTextField();
        JLabel zaposlenik_idLabel = new JLabel("Šifra zaposlenika");
        zaposlenik_idLabel.setLabelFor(zaposlenik_id);
        zaposlenik_id.setMaximumSize(new Dimension(400, 20));
        zaposlenik_idLabel.setFont(new Font("Verdana", Font.PLAIN, 11));

        JButton dialogButton = new JButton(new AbstractAction("Unesi") {
            @Override
            public void actionPerformed(ActionEvent e) {
                button = (JButton) e.getSource();
                try {
                    Class.forName(driverName);
                    Connection con = DriverManager.getConnection(url);
                    Statement statement = con.createStatement();
                    boolean test = gost_id.getText().equals("") || soba_id.getText().equals("") || rezervira_od.getText().equals("") || rezervira_do.getText().equals("");
                    if(zaposlenik_id.getText().equals("1") && !test && rezervira_do.getText().contains("-") && rezervira_od.getText().contains("-")) {
                        String sql;
                        sql = "INSERT INTO rezervira (gost_id, soba_id, rezervira_od, rezervira_do, zaposlenik_id) VALUES (" + gost_id.getText() + ","+ soba_id.getText() + ",'" + rezervira_od.getText() + "','" + rezervira_do.getText() + "'," + zaposlenik_id.getText() + ")";
                        System.out.println(sql);
                        statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null, "Unos uspješan!", "Success!",
                                JOptionPane.INFORMATION_MESSAGE);
                        frame1.setVisible(false);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Zaposlenik mora biti VODITELJ, i sva polja moraju biti popunjena. Dautm se unosi u formatu DD-MM-YYYY", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (ClassNotFoundException | SQLException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

            }
        });


        frame1.add(gost_idLabel);
        frame1.add(gost_id);
        frame1.add(soba_idLabel);
        frame1.add(soba_id);
        frame1.add(rezervira_odLabel);
        frame1.add(rezervira_od);
        frame1.add(rezervira_doLabel);
        frame1.add(rezervira_do);
        frame1.add(zaposlenik_idLabel);
        frame1.add(zaposlenik_id);
        frame1.add(dialogButton);


        frame1.setSize(200, 350);
        frame1.setVisible(true);
    }


}
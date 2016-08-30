# AOOPT_AdapterProxy
Advanced Object-oriented Programming Technics - Adapter and Proxy

Write a simple database of arrays Data. This database is visualized in the control (left panel) of the class JList (it needs an external data model derived from the class AbstractListModel).
Complete the skeleton:

* Write a simple implementation of the data - RealData (eg. as int[] or ArrayList/LinkedList);
* Complete the Database class (don't forget about calling fireIntervalAdded and fireIntervalRemoved).

Choosing an array on the list, the program should show it in the editable table on the right panel. To achieve it create an adapter implementing the interface type TableModel or (better) extending the class AbstractTableModel.

* It should present the array data in two columns: index and value;
* Methods: getValueAt, getRowCount, getColumnName, getColumnCount, implement also isCellEditable, setValueAt and getColumnClass - they will allow modification of values;
* Create an adapter object and put it in the control JTable in the marked place (in the source code);
* Change the array list. It should also change the data pointed in the adapter;
* Don't forget to call  fireTableStructureChanged method.

Add a virtual proxy to data realizing its lazy initialization:

* The database should not contain the real array but proxies to them;
* It is not necessary to create the array immediately - until the set method is called for the first time (first modification);
*It is not necessary to create the array to show it in the table  - it is empty so all its values are initialized to 0.

Add a button copying the chosen array using the copy-on-write proxy.

* At the beginning it is a shallow copy - proxy to the original array;
* On the first modification of the "copy" a deep copy will be created and the proxy will switch to point to the brand new created copy;

In the above implementation only modification of the copy will cause creation of the real copy - change it to fire this mechanism also on modification of the original array - you will need a list of the created copies in every real array.

Skeleton:

// data in the database

interface Data {
    public int get(int idx);
    public void set(int idx, int value);
    public int size();
}

// simple implementation - array

class RealData implements Data {
    /* ... */
}

// database - collection of Data

class Database extends AbstractListModel{
    private ArrayList<Data> ar = new ArrayList<Data>();

    public void add(Data tab){
        /* ... */
    }

    public void remove(int idx){
        /* ... */
    }

    public int getSize() {
        /* ... */
    }

    public Object getElementAt(int index) {
        /* ... */
    }
}


public class aoopt_adapter_proxy {

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

        JTable table = new JTable(/* ... ad here an adapter: TableModel ... */);
        scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(" Content: "));
        splitPane.setRightComponent(scrollPane);

        frame.getContentPane().add(splitPane);

        JMenuBar bar = new JMenuBar();
        JButton add = new JButton("Add new array");
        JButton del = new JButton("Remove array");
        bar.add(add);
        bar.add(del);

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
                    data.add(new RealData(size));
                } catch(Exception ex) { };
            }
        });
        del.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int idx = list.getSelectedIndex();
                try{
                    data.remove(idx);
                } catch(Exception ex) { };
            }
        });

        // change of selection at the list fires the table refresh
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int idx = list.getSelectedIndex();
                if (idx >= 0) {
                    /* ... */
                }
            }
        });
    }
}
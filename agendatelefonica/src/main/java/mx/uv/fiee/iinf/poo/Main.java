package mx.uv.fiee.iinf.poo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Clase cliente
 */
public class Main {

    public static void main (String [] args) throws IOException {
        new Agenda ().setVisible (true);
    }



}

/**
 * Clase que representa nuestra interfaz para la agenda telefónica
 */
class Agenda extends JFrame {
    private JTextField tfName; // campos de datos
    private JButton btnAdd, btnDelete,btnSaveAll; // acciones
    private MyTextField tfPhone; //JTextfield personalizado
    // JTable nos sirve para almacenar el listado de datos que deseamos mostrar al usuario
    private JTable table;
    private DefaultTableModel model; // JTable utiliza un modelo para mantener referencia a los datos

    public Agenda () throws IOException {
        super ("Agenda Telefónica");

        tfName = new JTextField (); // instanciamos al JTextField nombre
        tfPhone = new MyTextField (); // instanciamos al JTextField teléfono
        tfPhone.setToolTipText ("Format: (###) #######"); // establecemos un mensaje de ayuda al usuario

        btnAdd = new JButton ("Add"); // instanciamos al botón ADD
        // manejador del evento clic del botón ADD
        btnAdd.addActionListener (new ActionListener () {
            /**
             * cada vez que el usuario hace clic se ejecuta el método actionPerformed
             *
             * @param e parámetro con información de la fuente del evento
             */
            @Override
            public void actionPerformed (ActionEvent e) {
                // validamos que los campos no estén vacíos
                if (tfPhone.getText ().length () > 0 && tfName.getText ().length () > 0) {
                    // anexamos los valores de los campos al modelo
                    model.addRow (new String [] {tfName.getText (), tfPhone.getText ()});
                }

                // si alguno de los campos está vacío, pasamos el foco a él
                if (tfName.getText ().length () == 0)
                    tfName.grabFocus ();
                else if (tfPhone.getText ().length () == 0)
                    tfPhone.grabFocus ();
            }
        });

        btnDelete = new JButton ("Delete"); // instanaciamos al botón ELIMINAR
        // manejador del evento clic del botón ELIMINAR
        btnDelete.addActionListener (new ActionListener () {
            /**
             * cada vez que el usuario hace clic se ejecuta el método actionPerformed
             *
             * @param e parámetro con información de la fuente del evento
             */
            @Override
            public void actionPerformed (ActionEvent e) {
                if (table.getSelectedRow () > -1) // validamos si existe algún elemento seleccionado
                {
                    model.removeRow(table.getSelectedRow()); // si existe, se elimina del modelo
                    guardar();
                }
            }
        });

        btnSaveAll=new JButton("Save All");
        // manejador de evento clic en boton Guardar todo
        btnSaveAll.addActionListener(e -> {
            guardar();

            });

        // definimos la estructura del modelo que será utilizado por el JTable
        model = new DefaultTableModel ();
        model.addColumn ("Nombre"); // constará de dos columnas, nombre
        model.addColumn ("Teléfono"); // y teléfono

        File file=new File("src/main/java/DatosTabla.txt");
        FileReader fileReader=new FileReader(file);
        BufferedReader buff =new BufferedReader(fileReader);
        String line;
        while ((line=buff.readLine())!=null)
        {
            //model.addRow (new String [] {tfName.getText (), tfPhone.getText ()});
            //System.out.println(line);
            String[] Row=separar(line);
            model.addRow(new String[] {Row[0],Row[1]});

        }

        buff.close();
        fileReader.close();




        // creamos al JTable y definimos algunas características
        table = new JTable (model);
        table.setGridColor (Color.LIGHT_GRAY);
        table.setShowHorizontalLines (true);
        table.setShowVerticalLines (true);

        // como contenedor para los campos de datos usamos un JPanel
        // con un layout tipo GridLayout de 2 x 2
        JPanel pnlFields = new JPanel ();
        pnlFields.setLayout (new GridLayout(2, 2));

        // agreamos los controles al panel de campos de datos
        pnlFields.add (new JLabel ("Nombre: ")); // la etiqueta no requiere más que instanciación con el texto correspodiente
        pnlFields.add (tfName);
        pnlFields.add (new JLabel ("Teléfono: "));
        pnlFields.add (tfPhone);

        // para organizar al panel de campos + el botón de acción ADD, utilizamos un BoxLayout
        // con alineación vertical
        JPanel pnlTOP = new JPanel ();
        BoxLayout boxLayout = new BoxLayout (pnlTOP, BoxLayout.Y_AXIS);
        pnlTOP.setLayout (boxLayout);
        pnlTOP.add (pnlFields);
        pnlTOP.add (btnAdd);

        // al botón ELIMINAR lo colocamos en un contenedor JPanel, con el objetivo de agegar más controles
        // en esa sección posteriormente
        JPanel pnlBOTTOM = new JPanel ();
        pnlBOTTOM.add (btnDelete);
        pnlBOTTOM.add(btnSaveAll);

        // establecemos las dimensiones iniciales
        setSize (new Dimension(300, 400));
        setLayout (new BorderLayout ()); // y definimos al BorderLayout como layout principal

        // colocamos cada uno de los páneles y controles que corresponden a las secciones,
        // arriba, centro, abajo
        // pnlTOP -> pnlFields + botón ADD
        // table -> JTable para el listado
        // pnlBOTTOM -> botón ELIMINAR
        add (BorderLayout.NORTH, pnlTOP);
        add (BorderLayout.CENTER, table);
        add (BorderLayout.SOUTH, pnlBOTTOM);



        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); // establece el comportamiento de cierre
    }

    public String[] separar(String cadena)
    {
        String[] separadas=cadena.split("\\:");
        return separadas;

    }

    public void guardar()
    {
        try {

            String archivo = "src/main/java/DatosTabla.txt";
            BufferedWriter bfw = new BufferedWriter(new FileWriter(archivo ));

            for (int i = 0 ; i < table.getRowCount(); i++) //realiza un barrido por filas.
            {
                for(int j = 0 ; j < table.getColumnCount();j++) //realiza un barrido por columnas.
                {
                    bfw.write((String)(table.getValueAt(i,j)));
                    if (j < table.getColumnCount() -1) { //agrega separador "," si no es el ultimo elemento de la fila.
                        bfw.write(":");
                    }
                }
                bfw.newLine(); //inserta nueva linea.
            }

            bfw.close(); //cierra archivo!
            System.out.println("El archivo fue salvado correctamente!");
            //JOptionPane.showMessageDialog(null,"Datos almacenados en el archivo");
        } catch (IOException ex) {
            System.out.println("ERROR: Ocurrio un problema al salvar el archivo!" + ex.getMessage());
        }

    }

}

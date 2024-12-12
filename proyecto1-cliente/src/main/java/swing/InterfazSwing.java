package swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import ejb.proyecto1_cliente.Main;
import model.Member;
import service.MemberRegistrationRemote;

public class InterfazSwing {
    public static void main(String[] args) {
    	
    	
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestión de Miembros");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            // Panel para el formulario de entrada
            JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
            JLabel nameLabel = new JLabel("Nombre:");
            JTextField nameField = new JTextField();
            JLabel emailLabel = new JLabel("Correo:");
            JTextField emailField = new JTextField();
            JLabel phoneLabel = new JLabel("Teléfono:");
            JTextField phoneField = new JTextField();

            JButton saveButton = new JButton("Guardar");
            JButton exitButton = new JButton("Salir");
           

            formPanel.add(nameLabel);
            formPanel.add(nameField);
            formPanel.add(emailLabel);
            formPanel.add(emailField);
            formPanel.add(phoneLabel);
            formPanel.add(phoneField);
            formPanel.add(saveButton);
            formPanel.add(exitButton);

            // Panel para la tabla
            String[] columnNames = {"Nombre", "Correo", "Teléfono"};
            JTable table = new JTable(new DefaultTableModel(columnNames, 0));
            JScrollPane tableScrollPane = new JScrollPane(table);

            // Crear instancia de Main
            Main client = new Main();
            try {
                client.initialize();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Error inicializando conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
                return;
            }

            // Acción del botón "Guardar"
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    String email = emailField.getText();
                    String phone = phoneField.getText();
                    try {
                        client.registerMember(name, email, phone);
                        JOptionPane.showMessageDialog(frame, "Miembro guardado exitosamente: " + name);
                        updateMemberTable(client, table); // Actualizar la tabla después de guardar
                        clearFields( nameField,  emailField,  phoneField);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Error al guardar miembro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
               
            });

            

            // Acción del botón "Salir"
            exitButton.addActionListener(e -> System.exit(0));

            // Organizar componentes en el frame
            frame.setLayout(new BorderLayout());
            frame.add(formPanel, BorderLayout.NORTH);
            frame.add(tableScrollPane, BorderLayout.CENTER);
        

            frame.setVisible(true);
        });
    }

    // Método para actualizar la tabla con la lista de miembros
    private static void updateMemberTable(Main client, JTable table) {
        try {
            List<Member> members = client.getAllMembers();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos
            for (Member member : members) {
                model.addRow(new Object[]{member.getName(), member.getEmail(), member.getPhoneNumber()});
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener miembros: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private static void clearFields(JTextField nameField, JTextField emailField, JTextField phoneField) {
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
    }
    
}

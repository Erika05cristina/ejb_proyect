package swing;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import ejb.proyecto1_cliente.Main;
import service.MemberRegistrationRemote;

public class InterfazSwing {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestión de Miembros");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            // Crear un panel
            JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

            // Etiquetas y campos de texto
            JLabel nameLabel = new JLabel("Nombre:");
            JTextField nameField = new JTextField();
            JLabel emailLabel = new JLabel("Correo:");
            JTextField emailField = new JTextField();
            JLabel phoneLabel = new JLabel("Teléfono:");
            JTextField phoneField = new JTextField();

            // Botones
            JButton saveButton = new JButton("Guardar");
            JButton exitButton = new JButton("Salir");

            // Crear instancia de Main para la conexión con EJB
            Main client = new Main();
            try {
                client.initialize();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Error inicializando conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
                return;
            }

            // Acción del botón Guardar
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    String email = emailField.getText();
                    String phone = phoneField.getText();

                    try {
                        client.registerMember(name, email, phone);
                        JOptionPane.showMessageDialog(frame, "Miembro guardado exitosamente: " + name);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Error al guardar miembro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            });

            // Acción del botón Salir
            exitButton.addActionListener(e -> System.exit(0));

            // Agregar componentes al panel
            panel.add(nameLabel);
            panel.add(nameField);
            panel.add(emailLabel);
            panel.add(emailField);
            panel.add(phoneLabel);
            panel.add(phoneField);
            panel.add(saveButton);
            panel.add(exitButton);

            // Agregar el panel al frame
            frame.add(panel);
            frame.setVisible(true);
        });
    }
}
package taskhandler;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class TaskHandler extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaskHandler frame = new TaskHandler();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TaskHandler() {
		model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Task Name", "Description"
				}
			);
		initComponents();
	}

	private void initComponents() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setSize(screenSize.width, screenSize.height);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTaskName = new JLabel("Task Name:");
		lblTaskName.setBounds(10, 98, 890, 14);
		contentPane.add(lblTaskName);
		
		JLabel lblTaskDescription = new JLabel("Task Description:");
		lblTaskDescription.setBounds(10, 149, 890, 14);
		contentPane.add(lblTaskDescription);
		
		TextField taskNameField = new TextField();
		taskNameField.setBounds(10, 118, 158, 22);
		contentPane.add(taskNameField);
		
		TextField taskDescriptionField = new TextField();
		taskDescriptionField.setBounds(10, 169, 651, 73);
		contentPane.add(taskDescriptionField);
		
		Label title = new Label("Welcome to Task Handler for Sondeos:");
		title.setFont(new Font("Gotham Black", Font.BOLD | Font.ITALIC, 17));
		title.setBounds(10, 23, 319, 22);
		contentPane.add(title);
		
		Label helper = new Label("Please enter the name of the task and its description. Press OK to visualise it into the table.");
		helper.setBounds(10, 51, 890, 35);
		contentPane.add(helper);
		
		JScrollPane tasksTable = new JScrollPane();
		tasksTable.setBounds(10, 286, 651, 143);
		contentPane.add(tasksTable);
		
		table = new JTable();
		table.setModel(model);
		tasksTable.setViewportView(table);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(10, 253, 74, 22);
		contentPane.add(btnOk);
		
		JButton btnDeleteTask = new JButton("Delete Task");
		btnDeleteTask.setEnabled(false);
		btnDeleteTask.setBounds(94, 253, 200, 23);
		contentPane.add(btnDeleteTask);
		
		
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            btnDeleteTask.setEnabled(true);
	        }
	    });
		
		
		taskNameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taskNameField.setText("");
			}
		});
		
		
		taskDescriptionField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taskDescriptionField.setText("");
			}
		});
		
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (taskNameField.getText().isEmpty() || taskDescriptionField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Both name and description labels can't be empty, please, complete them and try again.");
				}
				else {
					Vector<String> row = new Vector<String>();
					row.add(taskNameField.getText());
					row.add(taskDescriptionField.getText());
					model.addRow(row);
					taskNameField.setText("");
					taskDescriptionField.setText("");
				}
			}
		});
		
		
		btnDeleteTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				model.removeRow(row);
			}
		});
	}
}

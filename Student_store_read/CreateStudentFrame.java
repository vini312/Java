package workshop4_5;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class CreateStudentFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtStudentID;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtCourse;
	private ArrayList<Student> students = new ArrayList<Student>();
	private JButton btnAddStd;
	private JTextField txtStdNum;
	private JLabel lblFile;
	private JTextField txtFileName;
	private JButton btnCreateFile;
	private JLabel lblFileCreated;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateStudentFrame frame = new CreateStudentFrame();
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
	public CreateStudentFrame() {
		setTitle("Students File Creator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudentID = new JLabel("Student ID:");
		lblStudentID.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudentID.setBounds(10, 11, 77, 14);
		contentPane.add(lblStudentID);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstName.setBounds(10, 36, 77, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastName.setBounds(10, 61, 77, 14);
		contentPane.add(lblLastName);
		
		txtStudentID = new JTextField();
		txtStudentID.setBounds(97, 8, 190, 20);
		contentPane.add(txtStudentID);
		txtStudentID.setColumns(10);
		
		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(97, 33, 190, 20);
		contentPane.add(txtFirstName);
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(97, 58, 190, 20);
		contentPane.add(txtLastName);
		
		JLabel lblCourse = new JLabel("Course 1 :");
		lblCourse.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCourse.setBounds(10, 98, 77, 14);
		contentPane.add(lblCourse);
		
		txtCourse = new JTextField();
		txtCourse.setColumns(10);
		txtCourse.setBounds(97, 95, 190, 20);
		contentPane.add(txtCourse);
		
		JButton btnAddCourse = new JButton("Add this course");
		btnAddCourse.setBounds(97, 126, 190, 23);
		contentPane.add(btnAddCourse);
		
		btnAddStd = new JButton("Add this student");
		btnAddStd.setEnabled(false);
		btnAddStd.setBounds(30, 160, 257, 23);
		contentPane.add(btnAddStd);
		
		txtStdNum = new JTextField();
		txtStdNum.setEditable(false);
		txtStdNum.setBounds(297, 161, 127, 20);
		contentPane.add(txtStdNum);
		txtStdNum.setColumns(10);
		
		lblFile = new JLabel("File name to store students:");
		lblFile.setBounds(30, 194, 164, 14);
		contentPane.add(lblFile);
		
		txtFileName = new JTextField();
		txtFileName.setText("StudentObj.out");
		txtFileName.setBounds(194, 191, 89, 20);
		contentPane.add(txtFileName);
		txtFileName.setColumns(10);
		
		btnCreateFile = new JButton("Create Students File");
		btnCreateFile.setBounds(30, 219, 257, 23);
		btnCreateFile.setEnabled(false);
		contentPane.add(btnCreateFile);
		
		lblFileCreated = new JLabel("");
		lblFileCreated.setHorizontalAlignment(SwingConstants.CENTER);
		lblFileCreated.setBounds(297, 194, 127, 48);
		contentPane.add(lblFileCreated);
		
		JTextArea txtStdPreview = new JTextArea();
		txtStdPreview.setEditable(false);
		txtStdPreview.setLineWrap(true);
		txtStdPreview.setBounds(297, 6, 127, 143);
		contentPane.add(txtStdPreview);
		
		scrollPane = new JScrollPane(txtStdPreview);
		scrollPane.setBounds(297, 8, 142, 141);
		contentPane.add(scrollPane);
		
		btnAddCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtCourse.getText().isBlank()) {
					if(students.isEmpty() || students.get(students.size()-1).getFirstName() != null) {
						students.add(new Student());
					}
					students.get(students.size()-1).addCourse(txtCourse.getText());
					lblCourse.setText("Course " + (students.get(students.size()-1).getCoursesLength()+1) + " :");
					btnAddStd.setEnabled(true);
					txtCourse.setText(null);
				} else {
					txtCourse.setText("Type a Course name");
				}
			}
		});
		
		btnAddStd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtFirstName.getText().isBlank() || txtLastName.getText().isBlank()) {
						throw new Exception();
					}
					students.get(students.size()-1).setStdID((Integer.parseInt(txtStudentID.getText())));
					students.get(students.size()-1).setFirstName(txtFirstName.getText());
					students.get(students.size()-1).setLastName(txtLastName.getText());
					txtStdNum.setText("Students added: " + students.size());
					
					txtStdPreview.setText(null);
					students.forEach((std)->txtStdPreview.setText(txtStdPreview.getText() + std.toString() + "\n\n"));
					
					//reset the forms
					txtStudentID.setText(null);
					txtFirstName.setText(null);
					txtLastName.setText(null);
					lblCourse.setText("Course 1 :");
					btnAddStd.setEnabled(false);
					btnCreateFile.setEnabled(true);
					
				} catch (NumberFormatException except) {
					txtStudentID.setText("Error: the ID should be an integer");	
				} catch (Exception ex) {
					txtFirstName.setText("Please Type First Name");
					txtLastName.setText("Please Type Last Name");
				}
			}
		});
		
		btnCreateFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileOutputStream fileOs = new FileOutputStream(txtFileName.getText());
					ObjectOutputStream studentOs = new ObjectOutputStream(fileOs);
										
					studentOs.writeObject(students);
					studentOs.flush();
					fileOs.close();

					lblFileCreated.setText("Student file created!");

				} catch (IOException except) {
					lblFileCreated.setText(except.getMessage());;
				}
			}
		});
		
	}
}

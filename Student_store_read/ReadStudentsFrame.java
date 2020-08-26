package workshop4_5;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class ReadStudentsFrame extends JFrame {

	private ArrayList<Student> students = new ArrayList<Student>();
	private JPanel contentPane;
	private JTextField txtFileName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReadStudentsFrame frame = new ReadStudentsFrame();
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
	public ReadStudentsFrame() {
		setTitle("Read Students File");
		txtFileName = new JTextField();
		JTextPane txtStdObjectsOut = new JTextPane();
		JButton btnReadFile = new JButton("Read Sudents File");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		txtStdObjectsOut.setEditable(false);
		txtStdObjectsOut.setBounds(28, 55, 377, 195);
		contentPane.add(txtStdObjectsOut);
		
		contentPane.setLayout(null);
		contentPane.add(btnReadFile);
		
		txtFileName.setHorizontalAlignment(SwingConstants.CENTER);
		txtFileName.setText("StudentObj.out");
		txtFileName.setBounds(131, 13, 86, 20);
		contentPane.add(txtFileName);
		txtFileName.setColumns(10);
		
		JLabel lblFileName = new JLabel("File name:");
		lblFileName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFileName.setBounds(28, 16, 93, 14);
		contentPane.add(lblFileName);
		
		btnReadFile.setBounds(271, 12, 119, 23);
		
		JScrollPane scrollPane = new JScrollPane(txtStdObjectsOut);
		scrollPane.setBounds(28, 55, 377, 195);
		contentPane.add(scrollPane);
		btnReadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
    				FileInputStream fileIs = new FileInputStream(txtFileName.getText());
    				ObjectInputStream studentIs = new ObjectInputStream(fileIs);
    				
    				students = (ArrayList<Student>)studentIs.readObject();
    		
    				fileIs.close();
    				
    				txtStdObjectsOut.setText("");//clear the text area before printing in case it was already ran before
    				for (Student std : students) {
    				txtStdObjectsOut.setText(txtStdObjectsOut.getText() + std.toString() + "\n\n");
    				}
    			} catch (Throwable error){
    				System.err.println(error);
    				txtStdObjectsOut.setText(error.getMessage());
    			}
			}
		});
	}
}

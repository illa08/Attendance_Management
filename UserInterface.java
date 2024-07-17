import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class UserInterface{
    private JFrame frame;
    private JTextField studentNameField;
    private JButton markPresentButton;
    private JButton markAbsentButton;

    public UserInterface() {
        frame = new JFrame("Attendance Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(null);

        JLabel studentNameLabel = new JLabel("Student Name:");
        studentNameLabel.setBounds(20, 20, 100, 25);
        frame.add(studentNameLabel);

        studentNameField = new JTextField();
        studentNameField.setBounds(120, 20, 150, 25);
        frame.add(studentNameField);

        markPresentButton = new JButton("Mark Present");
        markPresentButton.setBounds(20, 60, 120, 25);
        frame.add(markPresentButton);

        markAbsentButton = new JButton("Mark Absent");
        markAbsentButton.setBounds(150, 60, 120, 25);
        frame.add(markAbsentButton);

        markPresentButton.addActionListener(new AttendanceActionListener("Present"));
        markAbsentButton.addActionListener(new AttendanceActionListener("Absent"));

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new UserInterface();
    }

    private class AttendanceActionListener implements ActionListener {
        private String status;

        public AttendanceActionListener(String status) {
            this.status = status;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String studentName = studentNameField.getText();
            if (!studentName.isEmpty()) {
                saveAttendance(studentName, status);
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a student name.");
            }
        }

        private void saveAttendance(String studentName, String status) {
            try (Connection connection = DatabaseConnection.getConnection()) {
                String query = "INSERT INTO attendance (student_name, date, status) VALUES (?, CURDATE(), ?)";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, studentName);
                    statement.setString(2, status);
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(frame, "Attendance marked as " + status);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error saving attendance.");
            }
        }
    }
}


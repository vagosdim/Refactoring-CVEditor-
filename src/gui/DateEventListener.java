package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class DateEventListener implements ActionListener{
	
	private JButton btnDate;
	private String type;
	private ArrayList<String> educationAndTraining;
	private ArrayList<String> furtherCourses;
	private ArrayList<String> dateList;
	private ArrayList<String> careerSummary;
	private CvCommonFunctions cvCommonFunctions = new CvCommonFunctions();
	private DateFunctionsHandler dateFunctionsHandler;
	private InitWidgets initWidgets = new InitWidgets();
	
	public DateEventListener(JButton btnDate, String type,
			ArrayList<String> educationAndTraining,
			ArrayList<String> furtherCourses,
			ArrayList<String> dateList,
			ArrayList<String> careerSummary) {
		
		this.dateFunctionsHandler = new DateFunctionsHandler(dateList);
		this.educationAndTraining = educationAndTraining;
		this.furtherCourses = furtherCourses;
		this.careerSummary = careerSummary;
		this.dateList = dateList;
		this.btnDate = btnDate;
		this.type = type;
	}

	public void actionPerformed(ActionEvent e) {

		JFrame dateFrame = cvCommonFunctions.createFrame(type, 500, 250, 600, 400, false);
		cvCommonFunctions.onExit(dateFrame, true);
		
		JPanel panel = (JPanel) dateFrame.getContentPane();
		JTextField textField1 = initWidgets.createJtextField(panel,"", 117, 237, 319, 20, 10);
		JTextField textField2 = initWidgets.createJtextField(panel,"", 117, 268, 319, 20, 10);
		JTextField textField3 = initWidgets.createJtextField(panel,"", 117, 299, 319, 20, 10);		
		JTextField dateField = initWidgets.createJtextField(panel,"", 117, 330, 319, 20, 10);
		
		JLabel label1 = initWidgets.createJLabel(panel, "New Label", 10, 240, 97, 14);
		JLabel label2 = initWidgets.createJLabel(panel, "New Label", 10, 271, 97, 14);
		JLabel label3 = initWidgets.createJLabel(panel, "New Label", 10, 302, 97, 14);
		JLabel label4 = initWidgets.createJLabel(panel, "Date", 10, 333, 97, 14);
		
		if (type.equals("Career Summary")){
			label1.setVisible(false);
			textField1.setVisible(false);
			label2.setText("Company Name");
			label3.setText("Job Title");	
		} else if (type.equals("Education And Training")){
			label1.setText("Qualification");
			label2.setText("Establishment");
			label3.setText("Location");	
		} else if (type.equals("Further Courses")){
			label1.setText("Course");
			label2.setText("Establishment");
			label3.setText("Location");	
		}
		
		JScrollPane listScroll = new JScrollPane();		
		DefaultListModel<String> mainList = new DefaultListModel<>();
		
		JList<String> list = new JList<String>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.setVisibleRowCount(2);
		list.setBounds(10, 11, 564, 201);
		
		dateFrame.getContentPane().add(list);		
		listScroll.setViewportView(list);
		listScroll.setBounds(10, 11, 564, 201);		
		dateFrame.getContentPane().add(listScroll);
		
		JButton remove = initWidgets.createButton(panel, "Remove Selected", 446, 236, 128, 35);
		JButton edit = initWidgets.createButton(panel, "Edit Selected", 446, 275, 128, 35);
		JButton add = initWidgets.createButton(panel, "Add", 446, 314, 128, 35);

		dateList.clear();
		cvCommonFunctions.sortList(type,careerSummary,furtherCourses,educationAndTraining);
		if (type.equals("Career Summary")){
			for (String temp : careerSummary){
				dateFunctionsHandler.fillDateList(temp, 2);
				mainList.addElement(temp);
			}
		} else if (type.equals("Education And Training")){
			for (String temp : educationAndTraining){
				dateFunctionsHandler.fillDateList(temp, 3);
				mainList.addElement(temp);
			}
		} else if (type.equals("Further Courses")){
			for (String temp : furtherCourses){
				dateFunctionsHandler.fillDateList(temp, 3);
				mainList.addElement(temp);
			}
		}
		list.setModel(mainList);
		
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ((!textField2.getText().equals("") && !textField2.getText().equals(null)) &&
						 (!textField3.getText().equals("") && !textField3.getText().equals(null)) && 
						  (!dateField.getText().equals("") && !dateField.getText().equals(null))){
					if (!dateFunctionsHandler.checkDateList(dateField)){
						JOptionPane.showMessageDialog(null, "You have entered a wrong Date.");
					}
					if (type.equals("Career Summary")){
						careerSummary.add((textField2.getText() + ", " + textField3.getText() + ", " + dateField.getText()));		
						mainList.addElement(textField2.getText() + ", " + textField3.getText() + ", " + dateField.getText());
						list.setModel(mainList);
					} else if (!textField1.getText().equals("") && !textField1.getText().equals(null)){
						if (type.equals("Education And Training")){
							educationAndTraining.add((textField1.getText() + ", " + textField2.getText() + ", " + textField3.getText() + ", " + dateField.getText()));		
							mainList.addElement(textField1.getText() + ", " + textField2.getText() + ", " + textField3.getText() + ", " + dateField.getText());
							list.setModel(mainList);
							
						} else if (type.equals("Further Courses")){
							
							furtherCourses.add((textField1.getText() + ", " + textField2.getText() + ", " + textField3.getText() + ", " + dateField.getText()));		
							mainList.addElement(textField1.getText() + ", " + textField2.getText() + ", " + textField3.getText() + ", " + dateField.getText());
							list.setModel(mainList);
						}
					} 
				} else {
					JOptionPane.showMessageDialog(null, "One or more Entries are missing.");
				}
			}
		});

		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex() != -1){
					if (type.equals("Career Summary")){
						careerSummary.remove(list.getSelectedValue());
					} else if (type.equals("Education And Training")){
						educationAndTraining.remove(list.getSelectedValue());
					} else if (type.equals("Further Courses")){
						furtherCourses.remove(list.getSelectedValue());
					}
					
					mainList.remove(list.getSelectedIndex());
					list.setModel(mainList);
					
				} else {
					JOptionPane.showMessageDialog(null, "Select an item from the list, then press remove to remove it.");
					
				}
			}
		});
		
		edit.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex() != -1){
					if (!dateFunctionsHandler.checkDateList(dateField)){
						JOptionPane.showMessageDialog(null, "You have entered a wrong Date.");
					}
					if ((!textField2.getText().equals("") && !textField2.getText().equals(null)) &&
							 (!textField3.getText().equals("") && !textField3.getText().equals(null)) && 
							  (!dateField.getText().equals("") && !dateField.getText().equals(null))){
						if (type.equals("Career Summary")){
							int i = 0;
							String tempList = list.getSelectedValue() ;
							for (String temp : careerSummary){
								if (tempList.equals(temp) ){
									careerSummary.set(i, textField2.getText()
									  + ", " + textField3.getText() + ", " + dateField.getText() );

									mainList.set(list.getSelectedIndex(), careerSummary.get(i));
									list.setModel(mainList);
								}
								i++;	
							}
						}
						if ( !textField1.getText().equals("")){
							if (type.equals("Education And Training")){
								int i = 0;
								String tempList = list.getSelectedValue() ;
								for (String temp : educationAndTraining){
									if (!temp.equals(null)){
										if (tempList.equals(temp) ){
											educationAndTraining.set(i, textField1.getText() + ", " + textField2.getText()
											  + ", " + textField3.getText() + ", " + dateField.getText() );
											
											mainList.set(list.getSelectedIndex(), educationAndTraining.get(i));
											list.setModel(mainList);
										}
										i++;	
									}
								}
							} else if (type.equals("Further Courses")){
								int i = 0;
								String tempList = list.getSelectedValue() ;
								for (String temp : furtherCourses){
									if (tempList.equals(temp) ){
										furtherCourses.set(i, textField1.getText() + ", " + textField2.getText()
										  + ", " + textField3.getText() + ", " + dateField.getText() );

										mainList.set(list.getSelectedIndex(), furtherCourses.get(i));
										list.setModel(mainList);
									}
									i++;	
								}
							}
						}
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "No list item selected to edit.");
				}
			}
		});
		
		MouseListener mouseListen = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (list.getSelectedIndex() != -1){
					String[] listSelectedSplit = list.getSelectedValue().split(", "); 
					if (type.equals("Career Summary")){
						textField2.setText(listSelectedSplit[0]);
						textField3.setText(listSelectedSplit[1]);
						dateField.setText(listSelectedSplit[2]);
					} else {
						textField1.setText(listSelectedSplit[0]);
						textField2.setText(listSelectedSplit[1]);
						textField3.setText(listSelectedSplit[2]);
						dateField.setText(listSelectedSplit[3]);
					}
				}
			}
		};
		list.addMouseListener(mouseListen);
		dateFrame.setVisible(true);
		
	}
}
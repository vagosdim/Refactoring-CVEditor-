package gui;
import javax.swing.*;

import data.manage.BulletList;
import data.manage.BulletListItem;
import data.manage.CVTemplate;
import data.manage.Database;
import data.manage.Section;
import output.manage.OutputSystemLatex;
import output.manage.OutputSystemTxt;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.Date;
import java.util.HashMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.Color;

public class CombinedJFrameWindow extends JDialog{
	
	static JFrame combinedJFrameWindow;
	private JFrame appMainWindow;
	
	static String[] generalInformation = new String[5];
	
	static ArrayList<String> educationAndTraining = new ArrayList<String>();
	static ArrayList<String> furtherCourses = new ArrayList<String>();
	static ArrayList<String> skillsAndExperience = new ArrayList<String>();	
	
	static ArrayList<String> dateList = new ArrayList<String>();
	
	static HashMap<String, String> professionalExperience =  new HashMap<String, String>();
	// Key String in format : " Company, Job Title, date(ddMMyyyy)"
	// Value String in format : " achiev1, achiev2, ... , paragraph"
	
	static String tempKey;
	static String tempStr;
	static String interests, additionalInformation, professionalProfile;
	static String oldDate, newDate;
	
	static CVTemplate cvtemplate;
	
	public CombinedJFrameWindow(final JFrame appMainWindow) {
		this.appMainWindow = appMainWindow;
		
		combinedJFrameWindow = new JFrame();
		combinedJFrameWindow.getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setLayout(null);
		combinedJFrameWindow.setTitle("Curriculum Vitae (CV) Editor");
		combinedJFrameWindow.setResizable(false);
		combinedJFrameWindow.setBounds(400, 150, 725, 350);
		combinedJFrameWindow.setLocationRelativeTo(null);
		combinedJFrameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		combinedJFrameWindow.getContentPane().setLayout(null);
		onExit(combinedJFrameWindow, false);
		
		JButton btnGeneralInformation = new JButton("General Information");
		btnGeneralInformation.setBounds(10, 23, 154, 51);
		combinedJFrameWindow.getContentPane().add(btnGeneralInformation);
		
		JButton btnProfessionalProfile = new JButton("Professional Profile");
		btnProfessionalProfile.setBounds(185, 23, 154, 51);
		combinedJFrameWindow.getContentPane().add(btnProfessionalProfile);
		
		JButton btnSkillsAndExperience = new JButton("Skills And Experience");
		btnSkillsAndExperience.setBounds(368, 23, 154, 51);
		combinedJFrameWindow.getContentPane().add(btnSkillsAndExperience);
		
		JButton btnProfessionalExperience = new JButton("Professional Experience");
		btnProfessionalExperience.setBounds(550, 23, 154, 51);
		combinedJFrameWindow.getContentPane().add(btnProfessionalExperience);
		
		JButton btnEducationAndTraining = new JButton("Education And Training");
		btnEducationAndTraining.setBounds(10, 109, 154, 57);
		combinedJFrameWindow.getContentPane().add(btnEducationAndTraining);
		
		JButton btnFurtherCourses = new JButton("Further Courses");
		btnFurtherCourses.setBounds(187, 109, 152, 57);
		combinedJFrameWindow.getContentPane().add(btnFurtherCourses);
		
		JButton btnAdditionalInformation = new JButton("Additional Information");
		btnAdditionalInformation.setBounds(368, 110, 154, 56);
		combinedJFrameWindow.getContentPane().add(btnAdditionalInformation);
		
		JButton btnInterests = new JButton("Interests");
		btnInterests.setBounds(550, 109, 152, 57);
		combinedJFrameWindow.getContentPane().add(btnInterests);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancel.setBounds(10, 279, 154, 31);
		combinedJFrameWindow.getContentPane().add(btnCancel);
		
		JCheckBox chckbxTxt = new JCheckBox("txt");
		chckbxTxt.setHorizontalAlignment(SwingConstants.TRAILING);
		chckbxTxt.setBounds(425, 258, 97, 23);
		combinedJFrameWindow.getContentPane().add(chckbxTxt);
		
		JCheckBox chckbxTex = new JCheckBox("tex");
		chckbxTex.setHorizontalAlignment(SwingConstants.TRAILING);
		chckbxTex.setBounds(425, 284, 97, 23);
		combinedJFrameWindow.getContentPane().add(chckbxTex);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSave.setBounds(552, 258, 152, 52);
		combinedJFrameWindow.getContentPane().add(btnSave);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 205, 719, 116);
		combinedJFrameWindow.getContentPane().add(panel);
		
		eventListenerGeneralInformation(btnGeneralInformation);
		eventListenerDate(btnEducationAndTraining, "Education And Training");
		eventListenerDate(btnFurtherCourses, "Further Courses");
		eventListener(btnProfessionalProfile,"Professional Profile");
		eventListener(btnAdditionalInformation, "Additional Information");
		eventListener(btnInterests,"Interests");
		eventListenerSkillsAndExperience(btnSkillsAndExperience, "Skills And Experience");
		eventListenerProfessionalExperience(btnProfessionalExperience, "Professional Experience");
		
		
		/* Listeners */
		
		btnCancel.addActionListener(new ActionListener() {		
			
			public void actionPerformed(ActionEvent e) {
				combinedJFrameWindow.setVisible(false);
				CVMainJFrameWindow.makeVisible();
				
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				try {
					if (chckbxTxt.isSelected() && chckbxTex.isSelected() ){
						saveCV(0);
					}else if (chckbxTxt.isSelected()){
						saveCV(1);
					}else if (chckbxTex.isSelected()){
						saveCV(2);
					}else {
						JOptionPane.showMessageDialog(null, "Please select a type to output first, then save.");
					}
						
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			
			}
		});
		
	}

	public static void onExit(JFrame frame, boolean dispose){
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent){
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "title" , 0) == JOptionPane.YES_OPTION){
					
					if (dispose){
						frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					} else {
						frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
					}
				} else {
					frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
			}
		});
	}
	
	
	public static void eventListenerProfessionalExperience(JButton button, String type){
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setBounds( 500, 250, 600, 451);
				frame.getContentPane().setLayout(null);
				onExit(frame, true);
				frame.setResizable(false);
				
				JTextField companyField = new JTextField();
				companyField.setBounds(128, 213, 319, 20);
				frame.getContentPane().add(companyField);
				companyField.setColumns(10);
				
				JTextField jobField  = new JTextField();
				jobField.setBounds(128, 244, 319, 20);
				frame.getContentPane().add(jobField);
				jobField.setColumns(10);
				
				JTextField dateField = new JTextField();
				dateField.setColumns(10);
				dateField.setBounds(128, 275, 319, 20);
				frame.getContentPane().add(dateField);
			
				JLabel companyLabel = new JLabel("Company");
				companyLabel.setBounds(10, 213, 112, 14);
				frame.getContentPane().add(companyLabel);
				
				JLabel jobTitleLabel = new JLabel("Job Title");
				jobTitleLabel.setBounds(10, 244, 112, 14);
				frame.getContentPane().add(jobTitleLabel);
					
				JLabel lblDate = new JLabel("Date");
				lblDate.setBounds(10, 275, 46, 14);
				frame.getContentPane().add(lblDate);
				
				JLabel listOfAchievementsLabel = new JLabel("List of Achievements: ");
				listOfAchievementsLabel.setBounds(10, 306, 120, 14);
				frame.getContentPane().add(listOfAchievementsLabel);
				
				JLabel separateLabel = new JLabel("(seperate  by ', ')");
				separateLabel.setBounds(10, 323, 100, 14);
				frame.getContentPane().add(separateLabel);
				JLabel separate2Label = new JLabel("(Comma + Space)");
				separate2Label.setBounds(10, 340, 100, 14);
				frame.getContentPane().add(separate2Label);
				
					
				JLabel paragraphLabel = new JLabel("Paragraph");
				paragraphLabel.setBounds(10, 366, 84, 14);
				frame.getContentPane().add(paragraphLabel);
				
				JTextArea listOfAchievementsArea= new JTextArea();
				listOfAchievementsArea.setBounds(128, 306, 318, 45);
				frame.getContentPane().add(listOfAchievementsArea);
				
				JTextArea paragraphArea = new JTextArea();
				paragraphArea.setBounds(129, 362, 318, 45);
				frame.getContentPane().add(paragraphArea);
				
				JScrollPane listScroll = new JScrollPane();
				
				DefaultListModel<String> mainList = new DefaultListModel<>();
				
				JList<String> list = new JList<String>();
				list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				list.setSelectedIndex(0);
				list.setVisibleRowCount(2);
				list.setBounds(10, 11, 564, 191);
				
				frame.getContentPane().add(list);
				
				listScroll.setViewportView(list);
				listScroll.setBounds(10, 11, 564, 201);
				
				frame.getContentPane().add(listScroll);
				
				for (String key  : professionalExperience.keySet() ) {
					fillDateList(key, 2);
					mainList.addElement(key + ", " + professionalExperience.get(key));
				}
	
				list.setModel(mainList);
				
				
				JButton remove = new JButton("Remove Selected");
				remove.setBounds(462, 212, 112, 60);
				frame.getContentPane().add(remove);
				
				JButton edit = new JButton("Edit Selected");
				edit.setBounds(462, 279, 112, 60);
				frame.getContentPane().add(edit);
				
				JButton add = new JButton("Add");
				add.setBounds(462, 345, 112, 60);
				frame.getContentPane().add(add);
				
				add.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						tempKey = (companyField.getText() + ", " + jobField.getText() + ", " + dateField.getText());
						tempStr = ( listOfAchievementsArea.getText() + ", " + paragraphArea.getText() );
						
						if (companyField.getText().equals(" ") || jobField.getText().equals(" ") || dateField.getText().equals(" ") 
								|| listOfAchievementsArea.getText().equals(" ") || paragraphArea.getText().equals(" ") ){
							JOptionPane.showMessageDialog(null, "One or more Entries are missing.");
						} else if (professionalExperience.containsKey(tempKey)) {
							JOptionPane.showMessageDialog(null, "Please change one of the values: Company, Job Title, Date");
						} else {
							if (!checkDateList(dateField)){
								JOptionPane.showMessageDialog(null, "You have entered a wrong Date.");
							}
							
							mainList.addElement(companyField.getText() + ", " + jobField.getText() + ", " + dateField.getText()
								+ ", " + listOfAchievementsArea.getText()  + ", " + paragraphArea.getText() );	
							
							professionalExperience.put(tempKey , tempStr);
							list.setModel(mainList);
						}
					}
				});
				
				remove.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String tempListItem[];
						if (list.getSelectedIndex() != -1){
							tempListItem = list.getSelectedValue().split(", ");
							tempKey = (tempListItem[0] + ", " + tempListItem[1] + ", " + tempListItem[2]);
							professionalExperience.remove(tempKey);
						
							
							
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
							String[] tempArray = list.getSelectedValue().split(", ");
							String tempListKey = (tempArray[0] + ", " + tempArray[1] + ", " + tempArray[2]);
							tempKey = (companyField.getText() + ", " + jobField.getText() + ", " + dateField.getText());
							tempStr = (listOfAchievementsArea.getText() + ", " + paragraphArea.getText());
							
							if (companyField.getText().equals(" ") || jobField.getText().equals(" ") || dateField.getText().equals(" ") 
									|| listOfAchievementsArea.getText().equals(" ") || paragraphArea.getText().equals(" ") ){
								JOptionPane.showMessageDialog(null, "One or more Entries are missing.");
							} else {
								if (!checkDateList(dateField)){
									JOptionPane.showMessageDialog(null, "You have entered a wrong Date.");
								}
								
								if (tempListKey.equals(tempKey)){
									professionalExperience.put(tempKey, tempStr);
								} else {
									professionalExperience.remove(tempListKey);
									professionalExperience.put(tempKey, tempStr);
								}
								mainList.set(list.getSelectedIndex(), (tempKey + ", " + tempStr) );
								list.setModel(mainList);
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
							
							tempStr = listSelectedSplit[3];
							
							for (int i = 0; i < listSelectedSplit.length -1 ; i++){
								if (i > 3){
									tempStr = (tempStr + ", " + listSelectedSplit[i]);
								}
							}
							companyField.setText(listSelectedSplit[0]);
							jobField.setText(listSelectedSplit[1]);
							dateField.setText(listSelectedSplit[2]);
							listOfAchievementsArea.setText(tempStr);
							paragraphArea.setText(listSelectedSplit[listSelectedSplit.length-1]);
						}
					}
				};
				
				list.addMouseListener(mouseListen);
				
				frame.setVisible(true);
			}
		});
	}
	
	public static void eventListenerSkillsAndExperience(JButton button, String type){
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = createFrame(type, 500, 250, 600, 400, false);
				onExit(frame, true);
				
				JTextField field = new JTextField();
				field.setBounds(10, 280, 319, 20);
				frame.getContentPane().add(field);
				field.setColumns(10);
				
				JTextField skills = new JTextField();
				skills.setBounds(10, 330, 319, 20);
				frame.getContentPane().add(skills);
				skills.setColumns(10);
			
				JLabel fieldLabel = new JLabel("Field");
				fieldLabel.setBounds(10, 260, 319, 14);
				frame.getContentPane().add(fieldLabel);
				
				JLabel skillsLabel = new JLabel("Skills And Experience");
				skillsLabel.setBounds(10, 310, 319, 14);
				frame.getContentPane().add(skillsLabel);
				
				JButton remove = new JButton("Remove Selected");
				remove.setBounds(446, 236, 128, 35);
				frame.getContentPane().add(remove);
				
				JButton edit = new JButton("Edit Selected");
				edit.setBounds(446, 275, 128, 35);
				frame.getContentPane().add(edit);
				
				JButton add = new JButton("Add");
				add.setBounds(446, 314, 128, 35);
				frame.getContentPane().add(add);
			
				JScrollPane listScroll = new JScrollPane();
				
				DefaultListModel<String> mainList = new DefaultListModel<>();
				
				JList<String> list = new JList<String>();
				list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				list.setSelectedIndex(0);
				list.setVisibleRowCount(2);
				list.setBounds(10, 11, 564, 201);
				
				frame.getContentPane().add(list);
				
				listScroll.setViewportView(list);
				listScroll.setBounds(10, 11, 564, 201);
				
				frame.getContentPane().add(listScroll);
				
				list.setModel(mainList);
				
				dateList.clear();
				for (String temp : skillsAndExperience){
					mainList.addElement(temp);
				}
				
				add.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (field.getText().equals(" ") || skills.getText().equals(" ")  ){
							JOptionPane.showMessageDialog(null, "One or more Entries are missing.");
						}else {
							mainList.addElement(field.getText() + ", " + skills.getText());
							skillsAndExperience.add(field.getText() + ", " + skills.getText());
							list.setModel(mainList);
						}
				
						
					}
				});
				
				remove.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (list.getSelectedIndex() != -1){
						
							skillsAndExperience.remove(list.getSelectedValue());
						
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

						
							int i = 0;
							String tempList = list.getSelectedValue() ;
							if (field.getText().equals(" ") || skills.getText().equals(" ")  ){
								JOptionPane.showMessageDialog(null, "One or more Entries are missing.");
							}else {
								for (String temp : skillsAndExperience){
									if (!temp.equals(null)){
										if (tempList.equals(temp) ){
											skillsAndExperience.set(i, field.getText() + ", " + skills.getText() );
											
											mainList.set(list.getSelectedIndex(), skillsAndExperience.get(i));
											list.setModel(mainList);
										}
										i++;	
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
							field.setText(listSelectedSplit[0]);
							skills.setText(listSelectedSplit[1]);
							
						}
					}
				};
				list.addMouseListener(mouseListen);
				
				frame.setVisible(true);
			}
		});
	}
	
	public static void eventListenerDate(JButton btnDate, String type){
		btnDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFrame dateFrame = createFrame(type, 500, 250, 600, 400, false);
				onExit(dateFrame, true);
				
				JTextField textField1 = new JTextField();
				textField1.setBounds(117, 237, 319, 20);
				dateFrame.getContentPane().add(textField1);
				textField1.setColumns(10);
				
				JTextField textField2 = new JTextField();
				textField2.setBounds(117, 268, 319, 20);
				dateFrame.getContentPane().add(textField2);
				textField2.setColumns(10);
				
				JTextField textField3 = new JTextField();
				textField3.setBounds(117, 299, 319, 20);
				dateFrame.getContentPane().add(textField3);
				textField3.setColumns(10);
				
				JTextField dateField = new JTextField();
				dateField.setBounds(117, 330, 319, 20);
				dateFrame.getContentPane().add(dateField);
				dateField.setColumns(10);
				
				JLabel label1 = new JLabel("New label");
				label1.setBounds(10, 240, 97, 14);
				dateFrame.getContentPane().add(label1);
				
				JLabel label2 = new JLabel("Establishment");
				label2.setBounds(10, 271, 97, 14);
				dateFrame.getContentPane().add(label2);
				
				JLabel label3 = new JLabel("Location");
				label3.setBounds(10, 302, 97, 14);
				dateFrame.getContentPane().add(label3);
				
				JLabel label4 = new JLabel("Date");
				label4.setBounds(10, 333, 97, 14);
				dateFrame.getContentPane().add(label4);
				
				if (type.equals("Education And Training")){
					label1.setText("Qualification");

				} else if (type.equals("Further Courses")){
					label1.setText("Course");

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

				JButton remove = new JButton("Remove Selected");
				remove.setBounds(446, 236, 128, 35);
				dateFrame.getContentPane().add(remove);
				
				JButton edit = new JButton("Edit Selected");
				edit.setBounds(446, 275, 128, 35);
				dateFrame.getContentPane().add(edit);
				
				JButton add = new JButton("Add");
				add.setBounds(446, 314, 128, 35);
				dateFrame.getContentPane().add(add);
			
				
				dateList.clear();
				sortList(type);
				if (type.equals("Education And Training")){
					for (String temp : educationAndTraining){
						mainList.addElement(temp);
						fillDateList(temp, 3);
					}
				} else if (type.equals("Further Courses")){
					for (String temp : furtherCourses){
						mainList.addElement(temp);
						fillDateList(temp, 3);
					}
				}
				list.setModel(mainList);
			
				add.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if ((!textField1.getText().equals("")) &&
								 (!textField2.getText().equals("")) && 
								  (!textField3.getText().equals("")) &&
								   (!dateField.getText().equals(""))){
							
							if (!checkDateList(dateField)){
								JOptionPane.showMessageDialog(null, "You have entered a wrong Date.");
							}
							
							
							if (type.equals("Education And Training")){
								educationAndTraining.add((textField1.getText() + ", " + textField2.getText()
								  + ", " + textField3.getText() + ", " + dateField.getText()));		
								mainList.addElement(textField1.getText() + ", " + textField2.getText() + ", " + 
								  textField3.getText() + ", " + dateField.getText());
								list.setModel(mainList);
								
							} else if (type.equals("Further Courses")){
								furtherCourses.add((textField1.getText() + ", " + textField2.getText() + ", " 
								  + textField3.getText() + ", " + dateField.getText()));		
								mainList.addElement(textField1.getText() + ", " + textField2.getText() + ", "
								  + textField3.getText() + ", " + dateField.getText());
								list.setModel(mainList);
							
							} 
						} else {
							JOptionPane.showMessageDialog(null, "One or more Entries are missing.");
						}
					}
					
				});
	
				remove.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (list.getSelectedIndex() != -1){
							 if (type.equals("Education And Training")){
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
							if (!textField1.getText().equals(" ") && !textField2.getText().equals(" ") 
									&& !textField3.getText().equals(" ") &&!dateField.getText().equals(" ") ){
								
								if (!checkDateList(dateField)){
									JOptionPane.showMessageDialog(null, "You have entered a wrong Date.");
								}
								
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
								}else{
									JOptionPane.showMessageDialog(null, "Don't leave an input field blank.");
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
							textField1.setText(listSelectedSplit[0]);
							textField2.setText(listSelectedSplit[1]);
							textField3.setText(listSelectedSplit[2]);
							dateField.setText(listSelectedSplit[3]);
							
						}
					}
				};
				list.addMouseListener(mouseListen);
				
				dateFrame.setVisible(true);
			}
		});
		
	}

	public static void eventListener(JButton button, String type){
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = createFrame(type, 500, 250, 600, 400, false);
				onExit(frame, true);
				
				JLabel label = new JLabel("Input your " + type + " here.");
				label.setBounds(10, 10, 564, 20);
				frame.getContentPane().add(label);
				
				JScrollPane scrollArea = new JScrollPane();
				
				JTextArea textArea = new JTextArea();
				textArea.setBounds(10, 40, 564, 250);
				frame.getContentPane().add(textArea);
				
				if (type.equals("Interests")){
					textArea.setText(interests);
				} else if (type.equals("Professional Profile")){
					textArea.setText(professionalProfile);
				} else if (type.equals("Additional Information")) {
					textArea.setText(additionalInformation);
				}
				
				
				scrollArea.setViewportView(textArea);
				scrollArea.setBounds(10, 40, 564, 250);
				frame.getContentPane().add(scrollArea);

				JButton saveInfo = new JButton("Save " + type);
				saveInfo.setBounds(420, 300, 153, 60);
				frame.getContentPane().add(saveInfo);
			
				saveInfo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						if (type.equals("Interests")){
							interests = textArea.getText();
						} else if (type.equals("Professional Profile")) {
							professionalProfile = textArea.getText();
						} else if (type.equals("Additional Information")){
							additionalInformation = textArea.getText();
						}
					}
				});
				
				
				frame.setResizable(false);
				frame.setVisible(true);
			}
		});
	}

	public static void eventListenerGeneralInformation(JButton btnGeneralInformation){
		btnGeneralInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFrame generalInformationWindow = createFrame("General Information.", 600, 250, 400, 400, false);
				generalInformationWindow.getContentPane().setLayout(null);
				onExit(generalInformationWindow, true);
				
				JLabel labelName = createLabel("Name : ", 10, 10, 370, 20, 0);
				generalInformationWindow.getContentPane().add(labelName);
								
				JTextField  nameField = createTextField(10, 30, 370, 20);		
				generalInformationWindow.getContentPane().add(nameField);
				
				JLabel labelAddress = createLabel("Address : ", 10, 60, 370, 20, 0);
				generalInformationWindow.getContentPane().add(labelAddress);
				
				JTextField  addressField = createTextField(10, 80, 370, 20);	
				generalInformationWindow.getContentPane().add(addressField);
				
				JLabel labelHome = createLabel("Home Telephone : ", 10, 110, 370, 20, 0);
				generalInformationWindow.getContentPane().add(labelHome);
				
				JTextField  homeField = createTextField(10, 130, 370, 20);
				generalInformationWindow.getContentPane().add(homeField);
				
				JLabel labelMobile = createLabel("Mobile : ", 10, 160, 370, 20, 0);
				generalInformationWindow.getContentPane().add(labelMobile);
				
				JTextField  mobileField = createTextField(10, 180, 370, 20);	
				generalInformationWindow.getContentPane().add(mobileField);
				
				JLabel labelEmail = createLabel("Email : ", 10, 210, 370, 20, 0);
				generalInformationWindow.getContentPane().add(labelEmail);
				
				JTextField  emailField = createTextField(10, 230, 370, 20);
				generalInformationWindow.getContentPane().add(emailField);
				
				
				nameField.setText(generalInformation[0]);
				addressField.setText(generalInformation[1]);
				homeField.setText(generalInformation[2]);
				mobileField.setText(generalInformation[3]);
				emailField.setText(generalInformation[4]);
				
				JButton save = new JButton("Save");
				save.setBounds(300, 300, 80 , 50);
				generalInformationWindow.getContentPane().add(save);
				
				save.addActionListener(new ActionListener (){
					public void actionPerformed(ActionEvent e) {
						generalInformation[0] = nameField.getText();
						generalInformation[1] = addressField.getText();
						generalInformation[2] = homeField.getText();
						generalInformation[3] = mobileField.getText();
						generalInformation[4] = emailField.getText();
					}
					
				});
				
				generalInformationWindow.setVisible(true);
			}
		});
		
	}

	public static JLabel createLabel(String labelNam, int x, int y, int width, int height, int fontPosition){
		JLabel label = new JLabel(labelNam);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(x,y,width,height);
		if (fontPosition == 0) {
			label.setHorizontalAlignment(SwingConstants.LEFT);
		}else if (fontPosition == 1) {
			label.setHorizontalAlignment(SwingConstants.CENTER);
		}else {
			label.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		
		return label;
	}
	
	public static JFrame createFrame(String windowName, int x, int y, int width, int height, boolean exitOnClose){
		JFrame window = new JFrame(windowName);
		window.setBounds(x, y, width, height);
		window.setResizable(false);
		if (exitOnClose){
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}else {
			window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		window.getContentPane().setLayout(null);

		return window;
	}
	
	public static void createFunctDescription(JFrame cvEditor, JLabel labelFunctionalDescription){
		labelFunctionalDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelFunctionalDescription.setBounds(10, 11, 764, 14);
		labelFunctionalDescription.setHorizontalAlignment(SwingConstants.CENTER);
		cvEditor.getContentPane().add(labelFunctionalDescription);
	}	
	
	public static JTextField createTextField(int x, int y, int width, int height){
		JTextField generalField = new JTextField();
		generalField.setBounds(x, y, width, height);
		generalField.setColumns(10);

		return generalField;
	}
	
	public static void sortList(String type){
		
		if (type.equals("Further Courses")){
			Collections.sort(furtherCourses);
		}else if (type.equals("Education And Training")){
			Collections.sort(educationAndTraining);
		}
		
	}
	
	public static void fillDateList(String date, int position){
		String[] temp = date.split(", ");
		dateList.add(temp[position]);
	}
	
	public static boolean checkDateList(JTextField dateField){
		boolean check = true;
		for (String tempDate: dateList){
			try {
				check = checkDate(tempDate, dateField.getText());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		dateList.add(dateField.getText());	
		return check;
	}
	
	public static boolean checkDate(String oldDate, String newDate) throws ParseException {
		Date date1, date2;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		
		date1 = sdf.parse(oldDate);
		date2 = sdf.parse(newDate);
		
		if(date1.compareTo(date2)>0){    //check if earlier first
			return false;
		}
		return true;
	}
	
	public static Date makeDate(String dateStr) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date = sdf.parse(dateStr);
		
		return date;
	}
	
	public static void makeVisible(CVTemplate inputcvtemplate){
		cvtemplate = inputcvtemplate;
		if(cvtemplate.getApplicantName()!=null){
			initialize();
		}
		combinedJFrameWindow.setVisible(true);
		
	}
	
	
	public static void initialize(){
		
		
		generalInformation[0]=cvtemplate.getApplicantName();
		generalInformation[1]=cvtemplate.getApplicantAddress();
		generalInformation[2]=cvtemplate.getApplicantHomeTelephone();
		generalInformation[3]=cvtemplate.getApplicantWorkTelephone();
		generalInformation[4]=cvtemplate.getApplicantEmail();
		
		
		for (int i=cvtemplate.getNumberOfSectionObj()-1; i>=0; i--){
	    	
	    	if(cvtemplate.getSectionObjTitle(i).equals("Professional Profile:")){
	    		professionalProfile = cvtemplate.getSectionObj(i).getParagraph(0).getContents();
	    		
	    	}else if(cvtemplate.getSectionObjTitle(i).equals("Additional Information:")){
	    		additionalInformation = cvtemplate.getSectionObj(i).getParagraph(0).getContents();
	    		
	    	}else if(cvtemplate.getSectionObjTitle(i).equals("Interests:")){
	    		interests = cvtemplate.getSectionObj(i).getParagraph(0).getContents();
	    		
	    	}else if(cvtemplate.getSectionObjTitle(i).equals("Education and Training:")){
	    		int j=0;
	    		for (j=0;j<cvtemplate.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
	    			
	    			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    			String reportDate = sdf.format(cvtemplate.getSectionObj(i).getBulletListItem(j).getDate());
	    			educationAndTraining.add(cvtemplate.getSectionObj(i).getBulletListItem(j).getContents()+", "+reportDate);
	    				
	    		}
	    		
	    	}else if(cvtemplate.getSectionObjTitle(i).equals("Further Courses:")){
    			int j=0;
    			for (j=0;j<cvtemplate.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
 
    				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    				String reportDate = sdf.format(cvtemplate.getSectionObj(i).getBulletListItem(j).getDate());
    				furtherCourses.add(cvtemplate.getSectionObj(i).getBulletListItem(j).getContents()+", "+reportDate);
    				
    			}
    		
	    	}else if(cvtemplate.getSectionObjTitle(i).equals("Skills and Experience:")){
	
	    			for (int j=0;j<cvtemplate.getSectionObj(i).getNumberOfBulletLists();j++){
	    				
	    				for(int k=0; k<cvtemplate.getSectionObj(i).getBulletList(j).getNumberOfItems();k ++){
	    					skillsAndExperience.add(cvtemplate.getSectionObj(i).getBulletList(j).getTitle()+", "+
	    							cvtemplate.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents());
	    				}
	    			}
	    	}else if(cvtemplate.getSectionObjTitle(i).equals("Professional Experience:")){
	    		String temp = " ";
	    		for (int j=0;j<cvtemplate.getSectionObj(i).getNumberOfBulletLists();j++){
	    			tempKey =  cvtemplate.getSectionObj(i).getBulletList(j).getTitle().substring(2);
	    			
    				for(int k=0; k<cvtemplate.getSectionObj(i).getBulletList(j).getNumberOfItems();k ++){
    					if ( k == 0){
    						// Paragraph imported from file
    						temp= cvtemplate.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents();
    					} else if (k == 2) {
    						// First Achievement from file
    						tempStr = cvtemplate.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents();
						} else if (k > 2) {
							// Rest of Achievements from file
							tempStr = (tempStr + ", " + cvtemplate.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents() );	
						}
				
    				}
	    			tempStr = (tempStr + ", " + temp); 
	    			professionalExperience.put(tempKey, tempStr);	
    			}
	    	}
	    	
	    }
		
	}
	
	public static void saveCV(int output) throws ParseException{
		
		
		CVTemplate cvToSave = new CVTemplate(generalInformation[0], generalInformation[1], generalInformation[2], 
				  generalInformation[3], generalInformation[4]);

		
		for (int sectionCounter=0;sectionCounter<7;sectionCounter++) {	
			if (sectionCounter==0){
				cvToSave.addSectionObj(sectionCounter , new Section("Professional Profile:"));
				cvToSave.getSectionObj(sectionCounter).addParagraph(professionalProfile);
			
			}else if(sectionCounter==1){		
				cvToSave.addSectionObj(sectionCounter , new Section("Additional Information:"));
				cvToSave.getSectionObj(sectionCounter).addParagraph(additionalInformation);
		
			}else if(sectionCounter==2){
				cvToSave.addSectionObj(sectionCounter , new Section("Interests:"));
				cvToSave.getSectionObj(sectionCounter).addParagraph(interests);
				
			}else if(sectionCounter==3){
				
				cvToSave.addSectionObj(sectionCounter , new Section("Education and Training:"));
				
				for (String count : educationAndTraining){
					String[] temp = count.split(", ");
					String content = temp[0] + ", " + temp[1] + ", " + temp[2];
					Date date = makeDate(temp[3]);
					
					cvToSave.getSectionObj(sectionCounter).addBulletListItem(new BulletListItem(date, content));
				}
			}else if(sectionCounter==4){
				
				cvToSave.addSectionObj(sectionCounter , new Section("Further Courses:"));
				
				for (String count : furtherCourses){
					String[] temp = count.split(", ");
					String content = temp[0] + ", " + temp[1] + ", " + temp[2];
					Date date = makeDate(temp[3]);
					
					cvToSave.getSectionObj(sectionCounter).addBulletListItem(new BulletListItem(date, content));
				}
			
			}else if(sectionCounter==5){
			
				cvToSave.addSectionObj(sectionCounter , new Section("Professional Experience:"));
				Date date;
				String withoutDate, paragraph;
				String listOfAchievements;
				
				int listCounter = 0;
				int i = 0;
				
				for (String key : professionalExperience.keySet()){
					String[] valueStr = professionalExperience.get(key).split(", ");
					String[] keyStr = key.split(", ");
					date = makeDate(keyStr[2]);					
					withoutDate = keyStr[0] + ", " + keyStr[1];
					paragraph = valueStr[valueStr.length - 1];
					
					cvToSave.getSectionObj(sectionCounter).addBulletListItem(new BulletListItem(date, withoutDate));
					cvToSave.getSectionObj(sectionCounter).addBulletList(listCounter, new BulletList(key));
					
					
					cvToSave.getSectionObj(sectionCounter).getBulletList(listCounter).addBulletListItem(new BulletListItem(paragraph));
					
					
					listOfAchievements = ("List Of Achievements" + Integer.toString(i));
					
					cvToSave.getSectionObj(sectionCounter).getBulletList(listCounter).addBulletListItem(new BulletListItem(listOfAchievements));
					
					
					for (String achievement: valueStr){
						if ( !achievement.equals(paragraph)){
							cvToSave.getSectionObj(sectionCounter).getBulletList(listCounter).addBulletListItem(new BulletListItem(achievement));
							
						}
					}
					
					listCounter++;
					i++;
				}
				
			}else if(sectionCounter==6){
				int listCounter = 0;
				
				cvToSave.addSectionObj(sectionCounter , new Section("Skills and Experience:"));
				
				String[] str = skillsAndExperience.get(0).split(", ");
				String[] tempStr = skillsAndExperience.get(0).split(", ");
				String temp = " ";
				for (String str1 : skillsAndExperience){					
					str = str1.split(", ");
					if (temp.equals(str[0])){
						continue;
					}
					cvToSave.getSectionObj(sectionCounter).addBulletList(listCounter, new BulletList(str[0]));
					for (String str2 : skillsAndExperience){
						tempStr = str2.split(", ");
						if ( str[0].equals(tempStr[0])){
							cvToSave.getSectionObj(sectionCounter).getBulletList(listCounter).addBulletListItem(new BulletListItem(tempStr[1]));
						}
						
					temp = str[0];
					
					}
					
					listCounter++;
				}
			
			}
		}

		
		
		Database.addCVtemplateToList(cvToSave); 
		if (output==0){
			OutputSystemTxt.saveApplicantInfoToTxt("outputfiles", Database.getCVtemplateArrayListSize()-1);
			OutputSystemLatex.saveApplicantInfoToLatex("outputfiles", Database.getCVtemplateArrayListSize()-1);
		}else if (output==1){
			OutputSystemTxt.saveApplicantInfoToTxt("outputfiles", Database.getCVtemplateArrayListSize()-1);
		}else if (output==2){
			OutputSystemLatex.saveApplicantInfoToLatex("outputfiles", Database.getCVtemplateArrayListSize()-1);
		}
		

	}
	
	
}

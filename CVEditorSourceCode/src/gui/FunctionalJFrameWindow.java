package gui;
import javax.swing.*;

import dataManagePackage.BulletList;
import dataManagePackage.BulletListItem;
import dataManagePackage.CVTemplate;
import dataManagePackage.Database;
import dataManagePackage.Section;
import outputManagePackage.OutputSystemLatex;
import outputManagePackage.OutputSystemTxt;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.Color;

public class FunctionalJFrameWindow extends JDialog{
	
	static JFrame FunctionalJFrameWindow;
	private JFrame appMainWindow;
	
	static String[] generalInformation = new String[5];
	static ArrayList<String> careerSummary = new ArrayList<String>();
	static ArrayList<String> educationAndTraining = new ArrayList<String>();
	static ArrayList<String> furtherCourses = new ArrayList<String>();
	static ArrayList<String> skillsAndExperience = new ArrayList<String>();
	static ArrayList<String> dateList = new ArrayList<String>();
	
	static String interests, additionalInformation, professionalProfile;
	static String temp,oldDate, newDate;
	
	static CVTemplate cvtemplate;
	
	public FunctionalJFrameWindow(final JFrame appMainWindow) {
		this.appMainWindow = appMainWindow;
		
		
		
		FunctionalJFrameWindow = new JFrame();
		FunctionalJFrameWindow.getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setLayout(null);
		FunctionalJFrameWindow.setTitle("Curriculum Vitae (CV) Editor");
		FunctionalJFrameWindow.setResizable(false);
		FunctionalJFrameWindow.setBounds(400, 150, 725, 350);
		FunctionalJFrameWindow.setLocationRelativeTo(null);
		FunctionalJFrameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FunctionalJFrameWindow.getContentPane().setLayout(null);
		onExit(FunctionalJFrameWindow, false);
		
		JButton btnGeneralInformation = new JButton("General Information");
		btnGeneralInformation.setBounds(10, 23, 154, 51);
		FunctionalJFrameWindow.getContentPane().add(btnGeneralInformation);
		
		JButton btnProfessionalProfile = new JButton("Professional Profile");
		btnProfessionalProfile.setBounds(185, 23, 154, 51);
		FunctionalJFrameWindow.getContentPane().add(btnProfessionalProfile);
		
		JButton btnSkillsAndExperience = new JButton("Skills And Experience");
		btnSkillsAndExperience.setBounds(368, 23, 154, 51);
		FunctionalJFrameWindow.getContentPane().add(btnSkillsAndExperience);
		
		JButton btnCareerSummary = new JButton("Career Summary");
		btnCareerSummary.setBounds(550, 23, 154, 51);
		FunctionalJFrameWindow.getContentPane().add(btnCareerSummary);
		
		JButton btnEducationAndTraining = new JButton("Education And Training");
		btnEducationAndTraining.setBounds(10, 109, 154, 57);
		FunctionalJFrameWindow.getContentPane().add(btnEducationAndTraining);
		
		JButton btnFurtherCourses = new JButton("Further Courses");
		btnFurtherCourses.setBounds(187, 109, 152, 57);
		FunctionalJFrameWindow.getContentPane().add(btnFurtherCourses);
		
		JButton btnAdditionalInformation = new JButton("Additional Information");
		btnAdditionalInformation.setBounds(368, 110, 154, 56);
		FunctionalJFrameWindow.getContentPane().add(btnAdditionalInformation);
		
		JButton btnInterests = new JButton("Interests");
		btnInterests.setBounds(550, 109, 152, 57);
		FunctionalJFrameWindow.getContentPane().add(btnInterests);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancel.setBounds(10, 279, 154, 31);
		FunctionalJFrameWindow.getContentPane().add(btnCancel);
		
		JCheckBox chckbxTxt = new JCheckBox("txt");
		chckbxTxt.setHorizontalAlignment(SwingConstants.TRAILING);
		chckbxTxt.setBounds(425, 258, 97, 23);
		FunctionalJFrameWindow.getContentPane().add(chckbxTxt);
		
		JCheckBox chckbxTex = new JCheckBox("tex");
		chckbxTex.setHorizontalAlignment(SwingConstants.TRAILING);
		chckbxTex.setBounds(425, 284, 97, 23);
		FunctionalJFrameWindow.getContentPane().add(chckbxTex);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSave.setBounds(552, 258, 152, 52);
		FunctionalJFrameWindow.getContentPane().add(btnSave);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 205, 719, 116);
		FunctionalJFrameWindow.getContentPane().add(panel);
		
		eventListenerGeneralInformation(btnGeneralInformation);
		eventListenerDate(btnCareerSummary, "Career Summary");
		eventListenerDate(btnEducationAndTraining, "Education And Training");
		eventListenerDate(btnFurtherCourses, "Further Courses");
		eventListener(btnAdditionalInformation, "Additional Information");
		eventListener(btnInterests,"Interests");
		eventListener(btnProfessionalProfile,"Professional Profile");
		eventListenerSkillsAndExperience(btnSkillsAndExperience, "Skills And Experience");
		
		/* Listeners */
		
		btnCancel.addActionListener(new ActionListener() {		
			
			public void actionPerformed(ActionEvent e) {
				FunctionalJFrameWindow.setVisible(false);
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
	
	public static void eventListenerDate(JButton btnDate, String type){
		btnDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFrame dateFrame = createFrame(type, 500, 250, 600, 400, false);
				onExit(dateFrame, true);
				
				JTextField textField_1 = new JTextField();
				textField_1.setBounds(117, 237, 319, 20);
				dateFrame.getContentPane().add(textField_1);
				textField_1.setColumns(10);
				
				JTextField textField_2 = new JTextField();
				textField_2.setBounds(117, 268, 319, 20);
				dateFrame.getContentPane().add(textField_2);
				textField_2.setColumns(10);
				
				JTextField textField_3 = new JTextField();
				textField_3.setBounds(117, 299, 319, 20);
				dateFrame.getContentPane().add(textField_3);
				textField_3.setColumns(10);
				
				JTextField dateField = new JTextField();
				dateField.setBounds(117, 330, 319, 20);
				dateFrame.getContentPane().add(dateField);
				dateField.setColumns(10);
				
				JLabel label_1 = new JLabel("New label");
				label_1.setBounds(10, 240, 97, 14);
				dateFrame.getContentPane().add(label_1);
				
				JLabel label_2 = new JLabel("New label");
				label_2.setBounds(10, 271, 97, 14);
				dateFrame.getContentPane().add(label_2);
				
				JLabel label_3 = new JLabel("New label");
				label_3.setBounds(10, 302, 97, 14);
				dateFrame.getContentPane().add(label_3);
				
				JLabel label_4 = new JLabel("Date");
				label_4.setBounds(10, 333, 97, 14);
				dateFrame.getContentPane().add(label_4);
				
				if (type.equals("Career Summary")){
					label_1.setVisible(false);
					textField_1.setVisible(false);
					
					label_2.setText("Company Name");
					label_3.setText("Job Title");	
				} else if (type.equals("Education And Training")){
					label_1.setText("Qualification");
					label_2.setText("Establishment");
					label_3.setText("Location");	
				} else if (type.equals("Further Courses")){
					label_1.setText("Course");
					label_2.setText("Establishment");
					label_3.setText("Location");	
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
				if (type.equals("Career Summary")){
					for (String temp : careerSummary){
						fillDateList(temp, 2);
						mainList.addElement(temp);
					}
				} else if (type.equals("Education And Training")){
					for (String temp : educationAndTraining){
						fillDateList(temp, 3);
						mainList.addElement(temp);
					}
				} else if (type.equals("Further Courses")){
					for (String temp : furtherCourses){
						fillDateList(temp, 3);
						mainList.addElement(temp);
					}
				}
				list.setModel(mainList);
				
				add.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if ((!textField_2.getText().equals("") && !textField_2.getText().equals(null)) &&
								 (!textField_3.getText().equals("") && !textField_3.getText().equals(null)) && 
								  (!dateField.getText().equals("") && !dateField.getText().equals(null))){
							if (!checkDateList(dateField)){
								JOptionPane.showMessageDialog(null, "You have entered a wrong Date.");
							}
							if (type.equals("Career Summary")){
								careerSummary.add((textField_2.getText() + ", " + textField_3.getText() + ", " + dateField.getText()));		
								mainList.addElement(textField_2.getText() + ", " + textField_3.getText() + ", " + dateField.getText());
								list.setModel(mainList);
							} else if (!textField_1.getText().equals("") && !textField_1.getText().equals(null)){
								if (type.equals("Education And Training")){
									educationAndTraining.add((textField_1.getText() + ", " + textField_2.getText() + ", " + textField_3.getText() + ", " + dateField.getText()));		
									mainList.addElement(textField_1.getText() + ", " + textField_2.getText() + ", " + textField_3.getText() + ", " + dateField.getText());
									list.setModel(mainList);
									
								} else if (type.equals("Further Courses")){
									
									furtherCourses.add((textField_1.getText() + ", " + textField_2.getText() + ", " + textField_3.getText() + ", " + dateField.getText()));		
									mainList.addElement(textField_1.getText() + ", " + textField_2.getText() + ", " + textField_3.getText() + ", " + dateField.getText());
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
							if (!checkDateList(dateField)){
								JOptionPane.showMessageDialog(null, "You have entered a wrong Date.");
							}
							if ((!textField_2.getText().equals("") && !textField_2.getText().equals(null)) &&
									 (!textField_3.getText().equals("") && !textField_3.getText().equals(null)) && 
									  (!dateField.getText().equals("") && !dateField.getText().equals(null))){
								if (type.equals("Career Summary")){
									int i = 0;
									String tempList = list.getSelectedValue() ;
									for (String temp : careerSummary){
										if (tempList.equals(temp) ){
											careerSummary.set(i, textField_2.getText()
											  + ", " + textField_3.getText() + ", " + dateField.getText() );

											mainList.set(list.getSelectedIndex(), careerSummary.get(i));
											list.setModel(mainList);
										}
										i++;	
									}
								
								}
								if ( !textField_1.getText().equals("")){
									if (type.equals("Education And Training")){
										int i = 0;
										String tempList = list.getSelectedValue() ;
										for (String temp : educationAndTraining){
											if (!temp.equals(null)){
												if (tempList.equals(temp) ){
													educationAndTraining.set(i, textField_1.getText() + ", " + textField_2.getText()
													  + ", " + textField_3.getText() + ", " + dateField.getText() );
													
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
												furtherCourses.set(i, textField_1.getText() + ", " + textField_2.getText()
												  + ", " + textField_3.getText() + ", " + dateField.getText() );

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
								textField_2.setText(listSelectedSplit[0]);
								textField_3.setText(listSelectedSplit[1]);
								dateField.setText(listSelectedSplit[2]);
							} else {
								textField_1.setText(listSelectedSplit[0]);
								textField_2.setText(listSelectedSplit[1]);
								textField_3.setText(listSelectedSplit[2]);
								dateField.setText(listSelectedSplit[3]);
							}
						}
					}
				};
				list.addMouseListener(mouseListen);
				
				dateFrame.setVisible(true);
			}
		});
	}
	
	public static void eventListenerGeneralInformation(JButton btnGeneralInformation){
		btnGeneralInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(cvtemplate.getApplicantName()!=null){
					generalInformation[0]=cvtemplate.getApplicantName();
					generalInformation[1]=cvtemplate.getApplicantAddress();
					generalInformation[2]=cvtemplate.getApplicantHomeTelephone();
					generalInformation[3]=cvtemplate.getApplicantWorkTelephone();
					generalInformation[4]=cvtemplate.getApplicantEmail();
				}
				
				
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

		if (type.equals("Career Summary")){
			Collections.sort(careerSummary);
		}else if (type.equals("Further Courses")){
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
		FunctionalJFrameWindow.setVisible(true);
		
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
    		
	    	}else if(cvtemplate.getSectionObjTitle(i).equals("Career Summary:")){
    			int j=0;
    			for (j=0;j<cvtemplate.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
 
    				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    				String reportDate = sdf.format(cvtemplate.getSectionObj(i).getBulletListItem(j).getDate());
    				careerSummary.add(cvtemplate.getSectionObj(i).getBulletListItem(j).getContents()+", "+reportDate);
    				
    			}
    		
	    	}else if(cvtemplate.getSectionObjTitle(i).equals("Skills and Experience:")){
	
		    			for (int j=0;j<cvtemplate.getSectionObj(i).getNumberOfBulletLists();j++){
		    				
		    				for(int k=0; k<cvtemplate.getSectionObj(i).getBulletList(j).getNumberOfItems();k ++){
		    					skillsAndExperience.add(cvtemplate.getSectionObj(i).getBulletList(j).getTitle()+", "+
		    							cvtemplate.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents());
		    				}
		    			}
		    	}
	    	
	    }
		
	}
	


	public static void saveCV(int output) throws ParseException{
	
		
		Collections.sort(skillsAndExperience);
		
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
			
				cvToSave.addSectionObj(sectionCounter , new Section("Career Summary:"));
				for (String count : careerSummary){
					String[] temp = count.split(", ");
					String content = temp[0] + ", " + temp[1];
					Date date = makeDate(temp[2]);
					
					cvToSave.getSectionObj(sectionCounter).addBulletListItem(new BulletListItem(date, content));
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


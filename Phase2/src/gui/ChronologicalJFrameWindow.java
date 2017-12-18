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

public class ChronologicalJFrameWindow extends JDialog{
	
	static JFrame chronologicalJFrameWindow;
	static JFrame generalInfo;
	private JFrame appMainWindow;
	
	static String[] generalInformation = new String[5];
	static String interests, additionalInformation, professionalProfile, coreStrengths;
	static String oldDate, newDate, tempKey, tempStr;
	
	static ArrayList<String> educationAndTraining = new ArrayList<String>();
	static ArrayList<String> furtherCourses = new ArrayList<String>();
	static ArrayList<String> dateList = new ArrayList<String>();

	static HashMap<String, String> professionalExperience = new HashMap<String, String>();
	static CVTemplate cvtemplate;
	
	public ChronologicalJFrameWindow(final JFrame appMainWindow) {
		this.appMainWindow = appMainWindow;
		
		chronologicalJFrameWindow = new JFrame();
		chronologicalJFrameWindow.getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setLayout(null);
		chronologicalJFrameWindow.setTitle("Curriculum Vitae (CV) Editor");
		chronologicalJFrameWindow.setResizable(false);
		chronologicalJFrameWindow.setBounds(400, 150, 725, 350);
		chronologicalJFrameWindow.setLocationRelativeTo(null);
		chronologicalJFrameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chronologicalJFrameWindow.getContentPane().setLayout(null);
		onExit(chronologicalJFrameWindow, false);
		
		JPanel pane = (JPanel) chronologicalJFrameWindow.getContentPane();
		
		JButton btnGeneralInformation = InitWidgets.createButton(pane,"General Information",10, 23, 154, 51);
		JButton btnProfessionalProfile = InitWidgets.createButton(pane,"Professional Profile",185, 23, 154, 51);
		JButton btnProfessionalExperience = InitWidgets.createButton(pane,"Professional Experience",368, 23, 154, 51);
		JButton btnCoreStrengths = InitWidgets.createButton(pane,"Core Strengths",550, 23, 154, 51);
		JButton btnEducationAndTraining = InitWidgets.createButton(pane,"Education And Training",10, 109, 154, 51);
		JButton btnFurtherCourses = InitWidgets.createButton(pane,"Further Courses",187, 109, 152, 51);
		JButton btnAdditionalInformation = InitWidgets.createButton(pane,"Additional Info",368, 110, 154, 56);
		JButton btnInterests = InitWidgets.createButton(pane,"Interests",550, 109, 152, 57);
		JButton btnCancel = InitWidgets.createButton(pane,"Cancel",10, 279, 154, 31);
		JButton btnSave = InitWidgets.createButton(pane,"Save",552, 258, 152, 52);
		
		JCheckBox chckbxTxt = InitWidgets.createJCheckBox(pane,"txt",425,258,97,23);
		JCheckBox chckbxTex = InitWidgets.createJCheckBox(pane,"tex",425,284,97,23);
		
		/*maybe reusable*/
		JPanel panel = InitWidgets.createJPanel(pane, 0, 205, 719, 116, Color.WHITE);
		
		eventListenerGeneralInformation(btnGeneralInformation);
		eventListenerDate(btnEducationAndTraining, "Education And Training");
		eventListenerDate(btnFurtherCourses, "Further Courses");
		eventListener(btnProfessionalProfile,"Professional Profile");
		eventListener(btnAdditionalInformation, "Additional Information");
		eventListener(btnInterests,"Interests");	
		eventListener(btnCoreStrengths, "Core Strengths");
		eventListenerProfessionalExperience(btnProfessionalExperience, "Professional Experience");
		/* Listeners */
		btnCancel.addActionListener(new CancelCVListener(chronologicalJFrameWindow));		
		btnSave.addActionListener(new SaveCVListener(chckbxTxt, chckbxTex, "Chronological"));
		
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
		button.addActionListener(new ProfessionalExperienceListener(professionalExperience));
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
				} else if (type.equals("Additional Information")){
					textArea.setText(additionalInformation);
				} else if (type.equals("Core Strengths")){
					textArea.setText(coreStrengths);
				}
				
				scrollArea.setViewportView(textArea);
				scrollArea.setBounds(10, 40, 564, 250);
				frame.getContentPane().add(scrollArea);

				JButton saveInfo = new JButton("Save ");
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
						} else if (type.equals("Core Strengths")){
							coreStrengths = textArea.getText();
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
								educationAndTraining.add((textField1.getText() + ", " + textField2.getText() + ", " + textField3.getText() + ", " + dateField.getText()));		
								mainList.addElement(textField1.getText() + ", " + textField2.getText() + ", " + textField3.getText() + ", " + dateField.getText());
								list.setModel(mainList);
								
							} else if (type.equals("Further Courses")){
								furtherCourses.add((textField1.getText() + ", " + textField2.getText() + ", " + textField3.getText() + ", " + dateField.getText()));		
								mainList.addElement(textField1.getText() + ", " + textField2.getText() + ", " + textField3.getText() + ", " + dateField.getText());
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
		chronologicalJFrameWindow.setVisible(true);
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
	    	} else if(cvtemplate.getSectionObjTitle(i).equals("Additional Information:")){
	    		additionalInformation = cvtemplate.getSectionObj(i).getParagraph(0).getContents();
	    	} else if(cvtemplate.getSectionObjTitle(i).equals("Interests:")){
	    		interests = cvtemplate.getSectionObj(i).getParagraph(0).getContents();
	    	}else if(cvtemplate.getSectionObjTitle(i).equals("Core Strengths:")){
	    		coreStrengths = cvtemplate.getSectionObj(i).getParagraph(0).getContents();
	    	} else if(cvtemplate.getSectionObjTitle(i).equals("Education and Training:")){
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
	
	public  static void saveCV(int output) throws ParseException{
		
		CVTemplate cvToSave = new CVTemplate(generalInformation[0], generalInformation[1], generalInformation[2], 
				  generalInformation[3], generalInformation[4]);

		for (int sectionCounter=0;sectionCounter<7;sectionCounter++) {	
			if (sectionCounter==0){
				cvToSave.addSectionObj(sectionCounter , new Section("Professional Profile:"));
				cvToSave.getSectionObj(sectionCounter).addParagraph(professionalProfile);
			} else if(sectionCounter==1){		
				cvToSave.addSectionObj(sectionCounter , new Section("Additional Information:"));
				cvToSave.getSectionObj(sectionCounter).addParagraph(additionalInformation);
			} else if(sectionCounter==2){
				cvToSave.addSectionObj(sectionCounter , new Section("Interests:"));
				cvToSave.getSectionObj(sectionCounter).addParagraph(interests);
			} else if(sectionCounter==2){
				cvToSave.addSectionObj(sectionCounter , new Section("Core Strengths:"));
				cvToSave.getSectionObj(sectionCounter).addParagraph(coreStrengths);
			} else if(sectionCounter==3){
				
				cvToSave.addSectionObj(sectionCounter , new Section("Education and Training:"));
				for (String count : educationAndTraining){
					String[] temp = count.split(", ");
					String content = temp[0] + ", " + temp[1] + ", " + temp[2];
					Date date = makeDate(temp[3]);
					cvToSave.getSectionObj(sectionCounter).addBulletListItem(new BulletListItem(date, content));
				}
			} else if(sectionCounter==4){
				
				cvToSave.addSectionObj(sectionCounter , new Section("Further Courses:"));
				for (String count : furtherCourses){
					String[] temp = count.split(", ");
					String content = temp[0] + ", " + temp[1] + ", " + temp[2];
					Date date = makeDate(temp[3]);
					cvToSave.getSectionObj(sectionCounter).addBulletListItem(new BulletListItem(date, content));
				}
			
			} else if(sectionCounter==5){
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

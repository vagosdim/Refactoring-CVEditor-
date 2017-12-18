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
	
	private static JFrame combinedJFrameWindow;
	private JFrame appMainWindow;
	
	private static String[] generalInformation = new String[5];
	
	private static ArrayList<String> educationAndTraining = new ArrayList<String>();
	private static ArrayList<String> furtherCourses = new ArrayList<String>();
	private static ArrayList<String> skillsAndExperience = new ArrayList<String>();	
	
	private static ArrayList<String> dateList = new ArrayList<String>();
	
	private static HashMap<String, String> professionalExperience =  new HashMap<String, String>();
	// Key String in format : " Company, Job Title, date(ddMMyyyy)"
	// Value String in format : " achiev1, achiev2, ... , paragraph"
	
	private static String tempKey;
	private static String tempStr;
	private static String interests, additionalInformation, professionalProfile;
	private static String oldDate, newDate;
	
	private static CVTemplate cvtemplate;
	
	private static OutputSystemTxt outputSystemTxt = new OutputSystemTxt();
	private static OutputSystemLatex outputSystemLatex = new OutputSystemLatex();
	private static InitWidgets initWidgets = new InitWidgets();
	private static DateFunctionsHandler dateFunctionsHandler = new DateFunctionsHandler(dateList);
	private static CvCommonFunctions cvCommonFunctions = new CvCommonFunctions();
	
	public CombinedJFrameWindow(final JFrame appMainWindow) {
		this.appMainWindow = appMainWindow;
		
		combinedJFrameWindow = initWidgets.createJFrame();
		getContentPane().setLayout(null);
		JPanel pane = (JPanel) combinedJFrameWindow.getContentPane();
		
		JButton btnGeneralInformation = initWidgets.createButton(pane,"General Information",10, 23, 154, 51);
		JButton btnProfessionalProfile = initWidgets.createButton(pane,"Professional Profile",185, 23, 154, 51);
		JButton btnSkillsAndExperience = initWidgets.createButton(pane,"Skills And Experience",368, 23, 154, 51);
		JButton btnProfessionalExperience = initWidgets.createButton(pane,"Professional Experience",550, 23, 154, 51);
		JButton btnEducationAndTraining = initWidgets.createButton(pane,"Education And Training",10, 109, 154, 51);
		JButton btnFurtherCourses = initWidgets.createButton(pane,"Further Courses",187, 109, 152, 51);
		JButton btnAdditionalInformation = initWidgets.createButton(pane,"Additional Info",368, 110, 154, 56);
		JButton btnInterests = initWidgets.createButton(pane,"Interests",550, 109, 152, 57);
		JButton btnCancel = initWidgets.createButton(pane,"Cancel",10, 279, 154, 31);
		JButton btnSave = initWidgets.createButton(pane,"Save",552, 258, 152, 52);
		
		JCheckBox chckbxTxt = initWidgets.createJCheckBox(pane,"txt",425,258,97,23);
		JCheckBox chckbxTex = initWidgets.createJCheckBox(pane,"tex",425,284,97,23);
		
		JPanel panel = initWidgets.createJPanel(pane, 0, 205, 719, 116, Color.WHITE);
		
		eventListenerGeneralInformation(btnGeneralInformation);
		eventListenerDate(btnEducationAndTraining, "Education And Training");
		eventListenerDate(btnFurtherCourses, "Further Courses");
		eventListener(btnProfessionalProfile,"Professional Profile");
		eventListener(btnAdditionalInformation, "Additional Information");
		eventListener(btnInterests,"Interests");
		eventListenerSkillsAndExperience(btnSkillsAndExperience, "Skills And Experience");
		eventListenerProfessionalExperience(btnProfessionalExperience, "Professional Experience");
		
		btnCancel.addActionListener(new CancelCVListener(combinedJFrameWindow));		
		btnSave.addActionListener(new SaveCVListener(chckbxTxt, chckbxTex, "Combined"));
	}
	
	public static void eventListenerProfessionalExperience(JButton button, String type){
		button.addActionListener(new ProfessionalExperienceListener(professionalExperience));
	}
	
	public static void eventListenerSkillsAndExperience(JButton button, String type){
		button.addActionListener(new SkillsAndExperienceListener(button, type, skillsAndExperience));
	}
	
	public void eventListenerDate(JButton btnDate, String type){
		btnDate.addActionListener(new DateEventListener(btnDate, type, educationAndTraining, furtherCourses, dateList, null));
			
	}

	public static void eventListener(JButton button, String type){
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = cvCommonFunctions.createFrame(type, 500, 250, 600, 400, false);
				cvCommonFunctions.onExit(frame, true);
				
				JPanel panel = (JPanel) frame.getContentPane();
				JLabel label = initWidgets.createJLabel(panel, "Input your " + type + " here.", 10, 10, 564, 20);
				JScrollPane scrollArea = new JScrollPane();
				JTextArea textArea = initWidgets.createJTextArea(panel, 10, 40, 564, 250);
				
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
		btnGeneralInformation.addActionListener(new GeneralInformationListener(generalInformation));
		
	}
	
	public static void makeVisible(CVTemplate inputcvtemplate){
		cvtemplate = inputcvtemplate;
		if(cvtemplate.getApplicantName()!=null){
			initialize();
		}
		combinedJFrameWindow.setVisible(true);
	}
	
	public static void initialize(){
		
		cvCommonFunctions.initGeneralInfo(generalInformation, cvtemplate);
		
		for (int i=cvtemplate.getNumberOfSectionObj()-1; i>=0; i--){
	    	
	    	if(cvtemplate.getSectionObjTitle(i).equals("Professional Profile:")){
	    		professionalProfile = cvtemplate.getSectionObj(i).getParagraph(0).getContents();
	    		
	    	}else if(cvtemplate.getSectionObjTitle(i).equals("Additional Information:")){
	    		additionalInformation = cvtemplate.getSectionObj(i).getParagraph(0).getContents();
	    		
	    	}else if(cvtemplate.getSectionObjTitle(i).equals("Interests:")){
	    		interests = cvtemplate.getSectionObj(i).getParagraph(0).getContents();
	    		
	    	}else if(cvtemplate.getSectionObjTitle(i).equals("Education and Training:")){
	    		for (int j=0;j<cvtemplate.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
	    			
	    			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    			String reportDate = sdf.format(cvtemplate.getSectionObj(i).getBulletListItem(j).getDate());
	    			educationAndTraining.add(cvtemplate.getSectionObj(i).getBulletListItem(j).getContents()+", "+reportDate);	
	    		}
	    		
	    	}else if(cvtemplate.getSectionObjTitle(i).equals("Further Courses:")){
    			for (int j=0;j<cvtemplate.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
 
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
					Date date = dateFunctionsHandler.makeDate(temp[3]);
					cvToSave.getSectionObj(sectionCounter).addBulletListItem(new BulletListItem(date, content));
				}
			}else if(sectionCounter==4){
				
				cvToSave.addSectionObj(sectionCounter , new Section("Further Courses:"));
				
				for (String count : furtherCourses){
					String[] temp = count.split(", ");
					String content = temp[0] + ", " + temp[1] + ", " + temp[2];
					Date date = dateFunctionsHandler.makeDate(temp[3]);
					
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
					date = dateFunctionsHandler.makeDate(keyStr[2]);					
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
			outputSystemTxt.saveApplicantInfo("outputfiles", Database.getCVtemplateArrayListSize()-1);
			outputSystemLatex.saveApplicantInfo("outputfiles", Database.getCVtemplateArrayListSize()-1);
		}else if (output==1){
			outputSystemTxt.saveApplicantInfo("outputfiles", Database.getCVtemplateArrayListSize()-1);
		}else if (output==2){
			outputSystemLatex.saveApplicantInfo("outputfiles", Database.getCVtemplateArrayListSize()-1);
		}
	}
}
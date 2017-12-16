package gui;

import java.awt.Color;

import javax.swing.JFrame;

import dataManagePackage.CVTemplate;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CompareTwoCVsJFrameWindow {
	
	static JFrame CompareTwoCVsJFrameWindow;
	private JFrame appMainWindow;
	static CVTemplate cvtemplate1, cvtemplate2;
	private JTextField txtSeeTheDifferences;
	private static JTextField txtProfessionalProfile1, txtProfessionalProfile2, txtSkillsAndExperience1, txtSkillsAndExperience2,
				txtCoreStrengths1, txtCoreStrenghts2, txtProfessionalExperience1, txtProfessionalExperience2, txtCareerSummary1,
				txtCareerSummary2, txtEducationAndTraining1, txtEducationAndTraining2, txtFurtherCourses1, txtFurtherCourses2,
				txtAdditionalInformation1, txtAdditionalInformation2, txtInterests1, txtInterests2;
	
	private static JButton btnProfessionalProfile, btnSkillsAndExperience, btnCoreStrengths, btnProfessionalExperience, 
				btnCareerSummary, btnEducationAndTraining, btnFurtherCourses, btnAdditionalInformation, btnInterests;
	
	public CompareTwoCVsJFrameWindow(final JFrame appMainWindow) {
		this.appMainWindow = appMainWindow;
		
		CompareTwoCVsJFrameWindow = new JFrame();
		CompareTwoCVsJFrameWindow.getContentPane().setBackground(Color.DARK_GRAY);
		CompareTwoCVsJFrameWindow.setTitle("Curriculum Vitae (CV) Compare or Merge");
		CompareTwoCVsJFrameWindow.setResizable(false);
		CompareTwoCVsJFrameWindow.setBounds(400, 150, 478, 570);
		CompareTwoCVsJFrameWindow.setLocationRelativeTo(null);
		CompareTwoCVsJFrameWindow.getContentPane().setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 500, 101, 30);
		CompareTwoCVsJFrameWindow.getContentPane().add(btnBack);
		
		btnProfessionalProfile = new JButton("Professional Profile");
		btnProfessionalProfile.setEnabled(false);
		btnProfessionalProfile.setBounds(10, 384, 146, 23);
		CompareTwoCVsJFrameWindow.getContentPane().add(btnProfessionalProfile);
		
		btnSkillsAndExperience = new JButton("Skills and Experience");
		btnSkillsAndExperience.setEnabled(false);
		btnSkillsAndExperience.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnSkillsAndExperience.setBounds(166, 384, 146, 23);
		CompareTwoCVsJFrameWindow.getContentPane().add(btnSkillsAndExperience);
		
		btnCoreStrengths = new JButton("Core Strengths");
		btnCoreStrengths.setEnabled(false);
		btnCoreStrengths.setBounds(322, 384, 146, 23);
		CompareTwoCVsJFrameWindow.getContentPane().add(btnCoreStrengths);
		
		btnProfessionalExperience = new JButton("Professional Experience");
		btnProfessionalExperience.setEnabled(false);
		btnProfessionalExperience.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnProfessionalExperience.setBounds(10, 418, 146, 23);
		CompareTwoCVsJFrameWindow.getContentPane().add(btnProfessionalExperience);
		
		btnCareerSummary = new JButton("Career Summary");
		btnCareerSummary.setEnabled(false);
		btnCareerSummary.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCareerSummary.setBounds(166, 418, 146, 23);
		CompareTwoCVsJFrameWindow.getContentPane().add(btnCareerSummary);
		
		btnEducationAndTraining = new JButton("Education and Training");
		btnEducationAndTraining.setEnabled(false);
		btnEducationAndTraining.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnEducationAndTraining.setBounds(322, 418, 146, 23);
		CompareTwoCVsJFrameWindow.getContentPane().add(btnEducationAndTraining);
		
		btnFurtherCourses = new JButton("Further Courses");
		btnFurtherCourses.setEnabled(false);
		btnFurtherCourses.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnFurtherCourses.setBounds(10, 452, 146, 23);
		CompareTwoCVsJFrameWindow.getContentPane().add(btnFurtherCourses);
		
		btnAdditionalInformation = new JButton("Additional Information");
		btnAdditionalInformation.setEnabled(false);
		btnAdditionalInformation.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAdditionalInformation.setBounds(166, 452, 146, 23);
		CompareTwoCVsJFrameWindow.getContentPane().add(btnAdditionalInformation);
		
		btnInterests = new JButton("Interests");
		btnInterests.setEnabled(false);
		btnInterests.setBounds(322, 452, 146, 23);
		CompareTwoCVsJFrameWindow.getContentPane().add(btnInterests);
		
		txtSeeTheDifferences = new JTextField();
		txtSeeTheDifferences.setEditable(false);
		txtSeeTheDifferences.setBackground(Color.GRAY);
		txtSeeTheDifferences.setText("See the differences in:");
		txtSeeTheDifferences.setBounds(0, 350, 483, 23);
		CompareTwoCVsJFrameWindow.getContentPane().add(txtSeeTheDifferences);
		txtSeeTheDifferences.setColumns(10);
		
		JLabel lblFirstCv = new JLabel("First CV");
		lblFirstCv.setForeground(Color.WHITE);
		lblFirstCv.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirstCv.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFirstCv.setBounds(31, 11, 167, 43);
		CompareTwoCVsJFrameWindow.getContentPane().add(lblFirstCv);
		
		JLabel lblSecondCv = new JLabel("Second CV");
		lblSecondCv.setForeground(Color.WHITE);
		lblSecondCv.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecondCv.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSecondCv.setBounds(273, 11, 167, 43);
		CompareTwoCVsJFrameWindow.getContentPane().add(lblSecondCv);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(238, 0, 10, 350);
		CompareTwoCVsJFrameWindow.getContentPane().add(panel_1);
		
		txtProfessionalProfile1 = new JTextField();
		txtProfessionalProfile1.setEnabled(false);
		txtProfessionalProfile1.setEditable(false);
		txtProfessionalProfile1.setHorizontalAlignment(SwingConstants.CENTER);
		txtProfessionalProfile1.setText("Professional Profile");
		txtProfessionalProfile1.setBounds(0, 57, 248, 20);
		CompareTwoCVsJFrameWindow.getContentPane().add(txtProfessionalProfile1);
		txtProfessionalProfile1.setColumns(10);
		
		txtProfessionalProfile2 = new JTextField();
		txtProfessionalProfile2.setEnabled(false);
		txtProfessionalProfile2.setEditable(false);
		txtProfessionalProfile2.setText("Professional Profile");
		txtProfessionalProfile2.setHorizontalAlignment(SwingConstants.CENTER);
		txtProfessionalProfile2.setColumns(10);
		txtProfessionalProfile2.setBounds(238, 57, 245, 20);
		CompareTwoCVsJFrameWindow.getContentPane().add(txtProfessionalProfile2);
		
		txtSkillsAndExperience1 = new JTextField();
		txtSkillsAndExperience1.setEnabled(false);
		txtSkillsAndExperience1.setEditable(false);
		txtSkillsAndExperience1.setHorizontalAlignment(SwingConstants.CENTER);
		txtSkillsAndExperience1.setText("Skills and Experience");
		txtSkillsAndExperience1.setBounds(0, 88, 248, 20);
		CompareTwoCVsJFrameWindow.getContentPane().add(txtSkillsAndExperience1);
		txtSkillsAndExperience1.setColumns(10);
		
		txtSkillsAndExperience2 = new JTextField();
		txtSkillsAndExperience2.setEnabled(false);
		txtSkillsAndExperience2.setEditable(false);
		txtSkillsAndExperience2.setText("Skills and Experience");
		txtSkillsAndExperience2.setHorizontalAlignment(SwingConstants.CENTER);
		txtSkillsAndExperience2.setColumns(10);
		txtSkillsAndExperience2.setBounds(238, 88, 245, 20);
		CompareTwoCVsJFrameWindow.getContentPane().add(txtSkillsAndExperience2);
		
		txtCoreStrengths1 = new JTextField();
		txtCoreStrengths1.setEnabled(false);
		txtCoreStrengths1.setEditable(false);
		txtCoreStrengths1.setHorizontalAlignment(SwingConstants.CENTER);
		txtCoreStrengths1.setText("Core Strengths");
		txtCoreStrengths1.setBounds(0, 119, 248, 20);
		CompareTwoCVsJFrameWindow.getContentPane().add(txtCoreStrengths1);
		txtCoreStrengths1.setColumns(10);
		
		txtCoreStrenghts2 = new JTextField();
		txtCoreStrenghts2.setEnabled(false);
		txtCoreStrenghts2.setEditable(false);
		txtCoreStrenghts2.setText("Core Strengths");
		txtCoreStrenghts2.setHorizontalAlignment(SwingConstants.CENTER);
		txtCoreStrenghts2.setColumns(10);
		txtCoreStrenghts2.setBounds(238, 119, 248, 20);
		CompareTwoCVsJFrameWindow.getContentPane().add(txtCoreStrenghts2);
		
		txtProfessionalExperience1 = new JTextField();
		txtProfessionalExperience1.setEnabled(false);
		txtProfessionalExperience1.setEditable(false);
		txtProfessionalExperience1.setHorizontalAlignment(SwingConstants.CENTER);
		txtProfessionalExperience1.setText("Professional Experience");
		txtProfessionalExperience1.setBounds(0, 150, 248, 20);
		CompareTwoCVsJFrameWindow.getContentPane().add(txtProfessionalExperience1);
		txtProfessionalExperience1.setColumns(10);
		
		txtProfessionalExperience2 = new JTextField();
		txtProfessionalExperience2.setEnabled(false);
		txtProfessionalExperience2.setEditable(false);
		txtProfessionalExperience2.setText("Professional Experience");
		txtProfessionalExperience2.setHorizontalAlignment(SwingConstants.CENTER);
		txtProfessionalExperience2.setColumns(10);
		txtProfessionalExperience2.setBounds(238, 150, 248, 20);
		CompareTwoCVsJFrameWindow.getContentPane().add(txtProfessionalExperience2);
		
		txtCareerSummary1 = new JTextField();
		txtCareerSummary1.setEnabled(false);
		txtCareerSummary1.setEditable(false);
		txtCareerSummary1.setHorizontalAlignment(SwingConstants.CENTER);
		txtCareerSummary1.setText("Career Summary");
		txtCareerSummary1.setBounds(0, 181, 248, 20);
		CompareTwoCVsJFrameWindow.getContentPane().add(txtCareerSummary1);
		txtCareerSummary1.setColumns(10);
		
		txtCareerSummary2 = new JTextField();
		txtCareerSummary2.setEnabled(false);
		txtCareerSummary2.setEditable(false);
		txtCareerSummary2.setText("Career Summary");
		txtCareerSummary2.setHorizontalAlignment(SwingConstants.CENTER);
		txtCareerSummary2.setColumns(10);
		txtCareerSummary2.setBounds(238, 181, 248, 20);
		CompareTwoCVsJFrameWindow.getContentPane().add(txtCareerSummary2);
		
		txtEducationAndTraining1 = new JTextField();
		txtEducationAndTraining1.setEnabled(false);
		txtEducationAndTraining1.setEditable(false);
		txtEducationAndTraining1.setHorizontalAlignment(SwingConstants.CENTER);
		txtEducationAndTraining1.setText("Education and Training");
		txtEducationAndTraining1.setBounds(0, 212, 248, 20);
		CompareTwoCVsJFrameWindow.getContentPane().add(txtEducationAndTraining1);
		txtEducationAndTraining1.setColumns(10);
		
		txtEducationAndTraining2 = new JTextField();
		txtEducationAndTraining2.setEnabled(false);
		txtEducationAndTraining2.setEditable(false);
		txtEducationAndTraining2.setText("Education and Training");
		txtEducationAndTraining2.setHorizontalAlignment(SwingConstants.CENTER);
		txtEducationAndTraining2.setColumns(10);
		txtEducationAndTraining2.setBounds(235, 212, 248, 20);
		CompareTwoCVsJFrameWindow.getContentPane().add(txtEducationAndTraining2);
		
		txtFurtherCourses1 = new JTextField();
		txtFurtherCourses1.setEnabled(false);
		txtFurtherCourses1.setEditable(false);
		txtFurtherCourses1.setHorizontalAlignment(SwingConstants.CENTER);
		txtFurtherCourses1.setText("Further Courses");
		txtFurtherCourses1.setBounds(0, 243, 248, 20);
		CompareTwoCVsJFrameWindow.getContentPane().add(txtFurtherCourses1);
		txtFurtherCourses1.setColumns(10);
		
		txtFurtherCourses2 = new JTextField();
		txtFurtherCourses2.setEnabled(false);
		txtFurtherCourses2.setEditable(false);
		txtFurtherCourses2.setText("Further Courses");
		txtFurtherCourses2.setHorizontalAlignment(SwingConstants.CENTER);
		txtFurtherCourses2.setColumns(10);
		txtFurtherCourses2.setBounds(238, 243, 248, 20);
		CompareTwoCVsJFrameWindow.getContentPane().add(txtFurtherCourses2);
		
		txtAdditionalInformation1 = new JTextField();
		txtAdditionalInformation1.setEnabled(false);
		txtAdditionalInformation1.setEditable(false);
		txtAdditionalInformation1.setHorizontalAlignment(SwingConstants.CENTER);
		txtAdditionalInformation1.setText("Additional Information");
		txtAdditionalInformation1.setBounds(0, 274, 248, 20);
		CompareTwoCVsJFrameWindow.getContentPane().add(txtAdditionalInformation1);
		txtAdditionalInformation1.setColumns(10);
		
		txtAdditionalInformation2 = new JTextField();
		txtAdditionalInformation2.setEnabled(false);
		txtAdditionalInformation2.setEditable(false);
		txtAdditionalInformation2.setText("Additional Information");
		txtAdditionalInformation2.setHorizontalAlignment(SwingConstants.CENTER);
		txtAdditionalInformation2.setColumns(10);
		txtAdditionalInformation2.setBounds(238, 274, 248, 20);
		CompareTwoCVsJFrameWindow.getContentPane().add(txtAdditionalInformation2);
		
		txtInterests1 = new JTextField();
		txtInterests1.setEnabled(false);
		txtInterests1.setEditable(false);
		txtInterests1.setHorizontalAlignment(SwingConstants.CENTER);
		txtInterests1.setText("Interests");
		txtInterests1.setBounds(0, 305, 248, 20);
		CompareTwoCVsJFrameWindow.getContentPane().add(txtInterests1);
		txtInterests1.setColumns(10);
		
		txtInterests2 = new JTextField();
		txtInterests2.setEnabled(false);
		txtInterests2.setEditable(false);
		txtInterests2.setText("Interests");
		txtInterests2.setHorizontalAlignment(SwingConstants.CENTER);
		txtInterests2.setColumns(10);
		txtInterests2.setBounds(238, 305, 248, 20);
		CompareTwoCVsJFrameWindow.getContentPane().add(txtInterests2);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 65, 483, 424);
		CompareTwoCVsJFrameWindow.getContentPane().add(panel);
		
		
		/* Listeners */
		
		btnBack.addActionListener(new ActionListener() {		
			
			public void actionPerformed(ActionEvent e) {
				
				
				CompareTwoCVsJFrameWindow.setVisible(false);
				CompareOrMergeTwoCVsJFrameWindow.makeVisible();
				
				
			}
		
		});
		
		btnProfessionalProfile.addActionListener(new ActionListener() {		
			
			public void actionPerformed(ActionEvent e) {
				
				ComparisonJFrameWindow window = new ComparisonJFrameWindow(cvtemplate1, cvtemplate2, "Professional Profile:");
				window.frame.setVisible(true);
	
				
			}
		
		});
		
		btnAdditionalInformation.addActionListener(new ActionListener() {		
			
			public void actionPerformed(ActionEvent e) {
				
				ComparisonJFrameWindow window = new ComparisonJFrameWindow(cvtemplate1, cvtemplate2, "Additional Information:");
				window.frame.setVisible(true);
	
				
			}
		
		});
		
		btnInterests.addActionListener(new ActionListener() {		
			
			public void actionPerformed(ActionEvent e) {
				
				ComparisonJFrameWindow window = new ComparisonJFrameWindow(cvtemplate1, cvtemplate2, "Interests:");
				window.frame.setVisible(true);
	
				
			}
		
		});
		
		btnEducationAndTraining.addActionListener(new ActionListener() {		
			
			public void actionPerformed(ActionEvent e) {
				
				ComparisonJFrameWindow window = new ComparisonJFrameWindow(cvtemplate1, cvtemplate2, "Education and Training:");
				window.frame.setVisible(true);
	
				
			}
		
		});
		
		btnFurtherCourses.addActionListener(new ActionListener() {		
			
			public void actionPerformed(ActionEvent e) {
				
				ComparisonJFrameWindow window = new ComparisonJFrameWindow(cvtemplate1, cvtemplate2, "Further Courses:");
				window.frame.setVisible(true);
	
				
			}
		
		});
		
		btnSkillsAndExperience.addActionListener(new ActionListener() {		
			
			public void actionPerformed(ActionEvent e) {
				
				ComparisonJFrameWindow window = new ComparisonJFrameWindow(cvtemplate1, cvtemplate2, "Skills and Experience:");
				window.frame.setVisible(true);
	
				
			}
		
		});
		
		btnProfessionalExperience.addActionListener(new ActionListener() {		
			
			public void actionPerformed(ActionEvent e) {
				
				ComparisonJFrameWindow window = new ComparisonJFrameWindow(cvtemplate1, cvtemplate2, "Professional Experience:");
				window.frame.setVisible(true);
	
				
			}
		
		});
		
		btnCareerSummary.addActionListener(new ActionListener() {		
			
			public void actionPerformed(ActionEvent e) {
				
				ComparisonJFrameWindow window = new ComparisonJFrameWindow(cvtemplate1, cvtemplate2, "Career Summary:");
				window.frame.setVisible(true);
	
				
			}
		
		});
		
		btnCoreStrengths.addActionListener(new ActionListener() {		
			
			public void actionPerformed(ActionEvent e) {
				
				ComparisonJFrameWindow window = new ComparisonJFrameWindow(cvtemplate1, cvtemplate2, "Core Strengths:");
				window.frame.setVisible(true);
	
				
			}
		
		});
		
	}
	
	public static void makeVisible(CVTemplate inputcvtemplate1, CVTemplate inputcvtemplate2){
		
		cvtemplate1 = inputcvtemplate1;
		cvtemplate2 = inputcvtemplate2;
		CompareTwoCVsJFrameWindow.setVisible(true);
		initializeTextFields();
		
	}
	
	public static void initializeTextFields(){
		
		for (int i=0; i<cvtemplate1.getNumberOfSectionObj();i++){
			if (cvtemplate1.getSectionObjTitle(i).equals("Professional Profile:")) txtProfessionalProfile1.setEnabled(true);
			else if (cvtemplate1.getSectionObjTitle(i).equals("Skills and Experience:")) txtSkillsAndExperience1.setEnabled(true);
			else if (cvtemplate1.getSectionObjTitle(i).equals("Career Summary:")) txtCareerSummary1.setEnabled(true);
			else if (cvtemplate1.getSectionObjTitle(i).equals("Education and Training:")) txtEducationAndTraining1.setEnabled(true);
			else if (cvtemplate1.getSectionObjTitle(i).equals("Professional Experience:")) txtProfessionalExperience1.setEnabled(true);
			else if (cvtemplate1.getSectionObjTitle(i).equals("Core Strengths:")) txtCoreStrengths1.setEnabled(true);
			else if (cvtemplate1.getSectionObjTitle(i).equals("Further Courses:")) txtFurtherCourses1.setEnabled(true);
			else if (cvtemplate1.getSectionObjTitle(i).equals("Additional Information:")) txtAdditionalInformation1.setEnabled(true);
			else if (cvtemplate1.getSectionObjTitle(i).equals("Interests:")) txtInterests1.setEnabled(true);
			
		}
		
		for (int i=0; i<cvtemplate2.getNumberOfSectionObj();i++){
			if (cvtemplate2.getSectionObjTitle(i).equals("Professional Profile:")) txtProfessionalProfile2.setEnabled(true);
			else if (cvtemplate2.getSectionObjTitle(i).equals("Skills and Experience:")) txtSkillsAndExperience2.setEnabled(true);
			else if (cvtemplate2.getSectionObjTitle(i).equals("Career Summary:")) txtCareerSummary2.setEnabled(true);
			else if (cvtemplate2.getSectionObjTitle(i).equals("Education and Training:")) txtEducationAndTraining2.setEnabled(true);
			else if (cvtemplate2.getSectionObjTitle(i).equals("Professional Experience:")) txtProfessionalExperience2.setEnabled(true);
			else if (cvtemplate2.getSectionObjTitle(i).equals("Core Strengths:")) txtCoreStrenghts2.setEnabled(true);
			else if (cvtemplate2.getSectionObjTitle(i).equals("Further Courses:")) txtFurtherCourses2.setEnabled(true);
			else if (cvtemplate2.getSectionObjTitle(i).equals("Additional Information:")) txtAdditionalInformation2.setEnabled(true);
			else if (cvtemplate2.getSectionObjTitle(i).equals("Interests:")) txtInterests2.setEnabled(true);
			
		}
		
		for (int i=0; i<cvtemplate1.getNumberOfSectionObj(); i++){
			for (int j=0; j<cvtemplate2.getNumberOfSectionObj();j++){
				if(cvtemplate1.getSectionObjTitle(i).equals(cvtemplate2.getSectionObjTitle(j))){
					if(cvtemplate1.getSectionObjTitle(i).equals("Professional Profile:")) btnProfessionalProfile.setEnabled(true);
					else if(cvtemplate1.getSectionObjTitle(i).equals("Skills and Experience:")) btnSkillsAndExperience.setEnabled(true);
					else if(cvtemplate1.getSectionObjTitle(i).equals("Career Summary:")) btnCareerSummary.setEnabled(true);
					else if(cvtemplate1.getSectionObjTitle(i).equals("Education and Training:")) btnEducationAndTraining.setEnabled(true);
					else if(cvtemplate1.getSectionObjTitle(i).equals("Professional Experience:")) btnProfessionalExperience.setEnabled(true);
					else if(cvtemplate1.getSectionObjTitle(i).equals("Core Strengths:")) btnCoreStrengths.setEnabled(true);
					else if(cvtemplate1.getSectionObjTitle(i).equals("Further Courses:")) btnFurtherCourses.setEnabled(true);
					else if(cvtemplate1.getSectionObjTitle(i).equals("Additional Information:")) btnAdditionalInformation.setEnabled(true);
					else if(cvtemplate1.getSectionObjTitle(i).equals("Interests:")) btnInterests.setEnabled(true);
					
					
				}
				
				
			}
			
		}
		
		
	}
	
}

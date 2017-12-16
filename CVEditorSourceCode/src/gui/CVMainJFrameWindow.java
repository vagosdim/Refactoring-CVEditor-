package gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


import dataManagePackage.CVTemplate;
import dataManagePackage.Database;
import inputManagePackage.InputSystemLatex;
import inputManagePackage.InputSystemTxt;
import outputManagePackage.OutputSystemLatex;
import outputManagePackage.OutputSystemTxt;

//import inputManagePackage.InputSystem;
//import outputManagePackage.OutputSystemLatex;
//import outputManagePackage.OutputSystemTxt;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;


public class CVMainJFrameWindow{


	/*
	 * 		Creates the main Window and calls the event Listener of the checkboxes and "Create CV" button.
	 */
	static JFrame cvMainWindow;
	public static void main(String[] args) {

		ChronologicalJFrameWindow ChronologicalJFrameWindow = new ChronologicalJFrameWindow(cvMainWindow);
		FunctionalJFrameWindow FunctionalJFrameWindow = new FunctionalJFrameWindow(cvMainWindow);
		CombinedJFrameWindow CombinedJFrameWindow = new CombinedJFrameWindow(cvMainWindow);
		CompareOrMergeTwoCVsJFrameWindow CompareTwoCVsJFrameWindow = new CompareOrMergeTwoCVsJFrameWindow(cvMainWindow);

		cvMainWindow = createFrame();
		onExit(cvMainWindow, false);
		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(500, 500, 500, 500));
		panel.setSize(756, 568);
		//panel.setBackground(Color.RED);
		cvMainWindow.getContentPane().add(panel);
		panel.setLayout(null);


		JButton createCV = new JButton("Create a CV");
		createCVButton(createCV);
		panel.add(createCV);

		JLabel lblCheck = new JLabel("Hello. Please check what kind of CV you want to create and click on \"Create a CV\".");
		lblCheck.setForeground(Color.WHITE);
		lblCheck.setHorizontalAlignment(SwingConstants.CENTER);
		createJLabelCheck(lblCheck);
		panel.add(lblCheck);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(0, 0, 795, 56);
		panel.add(panel_1);

		JCheckBox chckbxChronological = new JCheckBox("Chronological\r\n");
		createChronoBox(chckbxChronological);
		panel.add(chckbxChronological);

		JCheckBox chckbxFunctional = new JCheckBox("Functional");
		createFuncBox(chckbxFunctional);
		panel.add(chckbxFunctional);

		JCheckBox chckbxCombined = new JCheckBox("Combined");
		createCombBox(chckbxCombined);
		panel.add(chckbxCombined);

		JLabel labelChronological = new JLabel("Create a Chronological CV");
		createChronoLabel(labelChronological);
		panel.add(labelChronological);

		JLabel labelFunctional = new JLabel("Create a Functional CV");
		createFuncLabel(labelFunctional);
		panel.add(labelFunctional);

		JLabel labelCombined = new JLabel("Create a Combined CV");
		createCombLabel(labelCombined);
		panel.add(labelCombined);



		JLabel lblYouCanSave = new JLabel("Here, you can edit an existing CV in a number of ways.");
		lblYouCanSave.setForeground(Color.WHITE);
		lblYouCanSave.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouCanSave.setBackground(Color.DARK_GRAY);
		lblYouCanSave.setBounds(0, 303, 773, 69);
		panel.add(lblYouCanSave);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(0, 303, 808, 69);
		panel.add(panel_2);

		JButton btnOpenACv = new JButton("Open a CV");
		btnOpenACv.setVerticalTextPosition(SwingConstants.CENTER);
		btnOpenACv.setToolTipText("Click This Button to open an existing CV.");
		btnOpenACv.setHorizontalTextPosition(SwingConstants.LEADING);
		btnOpenACv.setBounds(70, 407, 143, 76);
		panel.add(btnOpenACv);

		JButton btnDeleteACv = new JButton("Delete a CV");
		btnDeleteACv.setVerticalTextPosition(SwingConstants.CENTER);
		btnDeleteACv.setToolTipText("Click This Button to delete a CV.");
		btnDeleteACv.setHorizontalTextPosition(SwingConstants.LEADING);
		btnDeleteACv.setBounds(324, 407, 143, 76);
		panel.add(btnDeleteACv);

		JButton btnCompareTwoCvs = new JButton("Compare two CV's");
		btnCompareTwoCvs.setVerticalTextPosition(SwingConstants.CENTER);
		btnCompareTwoCvs.setToolTipText("Click This Button to compare or mergge two existing CV's.");
		btnCompareTwoCvs.setHorizontalTextPosition(SwingConstants.LEADING);
		btnCompareTwoCvs.setBounds(567, 407, 143, 76);
		panel.add(btnCompareTwoCvs);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.DARK_GRAY);
		panel_3.setBounds(0, 494, 795, 86);
		panel.add(panel_3);

		eventListenerMainWindow(cvMainWindow, chckbxChronological, labelChronological, chckbxFunctional, labelFunctional,
								chckbxCombined, labelCombined, createCV, btnOpenACv, btnDeleteACv, btnCompareTwoCvs);
	}

	/*		These Methods give values to the Frame, Buttons, Labels created in the main Function
	 *
	 */

	public static JFrame createFrame(){
		JFrame cvMainWindow =new JFrame("Curriculum Vitae (CV) Creator");
		cvMainWindow.setResizable(false);
		cvMainWindow.setBounds(400, 150, 800, 600);
		cvMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cvMainWindow.setVisible(true);
		return cvMainWindow;
	}
	public static void createCVButton(JButton createCV){
		createCV.setBounds(562, 203, 129, 49);
		createCV.setVerticalTextPosition(AbstractButton.CENTER);
		createCV.setHorizontalTextPosition(AbstractButton.LEADING);
		createCV.setToolTipText("Click This Button to create a new CV.");
	}
	public static void createJLabelCheck(JLabel lblCheck){
		lblCheck.setBounds(10, 11, 712, 49);
	}
	public static void createChronoBox(JCheckBox chckbxChronological){
		chckbxChronological.setBounds(20, 127, 167, 23);
	}
	public static void createFuncBox(JCheckBox chckbxFunctional){
		chckbxFunctional.setBounds(20, 70, 167, 23);
	}
	public static void createCombBox(JCheckBox chckbxCombined){
		chckbxCombined.setBounds(20, 190, 167, 23);
	}
	public static void createChronoLabel(JLabel labelChronological){
		labelChronological.setBounds(30, 157, 211, 14);
	}
	public static void createFuncLabel(JLabel labelFunctional){
		labelFunctional.setBounds(30, 100, 129, 14);
	}
	public static void createCombLabel(JLabel labelCombined){
		labelCombined.setBounds(30, 220, 129, 14);
	}

	public static void onExit(JFrame frame, boolean dispose){

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent){
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "title" , 0) == JOptionPane.YES_OPTION){

					if (dispose){
						frame.setDefaultCloseOperation(2);
					} else {
						frame.setDefaultCloseOperation(3);
					}
				} else {
					frame.setDefaultCloseOperation(0);
				}
			}
		});
	}

	public static void eventListenerMainWindow(JFrame cvMainWindow, JCheckBox chckbxChronological, JLabel labelChronological,
												JCheckBox chckbxFunctional, JLabel labelFunctional, JCheckBox chckbxCombined,
												JLabel labelCombined, JButton createCV, JButton btnOpenACv, JButton btnDeleteACv,
												JButton btnCompareTwoCvs){
//---------------------------- EVENT LISTENERS-----------------------


		btnOpenACv.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser openCvFileChooser = new JFileChooser();

				FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter( "txt files (*.txt)", "txt");

				openCvFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("tex files (*.tex)", "tex"));
				//openCvFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.txt", "txt"));

				openCvFileChooser.setFileFilter(xmlfilter);

				openCvFileChooser.setCurrentDirectory(new java.io.File("."));
				openCvFileChooser.setDialogTitle("Please select the CV file.");



				if(openCvFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {


				    String openCvFileChooserPath = openCvFileChooser.getSelectedFile().toString();
				    JOptionPane.showMessageDialog(null, openCvFileChooserPath, "Directory of chosen CV", JOptionPane.INFORMATION_MESSAGE);

				    if(openCvFileChooserPath.substring(openCvFileChooserPath.length()-3).equals("txt")){
					    try {
							InputSystemTxt.addCvDataFromFile(0,openCvFileChooserPath);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    }else if(openCvFileChooserPath.substring(openCvFileChooserPath.length()-3).equals("tex")){
					    try {
							InputSystemLatex.addCvDataFromFile(0, openCvFileChooserPath);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    }else{
				    	JOptionPane.showMessageDialog(null, "Invalid input file type. Txt or Tex allowed.");

				    	cvMainWindow.setVisible(true);
				    }

				    CVTemplate cvtemplate = Database.getCvtemplateFromArrayList(Database.getCVtemplateArrayListSize()-1);
				    System.out.println(cvtemplate.getApplicantName());
				    System.out.println(cvtemplate.getApplicantAddress());
				    System.out.println(cvtemplate.getApplicantHomeTelephone());
				    System.out.println(cvtemplate.getApplicantWorkTelephone());
				    System.out.println(cvtemplate.getApplicantEmail());

				    for (int i=cvtemplate.getNumberOfSectionObj()-1; i>=0; i--){


				    	System.out.println(cvtemplate.getSectionObjTitle(i));
				    	if(cvtemplate.getSectionObjTitle(i).equals("Professional Profile:") ||
				    			cvtemplate.getSectionObjTitle(i).equals("Core Strenghts:") ||
				    			cvtemplate.getSectionObjTitle(i).equals("Additional Information:") ||
				    			cvtemplate.getSectionObjTitle(i).equals("Interests:")){
				    			System.out.println(cvtemplate.getSectionObj(i).getParagraph(0).getContents());

				    	}else if(cvtemplate.getSectionObjTitle(i).equals("Career Summary:") ||
				    			cvtemplate.getSectionObjTitle(i).equals("Education and Training:") ||
				    			cvtemplate.getSectionObjTitle(i).equals("Further Courses:")){
				    			int j=0;
				    			for (j=0;j<cvtemplate.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
				    				System.out.println(cvtemplate.getSectionObj(i).getBulletListItem(j).getContents());

				    			}

				    	}else if(cvtemplate.getSectionObjTitle(i).equals("Skills and Experience:")){

				    			for (int j=0;j<cvtemplate.getSectionObj(i).getNumberOfBulletLists();j++){
				    				System.out.println(cvtemplate.getSectionObj(i).getBulletList(j).getTitle());

				    				for(int k=0; k<cvtemplate.getSectionObj(i).getBulletList(j).getNumberOfItems();k ++){
				    					System.out.println(cvtemplate.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents());
				    				}
				    			}
				    	}else if(cvtemplate.getSectionObjTitle(i).equals("Professional Experience:")){

				    		int j=0;
				    		int k=0;
			    			for (j=0;j<cvtemplate.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
			    				System.out.println(cvtemplate.getSectionObj(i).getBulletListItem(j).getContents());
			    				for (k=0;k<cvtemplate.getSectionObj(i).getBulletList(j).getNumberOfItems();k++){
			    					System.out.println(cvtemplate.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents());

			    				}

			    			}
				    	}

				    }
				    System.out.println(InputSystemTxt.templateTypeSilentIdentifier(cvtemplate));
				    if (InputSystemTxt.templateTypeSilentIdentifier(cvtemplate)==0){
				    	cvMainWindow.setVisible(false);
						System.out.println("Opening funct");
						FunctionalJFrameWindow.makeVisible(cvtemplate);

				    }else if (InputSystemTxt.templateTypeSilentIdentifier(cvtemplate)==1){
				    	cvMainWindow.setVisible(false);
				    	System.out.println("Opening chron");
						ChronologicalJFrameWindow.makeVisible(cvtemplate);

				    }else if (InputSystemTxt.templateTypeSilentIdentifier(cvtemplate)==2){
				    	cvMainWindow.setVisible(false);
				    	System.out.println("Opening comb");
						CombinedJFrameWindow.makeVisible(cvtemplate);
				    }
				    OutputSystemTxt.saveApplicantInfoToTxt("outputFiles", 0);
				    OutputSystemLatex.saveApplicantInfoToLatex("outputFiles", 0);

				}

			}

		});

		btnDeleteACv.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser deleteCvFileChooser = new JFileChooser();
				deleteCvFileChooser.setCurrentDirectory(new java.io.File("."));
				deleteCvFileChooser.setDialogTitle("Please select the CV file.");


				if(deleteCvFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				    String deleteCvFilePath = deleteCvFileChooser.getSelectedFile().toString();
				    Path deleteCvFilePath2 = Paths.get(deleteCvFilePath);

				    try {
						Files.delete(deleteCvFilePath2);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    JOptionPane.showMessageDialog(null, deleteCvFilePath, "Directory of deleted CV", JOptionPane.INFORMATION_MESSAGE);


				    cvMainWindow.setVisible(true);
				}

			}

		});



		btnCompareTwoCvs.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				CompareOrMergeTwoCVsJFrameWindow.makeVisible();
				//cvMainWindow.setVisible(true);


			}

		});



		// If Chronological CV is selected disable others
		chckbxChronological.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (chckbxChronological.isSelected()){
					chckbxFunctional.setEnabled(false);
					labelFunctional.setEnabled(false);
					chckbxCombined.setEnabled(false);
					labelCombined.setEnabled(false);
				}else{
					chckbxFunctional.setEnabled(true);
					labelFunctional.setEnabled(true);
					chckbxCombined.setEnabled(true);
					labelCombined.setEnabled(true);
				}
			}
		});

		// If Functional CV is selected disable others
		chckbxFunctional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxFunctional.isSelected()){
					chckbxChronological.setEnabled(false);
					labelChronological.setEnabled(false);
					chckbxCombined.setEnabled(false);
					labelCombined.setEnabled(false);
				}else{
					chckbxChronological.setEnabled(true);
					labelChronological.setEnabled(true);
					chckbxCombined.setEnabled(true);
					labelCombined.setEnabled(true);
				}
			}
		});

		// If Combined CV is selected disable others
		chckbxCombined.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxCombined.isSelected()){
					chckbxChronological.setEnabled(false);
					labelChronological.setEnabled(false);
					chckbxFunctional.setEnabled(false);
					labelFunctional.setEnabled(false);
				}else{
					chckbxChronological.setEnabled(true);
					labelChronological.setEnabled(true);
					chckbxFunctional.setEnabled(true);
					labelFunctional.setEnabled(true);
				}
			}
		});

		/* Event Handler for create CV button, checks if CV is selected and proceeds to create the next frame
		 *	or displays a warning message
		 */
		createCV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( chckbxChronological.isSelected()) {
					cvMainWindow.setVisible(false);
					CVTemplate cvtemplate = new CVTemplate(null,null,null,null,null);
					ChronologicalJFrameWindow.makeVisible(cvtemplate);

				} else if ( chckbxFunctional.isSelected() ){

					cvMainWindow.setVisible(false);
					CVTemplate cvtemplate = new CVTemplate(null,null,null,null,null);
					FunctionalJFrameWindow.makeVisible(cvtemplate);


				} else if (chckbxCombined.isSelected() ) {
					cvMainWindow.setVisible(false);
					CVTemplate cvtemplate = new CVTemplate(null,null,null,null,null);
					CombinedJFrameWindow.makeVisible(cvtemplate);

				} else {
					JOptionPane.showMessageDialog(null, "Please select a CV first. :)");
				}

			}
		});

	}


	public static void makeVisible(){
		cvMainWindow.setVisible(true);

	}
}



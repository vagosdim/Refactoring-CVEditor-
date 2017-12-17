package gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import dataManagePackage.CVTemplate;
import dataManagePackage.Database;
import inputManagePackage.InputSystemLatex;
import inputManagePackage.InputSystemTxt;
import outputManagePackage.OutputMerge;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.text.ParseException;

import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class CompareOrMergeTwoCVsJFrameWindow {

	static JFrame CompareOrMergeTwoCVsJFrameWindow;
	private JFrame appMainWindow;
	private JList<String> firstCVSectionsJList, secondCVSectionsJList;
	String openFirstCvFileChooserPath, openSecondCvFileChooserPath;
	int checkCounter=0, openFirstCounter=0, openSecondCounter=0;
	DefaultListModel<String> modelFirst = new DefaultListModel<>();
	DefaultListModel<String> modelSecond = new DefaultListModel<>();
	CVTemplate cvtemplate1, cvtemplate2;
	
	
	
	public CompareOrMergeTwoCVsJFrameWindow(final JFrame appMainWindow) {
		this.appMainWindow = appMainWindow;
		CompareTwoCVsJFrameWindow CompareTwoCVsJFrameWindow = new CompareTwoCVsJFrameWindow(appMainWindow);
		
		CompareOrMergeTwoCVsJFrameWindow = new JFrame();
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().setBackground(Color.DARK_GRAY);
		CompareOrMergeTwoCVsJFrameWindow.setTitle("Curriculum Vitae (CV) Compare or Merge");
		CompareOrMergeTwoCVsJFrameWindow.setResizable(false);
		CompareOrMergeTwoCVsJFrameWindow.setBounds(400, 150, 518, 538);
		CompareOrMergeTwoCVsJFrameWindow.setLocationRelativeTo(null);
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().setLayout(null);
		
		JLabel lblFirstCv = new JLabel("First CV");
		lblFirstCv.setBackground(Color.BLACK);
		lblFirstCv.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirstCv.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFirstCv.setForeground(Color.DARK_GRAY);
		lblFirstCv.setBounds(10, 11, 233, 35);
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().add(lblFirstCv);
		
		JLabel lblSecondCv = new JLabel("Second CV");
		lblSecondCv.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecondCv.setForeground(Color.DARK_GRAY);
		lblSecondCv.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSecondCv.setBackground(Color.BLACK);
		lblSecondCv.setBounds(267, 11, 245, 35);
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().add(lblSecondCv);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 512, 54);
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().add(panel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(253, -3, 4, 415);
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().add(panel_2);
		
		JScrollPane scrollPaneFirstCV = new JScrollPane();
		scrollPaneFirstCV.setBounds(20, 65, 198, 255);
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().add(scrollPaneFirstCV);
		
		
		
		
		
		JScrollPane scrollPaneSecondCV = new JScrollPane();
		scrollPaneSecondCV.setBounds(292, 65, 198, 255);
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().add(scrollPaneSecondCV);
		
		
		JButton btnOpenFirstCv = new JButton("Open First CV");
		btnOpenFirstCv.setBounds(49, 331, 156, 29);
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().add(btnOpenFirstCv);
		
		JButton btnOpenSecondCv = new JButton("Open Second CV");
		btnOpenSecondCv.setBounds(302, 331, 156, 29);
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().add(btnOpenSecondCv);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 469, 103, 35);
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().add(btnBack);
		
		JCheckBox chckbxTex = new JCheckBox("tex");
		chckbxTex.setBounds(323, 481, 60, 23);
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().add(chckbxTex);
		
		JButton btnMerge = new JButton("Merge");
		btnMerge.setBounds(389, 467, 113, 38);
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().add(btnMerge);
		btnMerge.setToolTipText("If in doubt, the second CV's data \n will be overwritten");
		
		JButton btnClearFirstCv = new JButton("Clear");
		btnClearFirstCv.setBounds(81, 371, 103, 23);
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().add(btnClearFirstCv);
		
		JButton btnClearSecondCv = new JButton("Clear");
		btnClearSecondCv.setBounds(327, 371, 103, 23);
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().add(btnClearSecondCv);
		
		JCheckBox chckbxTxt = new JCheckBox("txt");
		chckbxTxt.setBounds(323, 455, 60, 23);
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().add(chckbxTxt);
		
		JButton btnCompare = new JButton("Compare");
		btnCompare.setEnabled(false);
		btnCompare.setBounds(181, 413, 156, 35);
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().add(btnCompare);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(502, 412, -504, 95);
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().add(panel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 405, 512, 104);
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().add(panel_3);
		
		
		/* Listeners */
		
		btnCompare.addActionListener(new ActionListener() {		
			
			public void actionPerformed(ActionEvent e) {
				
				CompareTwoCVsJFrameWindow.makeVisible(cvtemplate1, cvtemplate2);
			}
		
		});
		
		btnClearFirstCv.addActionListener(new ActionListener() {		
			
			public void actionPerformed(ActionEvent e) {
				modelFirst.clear();
				openFirstCvFileChooserPath=null;
				if(openFirstCounter==1){
					btnOpenFirstCv.setEnabled(true);
					openFirstCounter--;
					checkCounter--;
				}
				
				if(checkCounter<2) btnCompare.setEnabled(false);
			}
		
		});
		
		btnClearSecondCv.addActionListener(new ActionListener() {		
			
			public void actionPerformed(ActionEvent e) {
				modelSecond.clear();
				openSecondCvFileChooserPath=null;
				if(openSecondCounter==1) {
					btnOpenSecondCv.setEnabled(true);
					openSecondCounter--;
					checkCounter--;
				}
				
				if(checkCounter<2) btnCompare.setEnabled(false);
			}
		
		});
		
		btnBack.addActionListener(new ActionListener() {		
			
			public void actionPerformed(ActionEvent e) {
				
				CompareOrMergeTwoCVsJFrameWindow.setVisible(false);
				CVMainJFrameWindow.makeVisible();
				
			}
		
		});
		
		btnOpenFirstCv.addActionListener(new ActionListener() {		
			
				public void actionPerformed(ActionEvent e) {
					JFileChooser openFirstCvFileChooser = new JFileChooser();
					
					FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter( "txt files (*.txt)", "txt");
					
					openFirstCvFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("tex files (*.tex)", "tex"));
					
					openFirstCvFileChooser.setFileFilter(xmlfilter);
					
					openFirstCvFileChooser.setCurrentDirectory(new java.io.File("."));
					openFirstCvFileChooser.setDialogTitle("Please select the CV file.");
					
					
					
					if(openFirstCvFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						
						
					    openFirstCvFileChooserPath = openFirstCvFileChooser.getSelectedFile().toString();
					    JOptionPane.showMessageDialog(null, openFirstCvFileChooserPath, "Directory of chosen CV", JOptionPane.INFORMATION_MESSAGE);
					    
					    if(openFirstCvFileChooserPath.substring(openFirstCvFileChooserPath.length()-3).equals("txt")){
						    try {
								InputSystemTxt.addCvDataFromFile(0,openFirstCvFileChooserPath);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					    }else if(openFirstCvFileChooserPath.substring(openFirstCvFileChooserPath.length()-3).equals("tex")){
						    try {
								InputSystemLatex.addCvDataFromFile(0,openFirstCvFileChooserPath);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					    }else{
					    	JOptionPane.showMessageDialog(null, "Invalid input file type. Txt or Tex allowed.");
					    	
					    	CompareOrMergeTwoCVsJFrameWindow.setVisible(true);
					    }

						
					    if (checkIfSameFiles()!=1){
					    
							firstCVSectionsJList = new JList<>( modelFirst );
							
							
							cvtemplate1 = Database.getCvtemplateFromArrayList(checkCounter);
							for (int i=cvtemplate1.getNumberOfSectionObj()-1; i>=0; i--){
								modelFirst.addElement(cvtemplate1.getSectionObjTitle(i).substring(0,cvtemplate1.getSectionObjTitle(i).length()-1));
							}
							firstCVSectionsJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
							firstCVSectionsJList.setForeground(Color.DARK_GRAY);
							firstCVSectionsJList.setFont(new Font("Tahoma", Font.BOLD, 15));
							scrollPaneFirstCV.setViewportView(firstCVSectionsJList);
							
							checkCounter++;
							openFirstCounter++;
					    }
					    if(checkCounter>=2) btnCompare.setEnabled(true);
						
					}
					
					
					if (openFirstCounter==1) btnOpenFirstCv.setEnabled(false);
					CompareOrMergeTwoCVsJFrameWindow.setVisible(true);
		
					
				}
		
		});
		
		btnOpenSecondCv.addActionListener(new ActionListener() {		
			
			public void actionPerformed(ActionEvent e) {
				JFileChooser openSecondCvFileChooser = new JFileChooser();
				
				
				FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter( "txt files (*.txt)", "txt");
				
				openSecondCvFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("tex files (*.tex)", "tex"));
				
				openSecondCvFileChooser.setFileFilter(xmlfilter);
				
				openSecondCvFileChooser.setCurrentDirectory(new java.io.File("."));
				openSecondCvFileChooser.setDialogTitle("Please select the CV file.");
				
				
				
				if(openSecondCvFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					
					
				    openSecondCvFileChooserPath = openSecondCvFileChooser.getSelectedFile().toString();
				    JOptionPane.showMessageDialog(null, openSecondCvFileChooserPath, "Directory of chosen CV", JOptionPane.INFORMATION_MESSAGE);
				    
				    if(openSecondCvFileChooserPath.substring(openSecondCvFileChooserPath.length()-3).equals("txt")){
					    try {
							InputSystemTxt.addCvDataFromFile(1,openSecondCvFileChooserPath);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    }else if(openSecondCvFileChooserPath.substring(openSecondCvFileChooserPath.length()-3).equals("tex")){
					    try {
							InputSystemLatex.addCvDataFromFile(1,openSecondCvFileChooserPath);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    }else{
				    	JOptionPane.showMessageDialog(null, "Invalid input file type. Txt or Tex allowed.");
				    	
				    	CompareOrMergeTwoCVsJFrameWindow.setVisible(true);
				    }
				    
				   
		  
				    if (checkIfSameFiles()!=1){
				    
						secondCVSectionsJList = new JList<>( modelSecond );
						
						
						cvtemplate2 = Database.getCvtemplateFromArrayList(checkCounter);
						for (int i=cvtemplate2.getNumberOfSectionObj()-1; i>=0; i--){
							modelSecond.addElement(cvtemplate2.getSectionObjTitle(i).substring(0,cvtemplate2.getSectionObjTitle(i).length()-1));
						}
						secondCVSectionsJList.setForeground(Color.DARK_GRAY);
						secondCVSectionsJList.setFont(new Font("Tahoma", Font.BOLD, 15));
						scrollPaneSecondCV.setViewportView(secondCVSectionsJList);
						secondCVSectionsJList.setVisibleRowCount(100);
						
						openSecondCounter++;
						checkCounter++;
						
				    }
				    if(checkCounter>=2) btnCompare.setEnabled(true);
				}
				
				if (openSecondCounter==1) btnOpenSecondCv.setEnabled(false);
				CompareOrMergeTwoCVsJFrameWindow.setVisible(true);
				
				
				
				
			}
			
	
	});
		
	btnMerge.addActionListener(new ActionListener() {		
		int outputType;
		
		public void actionPerformed(ActionEvent e) {
			if (chckbxTxt.isSelected() || chckbxTex.isSelected()){
				if(openFirstCvFileChooserPath!=null && openSecondCvFileChooserPath!=null){
					if (chckbxTxt.isSelected()) outputType=0;
					if (chckbxTex.isSelected()) outputType=1;
					if (chckbxTxt.isSelected() && chckbxTex.isSelected()) outputType=2;
					OutputMerge.mergeTwoCv(cvtemplate1, cvtemplate2, firstCVSectionsJList, secondCVSectionsJList, outputType);
					JOptionPane.showMessageDialog(null, "Your Two CVs have been succesfully merged! \n Output folder: /outputfiles");
				}else{
					JOptionPane.showMessageDialog(null, "Two CVs need to be selected in order to merge.");
					
				}	
			}else{
				JOptionPane.showMessageDialog(null, "Please select an output type first.");
				
			}	
			
			
			
			
		}
		
	});
		
	}
	
	public int checkIfSameFiles(){
		
		if (openFirstCvFileChooserPath!=null && openSecondCvFileChooserPath!=null){
			String[] openFirstCvFileChooserPathParts = openFirstCvFileChooserPath.split("\\\\");
			String[] openSecondCvFileChooserPathParts = openSecondCvFileChooserPath.split("\\\\");
			String errorDialogText = "Error! Different CV file names. \n Proceed Anyway?";
			
			if (!openFirstCvFileChooserPathParts[openFirstCvFileChooserPathParts.length-1].equals(openSecondCvFileChooserPathParts[openSecondCvFileChooserPathParts.length-1])){
				int dialogResult = JOptionPane.showConfirmDialog (null, errorDialogText, "File Name Check", JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.NO_OPTION){
					
					return 1;
					
				}
				
		
			}
			return 2;
		}
		return 0;
	}
	
	public static void makeVisible(){
		
		CompareOrMergeTwoCVsJFrameWindow.setVisible(true);
		
	}
}

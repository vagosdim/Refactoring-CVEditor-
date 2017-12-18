package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.DropMode;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;

import data.manage.CVTemplate;

import javax.swing.JButton;

public class ComparisonJFrameWindow {

	JFrame frame;
	private JTextArea firstTextField;
	private JTextArea secondTextField;
	static String toSet;
	static CVTemplate cvTemplateCom1;
	static CVTemplate cvTemplateCom2;
	

	public ComparisonJFrameWindow(CVTemplate cvtemplate1, CVTemplate cvtemplate2, String toSetThis) {
		cvTemplateCom1 = cvtemplate1;
		cvTemplateCom2 = cvtemplate2;
		toSet=toSetThis;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		String toSetString1Pr = "", toSetString1EaT ="", toSetString2EaT = "", toSetString1FC ="" , toSetString2FC ="",
				toSetString1CS ="", toSetString2CS ="",toSetString2Pr = "", toSetString1Ai = "", toSetString2Ai = "",
				toSetString1I = "", toSetString2I = "", toSetString1SaE = "", toSetString2SaE = "", toSetString1PE = "", 
				toSetString2PE = "", toSetString2Co="", toSetString1Co = "";
		
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 614, 380);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFirstCv = new JLabel("First CV");
		lblFirstCv.setForeground(Color.WHITE);
		lblFirstCv.setBackground(Color.WHITE);
		lblFirstCv.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFirstCv.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirstCv.setBounds(94, 11, 98, 42);
		frame.getContentPane().add(lblFirstCv);
		
		JLabel lblSecondCv = new JLabel("Second CV");
		lblSecondCv.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecondCv.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSecondCv.setForeground(Color.WHITE);
		lblSecondCv.setBounds(392, 11, 98, 42);
		frame.getContentPane().add(lblSecondCv);
		
		firstTextField = new JTextArea();
		firstTextField.setBackground(UIManager.getColor("Button.light"));
		firstTextField.setFont(new Font("Tahoma", Font.PLAIN, 10));
		for (int i=0; i<cvTemplateCom1.getNumberOfSectionObj(); i++){
			if (cvTemplateCom1.getSectionObjTitle(i).equals(toSet) && toSet.equals("Professional Profile:")){
				toSetString1Pr = "Paragraph: \n " + cvTemplateCom1.getSectionObj(i).getParagraph(0).getContents();
				firstTextField.setText(toSetString1Pr);
				
			}else if(cvTemplateCom1.getSectionObjTitle(i).equals(toSet) && toSet.equals("Additional Information:")){
				toSetString1Ai = "Paragraph: \n " + cvTemplateCom1.getSectionObj(i).getParagraph(0).getContents();
				
				firstTextField.setText(toSetString1Ai);
				
			}else if(cvTemplateCom1.getSectionObjTitle(i).equals(toSet) && toSet.equals("Core Strengths:")){
				toSetString1Co = "Paragraph: \n " + cvTemplateCom1.getSectionObj(i).getParagraph(0).getContents();
				
				firstTextField.setText(toSetString1Co);
				
			}else if(cvTemplateCom1.getSectionObjTitle(i).equals(toSet)&& toSet.equals("Interests:")){
				toSetString1I = "Paragraph: \n " + cvTemplateCom1.getSectionObj(i).getParagraph(0).getContents();
				firstTextField.setText(toSetString1I);
				
			}else if(cvTemplateCom1.getSectionObjTitle(i).equals(toSet)&& toSet.equals("Education and Training:")){
				toSetString1EaT = "Items: ";
				int j=0;
    			for (j=0;j<cvTemplateCom1.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
    				toSetString1EaT = toSetString1EaT +" \n "+ "  " + cvTemplateCom1.getSectionObj(i).getBulletListItem(j).getContents()+ ", " 
    						+ cvTemplateCom1.getSectionObj(i).getBulletListItem(j).getStringDate();
    				
    			}
				
				firstTextField.setText(toSetString1EaT);
				
			}else if(cvTemplateCom1.getSectionObjTitle(i).equals(toSet)&& toSet.equals("Further Courses:")){
				toSetString1FC = "Items: ";
				int j=0;
    			for (j=0;j<cvTemplateCom1.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
    				toSetString1FC = toSetString1FC +" \n "+ "  " + cvTemplateCom1.getSectionObj(i).getBulletListItem(j).getContents()+ ", " 
    						+ cvTemplateCom1.getSectionObj(i).getBulletListItem(j).getStringDate();
    				
    			}
				
				firstTextField.setText(toSetString1FC);
				
			}else if(cvTemplateCom1.getSectionObjTitle(i).equals(toSet)&& toSet.equals("Career Summary:")){
				toSetString1CS = "Items: ";
				int j=0;
    			for (j=0;j<cvTemplateCom1.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
    				toSetString1CS = toSetString1CS +" \n "+ "  " + cvTemplateCom1.getSectionObj(i).getBulletListItem(j).getContents()+ ", " 
    						+ cvTemplateCom1.getSectionObj(i).getBulletListItem(j).getStringDate();
    				
    			}
				
				firstTextField.setText(toSetString1CS);
				
			}else if(cvTemplateCom1.getSectionObjTitle(i).equals(toSet)&& toSet.equals("Skills and Experience:")){
				
				
				for (int j=0;j<cvTemplateCom1.getSectionObj(i).getNumberOfBulletLists();j++){
					toSetString1SaE = toSetString1SaE + "Field: ";
					toSetString1SaE = toSetString1SaE + cvTemplateCom1.getSectionObj(i).getBulletList(j).getTitle();
    				
    				for(int k=0; k<cvTemplateCom1.getSectionObj(i).getBulletList(j).getNumberOfItems();k ++){
    					toSetString1SaE = toSetString1SaE + "\n    " + cvTemplateCom1.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents();
    				}
    				toSetString1SaE = toSetString1SaE + "\n";
    			}
				
				
				firstTextField.setText(toSetString1SaE);
				
			}else if(cvTemplateCom1.getSectionObjTitle(i).equals(toSet)&& toSet.equals("Professional Experience:")){
				
				
				int j=0;
	    		int k=0;
    			for (j=0;j<cvTemplateCom1.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
    				toSetString1PE = toSetString1PE + "Item: ";
    				toSetString1PE = toSetString1PE + cvTemplateCom1.getSectionObj(i).getBulletListItem(j).getContents()+ "\n";
    				for (k=0;k<cvTemplateCom1.getSectionObj(i).getBulletList(j).getNumberOfItems();k++){
    					if (k==0) toSetString1PE = toSetString1PE + "  Paragraph:";
    					if (k==1) toSetString1PE = toSetString1PE + "  List: ";
    					else toSetString1PE = toSetString1PE + "    ";
    					toSetString1PE = toSetString1PE + cvTemplateCom1.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents()+ " \n";
    					
    				}
    				
    			}
				
				
				firstTextField.setText(toSetString1PE);
				
			}
			
		}
		firstTextField.setEditable(false);
		firstTextField.setBounds(10, 51, 288, 228);
		frame.getContentPane().add(firstTextField);
		firstTextField.setColumns(10);
		
		secondTextField = new JTextArea();
		secondTextField.setBackground(UIManager.getColor("Button.light"));
		secondTextField.setFont(new Font("Tahoma", Font.PLAIN, 10));
		for (int i=0; i<cvTemplateCom2.getNumberOfSectionObj(); i++){
			if (cvTemplateCom2.getSectionObjTitle(i).equals(toSet) && toSet.equals("Professional Profile:")){ 
				toSetString2Pr = "Paragraph: \n " + cvTemplateCom2.getSectionObj(i).getParagraph(0).getContents();
				if (toSetString1Pr.equals(toSetString2Pr)) toSetString2Pr = "Second CV's Paragraph is \n \t the same as the first's."; 
				secondTextField.setText(toSetString2Pr);
				
			}else if(cvTemplateCom2.getSectionObjTitle(i).equals(toSet)  && toSet.equals("Additional Information:")){
				toSetString2Ai = "Paragraph: \n " + cvTemplateCom2.getSectionObj(i).getParagraph(0).getContents();
				if (toSetString1Ai.equals(toSetString2Ai)) toSetString2Ai = "Second CV's Paragraph is \n \t the same as the first's.";
				secondTextField.setText(toSetString2Ai);
				
			}else if(cvTemplateCom2.getSectionObjTitle(i).equals(toSet)  && toSet.equals("Core Strengths:")){
				toSetString2Co = "Paragraph: \n " + cvTemplateCom2.getSectionObj(i).getParagraph(0).getContents();
				if (toSetString1Co.equals(toSetString2Co)) toSetString2Co = "Second CV's Paragraph is \n \t the same as the first's.";
				secondTextField.setText(toSetString2Co);
				
			}else if(cvTemplateCom2.getSectionObjTitle(i).equals(toSet) && toSet.equals("Interests:")){
				toSetString2I = "Paragraph: \n " + cvTemplateCom2.getSectionObj(i).getParagraph(0).getContents();
				if (toSetString1I.equals(toSetString2I)) toSetString2I = "Second CV's Paragraph is \n \t the same as the first's.";
				secondTextField.setText(toSetString2I);
				
			}else if(cvTemplateCom2.getSectionObjTitle(i).equals(toSet) && toSet.equals("Education and Training:")){
				toSetString2EaT = "Items: ";
				int j=0;
    			for (j=0;j<cvTemplateCom2.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
    				toSetString2EaT = toSetString2EaT +" \n "+ "  " + cvTemplateCom2.getSectionObj(i).getBulletListItem(j).getContents()+ ", " 
    						+ cvTemplateCom2.getSectionObj(i).getBulletListItem(j).getStringDate();
    				
    			}
    			if (toSetString1EaT.equals(toSetString2EaT)) toSetString2EaT = "Second CV's Items are \n \t the same as the first's.";
				secondTextField.setText(toSetString2EaT);
				
			}else if(cvTemplateCom2.getSectionObjTitle(i).equals(toSet) && toSet.equals("Further Courses:")){
				toSetString2FC = "Items: ";
				int j=0;
    			for (j=0;j<cvTemplateCom2.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
    				toSetString2FC = toSetString2FC +" \n "+ "  " + cvTemplateCom2.getSectionObj(i).getBulletListItem(j).getContents()+ ", " 
    						+ cvTemplateCom2.getSectionObj(i).getBulletListItem(j).getStringDate();
    				
    			}
    			if (toSetString1FC.equals(toSetString2FC)) toSetString2FC = "Second CV's Items are \n \t the same as the first's.";
				secondTextField.setText(toSetString2FC);
				
			}else if(cvTemplateCom2.getSectionObjTitle(i).equals(toSet) && toSet.equals("Career Summary:")){
				toSetString2CS = "Items: ";
				int j=0;
    			for (j=0;j<cvTemplateCom2.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
    				toSetString2CS = toSetString2CS +" \n "+ "  " + cvTemplateCom2.getSectionObj(i).getBulletListItem(j).getContents()+ ", " 
    						+ cvTemplateCom2.getSectionObj(i).getBulletListItem(j).getStringDate();
    				
    			}
    			if (toSetString1CS.equals(toSetString2CS)) toSetString2CS = "Second CV's Items are \n \t the same as the first's.";
				secondTextField.setText(toSetString2CS);
				
			}else if(cvTemplateCom2.getSectionObjTitle(i).equals(toSet)&& toSet.equals("Skills and Experience:")){
				
				
				for (int j=0;j<cvTemplateCom2.getSectionObj(i).getNumberOfBulletLists();j++){
					toSetString2SaE = toSetString2SaE + "Field: ";
					toSetString2SaE = toSetString2SaE + cvTemplateCom2.getSectionObj(i).getBulletList(j).getTitle();
    				
    				for(int k=0; k<cvTemplateCom2.getSectionObj(i).getBulletList(j).getNumberOfItems(); k++){
    					toSetString2SaE = toSetString2SaE + "\n    " + cvTemplateCom2.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents();
    				}
    				toSetString2SaE = toSetString2SaE + "\n";
    			}
				
				if (toSetString1SaE.equals(toSetString2SaE)) toSetString2SaE = "Second CV's Fields and Items \n \t are the same as the first's.";
				secondTextField.setText(toSetString2SaE);
				
			}else if(cvTemplateCom2.getSectionObjTitle(i).equals(toSet)&& toSet.equals("Professional Experience:")){
				
				
				int j=0;
	    		int k=0;
    			for (j=0;j<cvTemplateCom2.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
    				toSetString2PE = toSetString2PE + "Item: ";
    				toSetString2PE = toSetString2PE + cvTemplateCom2.getSectionObj(i).getBulletListItem(j).getContents()+ "\n";
    				for (k=0;k<cvTemplateCom2.getSectionObj(i).getBulletList(j).getNumberOfItems();k++){
    					if (k==0) toSetString2PE = toSetString2PE + "  Paragraph:";
    					if (k==1) toSetString2PE = toSetString2PE + "  List: ";
    					else toSetString2PE = toSetString2PE + "    ";
    					toSetString2PE = toSetString2PE + cvTemplateCom2.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents()+ " \n";
    					
    				}
    				
    			}
				
    			if (toSetString1PE.equals(toSetString2PE)) toSetString2PE = "Second CV's Items, paragraphs and lists, \n \t \t are the same as the first's.";
				secondTextField.setText(toSetString2PE);
				
			}
		}
		secondTextField.setEditable(false);
		secondTextField.setBounds(312, 51, 276, 226);
		frame.getContentPane().add(secondTextField);
		secondTextField.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 307, 89, 23);
		frame.getContentPane().add(btnBack);
		
		btnBack.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
			}
		
		});
		
		
	}
}

package input.manage;

import java.awt.Window.Type;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import data.manage.CVTemplate;
import data.manage.Database;
import data.manage.Section;

public abstract class InputSystem {
	
	protected Scanner inputStream = null;
	protected Scanner checkStream = null;
	protected int checkIfSection = 0;
 	protected int notSkipLine = 0;
	protected ArrayList<Section> inputCvTemplateSectionObj = new ArrayList<Section>();
	
	abstract protected void skipLines();
	abstract protected void inputCvAddSection(int sectionCounter,CVTemplate cvtemplate) throws ParseException;
	
	public InputSystem() {
		super();
	}
	
	public void addCvDataFromFile(int index,String filePath,int error,String type) throws ParseException{
		this.initScanner(filePath);
		this.checkIfValid(error);
		this.skipLines();
		CVTemplate cvtemplate = this.getBaseInfo(type); 
		this.populateCvWithSections(cvtemplate);
	}
	
	protected void initScanner(String filePath) {
		try{
			inputStream = new Scanner(new FileInputStream(filePath));
			checkStream = new Scanner(new FileInputStream(filePath));
		}catch(FileNotFoundException e){
			System.out.println("Problem opening file.");
			System.exit(0);
		}
	}
	
	public void checkIfValid(int error) {
		int i=0;
		while(checkStream.hasNextLine()){
			i++;
			checkStream.nextLine();
		}
		
		if(i<error){ 
			JOptionPane.showMessageDialog(null, "Invalid input file. Reached eof while on General Info.");
			return;
		}
	}
	
	
	public CVTemplate getBaseInfo(String type) {
		int subLength = 0;
		if(type.equals("Tex")) {
			subLength = 2;
		}
		String tempString = inputStream.nextLine();
		String applicantName = tempString.substring(6,tempString.length()-subLength);
		tempString = inputStream.nextLine();
		String applicantAddress = tempString.substring(9,tempString.length()-subLength);
		tempString = inputStream.nextLine();
		String applicantHomeTelephone = tempString.substring(18,tempString.length()-subLength);
		tempString = inputStream.nextLine();
		String applicantWorkTelephone = tempString.substring(18,tempString.length()-subLength);
		tempString = inputStream.nextLine();
		String applicantEmail = tempString.substring(7,tempString.length()-subLength);
		CVTemplate cvTemplate =  new CVTemplate(applicantName, applicantAddress, applicantHomeTelephone, 
				applicantWorkTelephone, applicantEmail);
		return cvTemplate;
	}
	
	public void populateCvWithSections(CVTemplate cvtemplate) throws ParseException {

		int sectionCounter = 0;
		String checkEoF=null;
		while(inputStream.hasNextLine()){
			
			if(notSkipLine==0){
				checkEoF = inputStream.nextLine();
			}
			notSkipLine=0;
			if (checkEoF.equals("\\end{document}")) break;
		
			inputCvAddSection(sectionCounter, cvtemplate);
			
		}
		
		inputStream.close();
		checkStream.close();
		
		this.templateTypeIdentifier(cvtemplate);
		Database.addCVtemplateToList(cvtemplate);
		
	}
	
	public void templateTypeIdentifier(CVTemplate cvtemplate){
		int checkForFunctional=0;
		int checkForChronological=0;
		int checkForCombined=0;
		
		for (int i=0; i<cvtemplate.getNumberOfSectionObj(); i++){
			if(cvtemplate.getSectionObjTitle(i).equals("Skills and Experience:")){
				checkForFunctional++;
				checkForCombined++;
			}else if(cvtemplate.getSectionObjTitle(i).equals("Career Summary:")){
				checkForFunctional++;
			}else if(cvtemplate.getSectionObjTitle(i).equals("Professional Experience:")){
				checkForChronological++;
				checkForCombined++;
			}else if(cvtemplate.getSectionObjTitle(i).equals("Core Strengths:")){
				checkForChronological++;	
			}
		}
		
		if(cvtemplate.getNumberOfSectionObj()<4) JOptionPane.showMessageDialog(null, "Less than 4 sections.");
		
		if(checkForChronological!=2 && checkForCombined!=2 && checkForFunctional==2){ 
			JOptionPane.showMessageDialog(null, "Functional CV identified.");
		}else if(checkForChronological==2 && checkForCombined!=2 && checkForFunctional!=2){ 
			JOptionPane.showMessageDialog(null, "Chronological CV identified.");
		}else if(checkForChronological!=2 && checkForCombined==2 && checkForFunctional!=2){ 
			JOptionPane.showMessageDialog(null, "Combined CV identified.");
		}else{
			JOptionPane.showMessageDialog(null, "CV type not identified.");
		}
	}
	protected boolean isSingleParagraphSection(String checkNextLine) {
		if(checkNextLine.equals("Professional Profile:")||checkNextLine.equals("Additional Information:")
			||checkNextLine.equals("Interests:")||checkNextLine.equals("Core Strengths:")) {
			return true;
		}
		return false;
	}
	
	protected boolean isBulletListItem(String checkNextLine) {
		if(checkNextLine.equals("Education and Training:")||checkNextLine.equals("Further Courses:")
			||checkNextLine.equals("Career Summary:")) {
				return true;
			}
		return false;
	}
	
}

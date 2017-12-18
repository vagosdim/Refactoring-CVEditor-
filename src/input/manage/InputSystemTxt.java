package input.manage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

import data.manage.BulletList;
import data.manage.BulletListItem;
import data.manage.CVTemplate;
import data.manage.Database;
import data.manage.Section;

public class InputSystemTxt extends InputSystem{
	
	protected void inputCvAddSection(int sectionCounter, CVTemplate cvtemplate) throws ParseException{
		String checkNextLine=null; 
		
		if (inputStream.hasNextLine()){
			checkNextLine = inputStream.nextLine();
		}else{
			checkNextLine = "exit";
		}
		
		if (sectionRepetitionCheck(checkNextLine, cvtemplate)) return;
		
		if(isSingleParagraphSection(checkNextLine)){
			cvtemplate.addSectionObj(sectionCounter , new Section(checkNextLine));
			cvtemplate.getSectionObj(sectionCounter).addParagraph(inputStream.nextLine().substring(1));
			
		}else if(isBulletListItem(checkNextLine)){
			cvtemplate.addSectionObj(sectionCounter , new Section(checkNextLine));
			inputCvAddBulletListItem(sectionCounter , cvtemplate);
			
		}else if(checkNextLine.equals("Skills and Experience:")){
			cvtemplate.addSectionObj(sectionCounter , new Section("Skills and Experience:"));
			inputCvAddBulletList(sectionCounter , cvtemplate);
	
		}else if(checkNextLine.equals("Professional Experience:")){
			cvtemplate.addSectionObj(sectionCounter , new Section("Professional Experience:"));
			inputCvAddBulletListItemAndItemList(sectionCounter , cvtemplate);
			
		}else{
			if (!checkNextLine.equals("exit")) System.out.println("Invalid Input File "+checkNextLine);	
		}
		sectionCounter++;	
	}
	
	private void inputCvAddBulletList(int sectionCounter, CVTemplate cvtemplate) throws ParseException{
		int listCounter = 0;
		String checkNextLine=inputStream.nextLine();
		
		while (checkNextLine.substring(0, 2).equals("  ")){	
			cvtemplate.getSectionObj(sectionCounter).addBulletList(listCounter , new BulletList(checkNextLine.substring(2)));
			checkNextLine=inputStream.nextLine();
				
			while (checkNextLine.substring(0, 4).equals("    ")){
				cvtemplate.getSectionObj(sectionCounter).getBulletList(listCounter).addBulletListItem(new BulletListItem(checkNextLine.substring(4)));
				checkNextLine=inputStream.nextLine();
				
				if(checkNextLine.isEmpty()) break;	
			}
			
			if(checkNextLine.isEmpty()) break;
			listCounter++;	
		}
		notSkipLine=1;
	}
	
	private void inputCvAddBulletListItem(int sectionCounter, CVTemplate cvtemplate) throws ParseException{
		String checkNextLine=inputStream.nextLine();
		while (checkNextLine.substring(0, 2).equals("  ")){
			
			cvtemplate.getSectionObj(sectionCounter).addBulletListItem(new BulletListItem(getDate(checkNextLine),getWithoutDate(checkNextLine.substring(2))));
			checkNextLine=inputStream.nextLine();
			
			if(checkNextLine.isEmpty()) break;
		}
		notSkipLine=1;
	}
	
	private void inputCvAddBulletListItemAndItemList(int sectionCounter, CVTemplate cvtemplate) throws ParseException{
			
			int listCounter = 0;
			String checkNextLine=inputStream.nextLine();
		
			while (checkNextLine.substring(0, 2).equals("  ")){
				cvtemplate.getSectionObj(sectionCounter).addBulletListItem(new BulletListItem(getDate(checkNextLine),getWithoutDate(checkNextLine.substring(2))));
				cvtemplate.getSectionObj(sectionCounter).addBulletList(listCounter, new BulletList(checkNextLine));
				int listItemCounter = 0;
				checkNextLine=inputStream.nextLine();
			
				if(checkNextLine.substring(0, 4).equals("    ")){
					cvtemplate.getSectionObj(sectionCounter).getBulletList(listCounter).addBulletListItem(new BulletListItem(checkNextLine.substring(4)));
					checkNextLine=inputStream.nextLine();
					listItemCounter++;
				
					if(checkNextLine.isEmpty()) break;	
				}else{
					System.out.println("Wrong Input Professional Experience");
					break;
				}
			
				if(checkNextLine.substring(0, 4).equals("    ")){
					cvtemplate.getSectionObj(sectionCounter).getBulletList(listCounter).addBulletListItem(new BulletListItem(checkNextLine.substring(4)));
					checkNextLine=inputStream.nextLine();
					listItemCounter++;
				
					if(checkNextLine.isEmpty()) break;	
				
				}else{
					System.out.println("Wrong Input Professional Experience");
					break;
				}
				
				while (checkNextLine.substring(0, 6).equals("      ")){
					cvtemplate.getSectionObj(sectionCounter).getBulletList(listCounter).addBulletListItem(new BulletListItem(checkNextLine.substring(6)));
					checkNextLine=inputStream.nextLine();
					listItemCounter++;
					if(checkNextLine.isEmpty()) break;	
				}
			
			if(checkNextLine.isEmpty()) break;
			listCounter++;
		}
		notSkipLine=1;
	}
	
	private boolean sectionRepetitionCheck(String checkNextLine, CVTemplate cvtemplate){
		for(int i=0; i<cvtemplate.getNumberOfSectionObj(); i++){
			if( checkNextLine.equals(cvtemplate.getSectionObjTitle(i))){
				JOptionPane.showMessageDialog(null, "Repeated section of " + checkNextLine + ". Skipping.");
				if(checkNextLine.equals("Professional Profile:") || checkNextLine.equals("Additional Information:") || 
						checkNextLine.equals("Interests:")){
					inputStream.nextLine();
					
					return true;
				}
			}
		}
		return false;
	}
	
	private Date getDate(String input) throws ParseException{
		Date date;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		date = sdf.parse(input.substring(input.length() - 10));
		
		return date;
	}
	
	private String getWithoutDate(String input){
		
		return input.substring(0, input.length() - 12);	
	}
	
	public int templateTypeSilentIdentifier(CVTemplate cvtemplate){
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
		
		if(checkForChronological!=2 && checkForCombined!=2 && checkForFunctional==2){ 
			return 0;
		}else if(checkForChronological==2 && checkForCombined!=2 && checkForFunctional!=2){ 
			return 1;
		}else if(checkForChronological!=2 && checkForCombined==2 && checkForFunctional!=2){ 
			return 2;
		}else{
			return -1;
		}
	}
	
	public int checkIfCVFile(String filePath){
		
		Scanner fileStream = null;
		try {
			fileStream = new Scanner(new FileInputStream(filePath));
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
	    int i=0;
		while(fileStream.hasNextLine() && fileStream.nextLine().length()>0){
			i++;
			fileStream.nextLine();
		}
		
		if(i<4){ 
			JOptionPane.showMessageDialog(null, "Invalid input file. Not a CV file.");
			return 1;
		}
		return 0;
	}

	protected void skipLines() {
		return;		
	}
}
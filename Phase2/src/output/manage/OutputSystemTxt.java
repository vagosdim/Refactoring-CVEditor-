package output.manage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import data.manage.CVTemplate;
import data.manage.Database;

public class OutputSystemTxt {
	
	
	static PrintWriter outputStream;
	
	public static void saveApplicantInfoToTxt(String savePath, int applicantIndex){
		
		CVTemplate cvtemplate = Database.getCvtemplateFromArrayList(applicantIndex);
		
		outputStream = null;
		try
		{
			outputStream = new PrintWriter(new FileOutputStream
					(savePath+"//"+cvtemplate.getApplicantName()+".txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Problem opening: "+savePath+"//"+cvtemplate.getApplicantName()+".txt");
		}
		
		
		outputStream.println("Name: "+cvtemplate.getApplicantName());
		outputStream.println("Address: "+cvtemplate.getApplicantAddress());
		outputStream.println("Telephone (Home): "+cvtemplate.getApplicantHomeTelephone());
		outputStream.println("Telephone (Work): "+cvtemplate.getApplicantWorkTelephone());
		outputStream.println("Email: "+cvtemplate.getApplicantEmail());
		outputStream.println();
		
		for (int i=cvtemplate.getNumberOfSectionObj()-1; i>=0; i--){
	    	
	    	
			outputStream.println(cvtemplate.getSectionObjTitle(i));
	    	if(cvtemplate.getSectionObjTitle(i).equals("Professional Profile:") ||
	    			cvtemplate.getSectionObjTitle(i).equals("Core Strenghts:") ||
	    			cvtemplate.getSectionObjTitle(i).equals("Additional Information:") ||
	    			cvtemplate.getSectionObjTitle(i).equals("Interests:")){
	    		outputStream.println("\t"+cvtemplate.getSectionObj(i).getParagraph(0).getContents());
	    		
	    	}else if(cvtemplate.getSectionObjTitle(i).equals("Career Summary:") || 
	    			cvtemplate.getSectionObjTitle(i).equals("Education and Training:") ||
	    			cvtemplate.getSectionObjTitle(i).equals("Further Courses:")){
	    			int j=0;
	    			for (j=0;j<cvtemplate.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
	    				outputStream.println("  "+cvtemplate.getSectionObj(i).getBulletListItem(j).getContents()+", "+cvtemplate.getSectionObj(i).getBulletListItem(j).getStringDate());
	    				
	    			}
	    		
	    	}else if(cvtemplate.getSectionObjTitle(i).equals("Skills and Experience:")){

	    			for (int j=0;j<cvtemplate.getSectionObj(i).getNumberOfBulletLists();j++){
	    				outputStream.println("  "+cvtemplate.getSectionObj(i).getBulletList(j).getTitle());
	    				
	    				for(int k=0; k<cvtemplate.getSectionObj(i).getBulletList(j).getNumberOfItems();k ++){
	    					outputStream.println("    "+cvtemplate.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents());
	    				}
	    			}
	    	}else if(cvtemplate.getSectionObjTitle(i).equals("Professional Experience:")){
	    		
	    		int j=0;
	    		int k=0;
    			for (j=0;j<cvtemplate.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
    				outputStream.println("  "+cvtemplate.getSectionObj(i).getBulletListItem(j).getContents()+", "+cvtemplate.getSectionObj(i).getBulletListItem(j).getStringDate());
    			
    				for (k=0;k<cvtemplate.getSectionObj(i).getBulletList(j).getNumberOfItems();k++){
    					if(k==0 || k==1) outputStream.println("    "+cvtemplate.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents());
    					else outputStream.println("      "+cvtemplate.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents());
    				}
    				
    			}
	    	}
	    	outputStream.println();
	    }
		
		
		outputStream.close();
	}
	
}

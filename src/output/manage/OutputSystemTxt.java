package output.manage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import data.manage.CVTemplate;
import data.manage.Database;

public class OutputSystemTxt extends OutputSystem{
	
	public void saveApplicantInfo(String savePath, int applicantIndex){
		
		CVTemplate cvtemplate = initOutputStream(savePath, applicantIndex, "txt");
		writeBaseInfo("txt", cvtemplate);
		String title;
		for (int i=cvtemplate.getNumberOfSectionObj()-1; i>=0; i--){
			title = cvtemplate.getSectionObjTitle(i);
			outputStream.println(title);
	    	if(title.equals("Professional Profile:") ||
	    			title.equals("Core Strenghts:") ||
	    			title.equals("Additional Information:") ||
	    			title.equals("Interests:")){
	    		outputStream.println("\t"+cvtemplate.getSectionObj(i).getParagraph(0).getContents());
	    		
	    	}else if(title.equals("Career Summary:") || 
	    			title.equals("Education and Training:") ||
	    			title.equals("Further Courses:")){
	    			for (int j=0;j<cvtemplate.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
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
	    		
    			for (int j=0;j<cvtemplate.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
    				outputStream.println("  "+cvtemplate.getSectionObj(i).getBulletListItem(j).getContents()+", "+cvtemplate.getSectionObj(i).getBulletListItem(j).getStringDate());
    			
    				for (int k=0;k<cvtemplate.getSectionObj(i).getBulletList(j).getNumberOfItems();k++){
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
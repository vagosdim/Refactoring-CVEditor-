package output.manage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import data.manage.CVTemplate;
import data.manage.Database;

public class OutputSystemLatex extends OutputSystem{

	
	public void saveApplicantInfo(String savePath, int applicantIndex){
		
		CVTemplate cvtemplate =  initOutputStream(savePath, applicantIndex, "tex");
		writeBaseInfo("tex", cvtemplate);
		String title ;
		for (int i=cvtemplate.getNumberOfSectionObj()-1; i>=0; i--){
	    	title = cvtemplate.getSectionObjTitle(i);
			outputStream.println("\\section{"+title+"}");
	    	if(title.equals("Professional Profile:") ||
	    			title.equals("Core Strenghts:") ||
	    			title.equals("Additional Information:") ||
	    			title.equals("Interests:")){
	    		outputStream.println(cvtemplate.getSectionObj(i).getParagraph(0).getContents()+"\\"+"\\");
	    		
	    	}else if(title.equals("Career Summary:") || 
	    			title.equals("Education and Training:") ||
	    			title.equals("Further Courses:")){
	    			outputStream.println("\\begin{itemize}");
	    			for (int j=0;j<cvtemplate.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
	    				outputStream.println("\\item "+cvtemplate.getSectionObj(i).getBulletListItem(j).getContents()+" "+cvtemplate.getSectionObj(i).getBulletListItem(j).getStringDate());				
	    			}
	    			outputStream.println("\\end{itemize}");
	    		
	    	}else if(cvtemplate.getSectionObjTitle(i).equals("Skills and Experience:")){

	    			for (int j=0;j<cvtemplate.getSectionObj(i).getNumberOfBulletLists();j++){
	    				outputStream.println("\\subsection{"+cvtemplate.getSectionObj(i).getBulletList(j).getTitle()+"}");
	    				outputStream.println("\\begin{itemize}");
	    				for(int k=0; k<cvtemplate.getSectionObj(i).getBulletList(j).getNumberOfItems();k ++){
	    					outputStream.println("\\item "+cvtemplate.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents());
	    				}
	    				outputStream.println("\\end{itemize}");
	    			}
	    	}else if(cvtemplate.getSectionObjTitle(i).equals("Professional Experience:")){
	    		
    			for (int j=0;j<cvtemplate.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
    				outputStream.println("\\subsection{"+cvtemplate.getSectionObj(i).getBulletListItem(j).getContents()+" "+cvtemplate.getSectionObj(i).getBulletListItem(j).getStringDate()+"}");
    				outputStream.println("\\begin{itemize}");
    				for (int k=0;k<cvtemplate.getSectionObj(i).getBulletList(j).getNumberOfItems();k++){
    					if(k==0 || k==1) outputStream.println("\\item "+cvtemplate.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents());
    					else outputStream.println(cvtemplate.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents()+"\\"+"\\");
    				}
    				outputStream.println("\\end{itemize}");
    			}
	    	}
	    	outputStream.println();
	    }	
		outputStream.println("\\end{document}");
		outputStream.close();
	}
}
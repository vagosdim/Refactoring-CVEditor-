package gui;
import data.manage.CVTemplate;
import data.manage.Database;
import input.manage.InputSystemFactory;
import input.manage.InputSystemLatex;
import input.manage.InputSystemTxt;
import output.manage.OutputSystemLatex;
import output.manage.OutputSystemTxt;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class OpenCVListener implements ActionListener {

	private JFrame cvMainWindow;
	private InputSystemFactory inputSystemFactory;
	private InputSystemTxt inputSystemTxt = new InputSystemTxt();
	private InputSystemLatex inputSystemLatex = new InputSystemLatex();
	
	public OpenCVListener(JFrame cvMainWindow) {
		this.cvMainWindow = cvMainWindow;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		JFileChooser openCvFileChooser = new JFileChooser();
		FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter( "txt files (*.txt)", "txt");
		openCvFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("tex files (*.tex)", "tex"));
		openCvFileChooser.setFileFilter(xmlfilter);
		openCvFileChooser.setCurrentDirectory(new java.io.File("."));
		openCvFileChooser.setDialogTitle("Please select the CV file.");

		if(openCvFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		    String openCvFileChooserPath = openCvFileChooser.getSelectedFile().toString();
		    JOptionPane.showMessageDialog(null, openCvFileChooserPath, "Directory of chosen CV", JOptionPane.INFORMATION_MESSAGE);
		    if(openCvFileChooserPath.substring(openCvFileChooserPath.length()-3).equals("txt")){
			    try {
					inputSystemTxt.addCvDataFromFile(0,openCvFileChooserPath,4,"Text");
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
		    }else if(openCvFileChooserPath.substring(openCvFileChooserPath.length()-3).equals("tex")){
			    try {
					inputSystemLatex.addCvDataFromFile(0, openCvFileChooserPath,11,"Tex");
				} catch (ParseException e1) {
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
		    			for (int j=0;j<cvtemplate.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
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
	    			for (int j=0;j<cvtemplate.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
	    				System.out.println(cvtemplate.getSectionObj(i).getBulletListItem(j).getContents());
	    				for (int k=0;k<cvtemplate.getSectionObj(i).getBulletList(j).getNumberOfItems();k++){
	    					System.out.println(cvtemplate.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents());
	    				}
	    			}
		    	}
		    }
		    System.out.println(inputSystemTxt.templateTypeSilentIdentifier(cvtemplate));
		    if (inputSystemTxt.templateTypeSilentIdentifier(cvtemplate)==0){
		    	cvMainWindow.setVisible(false);
				System.out.println("Opening funct");
				FunctionalJFrameWindow.makeVisible(cvtemplate);
		    }else if (inputSystemTxt.templateTypeSilentIdentifier(cvtemplate)==1){
		    	cvMainWindow.setVisible(false);
		    	System.out.println("Opening chron");
				ChronologicalJFrameWindow.makeVisible(cvtemplate);
		    }else if (inputSystemTxt.templateTypeSilentIdentifier(cvtemplate)==2){
		    	cvMainWindow.setVisible(false);
		    	System.out.println("Opening comb");
				CombinedJFrameWindow.makeVisible(cvtemplate);
		    }
		    OutputSystemTxt.saveApplicantInfoToTxt("outputFiles", 0);
		    OutputSystemLatex.saveApplicantInfoToLatex("outputFiles", 0);
		}
	}
}

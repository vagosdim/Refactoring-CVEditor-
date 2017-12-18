package output.manage;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JList;

import data.manage.BulletList;
import data.manage.BulletListItem;
import data.manage.CVTemplate;
import data.manage.Database;
import data.manage.Section;

public class OutputMerge {

	private CVTemplate cvtemplatemerge;
	private int checkDifferent, objectIndex;
	private int checkDifferentPE=0;
	private OutputSystemTxt outputSystemTxt = new OutputSystemTxt();
	private OutputSystemLatex outputSystemLatex = new OutputSystemLatex();
	
	public void mergeTwoCv(CVTemplate cvtemplate1, CVTemplate cvtemplate2, JList<String> firstCVSectionsJList,JList<String> secondCVSectionsJList, int outputType){
		
		objectIndex=0;
		
		String applicantName = cvtemplate1.getApplicantName();
		String applicantAddress = cvtemplate1.getApplicantAddress();
		String applicantHomeTelephone = cvtemplate1.getApplicantHomeTelephone();
		String applicantWorkTelephone = cvtemplate1.getApplicantWorkTelephone();
		String applicantEmail = cvtemplate1.getApplicantEmail();
		cvtemplatemerge= new CVTemplate(applicantName, applicantAddress, applicantHomeTelephone, 
				applicantWorkTelephone, applicantEmail);
		
		checkCVSectionJList(firstCVSectionsJList, cvtemplate1, secondCVSectionsJList, cvtemplate2);
		checkCVSectionJList(secondCVSectionsJList, cvtemplate1, firstCVSectionsJList, cvtemplate2);
		
		cvtemplatemerge=sortSections(cvtemplatemerge);
		
		Database.addCVtemplateToList(cvtemplatemerge);
		if(outputType==0){
			outputSystemTxt.saveApplicantInfo("outputFiles", Database.getCVtemplateArrayListSize()-1);
		}else if(outputType==1){
			outputSystemLatex.saveApplicantInfo("outputFiles", Database.getCVtemplateArrayListSize()-1);
		}else if(outputType==2){
			outputSystemTxt.saveApplicantInfo("outputFiles", Database.getCVtemplateArrayListSize()-1);
			outputSystemLatex.saveApplicantInfo("outputFiles", Database.getCVtemplateArrayListSize()-1);
		}
	}
	
	private void checkCVSectionJList(JList<String> firstCVSectionsJList,CVTemplate cvtemplate1,JList<String> secondCVSectionsJList,CVTemplate cvtemplate2) {
		
		for (int i=0; i<firstCVSectionsJList.getSelectedValuesList().size(); i++){
			checkDifferent =0;
			for (int j=0; j<secondCVSectionsJList.getSelectedValuesList().size(); j++){
				if (firstCVSectionsJList.getSelectedValuesList().get(i).equals(secondCVSectionsJList.getSelectedValuesList().get(j))){
					addToCVSameSection(cvtemplate1,cvtemplate2,firstCVSectionsJList.getSelectedValuesList().get(i));
					checkDifferent=1;
					break;
				}				
			}
			
			if (checkDifferent==0) addToCVDifferentSection(cvtemplate1, cvtemplate2 ,firstCVSectionsJList.getSelectedValuesList().get(i));
		}
	}
	
	private void addToCVSameSection(CVTemplate cvtemplate1, CVTemplate cvtemplate2, String section){
		if(section.equals("Professional Profile") || section.equals("Additional Information") 
				|| section.equals("Interests")  ){
			for(int i=0; i < cvtemplate1.getNumberOfSectionObj(); i++){	
				if(cvtemplate1.getSectionObjTitle(i).equals(section+":")){
					if (checkIfSameSection(cvtemplatemerge,cvtemplate1.getSectionObj(i))==0){
						cvtemplatemerge.addSectionObj(objectIndex, cvtemplate1.getSectionObj(i));
						objectIndex++;
					}
				}
			}	
			
		}else if(section.equals("Career Summary") || section.equals("Education and Training") 
				|| section.equals("Further Courses") || section.equals("Skills and Experience") ){
			
			for(int i=0; i < cvtemplate1.getNumberOfSectionObj(); i++){	
				for(int j=0; j < cvtemplate2.getNumberOfSectionObj(); j++){
					if(cvtemplate1.getSectionObjTitle(i).equals(section+":") && cvtemplate2.getSectionObjTitle(j).equals(section+":")){
						addToCVSameSectionCompareItems(cvtemplate1.getSectionObj(i), cvtemplate2.getSectionObj(j),section);
					}
				}
			}
			
		}else if(section.equals("Professional Experience")){
			
			for(int i=0; i < cvtemplate1.getNumberOfSectionObj(); i++){	
				for(int j=0; j < cvtemplate2.getNumberOfSectionObj(); j++){
					if(checkDifferentPE==0 && cvtemplate1.getSectionObjTitle(i).equals(section+":") && cvtemplate2.getSectionObjTitle(j).equals(section+":")){
						cvtemplatemerge.addSectionObj(objectIndex, cvtemplate1.getSectionObj(i));
						objectIndex++;
						checkDifferentPE=1;
					}
				}
			}
		}	
	}

	
	private void checkVariableAction(Section section, Section tempSection, int bulletListCounter, int i) {
		tempSection.addBulletListItem(section.getBulletListItem(i));
		tempSection.addBulletList(bulletListCounter,new BulletList (section.getBulletListItem(i).getContents()));
		bulletListCounter++;
		for (int k=0;k<section.getBulletList(i).getNumberOfItems();k++){
			tempSection.addBulletListItem(section.getBulletList(bulletListCounter).getBulletListItem(k));
		}
	}
	
	private void addToCVSameSectionCompareListsAndItems(Section section1, Section section2, String section){
		Section tempSection= new Section(section+":");
		
		for(int i=0; i<section1.getSizeOfSingleBulletListItems()-1;i++){
			for(int j=0; j<section2.getSizeOfSingleBulletListItems()-1;j++){
				if(!section1.getBulletListItem(i).getContents().equals(section2.getBulletListItem(j).getContents())){
					
					int checkOne=0, checkTwo=0, bulletListCounter1=0, bulletListCounter2=0;
						
					for (int p=0; p<tempSection.getSizeOfSingleBulletListItems(); p++){
						String tempSectionCVItem=tempSection.getBulletListItem(p).getContents()+
								tempSection.getBulletListItem(p).getStringDate();
						if(section1.getBulletListItem(i).getContents().equals(tempSectionCVItem)){
							checkOne++;
						}
						if(section2.getBulletListItem(j).getContents().equals(tempSectionCVItem)){
							checkTwo++;
						}
						if (checkOne==1 && checkTwo==1) break;
						
						if(checkOne==0){
							checkVariableAction(section1, tempSection, bulletListCounter1, i);
						}
						if(checkTwo==0){ 
							checkVariableAction(section2, tempSection, bulletListCounter2, i);
		    			}
					}
				}
			}
		}
		cvtemplatemerge.addSectionObj(objectIndex, tempSection);
		objectIndex++;
	}
	
	private void addToCVSameSectionCompareLists(Section section1, Section section2,String section){
		Section tempSection = new Section(section+":");
		int bulletListIndex=0;
		for(int i=0;i<section1.getNumberOfBulletLists();i++){
			for(int j=0; j<section2.getNumberOfBulletLists();j++){
				
				if(!section1.getBulletList(i).getTitle().equals(section2.getBulletList(j).getTitle())){
					if(checkIfBulletListExists(tempSection,section2.getBulletList(j))==0){
						tempSection.addBulletList(bulletListIndex,section2.getBulletList(j));
						bulletListIndex++;
					}
					
				}else{
					for(int k=0;k<section1.getBulletList(i).getNumberOfItems();k++){
						for(int p=0;p<section2.getBulletList(j).getNumberOfItems();p++){
							if(!section1.getBulletList(i).getBulletListItem(k).getContents().
									equals(section2.getBulletList(j).getBulletListItem(p).getContents())){
								if(checkIfBulletListItemExists(tempSection.getBulletList(bulletListIndex-1),section2.getBulletList(j).getBulletListItem(p))==0){
									tempSection.getBulletList(bulletListIndex-1).addBulletListItem(section2.getBulletList(j).getBulletListItem(p));
									
								}
							}
						}
					}
				}
					tempSection.addBulletList(bulletListIndex, section1.getBulletList(i));
					bulletListIndex++;
			}
		}
		
		for(int i=0; i<tempSection.getNumberOfBulletLists();i++){
			for(int j=i+1;j<tempSection.getNumberOfBulletLists();j++){
				if(tempSection.getBulletList(i).getTitle().equals(tempSection.getBulletList(j).getTitle())){
					tempSection.removeBulletList(j);
				}
			}
		}
		
		if (checkIfSameSection(cvtemplatemerge,tempSection)==0){ 
			cvtemplatemerge.addSectionObj(objectIndex, tempSection);
			objectIndex++;
		}
	}
	
	private int checkIfBulletListItemExists(BulletList toCheckList, BulletListItem toCheckItem){
		int checkIfExists=0;
		for (int i=0;i<toCheckList.getNumberOfItems();i++){
			if(toCheckList.getBulletListItem(i).getContents().equals(toCheckItem.getContents())){
				checkIfExists=1;
				break;
			}
		}
		return checkIfExists;
	}
	
	private int checkIfBulletListExists(Section toCheckSection, BulletList toCheckList){
		int checkIfExists=0;
		for (int i=0;i<toCheckSection.getNumberOfBulletLists();i++){
			if(toCheckSection.getBulletList(i).getTitle().equals(toCheckList.getTitle())){
				checkIfExists=1;
				break;
			}
		}
		return checkIfExists;
	}
	
	private void addToCVSameSectionCompareItems(Section section1, Section section2,String section){
		
		Section tempSection = new Section(section+":");
		for (int k=0; k<section1.getSizeOfSingleBulletListItems();k++){
			for (int l=0; l<section2.getSizeOfSingleBulletListItems();l++){
				String tempCVItem1 = section1.getBulletListItem(k).getContents()+ section1.getBulletListItem(k).getStringDate();
				String tempCVItem2=section2.getBulletListItem(l).getContents()+ section2.getBulletListItem(l).getStringDate();
				if(!tempCVItem1.equals(tempCVItem2)){
					int checkOne=0, checkTwo=0;
					
					for (int p=0; p<tempSection.getSizeOfSingleBulletListItems(); p++){
						String tempSectionCVItem=tempSection.getBulletListItem(p).getContents()+
								tempSection.getBulletListItem(p).getStringDate();
						if(tempCVItem1.equals(tempSectionCVItem)){
							checkOne++;
						}
						if(tempCVItem2.equals(tempSectionCVItem)){
							checkTwo++;
						}
						if (checkOne==1 && checkTwo==1) break;
					}
					
					if(checkOne==0) tempSection.addBulletListItem(section1.getBulletListItem(k));
					if(checkTwo==0) tempSection.addBulletListItem(section2.getBulletListItem(l));
				}
			}
		}
		
		if (checkIfSameSection(cvtemplatemerge,tempSection)==0){ 
			cvtemplatemerge.addSectionObj(objectIndex, tempSection);
			objectIndex++;
		}
	}
	
	private int checkIfSameSection(CVTemplate cvtemplatecheck, Section cvsectioncheck){
		int checkIfSameSection=0;
		for (int i=0;i<cvtemplatecheck.getNumberOfSectionObj();i++){
			if(cvtemplatecheck.getSectionObj(i).getTitle().equals(cvsectioncheck.getTitle())){
				checkIfSameSection=1;
				break;
			}
		}
		
		return checkIfSameSection;
	}
	
	private void addToCVDifferentSection(CVTemplate cvtemplate1, CVTemplate cvtemplate2, String sectiontitle){
		
		for(int i=0; i < cvtemplate1.getNumberOfSectionObj(); i++){
			if(cvtemplate1.getSectionObjTitle(i).equals(sectiontitle+":")){
				
				cvtemplatemerge.addSectionObj(objectIndex, cvtemplate1.getSectionObj(i));
				objectIndex++;
			}
		}
	}
	
	private CVTemplate sortSections(CVTemplate cvtemplate){
		CVTemplate tempCVTemplate = new CVTemplate(cvtemplate.getApplicantName(), cvtemplate.getApplicantAddress(),
				cvtemplate.getApplicantHomeTelephone(), cvtemplate.getApplicantWorkTelephone(), cvtemplate.getApplicantEmail());
		int tempCount=0;
		String title;
		for (int i=cvtemplate.getNumberOfSectionObj()-1; i>=0; i--){
			title = cvtemplate.getSectionObjTitle(i);
			if(title.equals("Professional Profile:")||title.equals("Skills and Experience:")||
					title.equals("Core Strengths:")||title.equals("Professional Experience:")||
					title.equals("Career Summary:")||title.equals("Education and Training:")||
					title.equals("Further Courses:")||title.equals("Additional Information:")||
					title.equals("Interests:")){
				tempCVTemplate.addSectionObj(tempCount, cvtemplate.getSectionObj(i));
				tempCount++;
			}
		}
		cvtemplate = tempCVTemplate;
		return cvtemplate;
	}
}
package dataManagePackage;

import java.util.ArrayList;

public class CVTemplate {
	
	protected String applicantName, applicantAddress, applicantHomeTelephone, applicantWorkTelephone, applicantEmail;
	protected ArrayList<Section> cvTemplateSectionObj = new ArrayList<Section>();
	
	
	public CVTemplate(String applicantName, String applicantAddress, String applicantHomeTelephone, String applicantWorkTelephone, 
			String applicantEmail){
		
		this.applicantName = applicantName;
		this.applicantAddress = applicantAddress;
		this.applicantHomeTelephone = applicantHomeTelephone;
		this.applicantWorkTelephone = applicantWorkTelephone;
		this.applicantEmail = applicantEmail;
		
	}
	
	
	
	public static void addParagraph(){
		
		
	}
	
	public void setApplicantName(String name){
		applicantName = name;	
	}
	
	public void setApplicantAddress(String address){
		applicantAddress = address;
	}
	
	public void setApplicantHomeTelephone(String homeTelephone){
		applicantHomeTelephone = homeTelephone;
	}
	
	public void setApplicantWorkTelephone(String workTelephone){
		applicantWorkTelephone = workTelephone;
	}
	
	public void setApplicantEmail(String email){
		applicantEmail = email;
	}
	
	public void addSectionObj(int index  ,Section section){
		
		cvTemplateSectionObj.add(index,section);
		
	}
	
	public Section getSectionObj(int index){
	
		return cvTemplateSectionObj.get(index);
		
	}
	
	public String getSectionObjTitle(int index){
		return cvTemplateSectionObj.get(index).getTitle();
		
	}
	
	public int getNumberOfSectionObj(){
		return cvTemplateSectionObj.size();
		
	}
	
	public String getApplicantName(){
		return applicantName;	
	}
	
	public String getApplicantAddress(){
		return applicantAddress;	
	}
	
	public String getApplicantHomeTelephone(){
		return applicantHomeTelephone;	
	}
	
	public String getApplicantWorkTelephone(){
		return applicantWorkTelephone;	
	}
	
	public String getApplicantEmail(){
		return applicantEmail;	
	}
	
}

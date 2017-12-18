package output.manage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import org.omg.CORBA.PRIVATE_MEMBER;

import data.manage.CVTemplate;
import data.manage.Database;

public abstract class OutputSystem {
	protected PrintWriter outputStream;
	
	public abstract void saveApplicantInfo(String savePath, int applicantIndex);
	
	public CVTemplate initOutputStream(String savePath, int applicantIndex, String type) {
		CVTemplate cvtemplate = Database.getCvtemplateFromArrayList(applicantIndex);
		outputStream = null;
		try
		{
			outputStream = new PrintWriter(new FileOutputStream
					(savePath+"//"+cvtemplate.getApplicantName()+"."+type));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Problem opening: "+savePath+"//"+cvtemplate.getApplicantName()+"."+type);
		}
		return cvtemplate;
	}
	
	public void writeBaseInfo(String type, CVTemplate cvtemplate) {
		if(type.equals("tex")) {
			outputStream.println("\\documentclass[12pt]{article}");
			outputStream.println("\\begin{document}");
			outputStream.println("\\title{CV of "+cvtemplate.getApplicantName()+"}");
			outputStream.println("\\maketitle");
			outputStream.println("\\section{General Information}");
		}
		outputStream.println("Name: "+cvtemplate.getApplicantName()+"\\"+"\\");
		outputStream.println("Address: "+cvtemplate.getApplicantAddress()+"\\"+"\\");
		outputStream.println("Telephone (Home): "+cvtemplate.getApplicantHomeTelephone()+"\\"+"\\");
		outputStream.println("Telephone (Work): "+cvtemplate.getApplicantWorkTelephone()+"\\"+"\\");
		outputStream.println("Email: "+cvtemplate.getApplicantEmail()+"\\"+"\\");
		outputStream.println();
	}
}
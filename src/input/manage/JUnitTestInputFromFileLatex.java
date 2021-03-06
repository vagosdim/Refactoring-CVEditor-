package input.manage;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

import data.manage.CVTemplate;
import data.manage.Database;

public class JUnitTestInputFromFileLatex {
	int i = 0;
	private InputSystemLatex inputSystemLatex = new InputSystemLatex();
	@Test
	public void test() throws ParseException {
		
		inputSystemLatex.addCvDataFromFile(0,"outputFiles//KostaPapadopoulos.tex",11,"Tex");
		CVTemplate cvtemplate1 = Database.getCvtemplateFromArrayList(0);
		if (cvtemplate1.getApplicantName().equals("Kosta Papadopoulos")){	//Check correct name insertion
			i++;
		}
		if (cvtemplate1.getApplicantEmail().equals("kostaspap@gmail.com")){  //check correct email insertion (general information)
			i++;
		}
		if(cvtemplate1.getNumberOfSectionObj()== 7){  //check correct number of sections
			
			i++;
		}
		for(int j=0; j<cvtemplate1.getNumberOfSectionObj(); j++){
			if(cvtemplate1.getSectionObjTitle(j).equals("Skills and Experience:")){ // check if correct insertion of section title
				i++;
				for (int k=0; k<cvtemplate1.getSectionObj(j).getNumberOfBulletLists();k++){
					if(cvtemplate1.getSectionObj(j).getBulletList(k).getTitle().equals("Skills and Experience on Programming")){
						i++;							//check if correct insertion of bulletList
						
					}
				}
			}
		}
		
		assertEquals(i,5);
	}

}

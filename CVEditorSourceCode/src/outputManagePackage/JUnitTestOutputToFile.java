package outputManagePackage;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.stream.Stream;

import org.junit.Test;

import dataManagePackage.CVTemplate;
import dataManagePackage.Database;
import inputManagePackage.InputSystemTxt;

public class JUnitTestOutputToFile {
	int i = 0;
	@Test
	public void test() throws ParseException, FileNotFoundException {
		
		InputSystemTxt.addCvDataFromFile(0,"outputFiles//KostaPapadopoulos.txt");
		OutputSystemTxt.saveApplicantInfoToTxt("outputFiles", 0);
		
		Scanner testOutputStream = new Scanner(new FileInputStream("outputFiles//KostaPapadopoulos.txt"));
		
		if (testOutputStream.nextLine().equals("Name: Kosta Papadopoulos")){	//Check correct name output
			i++;
		}
		testOutputStream.nextLine();
		testOutputStream.nextLine();
		testOutputStream.nextLine();
		if (testOutputStream.nextLine().equals("Email: kostaspap@gmail.com")){	//Check correct General Information output
			i++;
		}
		for(int j=0; j<8; j++){
			testOutputStream.nextLine();
		}
		if (testOutputStream.nextLine().equals("Skills and Experience:")){	//Check correct Section Title output
			i++;
		}
		if (testOutputStream.nextLine().equals("  Skills and Experience on Programming")){	//Check correct BulletList output
			i++;
		}
		if (testOutputStream.nextLine().equals("    Java")){	//Check correct BulletListItem output
			i++;
		}
		
		
		assertEquals(i,5);
	}

}

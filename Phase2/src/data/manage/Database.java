package data.manage;
import java.util.ArrayList;

public class Database {

	private static ArrayList<CVTemplate> cvtemplateArrayList = new ArrayList<CVTemplate>();
	
	public static void addCVtemplateToList(CVTemplate cvtemplate){
		cvtemplateArrayList.add(cvtemplate);
	}
	
	public static void addCVtemplateToList(int index, CVTemplate cvtemplate){
		cvtemplateArrayList.add(index, cvtemplate);
	}
	
	public static CVTemplate getCvtemplateFromArrayList(int index){
		return cvtemplateArrayList.get(index);
	}
	
	public static int getCVtemplateArrayListSize(){
		return cvtemplateArrayList.size();
	}
	
	public static void removeCVTemplateFromList(int index){
		cvtemplateArrayList.remove(index);
	}
}

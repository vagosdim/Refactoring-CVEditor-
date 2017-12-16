package dataManagePackage;

import java.util.ArrayList;

public class Section {
	String title;
	
	protected ArrayList<Paragraph> paragraphs = new ArrayList<Paragraph>();
	protected ArrayList<BulletListItem> bulletList = new ArrayList<BulletListItem>();
	protected ArrayList<BulletList> bulletLists = new ArrayList<BulletList>();
	
	public Section(String title){
		this.title = title;
		
	}
	
	public String getTitle(){
		return title;
	}
	
	public void addParagraph(String contents){
		paragraphs.add(new Paragraph(contents));
		
	}
	
	public void addBulletList(int index, BulletList bulletList){
		
		bulletLists.add(index, bulletList);
		
	}
	
	public ArrayList<BulletList> getArrayListBulletLists(){
		return bulletLists;
		
	}
	
	public int getSizeOfSingleBulletListItems(){
		return bulletList.size();
		
	}
	
	public int getNumberOfBulletLists(){
		return bulletLists.size();
		
	}
	
	public BulletList removeBulletList(int index){
		return bulletLists.remove(index);
		
	}
	
	public BulletList getBulletList(int index){
		return bulletLists.get(index);
		
	}
	
	public Paragraph getParagraph(int index){
	
		return paragraphs.get(index);
		
	}
	
	public void addBulletListItem(BulletListItem bulletListItem){
		
		bulletList.add(bulletListItem);
	
	}
	
	public BulletListItem getBulletListItem(int index){
		return bulletList.get(index);
		
	}
	
	
}



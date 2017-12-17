package data.manage;
import java.util.ArrayList;

public class BulletList {
	
	private String title;
	private ArrayList<BulletListItem> bulletList = new ArrayList<BulletListItem>();
	
	public BulletList(String title){
		this.title = title;
	}
	
	public void addBulletListItem(BulletListItem bulletListItem){
		bulletList.add(bulletListItem);	
	}
	
	public BulletListItem getBulletListItem(int index){
		return bulletList.get(index);	
	}
	
	public String getTitle(){
		return title;
	}
	
	public int getNumberOfItems(){
		return bulletList.size();	
	}
}

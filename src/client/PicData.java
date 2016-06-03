package client;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import processing.core.*;

public class PicData {
	
	private HashMap<String,PImage> imgData = new  HashMap<String,PImage>();
	private HashMap<String, ArrayList<String>> groupData = new HashMap<String, ArrayList<String>>();
	private String address = "./src/itemimg/";
	private MainApplet parent;
	
	public PicData(MainApplet parent){
		this.parent = parent;
		readData();
		loadImg();
	}
	
	public void readData(){
		String group, name;
		int times;
		try{
			Scanner filescn = new Scanner(new FileInputStream("./output.txt"));
			while(filescn.hasNext()){
				group = filescn.next();
				name = filescn.next();
				times = Integer.parseInt(filescn.next());
				if(groupData.containsKey(group)==false){
					ArrayList<String> list = new ArrayList<String>();
					list.add(name);
					groupData.put( group, list);
				}
				else{
					groupData.get(group).add(name);
				}	
			}
		}catch(IOException e){
			System.out.println("Can't not find file");
		}
	}
	
	public void loadImg(){
		for(String group : groupData.keySet()){
			for(String item : groupData.get(group)){
				PImage itemImg = parent.loadImage(address.concat(item).concat(".jpg"));
				itemImg.resize(300, 300);
				imgData.put(item, itemImg);

			}
		}
	}
	
	public HashMap<String,PImage> getImageData(){
		return imgData;
	}
	
	public HashMap<String, ArrayList<String>> getGroupData(){
		return groupData;
	}
}

package client;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;

public class ShowResult {

	int x,y,width,height, op_num;
	private MainApplet applet;
	private HashMap<Integer, ArrayList<Integer>> data = new HashMap<Integer, ArrayList<Integer>>() ;
	private PicData graphdata;
	private int id;
	private int[] sum =  new int [5];
	private String[] color = {"#6495ED", "#7FFFD4", "#32CD32", "#FF6347", "	#FFA54F","#B452CD", "#FF0000"};
	private String nowGroup;
	private float iniAngle = 0;
		
	ShowResult(MainApplet applet){
		this.x=0;
		this.y=0;
		this.width=300;
		this.height=670;
		this.applet=applet;
		graphdata = applet.picdata1;
		nowGroup = (String) graphdata.getGroupData().keySet().toArray()[0];
		id = 0;
		for(String g : graphdata.getGroupData().keySet()){
			ArrayList<Integer> seq = new ArrayList<Integer>();
			for(String itm : graphdata.getGroupData().get(g)){
				sum[id]+=graphdata.getItemData().get(itm);
				seq.add(graphdata.getItemData().get(itm));
			}
			data.put( id, seq);
			id++;
		}
		id = 0;
	}
	
	public void display(){
		if(x<=width/2+100){
			x++;
			iniAngle += applet.radians(1);
		}
		
		pieChart( 300, data.get(id));
	}
	
	void pieChart(float diameter, ArrayList<Integer> data) {
	    float lastAngle = 0;
	    int k = 0;
		for (int i : data) {
			float rad = 360 / sum[id] * i;
		    applet.fill(hex2Rgb(color[k]).getRGB());
		    applet.arc(x, height/2, diameter, diameter, iniAngle+lastAngle, iniAngle+lastAngle+applet.radians(rad));
		    applet.rect(500,100+40*k,100,20); //x,y,w,h
		    this.applet.fill( 0, 0, 0);
			this.applet.textSize(18);
			this.applet.text((String)graphdata.getGroupData().get(nowGroup).toArray()[k],650,115+40*k);
		    lastAngle += applet.radians(rad);  
		    k++;
		}
	}
	
	public static Color hex2Rgb(String colorStr) {
	    return new Color(
	            Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
	            Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
	            Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
	}
	public void Setini(){
		x  = 0;
		id = 0;
	}
}

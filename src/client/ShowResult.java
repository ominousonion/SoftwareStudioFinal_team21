package client;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PImage;

public class ShowResult {

	int x,y,width,height, op_num;
	private MainApplet applet;
	private HashMap<Integer, ArrayList<Integer>> data = new HashMap<Integer, ArrayList<Integer>>() ;
	private PicData graphdata;
	private int id;
	private int[] sum =  new int [10];
	private String[] color = {"#6495ED", "#7FFFD4", "#32CD32", "#FF6347", "#8F4586","#B452CD", "#FF0000"};
	private String nowGroup;
	private float iniAngle = 0;
	private int nextId, preId;
	private PImage bg;
		
	ShowResult(MainApplet applet){
		this.x=0;
		this.y=0;
		this.width=300;
		this.height=670;
		this.applet=applet;
		graphdata = applet.picdata1;
		bg = applet.loadImage("./src/img/Notebook.png");
		bg.resize(1200, 670);
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
		applet.image(bg,0,0);
		if(x<=width/2+100){
			x+=3;
			iniAngle += applet.radians(1);
		}
		pieChart( 300, data.get(id));
		if(id<graphdata.getGroupData().keySet().size()-1)   nextId = id+1;
		else nextId = 0;
		if(id != 0) preId = id-1;
		else preId = graphdata.getGroupData().keySet().size()-1;
		nextpieChart(150, data.get(nextId));
		prepieChart(150,  data.get(preId));
	}
	
	void pieChart(float diameter, ArrayList<Integer> data) {
	    float lastAngle = 0;
	    int k = 0;
		for (int i : data) {
			float rad = 360 * i / sum[id] ;
		    applet.fill(hex2Rgb(color[k]).getRGB());
		    applet.arc(x, height/2, diameter, diameter, iniAngle+lastAngle, iniAngle+lastAngle+applet.radians(rad));
		    applet.rect(500,100+40*k,100,20); //x,y,w,h
		    this.applet.fill( 0, 0, 0);
		    this.applet.textSize(30);
		    this.applet.text(nowGroup+" :", 40, 50);
			this.applet.textSize(18);
			if(graphdata.getGroupData().get(nowGroup).toArray()[k]!=null)this.applet.text((String)graphdata.getGroupData().get(nowGroup).toArray()[k],650,115+40*k);
		    lastAngle += applet.radians(rad);  
		    k++;
		}
	}
	
	void nextpieChart(float diameter, ArrayList<Integer> data) {
	    float lastAngle = 0;
	    int k = 0;
		for (int i : data) {
			float rad = 360 / sum[nextId] * i;
		    applet.fill(hex2Rgb(color[k]).getRGB());
		    applet.arc(950, 150, diameter, diameter, lastAngle, lastAngle+applet.radians(rad));
		    this.applet.fill( 0, 0, 0);
		    this.applet.textSize(20);
		    this.applet.text("Next :", 800, 50);
		    lastAngle += applet.radians(rad);  
		    k++;
		}
	}
	
	void prepieChart(float diameter, ArrayList<Integer> data) {
	    float lastAngle = 0;
	    int k = 0;
		for (int i : data) {
			float rad = 360 / sum[preId] * i;
		    applet.fill(hex2Rgb(color[k]).getRGB());
		    applet.arc(950, 450, diameter, diameter, lastAngle, lastAngle+applet.radians(rad));
		    this.applet.fill( 0, 0, 0);
		    this.applet.textSize(20);
		    this.applet.text("Previous :", 800, 350);
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
	
	public void changeGroup(boolean right){
		if(right) this.id = (this.id+1)%(graphdata.getGroupData().keySet().size());
		else this.id = (this.id-1+graphdata.getGroupData().keySet().size())%(graphdata.getGroupData().keySet().size());
		x = 0;
		nowGroup = (String) graphdata.getGroupData().keySet().toArray()[id];
		if(id<graphdata.getGroupData().keySet().size()-1)   nextId = id+1;
		else nextId = 0;
		if(id != 0) preId = id-1;
		else preId = graphdata.getGroupData().keySet().size()-1;
		pieChart( 300, data.get(id));
		nextpieChart(150, data.get(nextId));
		prepieChart(150,  data.get(preId));
	}
}
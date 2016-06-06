package client;

public class storeLabel {
	
	private MainApplet parent;
	private GameMap gm;
		
	storeLabel(MainApplet parent,GameMap gm){
		this.parent=parent;
		this.gm=gm;
	}
	
	public void display(){
		for (MapComponent mc: gm.components) {
			if(mc.type>=1 && mc.type<=4){
				this.parent.stroke(0);
				this.parent.fill(255,255,255);
				this.parent.rect(mc.x+7, mc.y+11, 25, 11);
				this.parent.fill( 0, 0, 0);
				this.parent.textSize(8);
				this.parent.text("Store", mc.x+10, mc.y+19);
			}
		}
	}
}


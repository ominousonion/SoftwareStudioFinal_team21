package client;

public class OccupipeBlock {
	GameMap gm;
	Character pc;
	int index;
	
	OccupipeBlock(GameMap gm,Character pc){
		this.gm=gm;
		this.pc=pc;
	}
	
	public void toOccupipeBlock(){
		this.index=pc.index;
		gm.components.get(index).occupipe(pc.number);
	}
}

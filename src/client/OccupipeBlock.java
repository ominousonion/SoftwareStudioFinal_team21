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
		boolean faceEdge=false;
		
		if(gm.character.face=="up"){
			this.index=pc.index-15;
			if(index<0) faceEdge=true;
		}else if(gm.character.face=="down"){
			this.index=pc.index+15;
			if(index>=225) faceEdge=true;
		}else if(gm.character.face=="left"){
			this.index=pc.index-1;
			if(index%15==14) faceEdge=true;
		}else if(gm.character.face=="right"){
			this.index=pc.index+1;
			if(index%15==0) faceEdge=true;
		}
		if(index>=0 && index<225 && !faceEdge) gm.components.get(this.index).occupipe(pc.number);
		
	}
}

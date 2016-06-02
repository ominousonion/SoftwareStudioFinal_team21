package client;

public class CreateBlock {
	GameMap gm;
	Character pc;
	
	int index;
	private boolean faceEdge;
	
	CreateBlock(GameMap gm,Character pc){
		this.gm=gm;
		this.pc=pc;
	}
	
	public void toMakeBlock(){
		faceEdge=false;
		if(pc.face=="up"){
			this.index=pc.index-15;
			if(index<0) faceEdge=true;
		}else if(pc.face=="down"){
			this.index=pc.index+15;
			if(index>=225) faceEdge=true;
		}else if(pc.face=="left"){
			this.index=pc.index-1;
			if(index%15==14) faceEdge=true;
		}else if(pc.face=="right"){
			this.index=pc.index+1;
			if(index%15==0) faceEdge=true;
		}

		if(index>=0 && index<225 && !faceEdge) gm.components.get(index).createWall();
	}
}

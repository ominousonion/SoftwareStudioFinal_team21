package client;

public class DeleteBlock {
	GameMap gm;
	Character pc;
	int index;
	private boolean faceEdge;
	
	DeleteBlock(GameMap gm,Character pc){
		this.gm=gm;
		this.pc=pc;
	}
	
	public boolean toDeleteBlock(){

		faceEdge=false;
		boolean exe=false;
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
		if(index>=0 && index<225 && !faceEdge) exe=gm.components.get(index).delWall(index);
		return exe;
	}
}

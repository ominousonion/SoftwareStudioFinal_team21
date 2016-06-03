package client;

public class DeleteBlock {
	GameMap gm;
	int index;
	private boolean faceEdge;
	
	DeleteBlock(GameMap gm){
		this.gm=gm;
	}
	
	public void toDeleteBlock(){
		faceEdge=false;
		if(gm.character.face=="up"){
			this.index=gm.character.index-15;
			if(index<0) faceEdge=true;
		}else if(gm.character.face=="down"){
			this.index=gm.character.index+15;
			if(index>=225) faceEdge=true;
		}else if(gm.character.face=="left"){
			this.index=gm.character.index-1;
			if(index%15==14) faceEdge=true;
		}else if(gm.character.face=="right"){
			this.index=gm.character.index+1;
			if(index%15==0) faceEdge=true;
		}
		if(index>=0 && index<225 && !faceEdge) gm.components.get(index).delWall(index);
	}
}

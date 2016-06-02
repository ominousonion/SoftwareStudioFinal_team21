package client;

public class DeleteBlock {
	GameMap gm;
	int index;
	
	DeleteBlock(GameMap gm){
		this.gm=gm;
	}
	
	public void toDeleteBlock(){
		if(gm.character.face=="up"){
			this.index=gm.character.index-15;
		}else if(gm.character.face=="down"){
			this.index=gm.character.index+15;
		}else if(gm.character.face=="left"){
			this.index=gm.character.index-1;
		}else if(gm.character.face=="right"){
			this.index=gm.character.index+1;
		}
		if(index>=0 && index<225) gm.components.get(index).delWall();
	}
}

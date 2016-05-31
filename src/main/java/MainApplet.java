package main.java;

import java.util.ArrayList;
import java.util.Iterator;

import controlP5.ControlP5;
import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	int a=1;
	private String path = "main/resources/";
	private String file = "resources/starwars-episode-"+a+"-interactions.json";
	JSONObject data;
	JSONArray nodes,links;
	int flag=-1;
	int rad;
	int rad2=30;
	int x=40;
	int y=20;
	Ani ani;
	int ox;
	int oy;
	private ControlP5 cp5;
	int[] aX = new int[50];
	int[] aY = new int[50];
	String msg=" ";
	int flag2,flag3=0;
	float count=0,angle;
	private ArrayList<Character> characters;
	private ArrayList<Character> circleCount;
	private Network network;
	private final static int width = 1200, height = 650;
	Character character;
	public void setup() {
		cp5 = new ControlP5(this); 
		cp5.addButton("buttonA") .setLabel("Add All") .setPosition(width-250, height-620) .setSize(200, 60); 
		cp5.addButton("buttonB") .setLabel("Clear All") .setPosition(width-250, height-540) .setSize(200, 60);
		
		size(width, height);
		smooth();
		characters = new ArrayList<Character>();
		circleCount = new ArrayList<Character>();
		Ani.init(this);
		this.rad=30;
		ani=Ani.to(this, (float)3,"rad2",30,Ani.LINEAR);
		ani.setCallback("onStart:startScaling");
		ani.setCallback("onEnd:endScaling");
		loadData();
		
	}

	public void draw() {
		background(255);
		
		
		stroke(0,255,0);
		strokeWeight(3);
		this.fill(255);
		this.ellipse(this.width/2, this.height/2, 450, 450);
		
		stroke(60, 119, 119);
		strokeWeight(4);
	
		
		for(Character character: this.characters)
		{
		character.display(rad);	
		}
		
	
		
		stroke(255,255,255);
		strokeWeight(3);
		for(Character character: this.characters)
		{
		character.display2(rad2);	
		}
		for(Character character: this.characters){
		//	if(character.flag3==1){

				stroke(60, 119, 119);
				strokeWeight(4);
			
				for(Character target: character.getTargets()){
					if(character.flag3==1&&target.flag3==1){
						float a=(character.x+target.x)/2;
						float b=(character.y+target.y)/2;
						a=(a+600)/2;
						b=(b+325)/2; 
						noFill();
				this.bezier(character.x, character.y, a, b, a , b, target.x, target.y);;
					}
				}
		
			
		//	}
		}
		//rad2=30;
	}
	
	public void keyPressed(){
		if(key=='1'){
			file = "main/resources/starwars-episode-1-interactions.json";
			characters.clear();
			loadData();
		}
		if(key=='2'){
			file = "main/resources/starwars-episode-2-interactions.json";
			characters.clear();
			loadData();
		}
		if(key=='3'){
			file = "main/resources/starwars-episode-3-interactions.json";			
			characters.clear();
			loadData();
		}
		if(key=='4'){
			file = "main/resources/starwars-episode-4-interactions.json";
			characters.clear();
			loadData();
		}
		if(key=='5'){
			file = "main/resources/starwars-episode-5-interactions.json";
			characters.clear();
			loadData();
		}
		if(key=='6'){
			file = "main/resources/starwars-episode-6-interactions.json";
			characters.clear();
			loadData();
		}
		if(key=='7'){
			file = "main/resources/starwars-episode-7-interactions.json";
			characters.clear();
			loadData();
		}
	}


	private void loadData(){
		data = loadJSONObject(file);
		nodes = data.getJSONArray("nodes");
		links = data.getJSONArray("links");
		System.out.println("Number of nodes: " + nodes.size());
		System.out.println("Number of links: " + links.size());
		x = 20;
		y = 40;
		for(int i=0;i<nodes.size();i++){
			JSONObject node = nodes.getJSONObject(i);
			characters.add(new Character(this, node.getString("name"), node.getString("colour"), x, y,i,flag3));
			aX[i] = x;
			aY[i] = y;
			y = y+60;
			if(y>=630){
				x=x+50;
				y=40;
			}
		}
		
		for(int i=0; i<links.size(); i++){
			JSONObject link = links.getJSONObject(i);
			characters.get(link.getInt("source")).addTarget(characters.get(link.getInt("target")));
		}
	}
	public void mousePressed(){
		if(flag2==0)
		ani=Ani.to(this, (float)0.05,"rad2",40,Ani.LINEAR); 
		if(mouseX>=850&&mouseX<=1300&&mouseY>=20&&mouseY<=90){
			int i=0;
		    float q;
			for(Character character:characters){
				circleCount.add(character);
			}
			q=circleCount.size();
			angle=360/q;
				System.out.println(characters.size());
				for(Character ch:characters){
				//angle = 360 /circleCount.size();
				float count2;
				count2 = i*angle;
				ch.x = (int)(225*Math.sin(Math.toRadians(count2)))+600; //maybe wrong 
				ch.y =  (int)(225*Math.cos(Math.toRadians(count2)))+325;
				i++;
				ch.flag3=1;
				}
		}
		

			if(mouseX>=850&&mouseX<=1300&&mouseY>=110&&mouseY<=160){
			
				for(Character character:circleCount){
					character.x = aX[character.num];
					character.y = aY[character.num];
					character.flag3=0;
				}
				circleCount.clear();
	
			
		}
	}
	public void mouseReleased(){ 
		
		
		ani=Ani.to(this, (float)0.05,"rad2",30,Ani.LINEAR);
		System.out.println("Animation ended.");
		//ani.end();
		flag2=0;
		int i=0;
		int z,zz;
		if(flag!=-1){
		if(characters.get(flag).x>=375&&characters.get(flag).x<=825&&characters.get(flag).y>=100&&characters.get(flag).y<=550){
			
			//count++;
			characters.get(flag).flag3=1;
			circleCount.add(this.characters.get(flag));
			
			count=circleCount.size();
			angle = 360 /count;
			for(Character character:circleCount ){
			float count2;
			count2 = i*angle;
			character.x = (int)(225*Math.sin(Math.toRadians(count2)))+600; //maybe wrong 
			character.y =  (int)(225*Math.cos(Math.toRadians(count2)))+325;
			i++;
			
				}
			}
		else{
			characters.get(flag).flag3=0;
			circleCount.remove(this.characters.get(flag));
			count=circleCount.size();
			angle = 360 /count;
			for(Character character:circleCount ){
			float count2;
			count2 = i*angle;
			character.x = (int)(225*Math.sin(Math.toRadians(count2)))+600; //maybe wrong 
			character.y =  (int)(225*Math.cos(Math.toRadians(count2)))+325;
			i++;
			
				}
			characters.get(flag).x = aX[flag];
			characters.get(flag).y = aY[flag];
			}
		flag=-1;
		System.out.println(count);
		}
}

	
	

	public void startScaling(){
//		ani=Ani.to(this, (float)5,"rad2",40,Ani.LINEAR); 
//		System.out.println("Animation started."); 
		}
	public void endScaling(){
	

	}
	//flag=num flag2=判斷有沒有人在被拖 flag3=判斷有沒有在圈裡
	public void mouseDragged(){
		for(Character character: this.characters)
		{
			
			if(mouseX<=character.x+15&&mouseX>=character.x-15&&mouseY<=character.y+15&&mouseY>=character.y-15&&flag2==0){
				flag=character.num;
				flag2=1;
			
			}
			
		
		}
		if(flag!=-1)
		{
		characters.get(flag).x=mouseX;
		characters.get(flag).y=mouseY;
		}
	}
	
		
}


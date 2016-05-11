package main.java;

import java.util.ArrayList;

import javax.xml.bind.ParseConversionEvent;

import de.looksgood.ani.Ani;
import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	private MainApplet parent;
	private String colour;
	private String name;
	int x;
	int y,r,g,b,flag,num,flag3;
	private ArrayList<Character> targets;
	
	public Character(MainApplet parent, String name, String colour,int x,int y,int i,int flag3){
		flag = 0;
		this.parent = parent;
		this.name = name;
		this.colour  = colour;
		this.x = x;
		this.y = y;
		this.num=i;
		this.targets = new ArrayList<Character>();
		
	}

	public void display(int rad){
		
		this.parent.noStroke();
		
		r = Integer.parseInt( colour.substring(3,5),16);
		g = Integer.parseInt( colour.substring(5,7),16);;
		b = Integer.parseInt( colour.substring(7,9),16);
		this.parent.fill(r,g,b);
		this.parent.ellipse(x,y , rad, rad);
	
			
			
		}
		
	public void display2(int rad2){

		
		if(this.parent.mouseX<=x+15&&this.parent.mouseX>=x-15&&this.parent.mouseY<=y+15&&this.parent.mouseY>=y-15){
			
			
			this.parent.fill(r,g,b);
			this.parent.ellipse(x,y , rad2, rad2);
			
//			this.parent.ani.start();
			//this.parent.fill(255);
			//this.parent.fill(r,g,b);
			//this.parent.ellipse(x,y , rad, rad);
			//this.parent.ellipse(1150, 325, 60, 60);
			this.parent.fill(102,255,204);
			this.parent.rect(x-name.length()*10, y-20, name.length()*20, 40, 12, 12, 12, 12);
			this.parent.textSize(26);
			this.parent.fill(255);
			this.parent.text(name, x-name.length()*10+5, y+10);
		}
		
		
	
	}

	public void addTarget(Character target){ this.targets.add(target); }
	
	public ArrayList<Character> getTargets(){ return this.targets; }
}

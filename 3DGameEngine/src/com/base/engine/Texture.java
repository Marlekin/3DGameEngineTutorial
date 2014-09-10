package com.base.engine;

import static org.lwjgl.opengl.GL11.*;

import java.io.FileInputStream;

import org.newdawn.slick.opengl.TextureLoader;

public class Texture {

	private int id;

	public Texture(String fileName)
	{
		this(loadTexture(fileName));
	
	}
	
	public Texture(int id)
	{
		this.id = id;
	}
	
	private static int loadTexture(String fileName)
	{
		String[] splitArray = fileName.split("\\.");
		String ext = splitArray[splitArray.length -1];
		
		try{
			int id = TextureLoader.getTexture(ext, new FileInputStream("./res/textures/" + fileName)).getTextureID();
			return id;
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
		return 0;
	}
	
	public void bind()
	{
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	public int getID()
	{
		return id;
	}
}
	
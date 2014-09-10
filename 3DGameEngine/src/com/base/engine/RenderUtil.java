package com.base.engine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL32.*;

import org.newdawn.slick.Color;

public class RenderUtil {

	public static void clearScreen(){

		//Stencil buffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public static void setTextures(boolean enabled){
		if(enabled){
			glEnable(GL_TEXTURE_2D);
		}
		else{
			glDisable(GL_TEXTURE_2D);
		}
	}
	
	public static void unbindTextures(){
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	public static void setClearColor(Vector3f color){
		glClearColor(color.getX(), color.getY(), color.getZ(), 1.0f);
	}

	public static void initGraphics(){

		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);


		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);

		//Depth clamp later
		glEnable(GL_DEPTH_CLAMP);

		glEnable(GL_TEXTURE_2D);

	}

	public static String getOpenGlVersion(){
		return glGetString(GL_VERSION);
	}

}

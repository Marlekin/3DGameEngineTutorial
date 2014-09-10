package com.base.engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class MainWindow {

	private final int WIDTH = 960;
	private final int HEIGHT = 540;
	private final String TITLE = "Game Engine";


	private MainWindow(){

		Display.setTitle(TITLE);

		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create();
			Keyboard.create();
			Mouse.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static class WindowHolder{
		private static MainWindow instance = new MainWindow();
	}

	public static MainWindow getInstance(){

		return WindowHolder.instance;
	}

	public static void render(){

		Display.update();

	}

	public static boolean isCloseRequested(){
		return Display.isCloseRequested();
	}

	public static int getWidth(){
		return Display.getDisplayMode().getWidth();
	}

	public static int getHeight(){
		return Display.getDisplayMode().getHeight();
	}

	public static String getTitle(){
		return Display.getTitle();

	}

	public static void dispose(){
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
	}

}

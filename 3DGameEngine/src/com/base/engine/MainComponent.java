package com.base.engine;

public class MainComponent {

	private boolean isRunning;
	public final double FRAME_CAP = 5000.0;

	private Game game;

	public MainComponent(){

		System.out.println(RenderUtil.getOpenGlVersion());
		RenderUtil.initGraphics();
		isRunning = false;

		game = new Game();
	}

	public void start(){

		if(isRunning){
			return;
		}

		run();

	}

	public void stop(){

		if(!isRunning){
			return;
		}

		isRunning = false;

	}

	private void run(){

		isRunning = true;
		final double frameTime = 1.0 / FRAME_CAP;
		long lastTime = Time.getTime();
		double unprocessedTime = 0;
		int frames = 0;
		int frameCounter = 0;


		while(isRunning){

			boolean render = false;

			long startTime = Time.getTime();
			long passedTime = startTime - lastTime;
			lastTime = startTime;

			unprocessedTime += passedTime / (double)Time.SECOND;
			frameCounter += passedTime;


			while(unprocessedTime > frameTime){

				render = true;

				unprocessedTime -= frameTime;

				if(MainWindow.isCloseRequested()){
					stop();
				}

				Time.setDelta(frameTime);

				//Update game

				game.input();
				Input.update();

				game.update();

				if(frameCounter >= Time.SECOND){

					System.out.println(frames);
					frames = 0;
					frameCounter = 0;

				}

			}

			if(render){

				render();
				frames++;

			}
			else{
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		cleanUp();

	}

	private void render(){

		RenderUtil.clearScreen();
		game.render();
		MainWindow.render();

	}

	private void cleanUp(){

		MainWindow.dispose();

	}

	public static void main(String[] args){

		MainWindow.getInstance();

		MainComponent game = new MainComponent();

		game.start();

	}

}

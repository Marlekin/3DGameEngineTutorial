package com.base.engine;



public class Game {

	private Mesh _mesh;
	private Shader _shader;
	private Material _material;
	private Transform _transform;
	private Camera _camera;
	
	

	PointLight pLight1 = new PointLight(new BaseLight(new Vector3f(1, 0.5f, 0), 10f), new Attenuation(0, 0, 1), new Vector3f(-2, 0 , 5f), 8);
	PointLight pLight2 = new PointLight(new BaseLight(new Vector3f(0, 0.5f, 1), 10f), new Attenuation(0, 0, 1), new Vector3f(5, 0 , 10f), 8);
	PointLight pLight3 = new PointLight(new BaseLight(new Vector3f(0, 1, 0.5f), 10f), new Attenuation(0, 0, 1), new Vector3f(3, 0 , 15f), 8);

	SpotLight sLight1 = new SpotLight(new PointLight(new BaseLight(new Vector3f(1,1,1), 0.8f), new Attenuation(0,0,0.1f), new Vector3f(-2,0,5f), 30),
			new Vector3f(1,1,1), 0.7f);

	public Game(){


		//ResourceLoader.loadMesh("Gemstone.obj");//new Mesh();
		_material = new Material(new Texture("test.png"), new Vector3f(1, 1, 1), 1, 8);
		_shader = PhongShader.getInstance();
		_camera = new Camera();
		_transform = new Transform();


		float fieldDepth = 10.0f;
		float fieldWidth = 10.0f;

		Vertex[] vertices = new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
				new Vertex( new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)),
				new Vertex( new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
				new Vertex( new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f))};

		int indices[] = { 0, 1, 2,
				2, 1, 3};




		_mesh = new Mesh(vertices, indices, true);

		Transform.setProjection(70f, MainWindow.getWidth(), MainWindow.getHeight(), 0.1f, 1000f);
		Transform.setCamera(_camera);


		PhongShader.setAmbientLight(new Vector3f(0.1f, 0.1f, 0.1f));
		PhongShader.setDirectionalLight(new DirectionalLight(new BaseLight(new Vector3f(1,1,1), 0.01f), new Vector3f(1, 1, 1)));


		PhongShader.setPointLight(new PointLight[]{pLight1, pLight2, pLight3});
		PhongShader.setSpotLights(new SpotLight[]{sLight1});
	}

	public void input(){

		_camera.input();

	}

	float temp = 0.0f;


	public void update(){

		temp += Time.getDelta() *2;

		float sinTemp = (float)Math.sin(temp);

		_transform.setTranslation(0, -1, 5);
		//_transform.setRotation(0, sinTemp*90,sinTemp*25);
		//_transform.set_scale(0.5f * sinTemp, 0.5f * sinTemp, 0.5f * sinTemp);

		pLight1.setPosition(new Vector3f(3, 0.5f, 8.0f * (float)(Math.sin(temp) + 1.0/2.0) +10));
		pLight2.setPosition(new Vector3f(7, 0.5f, 8.0f * (float)(Math.cos(temp) + 1.0/2.0) +10));
		pLight3.setPosition(new Vector3f(11, 0.5f, 8.0f * (float)(Math.sin(temp) + 1.0/2.0) +10));

		sLight1.getPoint().setPosition(_camera.getPos());
		sLight1.setDirection(_camera.getForward());

	}

	public void render(){

		RenderUtil.setClearColor(Transform.getCamera().getPos().div(2048f).abs());
		_shader.bind();
		_shader.updateUniforms(_transform.getTransformation(), _transform.getProjectedTransformation(), _material);
		_mesh.draw();
	}
}

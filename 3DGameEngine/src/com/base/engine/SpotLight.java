package com.base.engine;

public class SpotLight {

	private PointLight point;
	private Vector3f direction;
	private float cutoff;
	
	public SpotLight(PointLight point, Vector3f direction, float cutoff){
		
		this.point = point;
		this.direction = direction.normalized();
		this.cutoff = cutoff;
		
	}
	
	public PointLight getPoint() {
		return point;
	}
	public void setPoint(PointLight point) {
		this.point = point;
	}
	public Vector3f getDirection() {
		return direction;
	}
	public void setDirection(Vector3f direction) {
		this.direction = direction.normalized();
	}
	public float getCutoff() {
		return cutoff;
	}
	public void setCutoff(float cutoff) {
		this.cutoff = cutoff;
	}
}

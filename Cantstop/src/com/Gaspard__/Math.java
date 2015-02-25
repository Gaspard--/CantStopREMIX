package com.Gaspard__;

public class Math{
	public static class Vector2{
		final float x;
		final float y;
		public Vector2(float x, float y) {
			this.x = x;
			this.y = y;
		}
		public Vector2 minus(){
			return scale(this, -1);
		}
	}
	static Vector2 plus(Vector2 a, Vector2 b){
		return new Vector2(a.x+b.x, a.y+b.y);
	}
	static Vector2 times(Vector2 a, Vector2 b){
		return new Vector2(a.x*b.x-a.y*b.y,a.x*b.y+a.y*b.x);
	}
	static Vector2 scale(Vector2 a, float b){
		return new Vector2(a.x*b,a.y*b);
	}
	static Vector2 scale(Vector2 a, Vector2 b){
		return new Vector2(a.x*b.x,a.y*b.y);
	}
	static float scalar(Vector2 a, Vector2 b){
		return a.x*b.x+a.y*b.y;
	}
}

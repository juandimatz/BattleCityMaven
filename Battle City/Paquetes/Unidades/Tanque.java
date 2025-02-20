package Unidades;

import General.GameObject;
import Municion.Bala;
import java.applet.AudioClip;
import java.awt.Point;
import Game.Juego;

public abstract class Tanque extends GameObject 
{	
	protected int GolpesResis;
	protected int VelocidadDis;
	protected int direccion;
	protected Juego mij;
	protected int dispAct;
	protected AudioClip sonido;
	
	public Tanque(int golpes, int VD, int x,int y ,int n)
	{
		super(x,y,n);
		sonido = java.applet.Applet.newAudioClip(getClass().getResource("/Recursos/disparo.wav"));
		GolpesResis=golpes;
		VelocidadDis=VD;
		direccion = 0;
		dispAct = 0;
	}
	
	public int getDispAct()
	{
		return dispAct;
	}
	
	public void setDispAct(int dis)
	{
		dispAct = dis;
	}
	
	public abstract void mover(int dir);	
	
	public int getDireccion()
	{
		return direccion;
	}	
	
	public int getVelocidadDis()
	{
		return VelocidadDis;
	}
	
	public void setGolpesResis(int g)
	{
		GolpesResis=g;
	}	
	
	public int getGolpesResis()
	{
		return GolpesResis;
	}
	
	public void setVelocidadDis(int g)
	{
		VelocidadDis=g;
	}
	
	public abstract Bala disparar();
	
	public Juego getJuego()
	{
		return mij;
	}
	
	public void setJuego(Juego j)
	{ 
		mij = j; 
	}
	
	public void setX(int n)
	{
		pos.x = n;
	}
	
	public void setY(int n)
	{
		pos.y = n;
	}
	
	public void setPos(Point p)
	{
		pos.x = p.x;
		pos.y = p.y;
	}
	
	public Point getPos()
	{
		return pos;
	}
}

package Visitantes;

import Municion.*;
import Unidades.*;
import General.*;
import TDALista.*;
import Obstaculos.*;
import PowerUps.*;

public class ColisionadorBalasEnemigo extends Visitor 
{
	
	protected BalaEnemigo miBala;
	
	public ColisionadorBalasEnemigo(BalaEnemigo b)
	{
		miBala = b;
	}
	
	public boolean ColisionarParedLadrillo(ParedLadrillo p)
	{
		try
		{
			miBala.destruirBalaEnemigo();
			miBala.getTanque().getJuego().getGui().actualizar();
			p.setGolpesResis(p.getGolpeResis() - 1);
			if (p.getGolpeResis() == 3)
			{
				p.cambiarGrafico(1);				
			}
			else
				if (p.getGolpeResis() == 2)
				{
					p.cambiarGrafico(2);
				}
				else
					if (p.getGolpeResis() == 1)
					{
						p.cambiarGrafico(3);
					}
					else
						if (p.getGolpeResis() == 0)
						{							
							miBala.getTanque().getJuego().getGui().getPanel1().remove(p.getGrafico());
							boolean encontre= false;
							PositionList<GameObject> todo = miBala.getTanque().getJuego().getTodo();
							PositionList<Obstaculo> obs = miBala.getTanque().getJuego().getObs();
							Position<GameObject> puntero= todo.first();
							Position<Obstaculo> puntero2= obs.first();
							for(int j=0;j<todo.size()&&!encontre;j++){
								encontre=puntero.element()==p;
								if(!encontre)
									puntero=todo.next(puntero);					
							}	
							if(encontre){
								todo.remove(puntero);
								miBala.getTanque().getJuego().setTodo(todo);
							}
							encontre = false;
							for(int j=0;j<obs.size()&&!encontre;j++){
								encontre=puntero2.element()==p;
							if(!encontre)
							puntero2=obs.next(puntero2);					
							}	
							if(encontre){
								obs.remove(puntero2);
								miBala.getTanque().getJuego().setObs(obs);
							}
							p = null;
							miBala.getTanque().getJuego().getGui().actualizar();
							miBala = null;							
						}
		}
		catch (EmptyListException| InvalidPositionException | BoundaryViolationException | IndexOutOfBoundsException exc)
		{
			exc.getMessage();
		}
		return true;
	}
	
	public boolean ColisionarParedAcero(ParedAcero pa )
	{
		try
		{
			miBala.destruirBalaEnemigo();
			miBala.getTanque().getJuego().getGui().actualizar();
			miBala = null;
		}
		catch(IndexOutOfBoundsException exc)
		{
			exc.getMessage();
		}
		return true;
	}
	
	public boolean ColisionarArbol(Arbol a)
	{
		return false;
	}
	
	public boolean ColisionarAgua(Agua a)
	{
		return false;
	}
	
	public boolean ColisionarAguila(Aguila a)
	{
		try
		{
			miBala.getTanque().getJuego().GameOver();
			miBala.destruirBalaEnemigo();
			miBala.getTanque().getJuego().getGui().actualizar();			
			PositionList<GameObject> todo = miBala.getTanque().getJuego().getTodo();
			PositionList<Obstaculo> obs = miBala.getTanque().getJuego().getObs();
			Position<GameObject> puntero = todo.first();
			Position<Obstaculo> puntero2 = obs.first();
			boolean encontre = false;
			for(int j=0;j<todo.size()&&!encontre;j++){
				encontre=puntero.element()==a;
				if(!encontre)
					puntero=todo.next(puntero);					
			}	
			if(encontre){
				todo.remove(puntero);
				miBala.getTanque().getJuego().setTodo(todo);
			}
			encontre = false;
			for(int j=0;j<obs.size()&&!encontre;j++){
				encontre=puntero2.element()==a;
				if(!encontre)
					puntero2=obs.next(puntero2);					
			}	
			if(encontre){
				obs.remove(puntero2);
				miBala.getTanque().getJuego().setObs(obs);
			}
			miBala.getTanque().getJuego().getGui().getPanel1().remove(a.getGrafico());
			a = null;
			miBala = null;
		}
		catch (EmptyListException | InvalidPositionException | BoundaryViolationException | IndexOutOfBoundsException exc)
		{
			exc.getMessage();
		}
		return true;
	}
	
	public boolean ColisionarBloqueo(Bloqueo b)
	{
		try
		{
			miBala.destruirBalaEnemigo();
			miBala.getTanque().getJuego().getGui().actualizar();
			miBala = null;
		}
		catch(IndexOutOfBoundsException exc)
		{
			exc.getMessage();
		}
		return true;
	}
	
	public boolean ColisionarEstrella(Estrella e)
	{
		return false;
	}
	
	public boolean ColisionarCasco(Casco c)
	{
		return false;
	}
	
	public boolean ColisionarGranada(Granada g)
	{
		return false;
	}
	
	public boolean ColisionarPala(Pala p)
	{
		return false;
	}
	
	public boolean ColisionarTanqueP(TanqueP t)
	{
		return false;
	}
	
	public boolean ColisionarTimerP(TimerP t)
	{
		return false;
	}
	
	public boolean ColisionarTanqueEnemigo(Enemigo t)
	{
		try
		{
			miBala.destruirBalaEnemigo();
			miBala.getTanque().getJuego().getGui().actualizar();
			miBala = null;
		}
		catch(IndexOutOfBoundsException exc)
		{
			exc.getMessage();
		}
		return true;
	}
	
	public boolean ColisionarTanqueJugador(Jugador j)
	{
		try
		{
			miBala.destruirBalaEnemigo();	
			miBala.getTanque().getJuego().getGui().actualizar();
			miBala = null;
			j.setGolpesResis(j.getGolpesResi() - 1);
			if (j.getGolpesResi() == 0)
			{
				if(j.getVida()>1)
				{
					j.setVida(j.getVida()-1);
					j.getJuego().getGui().setLabelVidas(j.getVida()+"");
					j.renacer();
					j.setGolpesResis(1);
					j.cambiarX(335);
					j.cambiarY(575);
					j.cambiarGrafico(0);
					j.getJuego().getGui().getPanel1().remove(j.getGrafico());
					j.getJuego().getGui().getPanel1().add(j.getGrafico());
					j.getJuego().getGui().actualizar();
				}
				else
				{	
					j.getJuego().GameOver();
				}
			}
		}
		catch(IndexOutOfBoundsException exc)
		{
			exc.getMessage();
		}
		return false;
	}
	
	public boolean ColisionarJugadorInvulnerable(JugadorInvulnerable j)
	{
		try
		{
			miBala.destruirBalaEnemigo();
			miBala.getTanque().getJuego().getGui().actualizar();
			miBala = null;
		}
		catch(IndexOutOfBoundsException exc)
		{
			exc.getMessage();
		}
		return false;
	}
	
	public boolean ColisionarBalaJugador(BalaJugador t)
	{	
		try
		{
			miBala.destruirBalaEnemigo();
			t.destruirBalaJugador();
			miBala.getTanque().getJuego().getGui().getPanel1().remove(t.getGrafico());
			miBala.getTanque().getJuego().getGui().actualizar();
			miBala = null;
		}
		catch(IndexOutOfBoundsException exc)
		{
			exc.getMessage();
		}
		return true;
	}
	
	public boolean ColisionarBalaEnemigo(BalaEnemigo t)
	{		
		return false;
	}	
}
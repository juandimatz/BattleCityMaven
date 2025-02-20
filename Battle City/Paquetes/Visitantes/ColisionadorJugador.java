package Visitantes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import Obstaculos.*;
import Unidades.*;
import General.GameObject;
import Municion.*;
import PowerUps.*;
import TDALista.*;
import InteligenciaArtificial.*;
import Game.Juego;
import Nivel.*;

public class ColisionadorJugador extends Visitor 
{
	
	protected Jugador miJ;
	protected boolean vulnerable;
	protected Timer timer;
	
	public ColisionadorJugador(Jugador j) {
		miJ = j;
		vulnerable = false;
	}
	
	public void setVulnerable(boolean b) {
		vulnerable = b;
	}
	
	public boolean ColisionarParedLadrillo(ParedLadrillo p){
		
		return true;
	}
	
	public boolean ColisionarParedAcero(ParedAcero pa ){
		return true;
	}
	
	public boolean ColisionarArbol(Arbol a){
		return false;
	}
	
	public boolean ColisionarAgua(Agua a){
		return true;
	}
	
	public boolean ColisionarAguila(Aguila a){
		return true;
	}
	
	public boolean ColisionarBloqueo(Bloqueo b){
		return true;
	}
	
	public boolean ColisionarEstrella(Estrella e)
	{
		try
		{
			for(Position<GameObject> p: miJ.getJuego().getTodo().positions())
			{
				if (p.element()== e)
				{
					try 
					{
						miJ.getJuego().getTodo().remove(p);
					} catch (InvalidPositionException exc) {
						exc.getMessage();
					}
				}
			}
			miJ.getJuego().getGui().getPanel1().remove(e.getGrafico());		
			miJ.subirNivel();
			e.getIA().cambiarVariable();
			int puntajeViejo = miJ.getJuego().getPuntaje();
			miJ.getJuego().setPuntaje(miJ.getJuego().getPuntaje() + 500);
			int puntajeNuevo = miJ.getJuego().getPuntaje();
			if (puntajeViejo < 5000 && puntajeNuevo >= 5000)
			{
				miJ.setVida(miJ.getVida() + 1);
				miJ.getJuego().getGui().setLabelVidas(miJ.getVida()+"");
			}
			miJ.getJuego().getGui().setLabelPuntaje(miJ.getJuego().getPuntaje()+"");
			e = null;
			miJ.getJuego().getGui().actualizar();
		}
		catch(IndexOutOfBoundsException exc)
		{
			exc.getMessage();
		}
		return false;
	}
	
	public boolean ColisionarCasco(Casco c)
	{
		try
		{
			for(Position<GameObject> p: miJ.getJuego().getTodo().positions())
			{
				if (p.element()== c)
				{
					try 
					{
						miJ.getJuego().getTodo().remove(p);
					} catch (InvalidPositionException exc) 
					{
						exc.getMessage();
					}
				}
			}
			Juego j = miJ.getJuego();
			Nivel n = miJ.getNivel();
			miJ.getJuego().getGui().getPanel1().remove(c.getGrafico());
			miJ.getJuego().getGui().getPanel1().remove(miJ.getGrafico());
			int puntajeViejo = miJ.getJuego().getPuntaje();
			miJ.getJuego().setPuntaje(miJ.getJuego().getPuntaje() + 500);
			int puntajeNuevo = miJ.getJuego().getPuntaje();
			if (puntajeViejo < 5000 && puntajeNuevo >= 5000)
			{
				miJ.setVida(miJ.getVida() + 1);
				miJ.getJuego().getGui().setLabelVidas(miJ.getVida()+"");
			}
			miJ = new JugadorInvulnerable(miJ.getGolpesResi(),miJ.getVelocidadMovimiento(), miJ.getVelocidadDisparo(), miJ.getPos().x, miJ.getPos().y, 4, miJ.getVida(), miJ.getDisparosSim());
			miJ.setJuego(j);
			miJ.getJuego().setJugador(miJ);
			miJ.setNivel(n);
			InteligenciaInvulnerable i = new InteligenciaInvulnerable (miJ.getJuego());
			i.start();
			miJ.getJuego().getGui().setLabelPuntaje(miJ.getJuego().getPuntaje()+"");
			c = null;
			miJ.getJuego().getGui().actualizar();
		}
		catch(IndexOutOfBoundsException exc)
		{
			exc.getMessage();
		}
		return false;
	}
	
	public boolean ColisionarGranada(Granada g)
	{
		try
		{
			PositionList<Enemigo> lista = miJ.getJuego().getMalos();
			if (lista.size() > 0)
			{
				for (Enemigo e : lista) {
					crearTimer(e);
					cambiarLabel(e);
					miJ.getJuego().setPuntaje(miJ.getJuego().getPuntaje()+e.getPuntaje());
					for(Position<Enemigo> p: miJ.getJuego().getMalos().positions())
					{
						if (p.element()== e)
						{
							try 
							{
								miJ.getJuego().getMalos().remove(p);
							} catch (InvalidPositionException exc) {
								exc.getMessage();
							}
						}
					}
					for(Position<GameObject> p: miJ.getJuego().getTodo().positions()){
						if (p.element()== e)
							try {
								miJ.getJuego().getTodo().remove(p);
							} catch (InvalidPositionException exc) {
								exc.getMessage();
							}
					}	
				}
			}
			miJ.getJuego().getGui().getPanel1().remove(g.getGrafico());
			for(Position<GameObject> p: miJ.getJuego().getTodo().positions()){
				if (p.element()== g)
					try {
						miJ.getJuego().getTodo().remove(p);
					} catch (InvalidPositionException exc) {
						exc.getMessage();
					}
			}
			int puntajeViejo = miJ.getJuego().getPuntaje();
			miJ.getJuego().setPuntaje(miJ.getJuego().getPuntaje() + 500);
			int puntajeNuevo = miJ.getJuego().getPuntaje();
			if (puntajeViejo < 5000 && puntajeNuevo >= 5000)
			{
				miJ.setVida(miJ.getVida() + 1);
				miJ.getJuego().getGui().setLabelVidas(miJ.getVida()+"");
			}
			miJ.getJuego().getGui().setLabelPuntaje(miJ.getJuego().getPuntaje()+"");
			g = null;
			miJ.getJuego().getGui().actualizar();
		}
		catch(IndexOutOfBoundsException exc)
		{
			exc.getMessage();
		}		
		return false;
	}
	
	public boolean ColisionarPala(Pala pa)
	{
		PositionList<Obstaculo> listaAux = new ListaDoblementeEnlazada<Obstaculo>();
		try
		{
			for(Position<GameObject> p: miJ.getJuego().getTodo().positions())
			{
				if (p.element() == pa)
				{				
					miJ.getJuego().getTodo().remove(p);				
				}
			}
			miJ.getJuego().getGui().getPanel1().remove(pa.getGrafico());
			for (Position<Obstaculo> o : miJ.getJuego().getBase().positions()) 
			{
				miJ.getJuego().getGui().getPanel1().remove(o.element().getGrafico());
				for (Position<GameObject> g : miJ.getJuego().getTodo().positions()) 
				{
					if (g.element() == o.element()) 
					{					
						miJ.getJuego().getTodo().remove(g);
					} 
				}
				for (Position<Obstaculo> g : miJ.getJuego().getObs().positions()) {
					if (g.element() == o.element()) 
					{					
						miJ.getJuego().getObs().remove(g);					
					} 
				}
				Obstaculo aux = new ParedAcero(4,o.element().getPos().x,o.element().getPos().y);
				miJ.getJuego().getBase().remove(o);
				miJ.getJuego().getTodo().addLast(aux);
				miJ.getJuego().getObs().addLast(aux);
				miJ.getJuego().getGui().getPanel1().add(aux.getGrafico());
				listaAux.addLast(aux);
			}
			for (Obstaculo x : listaAux)
			{
				miJ.getJuego().getBase().addLast(x);
			}
			miJ.getJuego().getInteligenciaPala().start();
			int puntajeViejo = miJ.getJuego().getPuntaje();
			miJ.getJuego().setPuntaje(miJ.getJuego().getPuntaje() + 500);
			int puntajeNuevo = miJ.getJuego().getPuntaje();
			if (puntajeViejo < 5000 && puntajeNuevo >= 5000)
			{
				miJ.setVida(miJ.getVida() + 1);
				miJ.getJuego().getGui().setLabelVidas(miJ.getVida()+"");
			}
			miJ.getJuego().getGui().setLabelPuntaje(miJ.getJuego().getPuntaje()+"");
			pa = null;
			miJ.getJuego().getGui().actualizar();
		}
		catch(InvalidPositionException | IndexOutOfBoundsException exc)
		{
			exc.getMessage();
		}
		return false;
	}	
	
	public boolean ColisionarTanqueP(TanqueP t)
	{
		try
		{
			for(Position<GameObject> p: miJ.getJuego().getTodo().positions()){
				if (p.element()== t)
					try {
						miJ.getJuego().getTodo().remove(p);
					} catch (InvalidPositionException exc) {
						exc.getMessage();
					}
			}
			miJ.getJuego().getGui().getPanel1().remove(t.getGrafico());		
			miJ.setVida(miJ.getVida() + 1);
			miJ.getJuego().getGui().setLabelVidas(miJ.getVida()+"");
			t.getIA().cambiarVariable();
			int puntajeViejo = miJ.getJuego().getPuntaje();
			miJ.getJuego().setPuntaje(miJ.getJuego().getPuntaje() + 500);
			int puntajeNuevo = miJ.getJuego().getPuntaje();
			if (puntajeViejo < 5000 && puntajeNuevo >= 5000)
			{
				miJ.setVida(miJ.getVida() + 1);
				miJ.getJuego().getGui().setLabelVidas(miJ.getVida()+"");
			}
			miJ.getJuego().getGui().setLabelPuntaje(miJ.getJuego().getPuntaje()+"");
			t = null;
			miJ.getJuego().getGui().actualizar();
		}
		catch(IndexOutOfBoundsException exc)
		{
			exc.getMessage();
		}
		return false;
	}
	
	public boolean ColisionarTimerP(TimerP t)
	{
		try
		{
			InteligenciaEnemigo iae= miJ.getJuego().getIAEnemigo();
			iae.interrumpir();
			for(Position<GameObject> p: miJ.getJuego().getTodo().positions()){
				if (p.element()== t)
					try {
						miJ.getJuego().getTodo().remove(p);
					} catch (InvalidPositionException exc) {
						exc.getMessage();
					}
			}
			miJ.getJuego().getGui().getPanel1().remove(t.getGrafico());	
			int puntajeViejo = miJ.getJuego().getPuntaje();
			miJ.getJuego().setPuntaje(miJ.getJuego().getPuntaje() + 500);
			int puntajeNuevo = miJ.getJuego().getPuntaje();
			if (puntajeViejo < 5000 && puntajeNuevo >= 5000)
			{
				miJ.setVida(miJ.getVida() + 1);
				miJ.getJuego().getGui().setLabelVidas(miJ.getVida()+"");
			}
			miJ.getJuego().getGui().setLabelPuntaje(miJ.getJuego().getPuntaje()+"");
			t = null;
			miJ.getJuego().getGui().actualizar();
		}
		catch(IndexOutOfBoundsException exc)
		{
			exc.getMessage();
		}
		return false;
	}
	
	public boolean ColisionarTanqueEnemigo(Enemigo t){
		return true;
	}
	
	public boolean ColisionarTanqueJugador(Jugador j){
		return true;
	}
	
	public boolean ColisionarJugadorInvulnerable(JugadorInvulnerable j){
		return true;
	}
	
	public boolean ColisionarBalaJugador(BalaJugador t){
		return false;
	}
	
	public boolean ColisionarBalaEnemigo(BalaEnemigo t){
		return false;
	}
	
	private void crearTimer(Enemigo ene) {
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ene.getGrafico().setIcon(null);
			}
		});
		timer.setRepeats(false);
	}
	
	private void cambiarLabel(Enemigo ene) {
		ImageIcon sangre = new ImageIcon(getClass().getResource("/Recursos/Sangre.gif"));
		ene.getGrafico().setIcon(sangre);
		timer.restart();
	}
}
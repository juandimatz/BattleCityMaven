package Game;

import java.io.BufferedReader;
import General.GameObject;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import Obstaculos.*;
import TDALista.*;
import Unidades.*;

public class CargadorMapa
{
	private BufferedReader br;
	private final int tam = 30;
	private final int ancho = 960;
	PositionList<Obstaculo> listaObs;
	PositionList<Enemigo> listaEne;
	PositionList<GameObject> listaTodo;
	PositionList<Obstaculo> listaBorde;
	PositionList<Obstaculo> base;
	Random r;

	public CargadorMapa()
	{
		try
		{
			listaObs = new ListaDoblementeEnlazada<Obstaculo>();
			listaEne = new ListaDoblementeEnlazada<Enemigo>();
			listaTodo = new ListaDoblementeEnlazada<GameObject>();
			listaBorde = new ListaDoblementeEnlazada<Obstaculo>();
			base = new ListaDoblementeEnlazada<Obstaculo>();
			br =  new BufferedReader(new FileReader("Mapa.txt"));
			r = new Random();
			String sCurrentLine;	
			int x=0;
			int y=0;
			while ((sCurrentLine = br.readLine()) != null)
			{
				for(int i = 0; i < sCurrentLine.length(); i++)
				{
					char letra = sCurrentLine.charAt(i);
					switch (letra) 
					{
		        		case 'l' :
		        			x+=30;
		        			Obstaculo paredLadrillo = new ParedLadrillo(4,x,y);
		        			listaObs.addLast(paredLadrillo);
		        			listaTodo.addLast(paredLadrillo);
		        			break;        		      			
		        		case 'e' :
		        			x+=30;
		        			Enemigo tanqueBasico = new TanqueBasico(x,y,r.nextInt(4));
		        			listaEne.addLast(tanqueBasico);
		        			listaTodo.addLast(tanqueBasico);
		        			break;
		        		case 'b' :
		        			Obstaculo arbol = new Arbol(1,x,y);
		        			listaObs.addLast(arbol);
		        			listaTodo.addLast(arbol);
		        			break;
		        		case 'm' :
		        			x+=30;
		        			Obstaculo paredAcero = new ParedAcero(4,x,y);
		        			listaObs.addLast(paredAcero);
		        			listaTodo.addLast(paredAcero);
		        			break;
		        		case 'g' :
		        			x+=30;
		        			Obstaculo aguila = new Aguila(1,x,y);
		        			listaObs.addLast(aguila);
		        			listaTodo.addLast(aguila);
		        			break;
		        		case 'a' :
		        			x+=30;
		        			Obstaculo agua = new Agua(1,x,y);
		        			listaObs.addLast(agua);
		        			listaTodo.addLast(agua);
		        			break;
		        		case 'n' :
		        			Obstaculo bloqueo = new Bloqueo(1,x,y);
		        			listaBorde.addLast(bloqueo);
		        			listaTodo.addLast(bloqueo);
		        			break;
		        		case 'x' :
		        			x+=30;
		        			Obstaculo paredBase = new ParedLadrillo(4,x,y);
		        			listaObs.addLast(paredBase);
		        			listaTodo.addLast(paredBase);
		        			base.addLast(paredBase);
		        		default :
		        			System.out.print("");		        			
	        		}
					x += tam;
	    			if(x + tam > ancho)
	    			{
	    				x=0;
	    				y+=tam;
	    			} 
	        	} 
			}			
		}
		catch (IOException e) 
		{ 
            e.printStackTrace();
        } 
		finally 
		{ 
            try 
            {
                if (br != null)
                	br.close();
            } 
            catch (IOException ex) 
            {
                ex.printStackTrace();
            }
        }
	}
	
	public PositionList<GameObject> getTodo()
	{
		return listaTodo;
	}
	
	public PositionList<Obstaculo> getObs()
	{
		return listaObs;
	}
	
	public PositionList<Enemigo> getEne()
	{
		return listaEne;
	}
	
	public PositionList<Obstaculo> getBorde()
	{
		return listaBorde;
	}
	
	public PositionList<Obstaculo> getBase()
	{
		return base;
	}
}
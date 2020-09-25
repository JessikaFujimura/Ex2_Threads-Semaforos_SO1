package controller;

import java.text.DecimalFormat;
import java.util.concurrent.Semaphore;

public class ThreadPrato extends Thread {
	private int id;
	private Semaphore semaforo;
	

	public ThreadPrato(int id, Semaphore semaforo){
		this.semaforo = semaforo;
		this.id = id;
	}

	@Override
	public void run() {
		switch (CalculaRestoId()) {
		case 0:
			TempoCozimento(0.5, 0.8, "Sopa de Cebola");
			try {
				semaforo.acquire();
				Entrega();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			finally {
				semaforo.release();
			}
			break;
		case 1: 
			TempoCozimento(0.6, 1.2, "Lasanha de Bolonhesa");
			try {
				semaforo.acquire();
				Entrega();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			finally {
				semaforo.release();
			}
			break;
		}
	}


	private int CalculaRestoId(){
		return id % 2;
	}

	private void TempoCozimento(double inferior, double superior, String nomePrato){
		String result;
		double percentual;
		double num = 0;
		System.out.println("#" + id + " prato " + nomePrato);
		double random = Math.random();
		double multiplicador = (random > (superior - inferior)) ? (superior - inferior) : random;
		double tempo = (double) ((multiplicador + inferior) *1000);
		while(100 >= num){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			num += ((1 - ((tempo-100)/tempo))*100);
			percentual = num>=100 ? 100 : num;
			result = new DecimalFormat("###.##").format(percentual);
			System.out.println(result + "%: #" + id + " prato " + nomePrato);
		}

	}

	private void Entrega(){
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("#" + id + " prato está entregue");
	}
}

package view;

import java.util.concurrent.Semaphore;

import controller.ThreadPrato;

public class Principal {

	public static void main(String[] args) {

		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);
		
		for(int id= 1; id < 6; id++){
			Thread thread = new ThreadPrato(id,semaforo);
			thread.start();

		}
		
		
	}

}

package ru.spbstu.telematics.student_filippova.coursework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		var firstMessage = "¬ведите до какого числа нужно искать простые числа";
		var endMessage = "ѕростые числа в заданном диапозоне: ";
		var errorMessage = "Ќеверный аргумент";
		var outMessage = "≈сли хотите вывести числа нажмите y";
		var secondMessage = "¬ведите количество потоков( 1, 2, 4, 8)";
		var timeCreateMessage = "¬рем€ на создание потоков ";
		Eratosfen first;
		Eratosfen second;
		Eratosfen third;
		Eratosfen fourth;
		Eratosfen fifth;
		Eratosfen sixth;
		Eratosfen seventh;
		Eratosfen eighth;

		int count = 0;
		ArrayList<Integer> result = new ArrayList<>();

		System.out.println( firstMessage );
		count = in.nextInt();
		if( count < 40)
		{
			if( count == 2) {
				in.close();
				System.out.print( endMessage );
				System.out.println(2);
				return;
			}
			if( count < 2 ) {
				in.close();
				System.out.println( errorMessage );
				return;
			}
			//если ищем простые числа до count = 40, mode false - простой режим поска простых чисел
			Eratosfen.PrepareData( count * count, false );
			Eratosfen.CalculateFirst();

			result =  Eratosfen.primeNumbers;
			System.out.print( outMessage );
			var str = in.next();
			if( str.contains( "Y") || str.contains( "y"))
				for (var elem : result)
					System.out.print( elem + " ");

			in.close();
			//точка останова
			int k = 1;
			return;
		}


		System.out.println( secondMessage );
		int countPotok = in.nextInt();
		long startTime = System.currentTimeMillis();//отсчет времени начинаетс€ перед по€влением сообщени€ о выборе количества потоков
		if (countPotok == 1)
		{
			Eratosfen.PrepareData( count, true );
			Eratosfen.CalculateFirst();
		}
		else {
			if (!(countPotok > 0 && countPotok < 9 && countPotok % 2 == 0)) {
				System.out.println(errorMessage);
				return;
			}

			Eratosfen.PrepareData( count, false );
			Eratosfen.CalculateFirst();
		}

		switch (countPotok){
			case 2:
				first = new Eratosfen( 1 );
				second = new Eratosfen( 7 );

				first.start();
				second.start();

				while( first.isAlive() || second.isAlive() )
					try {
						Thread.sleep( 50);
					}
					catch ( InterruptedException e)
					{
						e.getStackTrace();
					}

				first = new Eratosfen( 11 );
				second = new Eratosfen( 13 );

				first.start();
				second.start();

				while( first.isAlive() || second.isAlive() )
					try {
						Thread.sleep( 50);
					}
					catch ( InterruptedException e)
					{
						e.getStackTrace();
					}

				first = new Eratosfen( 17 );
				second = new Eratosfen( 19 );

				first.start();
				second.start();

				while( first.isAlive() || second.isAlive() )
					try {
						Thread.sleep( 50);
					}
					catch ( InterruptedException e)
					{
						e.getStackTrace();
					}

				first = new Eratosfen( 23 );
				second = new Eratosfen( 29 );

				first.start();
				second.start();

				while( first.isAlive() || second.isAlive() )
					try {
						Thread.sleep( 200);
					}
					catch ( InterruptedException e)
					{
						e.getStackTrace();
					}

				break;

			case 4:
				first = new Eratosfen( 1 );
				second = new Eratosfen( 7 );
				third = new Eratosfen( 11 );
				fourth = new Eratosfen( 13 );

				first.start();
				second.start();
				third.start();
				fourth.start();

				while( first.isAlive() || second.isAlive() || third.isAlive() || fourth.isAlive())
					try {
						Thread.sleep( 50);
					}
					catch ( InterruptedException e)
					{
						e.getStackTrace();
					}

				//long timeSpent = System.currentTimeMillis() - startTime;
				//System.out.println("программа выполн€лась " + timeSpent + " миллисекунд");

				first = new Eratosfen( 17 );
				second = new Eratosfen( 19 );
				third = new Eratosfen( 23 );
				fourth = new Eratosfen( 29 );


				first.start();
				second.start();
				third.start();
				fourth.start();

				while( first.isAlive() || second.isAlive() || third.isAlive() || fourth.isAlive())
					try {
						Thread.sleep( 10);
					}
					catch ( InterruptedException e)
					{
						e.getStackTrace();
					}
				//timeSpent = System.currentTimeMillis() - startTime;
				//System.out.println("программа выполн€лась " + timeSpent + " миллисекунд");

				break;

			case 8:
				first = new Eratosfen( 1 );
				second = new Eratosfen( 7 );
				third = new Eratosfen( 11 );
				fourth = new Eratosfen( 13 );
				fifth = new Eratosfen( 17 );
				sixth = new Eratosfen( 19 );
				seventh = new Eratosfen( 23 );
				eighth = new Eratosfen( 29 );

				first.start();
				second.start();
				third.start();
				fourth.start();
				fifth.start();
				sixth.start();
				seventh.start();
				eighth.start();

				//timeSpent = System.currentTimeMillis() - startTime;
				//System.out.println( timeCreateMessage + timeSpent + " миллисекунд");

				while( first.isAlive() || second.isAlive() || third.isAlive() || fourth.isAlive() || fifth.isAlive() || sixth.isAlive() || seventh.isAlive() || eighth.isAlive() )
					try {
						Thread.sleep( 10);
					}
					catch ( InterruptedException e)
					{
						e.getStackTrace();
					}
				break;
		}

		long timeSpent = System.currentTimeMillis() - startTime;
		System.out.println("ѕрограмма выполн€лась " + timeSpent + " миллисекунд");
		result =  Eratosfen.getList();
		System.out.print( outMessage );
		var str = in.next();
		if( str.contains( "Y") || str.contains( "y"))
			for (var elem : result)
				System.out.print( elem + " ");

		in.close();

		int k = 1;

	}

}

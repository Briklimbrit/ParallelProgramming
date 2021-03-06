// LenaOpenMp.cpp : Этот файл содержит функцию "main". Здесь начинается и заканчивается выполнение программы.
//
//#include <omp.h>
#include "pch.h"
#include <iostream>
#include "Eratosfen.h"
//#include <conio.h>
//#include <stdio.h>
//#include <stdlib.h>
//#include <vector>
#define HAVE_STRUCT_TIMESPEC
#include <pthread.h>
#include <vector>
#include <list>
#include <ctime>


using namespace std;
static const int n_threads = 8;

struct Args
{
	int k;
	list<int> result;
	Args() {
		int k = 0;
		list<int> result;
	}
};

int main()
{
	unsigned int start_time = clock();
	pthread_t ThreadArray[n_threads];
	vector<int> vectorOfK = { 1,7,11,13,17,19,23,29 };
	Eratosfen::PrepareData( 1000000, false);
	Eratosfen::CalculateFirst();
	list<int> result = Eratosfen::primeNumbers;
	Args* ThreadArgs[8];



	//8
	/*
	for (int i = 0; i < 8; i++) {
		ThreadArgs[i] = new Args();
		ThreadArgs[i]->k = vectorOfK[i];
		pthread_create(&ThreadArray[i], nullptr, (Eratosfen::Calculate), ThreadArgs[i]);
	}

	for (int i = 0; i < 8; i++) {
		while (!pthread_join(ThreadArray[i], NULL))
			result.insert(result.end(), ThreadArgs[i]->result.begin(), ThreadArgs[i]->result.end());
	}
	*/

	/***********************************************************************************************************************************************************************************/
	//4
	//4
	//4
	//{
	/*
	for (int i = 0; i < 4; i++) {
		ThreadArgs[i] = new Args();
		ThreadArgs[i]->k = vectorOfK[i];
		pthread_create(&ThreadArray[i], nullptr, (Eratosfen::Calculate), ThreadArgs[i]);
	}

	for (int i = 0; i < 4; i++) {
		while (!pthread_join(ThreadArray[i], NULL))
			result.insert(result.end(), ThreadArgs[i]->result.begin(), ThreadArgs[i]->result.end());
	}



	for (int i = 4; i < 8; i++) {
		ThreadArgs[i] = new Args();
		ThreadArgs[i]->k = vectorOfK[i];
		pthread_create(&ThreadArray[i], nullptr, (Eratosfen::Calculate), ThreadArgs[i]);
	}

	for (int i = 4; i < 8; i++) {
		while (!pthread_join(ThreadArray[i], NULL))
			result.insert(result.end(), ThreadArgs[i]->result.begin(), ThreadArgs[i]->result.end());
	}
	*/
	//}
	/***********************************************************************************************************************************************************************************/


	/***********************************************************************************************************************************************************************************/
	//2
	//2
	//2
	//{
	
	for (int i = 0; i < 2; i++) {
		ThreadArgs[i] = new Args();
		ThreadArgs[i]->k = vectorOfK[i];
		pthread_create(&ThreadArray[i], nullptr, (Eratosfen::Calculate), ThreadArgs[i]);
	}

	for (int i = 0; i < 2; i++) {
		while (!pthread_join(ThreadArray[i], NULL))
			result.insert(result.end(), ThreadArgs[i]->result.begin(), ThreadArgs[i]->result.end());
	}



	for (int i = 2; i < 4; i++) {
		ThreadArgs[i] = new Args();
		ThreadArgs[i]->k = vectorOfK[i];
		pthread_create(&ThreadArray[i], nullptr, (Eratosfen::Calculate), ThreadArgs[i]);
	}

	for (int i = 2; i < 4; i++) {
		while (!pthread_join(ThreadArray[i], NULL))
			result.insert(result.end(), ThreadArgs[i]->result.begin(), ThreadArgs[i]->result.end());
	}


	for (int i = 4; i < 6; i++) {
		ThreadArgs[i] = new Args();
		ThreadArgs[i]->k = vectorOfK[i];
		pthread_create(&ThreadArray[i], nullptr, (Eratosfen::Calculate), ThreadArgs[i]);
	}

	for (int i = 4; i < 6; i++) {
		while (!pthread_join(ThreadArray[i], NULL))
			result.insert(result.end(), ThreadArgs[i]->result.begin(), ThreadArgs[i]->result.end());
	}



	for (int i = 6; i < 8; i++) {
		ThreadArgs[i] = new Args();
		ThreadArgs[i]->k = vectorOfK[i];
		pthread_create(&ThreadArray[i], nullptr, (Eratosfen::Calculate), ThreadArgs[i]);
	}

	for (int i = 6; i < 8; i++) {
		while (!pthread_join(ThreadArray[i], NULL))
			result.insert(result.end(), ThreadArgs[i]->result.begin(), ThreadArgs[i]->result.end());
	}
	
	
	//}
	/***********************************************************************************************************************************************************************************/


	unsigned int end_time = clock();
	unsigned int search_time = end_time - start_time;

	cout << "The program worked " << search_time << " ms\n";


	for (int i = 0; i < n_threads; i++) {
		delete ThreadArgs[i];
	}

	result.sort();

	/*for (int i: result) {
		cout << i << " ";
	}*/
	system("PAUSE");

	return 0;
}


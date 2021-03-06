// LenaOpenMp.cpp : Этот файл содержит функцию "main". Здесь начинается и заканчивается выполнение программы.
//
//#include <omp.h>
#include "pch.h"
#include <iostream>
#include "Eratosfen.h"
#include <vector>
#include <ctime>


using namespace std;

int main()
{
	//В строчке 141 менять количество ядер

	unsigned int start_time = clock();
	Eratosfen::PrepareData(100000, false);
	Eratosfen::CalculateFirst();
	Eratosfen::Calculate();

	unsigned int end_time = clock();
	unsigned int search_time = end_time - start_time;

	cout << "The program worked " << search_time << " ms\n";
	Eratosfen::primeNumbers.sort();

	/*
	#pragma omp parallel num_threads(5)
	{
	cout << "Hello World!\n";
	}*/
	system("PAUSE");
	return 0;
}





// Запуск программы: CTRL+F5 или меню "Отладка" > "Запуск без отладки"
// Отладка программы: F5 или меню "Отладка" > "Запустить отладку"

// Советы по началу работы 
//   1. В окне обозревателя решений можно добавлять файлы и управлять ими.
//   2. В окне Team Explorer можно подключиться к системе управления версиями.
//   3. В окне "Выходные данные" можно просматривать выходные данные сборки и другие сообщения.
//   4. В окне "Список ошибок" можно просматривать ошибки.
//   5. Последовательно выберите пункты меню "Проект" > "Добавить новый элемент", чтобы создать файлы кода, или "Проект" > "Добавить существующий элемент", чтобы добавить в проект существующие файлы кода.
//   6. Чтобы снова открыть этот проект позже, выберите пункты меню "Файл" > "Открыть" > "Проект" и выберите SLN-файл.


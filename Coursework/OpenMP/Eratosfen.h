#pragma once
#include <list>
//#include <iostream>
#include <vector>
#include <math.h>
#include <omp.h>
using namespace std;
class  Eratosfen
{
private:
	int k = 0;

public:
	static long count;
	static long length;
	static vector<int> firstNumber;
	static list<int> primeNumbers;
	//static bool firstNumber[];
	Eratosfen( );
	virtual ~Eratosfen();
	void static PrepareData( long lenght, bool mode );
	void static CalculateFirst();
	int static FindFirstPrimeInMas(int index);
	static void addElementsInList();
	static int  firstIndex(int prime, int k);
	static int  findNok(int k);
	static int findNod(int first, int second);
	void static Calculate();
};

vector<int> Eratosfen::firstNumber;
list<int> Eratosfen::primeNumbers;
long Eratosfen::count = 0;
long Eratosfen::length = 0;

Eratosfen::Eratosfen()
{
	
}


Eratosfen::~Eratosfen()
{
}

int  Eratosfen::firstIndex(int prime, int k)
{
	int count = 0;
	while (k % prime != 0)
	{
		k += 30;
		count++;
	}

	return count;
}

 void  Eratosfen::PrepareData(long count, bool mode)
{
	int length = 0;
	if (!mode)
	{
		length = (int)sqrt(count);
		Eratosfen::length = length + 1;
		Eratosfen::count = count;
		length = length % 2 == 1 ? ++length / 2 : length / 2;
	}
	else
	{
		length = count % 2 == 1 ? ++count / 2 : count / 2;
	}
	firstNumber.resize(length, 0);
	firstNumber[0] = 1;
	primeNumbers.push_back(2);
}

 void  Eratosfen::CalculateFirst()
 {
	 int index = 1;
	 while (index != -1)
	 {
		 int number = index * 2 + 1;
		 for (int position = index + number; position < firstNumber.size(); position += number)
			 firstNumber[position] = 2;

		 firstNumber[index] = 1;
		 index = FindFirstPrimeInMas(index);
	 }

	 addElementsInList();
	 int k = 1;
 }


 int  Eratosfen::FindFirstPrimeInMas( int index)
 {
	 for (int position = index + 1; position < firstNumber.size(); position++)
		 if (firstNumber[position] == 0)
			 return position;

	 return -1;
 }


 void  Eratosfen::addElementsInList()
 {
	 for (int i = 1; i < firstNumber.size(); i++)
		 if (firstNumber[i] == 1)
			 primeNumbers.push_back(i * 2 + 1);
 }


 int  Eratosfen::findNok(int k)
 {
	 if (30 % k == 0 || k % 30 == 0)
		 return -1;

	 return 30 * k / findNod(30, k) / 30;
 }

 int  Eratosfen::findNod(int first, int second)
 {
	 while (first != 0 && second != 0)
		 if (first > second)
			 first = first % second;
		 else
			 second = second % first;

	 return first + second;
 }


 void  Eratosfen::Calculate()
 {
	 omp_lock_t writelock;
	 omp_init_lock(&writelock);

	 vector<int> vectorOfK = { 1,7,11,13,17,19,23,29 };

	 //ВОТ ТУТ МЕНЯТЬ КОЛИЧЕСТВО ЯДЕР
	 #pragma omp parallel for num_threads(2)
	 for (int j = 0; j < 8; j++)
	 {
		 int k = vectorOfK[j];

		 //Наименьшее n для которого 30n + k >= lenght
		 int firstCountDiv = 0;

		 if (length > k)
			 firstCountDiv = (length - k) % 30 == 0 ? (length - k) / 30 : (length - k) / 30 + 1;

		 //Наибольшое число n для которого 30n + k <= count
		 int  secondCountDiv = (count - k) / 30;

		 //Массив в котором хранятся n, но по факту 0 индекс приранивается к firstCountDiv
		 vector<int> privateNumbers(secondCountDiv - firstCountDiv + 1, 0);

		 //Высеиваем числа с помощью первых найденных простых чисел
		 for (int prime : primeNumbers)
		 {
			 
			 //чётных чисел нет
			 if (prime == 2)
				 continue;

			 //период высеивания
			 int range = findNok(prime);

			 //простое число делится на 30 или наоборот
			 if (range == -1)
			 {
				 //переход к следующему числу
				 if (k % prime != 0)
				 {
					 continue;
				 }
				 else {
					 //высеиваем все числа
					 for (int i = 0; i < privateNumbers.size(); i++)
						 privateNumbers[i] = 1;

					 break;
				 }
			 }
			 
			 
			 // первого n, для которого 30n + k  делится на prime без остатка.
			 int firstIndex = Eratosfen::firstIndex(prime, k);

			 //Индекс массива с которого начинваем
			 int startIndex = 0;
			 if (firstIndex < firstCountDiv)
				 startIndex = (firstCountDiv - firstIndex) % range == 0 ? 0 : range - (firstCountDiv - firstIndex) % range;

			 if (firstIndex >= firstCountDiv && firstCountDiv <= secondCountDiv)
				 startIndex = firstIndex - firstCountDiv;

			 if (firstIndex > secondCountDiv)
				 continue;

			 //высеиваем числа по периоду
			 for (int i = startIndex; i < privateNumbers.size(); i += range)
				 privateNumbers[i] = 1;
			 
			 
		 }

		 
		 list<int> localPrime;
		 for (int i = 0; i < privateNumbers.size(); i++)
			 if (privateNumbers[i] == 0)
				 localPrime.push_back((firstCountDiv + i) * 30 + k);

		 
		 omp_set_lock(&writelock);
		 primeNumbers.insert(primeNumbers.end(), localPrime.begin(), localPrime.end());
		 omp_unset_lock(&writelock);
	 }
 }




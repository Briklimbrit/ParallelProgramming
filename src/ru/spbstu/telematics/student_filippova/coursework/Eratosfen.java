package ru.spbstu.telematics.student_filippova.coursework;

import java.util.ArrayList;
import java.util.Collections;

public class Eratosfen extends Thread
{

    private static final Object lock = new Object();                            //используется для синхронизации

    public static ArrayList<Integer> primeNumbers = new ArrayList<Integer>();   //Коллекция в которой хранятся первые найденные простые числа
    public static ArrayList<Integer> localList = new ArrayList<Integer>();      //Коллекция в которой оставшиеся простые числа( потоки сюда закидывают их )


    public  static byte[] firstNumber = new byte[0];    //Массив для выссеивания первых чисел.
    public volatile static int length = 0;              //До какого числа ищутся первые простые числа
    public static int count = 0;                        //До какого числа ищутся все простые числа.


    private int k = 0;                              // Для каждого потока своё k - где k это простое число из прогрессии 30n + k
    private byte[] privateNumbers = new byte[ 0 ];  //Массив для выссеивания чисел из определённой прогрессиии.

    //конструктор
    public Eratosfen( int k) { this.k = k; }

    @Override
    public void run()
    {
        //Наименьшее n для которого 30n + k >= lenght
        int firstCountDiv = 0;

        if( length > k)
            firstCountDiv = ( length - k ) % 30 == 0 ? ( length - k ) / 30 : ( length - k ) / 30 + 1;

        //Наибольшое число n для которого 30n + k <= count
        var secondCountDiv = ( count - k ) / 30;
        //var secondCountDiv = ( count - k ) % 30 == 0 ? ( count - k ) / 30 : ( count - k ) / 30 + 1;


        //Массив в котором по факту кранятся n, но по факту 0 индекс приранивается к firstCountDiv
        privateNumbers = new byte[ secondCountDiv - firstCountDiv + 1];

        //Высеиваем числа с помощью первых найденных простых чисел
        for ( var prime : primeNumbers)
        {
            //для двойки ничего делать не надо, так как чётных чисел нету.
            if( prime == 2)
                continue;

            if( prime == 307 ) {
                int we = 1;
            }
            //периуд высеивания
            var range = findNok( prime);

            //Если range == -1 значит периода нет. Так как простое число делится на 30 или наоборот.
            if( range == -1 )
                //переход к следующему числу
                if(  k % prime != 0)
                    continue;
                else {
                    //высеиваем все числа
                    for (int i = 0; i < privateNumbers.length; i++)
                        privateNumbers[i] = 1;

                    return;
                }

            // первого n, для которого 30n + k  делится на prime без остатка.
            var firstIndex = firstIndex( prime, k);

            //Индекс массива с которого начинваем
            int startIndex = 0;
            if( firstIndex < firstCountDiv)
                startIndex = ( firstCountDiv  - firstIndex ) % range == 0 ? 0 : range - ( firstCountDiv  - firstIndex ) % range;

            if( firstIndex >= firstCountDiv && firstCountDiv <= secondCountDiv)
                startIndex = firstIndex - firstCountDiv;

            if( firstIndex > secondCountDiv)
                continue;

            //высеиваем числа по периоду
            for( int i = startIndex; i < privateNumbers.length; i+=range )
                privateNumbers[ i ] = 1;
        }


        ArrayList<Integer> localPrime = new ArrayList<>();
        for ( int i = 0; i < privateNumbers.length; i++)
            if( privateNumbers[ i ] == 0)
                localPrime.add( (firstCountDiv + i) * 30 + k);

        synchronized ( lock )
        {
            localList.addAll( localPrime );
        }

        System.out.println( "Поток с K = " + k + " отработал");
    }

    /**
     * Используется для подготовки данных к поиску простых чисел
     * @param count - до какого числа идёт поиск.
     */
    public static void PrepareData(int count , boolean mode)
    {
        int length = 0;
        if( !mode ) {
            length = (int) Math.sqrt(count);
            Eratosfen.count = count;
            Eratosfen.length = length + 1;
            length = length % 2 == 1 ? ++length / 2 : length / 2;
        }
        else
            length = count % 2 == 1 ? ++count / 2 : count / 2;//половина от count - до куда ищутся первые простые числа

        firstNumber = new byte[ length  ];//инициализация массива для высеивания первых простых чисел
        firstNumber[ 0 ] = 1;
        primeNumbers.add( 2 );//заполнение массива первым числом среди первых найденных первых простых чисел
    }

    /**
     * Используется для поиска первых простых чисел до sqrt( Count ).
     * Где Count - до какого числа идёт поиск
     */
    public  static  void CalculateFirst()//элементарное решето
    {
        var index = 1;
        while( index != -1) {
            var number = index * 2 + 1;
            for (var position = index + number; position < firstNumber.length; position += number)
                firstNumber[position] = 2;

            firstNumber[ index ] = 1;
            index = FindFirstPrimeInMas( index );
        }

        addElementsInList();
        int k = 1;

    }

    /**
     * Используется для нахождения "невысеянных" решетом Эратосфена чисел.
     * @param index Индекс с которого начинается поиск.
     * @return Возвращает индекс первого "невысеянных" числа.
     */
    public static int FindFirstPrimeInMas( int index )
    {
        for( var position = index + 1; position < firstNumber.length; position++ )
            if( firstNumber[ position ] == 0)
                return position;

        return -1;
    }

    /**
     * Используется для добавления найденных простых чисел в ArrayList из массива
     */
    public  static void addElementsInList()
    {
        for( var i = 1; i < firstNumber.length; i++)
            if( firstNumber[ i ] == 1)
                primeNumbers.add( i * 2 + 1 );
    }

    /**
     * Используется для поиска первого n, для которого 30n + k  делится на prime без остатка.
     * @param prime - Простое число, с помощью которого высеиваются числа.
     * @param k - k, где k это слагаемое из прогрессии 30n + k
     * @return Первый индекс выссеивания.
     */
    public  int  firstIndex( int prime, int k )
    {
        int count = 0;
        while( k % prime != 0) {
            k += 30;
            count++;
        }

        return count;
    }

    /**
     * Используется для поиска периуда высеивания.
     * @param k - k, где k это слагаемое из прогрессии 30n + k
     * @return Возвращает периуд высеивания ( n );
     */
    public  int findNok( int k)
    {
        if( 30 % k == 0 || k % 30 == 0)
            return -1;

        return 30 * k / findNod( 30, k ) / 30;
    }

    /**
     * Функция поиска наибольшего общего делителя.
     * @param first - Первое числа
     * @param second - Второе число
     * @return - НОД числе first и second.
     */
    public  int findNod( int first, int second )
    {
        while( first != 0 && second != 0)
            if( first > second)
                first = first % second;
            else
                second = second % first;

        return first + second;
    }

    /**
     * Используется для получения всех найденных простых чисел до Count. Данный метод соеденяет ArrayList
     * с первыми найденными простыми числами и ArrayList в котором хранятся числа найденный с помощью
     * прогрессий 30n+1, 30n+7...
     * @return Возвращает отсортированный список всех простых чисел до Count.
     */
    public static ArrayList<Integer> getList()
    {
        Eratosfen.localList.addAll( Eratosfen.primeNumbers );
        Collections.sort( localList );
        return localList;
    }
}

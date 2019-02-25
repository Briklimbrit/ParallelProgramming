package ru.spbstu.telematics.student_filippova.coursework;

import java.util.ArrayList;
import java.util.Collections;

public class Eratosfen extends Thread
{

    private static final Object lock = new Object();                            //������������ ��� �������������

    public static ArrayList<Integer> primeNumbers = new ArrayList<Integer>();   //��������� � ������� �������� ������ ��������� ������� �����
    public static ArrayList<Integer> localList = new ArrayList<Integer>();      //��������� � ������� ���������� ������� �����( ������ ���� ���������� �� )


    public  static byte[] firstNumber = new byte[0];    //������ ��� ����������� ������ �����.
    public volatile static int length = 0;              //�� ������ ����� ������ ������ ������� �����
    public static int count = 0;                        //�� ������ ����� ������ ��� ������� �����.


    private int k = 0;                              // ��� ������� ������ ��� k - ��� k ��� ������� ����� �� ���������� 30n + k
    private byte[] privateNumbers = new byte[ 0 ];  //������ ��� ����������� ����� �� ����������� �����������.

    //�����������
    public Eratosfen( int k) { this.k = k; }

    @Override
    public void run()
    {
        //���������� n ��� �������� 30n + k >= lenght
        int firstCountDiv = 0;

        if( length > k)
            firstCountDiv = ( length - k ) % 30 == 0 ? ( length - k ) / 30 : ( length - k ) / 30 + 1;

        //���������� ����� n ��� �������� 30n + k <= count
        var secondCountDiv = ( count - k ) / 30;
        //var secondCountDiv = ( count - k ) % 30 == 0 ? ( count - k ) / 30 : ( count - k ) / 30 + 1;


        //������ � ������� �� ����� �������� n, �� �� ����� 0 ������ ������������� � firstCountDiv
        privateNumbers = new byte[ secondCountDiv - firstCountDiv + 1];

        //��������� ����� � ������� ������ ��������� ������� �����
        for ( var prime : primeNumbers)
        {
            //��� ������ ������ ������ �� ����, ��� ��� ������ ����� ����.
            if( prime == 2)
                continue;

            if( prime == 307 ) {
                int we = 1;
            }
            //������ ����������
            var range = findNok( prime);

            //���� range == -1 ������ ������� ���. ��� ��� ������� ����� ������� �� 30 ��� ��������.
            if( range == -1 )
                //������� � ���������� �����
                if(  k % prime != 0)
                    continue;
                else {
                    //��������� ��� �����
                    for (int i = 0; i < privateNumbers.length; i++)
                        privateNumbers[i] = 1;

                    return;
                }

            // ������� n, ��� �������� 30n + k  ������� �� prime ��� �������.
            var firstIndex = firstIndex( prime, k);

            //������ ������� � �������� ���������
            int startIndex = 0;
            if( firstIndex < firstCountDiv)
                startIndex = ( firstCountDiv  - firstIndex ) % range == 0 ? 0 : range - ( firstCountDiv  - firstIndex ) % range;

            if( firstIndex >= firstCountDiv && firstCountDiv <= secondCountDiv)
                startIndex = firstIndex - firstCountDiv;

            if( firstIndex > secondCountDiv)
                continue;

            //��������� ����� �� �������
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

        System.out.println( "����� � K = " + k + " ���������");
    }

    /**
     * ������������ ��� ���������� ������ � ������ ������� �����
     * @param count - �� ������ ����� ��� �����.
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
            length = count % 2 == 1 ? ++count / 2 : count / 2;//�������� �� count - �� ���� ������ ������ ������� �����

        firstNumber = new byte[ length  ];//������������� ������� ��� ���������� ������ ������� �����
        firstNumber[ 0 ] = 1;
        primeNumbers.add( 2 );//���������� ������� ������ ������ ����� ������ ��������� ������ ������� �����
    }

    /**
     * ������������ ��� ������ ������ ������� ����� �� sqrt( Count ).
     * ��� Count - �� ������ ����� ��� �����
     */
    public  static  void CalculateFirst()//������������ ������
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
     * ������������ ��� ���������� "�����������" ������� ���������� �����.
     * @param index ������ � �������� ���������� �����.
     * @return ���������� ������ ������� "�����������" �����.
     */
    public static int FindFirstPrimeInMas( int index )
    {
        for( var position = index + 1; position < firstNumber.length; position++ )
            if( firstNumber[ position ] == 0)
                return position;

        return -1;
    }

    /**
     * ������������ ��� ���������� ��������� ������� ����� � ArrayList �� �������
     */
    public  static void addElementsInList()
    {
        for( var i = 1; i < firstNumber.length; i++)
            if( firstNumber[ i ] == 1)
                primeNumbers.add( i * 2 + 1 );
    }

    /**
     * ������������ ��� ������ ������� n, ��� �������� 30n + k  ������� �� prime ��� �������.
     * @param prime - ������� �����, � ������� �������� ����������� �����.
     * @param k - k, ��� k ��� ��������� �� ���������� 30n + k
     * @return ������ ������ �����������.
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
     * ������������ ��� ������ ������� ����������.
     * @param k - k, ��� k ��� ��������� �� ���������� 30n + k
     * @return ���������� ������ ���������� ( n );
     */
    public  int findNok( int k)
    {
        if( 30 % k == 0 || k % 30 == 0)
            return -1;

        return 30 * k / findNod( 30, k ) / 30;
    }

    /**
     * ������� ������ ����������� ������ ��������.
     * @param first - ������ �����
     * @param second - ������ �����
     * @return - ��� ����� first � second.
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
     * ������������ ��� ��������� ���� ��������� ������� ����� �� Count. ������ ����� ��������� ArrayList
     * � ������� ���������� �������� ������� � ArrayList � ������� �������� ����� ��������� � �������
     * ���������� 30n+1, 30n+7...
     * @return ���������� ��������������� ������ ���� ������� ����� �� Count.
     */
    public static ArrayList<Integer> getList()
    {
        Eratosfen.localList.addAll( Eratosfen.primeNumbers );
        Collections.sort( localList );
        return localList;
    }
}

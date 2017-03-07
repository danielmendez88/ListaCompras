package app.com.listacompras.clases;


import android.util.Log;

import java.util.LinkedList;

/**
 * daniel Mendez Cruz on 25/02/2017. Created By DMC
 */

public class Prngenerator{

    private LinkedList<Long> new_list = new LinkedList<>();
    private StringBuilder stb = new StringBuilder();
    private long seed_generator;
    private int length_generator;
    private int a[] = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000}; //general array

    /**
     *
     * @param seed seed value
     * @param length iterations' numbers that I want to generate
     */
    public Prngenerator(long seed, int length) {
        this.seed_generator = seed;
        this.length_generator = length;
    }

    /**
     *
     * @return a long value that send a type int data to textView
     */
    public String random_generator()
    {
        int limit = 6; //limit of numbers that the array can stores
        long set_value; //this is the value we can set as module
        long z = (long) Math.pow(seed_generator, 2); //long value to raise seed to square
        int trim = (length_generator/2); // in this case the value gets the result that divide length generator between 2
        z = z/a[trim];
        long n = z%10;
        new_list.addFirst(n); // add into the list array but we add the current element first
        Log.d("actual valor", String.valueOf(n));
        for (int j = 0; j < length_generator; j++) //Main loop to get the middle values
        {
            Log.d("next num" + String.valueOf(j), String.valueOf((z%(a[trim]))*(a[j])));
             z /= 10;
            if (j <= limit)
            {
                set_value = z%10; //set module and assign the value to it
                new_list.addFirst(set_value); //
                Log.d("division values" + String.valueOf(j) + ":", String.valueOf(set_value));
            }
        }

        for (int i = 0; i < new_list.size(); i++)
        {
            stb.append(new_list.get(i));
            Log.d("numbers stored", String.valueOf(new_list.get(i)));
        }

        Log.d("each value from", String.valueOf(new_list.toString()));
        Log.d("each value from", stb.toString().trim());
        return stb.toString().trim();
    }


}
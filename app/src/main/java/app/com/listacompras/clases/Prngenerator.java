package app.com.listacompras.clases;


import android.util.Log;

import java.util.LinkedList;
import java.util.Random;

/**
 * daniel Mendez Cruz on 25/02/2017. Created By DMC
 */

public class Prngenerator{

    private LinkedList<Long> new_list = new LinkedList<>();
    private StringBuilder stb = new StringBuilder();
    private long seed_generator;
    private int length_generator;
    private LinkedList<Long> final_array = new LinkedList<>();
    private int a[] = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};

    /**
     *
     * @param seed seed value
     * @param length iterations' numbers that I want to generate
     */
    public Prngenerator(long seed, int length) {
        this.seed_generator = seed;
        this.length_generator = length;
        //int start = 1, end = 1;
        //start = a[seed_generator-1];
        //end = a[seed_generator];

    }
    public long generator()
    {
        //max for digits = 8, max will be 99999999
        int max = (int) Math.pow(10, length_generator) - 1;
        //for digits = 8, min will be 10000000
        int min = (int) Math.pow(10, length_generator - 1);
        //range
        int range = max-min;

        //create a random object
        Random rand = new Random();
        rand.setSeed(seed_generator);
        //setting seed
        int x = rand.nextInt(range) + min;
        Log.d("random: ", String.valueOf(x));
        return x;
    }

    public long random_generator()
    {
        long z = (long) Math.pow(seed_generator, 2);
        int trim = (length_generator/2);
        z = z/a[trim];
        for (int j = 0; j < length_generator; j++)
        {
            Log.d("next num" + String.valueOf(j), String.valueOf((z%(a[trim]))));
            if (j % 2 == 0)
            {
                    new_list.addLast(z%(a[trim]));
            }
             z /= 10;
            Log.d("division values", String.valueOf(z));
        }

        for (int i = 0; i < new_list.size(); i++)
        {
            //addFirst
            if (final_array.isEmpty())
            {
                //if array is empty we can save the value from another arrayList
                final_array.addFirst(new_list.get(i));
            }
            else
            {
                //as we already know, if the array isn't
                    String actual_position = String.valueOf(new_list.get(i));
                    String previous_position = String.valueOf(new_list.get(i-1));
                Log.d("previous position ", previous_position);
            }
            Log.d("random_generator" + String.valueOf(i), String.valueOf(new_list.get(i)));
            stb.append(new_list.get(i));
        }
        return  Long.valueOf(stb.toString().trim());
    }


}

//http://stackoverflow.com/questions/34049996/how-can-i-check-if-two-strings-have-the-same-letters-but-only-print-the-common

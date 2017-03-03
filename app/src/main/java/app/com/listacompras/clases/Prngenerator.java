package app.com.listacompras.clases;


import android.util.Log;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * daniel Mendez Cruz on 25/02/2017. Created By DMC
 */

public class Prngenerator{

    private LinkedList<Long> new_list = new LinkedList<>();
    StringBuilder stb = new StringBuilder();
    private long seed_generator;
    private int length_generator;
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
        long next_num = 0;
        z = z/a[trim];
        //next_num = z;
        for (int j = 0; j < length_generator; j++)
        {
            //next_num += (z%(a[trim]))*(a[j]);
            Log.d("next num" + String.valueOf(j), String.valueOf((z%(a[trim]))));
            if (j % 2 == 0)
            {
                new_list.addFirst(z%(a[trim]));
            }
             z /= 10;
        }

        for (int i = 0; i < new_list.size(); i++)
        {
            Log.d("random_generator: ", String.valueOf(new_list.get(i)));
            stb.append(new_list.get(i));
        }
        return  Long.valueOf(stb.toString().trim());
    }


}

package app.com.listacompras.clases;


import java.util.Random;

/**
 * daniel Mendez Cruz on 25/02/2017. Created By DMC
 */

public class Prngenerator  {
    private long seed_generator;
    private int ci_generator;
    private int a[] = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};
    /**
     *
     * @param seed seed value
     * @param cicles iterations' numbers that I want to generate
     */
    public Prngenerator(long seed, int cicles) {
        this.seed_generator = seed;
        this.ci_generator = cicles;
        //int start = 1, end = 1;
        //start = a[seed_generator-1];
        //end = a[seed_generator];

    }
    public long generator()
    {
        Random rand = new Random();
        rand.setSeed(seed_generator);
        return rand.nextInt(100000);
    }
}

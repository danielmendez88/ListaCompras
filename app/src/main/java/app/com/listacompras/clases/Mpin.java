package app.com.listacompras.clases;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * daniel Mendez Cruz on 25/04/2017. Created By DMC
 */

public class Mpin {
    /**
     * SECURE RANDOM VALUES TO GENERATE A RNG
     */
    SecureRandom rng2;
    /* construct class*/
    public Random Mpin()
    {
        try
        {
            // Create a secure random number generator using the SHA1PRNG algorithm
            rng2 = SecureRandom.getInstance("SHA1PRNG");

            // get 128 random bytes
            byte[] randomBytes = new byte[128];
            rng2.nextBytes(randomBytes);

            //create two secure number generators with the same seed
            int seedByteCount = 5;
            byte[] seed = rng2.generateSeed(seedByteCount);

            SecureRandom SR1 = SecureRandom.getInstance("SHA1PRNG");
            SR1.setSeed(seed);
            SecureRandom SR2 = SecureRandom.getInstance("SHA1PRNG");
            SR2.setSeed(seed);

            return SR1;
        }
        catch (NoSuchAlgorithmException e)
        {
            throw  new SecurityException("SHA1PRNG not available", e);
        }
    }
}

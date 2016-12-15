//This code has been modified for the job shop scheduling. The allele byte array has been converted into an int array.

import java.util.Random;
import java.io.* ;

public class Chromosome implements Serializable
{
  private int alleles[];		// Allele array
  private int allele_Length;
  private static Random r = new Random(); // Only the first time it is initialized


  // CONSTRUCTOR - FILL UP THE CONTENTS
  public Chromosome(int allele_length)
  {

    alleles = new int[allele_length];
    allele_Length = allele_length;
    for (int i=0; i < allele_length; i++){
	    alleles[i] = r.nextInt(16);
    }
  }

  public void set_allele(int index, int value)
  {
    alleles[index] = value;
  }

  public int get_allele(int index)
  {
    return alleles[index];
  }

  public void print()
  {
    for(int i=0; i<allele_Length; i++)
     System.out.print(alleles[i]);
  }

}
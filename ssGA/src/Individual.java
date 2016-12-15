import java.io.Serializable;

public class Individual implements Serializable
{
  private Chromosome chromosome;
  private int        allele_length;
  private double     fitness;

  public Individual(int L)
  {
     chromosome   = new Chromosome(L);
     fitness = 0.0;
     this.allele_length  = L;
  }

  public void print()
  {
     chromosome.print();
     System.out.print("   ");
     System.out.println(fitness);
  }

  public int get_length()
  {
     return allele_length;
  }

  public void set_fitness(double fit)
  {
     fitness = fit;
  }

  public double get_fitness()
  {
     return fitness;
  }

  public void set_allele(int index, int value)
  {
     chromosome.set_allele(index,value);
  }

  public int get_allele(int index)
  {
     return chromosome.get_allele(index);
  }

  private void copy(Chromosome source, Chromosome destination)
  {
     for (int i=0; i<allele_length; i++)
     {
         destination.set_allele(i,source.get_allele(i));
     }
  }

  public void assign(Individual I)
  {
    copy(I.get_chromosome(),chromosome);
    fitness = I.get_fitness();
    allele_length       = I.get_length();
  }

  public void set_chrom(Chromosome ch)
  {
     copy(ch,chromosome);
  }

  public Chromosome get_chromosome()
  {
     return chromosome;
  }

}
// END OF CLASS: Individual

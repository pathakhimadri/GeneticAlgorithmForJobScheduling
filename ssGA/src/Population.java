/***********************************************/
/** PROBLEM SOLVING                           **/
/** UNIVERSITY OF LUXEMBOURG                  **/
/** DEC 2010                                  **/
/** Prof. Pascal Bouvry                       **/
/** Assistant Patricia Ruiz                   **/
/** Assistant Cesar Diaz                      **/
/***********************************************/

public class Population
{
  // PRIVATE MEMORY
  private int       popsize;  // The number of individuals
  public Individual pop[];   // The vector of individuals

  private int chrom_length;   // The length of the chromosomes

  // STATISTICS
  private int     bestp; // The position of the best  individual: [0..popsize-1]
  private int     worstp;// The position of the worst individual: [0..popsize-1]
  private double  bestf; // The best fitness of the present population
  private double  avgf;  // The average fitness of the present population
  private double  worstf;// The worst fitness of the present population
  private double  BESTF; // The best fitness ever found during the search


  public Population(int ps, int chroml)
  {
    popsize      = ps;
    pop          = new Individual[popsize];
    chrom_length = chroml;

    for(int i=0;i<popsize;i++)  pop[i] = new Individual(chroml);

    // Initialize statistics
    bestp = 0;     worstp = 0;
    bestf = 0.0;   avgf   = 0.0;   worstf = 9999999999.0;    BESTF = 0.0;
  }
  
  
  //sort by QuickSort
  public void sort_pop(int start, int end) {
      if (start >= end)
          return;
      int i = start, j = end;
      int cur = i - (i - j) / 2;
      while (i < j) {
          while (i < cur && (pop[i].get_fitness() >= pop[cur].get_fitness())) {
              i++;
          }
          while (j > cur && (pop[cur].get_fitness() >= pop[j].get_fitness())) {
              j--;
          }
          if (i < j) {
              Individual temp = pop[i];
              pop[i] = pop[j];
              pop[j] = temp;
              if (i == cur)
                  cur = j;
              else if (j == cur)
                  cur = i;
          }
      }
      sort_pop(start, cur);
      sort_pop(cur+1, end);
  }
  
  //---!sort by QuickSort
  
  public int get_popsize()
  {
    return popsize;
  }

  public int worst_pos()
  {
    return worstp;
  }


  public Individual get_ith(int index)throws Exception
  {
    if ((index<popsize) && (index>=0))
    return pop[index];
    else
    throw new Exception("Index out of range when getting a copy of an individual");
  }

  public void set_ith(int index, Individual indiv)  throws Exception
  {
    if ((index<popsize) && (index>=0))
    pop[index].assign(indiv);
    else
    throw new Exception("Index out of range when inserting and individual");

    // ALWAYS RECOMPUTE STATS AFTER INSERTION
    compute_stats();
  }

  public void set_fitness( int index, double fitness ) throws Exception
  {
    pop[index].set_fitness(fitness);
  }

  public void compute_stats()
  {
    double f, total;

    // Initialize values (always needed!!!)
    total  = 0.0;
    worstf = pop[0].get_fitness();     worstp = 0;
    bestf  = pop[0].get_fitness();     bestp  = 0;

    for(int i=0;i<popsize;i++)
    {   f = pop[i].get_fitness();
    if(f<=worstf) {worstf = f; worstp = i;}
    if(f>=bestf)  {bestf  = f; bestp  = i;}
    if(f>=BESTF)  {BESTF  = f;            }
      total += f;
    }

    avgf = total/(double)popsize;
  }


public int    get_worstp() { return worstp; }
public int    get_bestp()  { return bestp;  }
public double get_worstf() { return worstf; }
public double get_avgf()   { return avgf;   }
public double get_bestf()  { return bestf;  }
public double get_BESTF()  { return BESTF;  }

  public void print()
  {
	  System.out.println("POPSIZE="+popsize);
    for(int i=0;i<popsize;i++)
    {  System.out.print(i);    System.out.print("   ");
      for(int j=0;j<chrom_length;j++)
   //   System.out.print(pop[i].get_allele(j));
      System.out.print("");
      System.out.println(pop[i].get_fitness()+" hbh");
    }
  }


  public void print_stats()
  {
    System.out.print(BESTF);  System.out.print("   ");
    System.out.print(bestf);  System.out.print("   ");
    System.out.print(avgf);   System.out.print("   ");
    System.out.print(worstf); System.out.print("   ");
    System.out.print(bestp);  System.out.print("   ");
    System.out.println(worstp);
  }

}
// END OF CLASS: Population

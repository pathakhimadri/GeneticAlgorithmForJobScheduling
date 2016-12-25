import java.math.BigDecimal;
import java.net.URL;

/***********************************************/
/** PROBLEM SOLVING                           **/
/** UNIVERSITY OF LUXEMBOURG                  **/
/** DEC 2010                                  **/
/** Prof. Pascal Bouvry                       **/
/** Assistant Patricia Ruiz                   **/
/** Assistant Cesar Diaz                      **/
/***********************************************/
//

//population - left top 10. Create rest 10
//rank selection
public class Exe
{
  public static void main(String args[]) throws Exception
  {
/*     
    // PARAMETERS PPEAKS 
    int    gn         = 512;                           // Gene number
    int    gl         = 1;                            // Gene length
    int    popsize    = 512;                          // Population size
    double pc         = 0.8;                          // Crossover probability
    double pm  = 1.0/(double)((double)gn*(double)gl); // Mutation probability
    double tf         = (double)1 ;              // Target fitness beign sought
    long   MAX_ISTEPS = 50000;
*/
      
    // PARAMETERS ONEMAX
    int    gn         = 512;                          // Gene number
    int    gl         = 1;                            // Gene length
    int    popsize    = 50;                          // Population size
    double pc         = 0.7;                          // Crossover probability
    double pm 		  = 0.01;
    //double pm  	= 1.0/(double)((double)gn*(double)gl); // Mutation probability
    double tf         = (double)gn*gl ;               // Target fitness being sought
    long   MAX_ISTEPS = 1000;
     //Crossover: 0.7, 0.5, 0.9
    //Mutation: 0.01, 0.005, 0.02
    Problem   problem;                             // The problem being solved
 
    problem = new ProblemMMS();
    String filename;
 //   filename="u_s_hihi_512_16";
  //  filename = "u_c_hihi_512_16";
  //  filename = "u_s_hilo_512_16";
  // filename = "u_s_lohi_512_16";
    filename = "u_s_lolo_512_16";
   // filename = "u_i_hihi_512_16";
    
    problem.create2DIntMatrixFromFile(filename);
	
    problem.set_geneN(gn);
    problem.set_geneL(gl);
    //problem.set_target_fitness(tf);



    Algorithm ga;          // The ssGA being used
    double [] result = new double[50];
    
    for(int j=0; j<50;j++){
    ga = new Algorithm(problem, popsize, gn, gl, pc, pm);

    for (int step=0; step<MAX_ISTEPS; step++)
    {  
    	
      for(int i=0;i<popsize/2; i++){
    	ga.go_one_step();
      }
   //   System.out.print(step); System.out.print("  ");
   //  System.out.println(ga.get_bestf());

      if(     (problem.tf_known())                    &&
      (ga.get_solution()).get_fitness()>=problem.get_target_fitness()
      )
      { System.out.print("Solution Found! After ");
        System.out.print(problem.get_fitness_counter());
        System.out.println(" evaluations");
        break;
      }

    }

    // Print the solution
    //for(int i=0;i<gn*gl;i++)
    	//System.out.print( (ga.get_solution()).get_allele(i) + "  " ); System.out.println();
    
 //   System.out.println("Best Fitness "+(ga.get_solution()).get_fitness());
  //  System.out.println("blabla");    
    String minMakeSpan = new BigDecimal(Double.valueOf(1000000000/ga.get_solution().get_fitness())).toString();
    result[j]=Double.valueOf(minMakeSpan);
    System.out.println( minMakeSpan);
  }
    double min=100000000;
    for(int i=0; i<50; i++)
    	if(result[i]<min)
    		min=result[i];

    System.out.println( "min="+min);
  }
}
// END OF CLASS: Exe

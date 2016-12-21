import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class ProblemMMS extends Problem{

	
	@Override
	public double Evaluate(Individual Indiv) {
		return MMSFitness(Indiv);
	}
		
	private double MMSFitness(Individual Indiv){
		//Step 1: Calculate the MMS
		double[] makeSpan = new double[16];
		
		for(int i = 0; i< 512; i++){
			int alleleMachineNumber;
			alleleMachineNumber = Indiv.get_chromosome().get_allele(i);
			makeSpan[alleleMachineNumber] += instanceMatrix[i][alleleMachineNumber];
		}
		double highest = 0;
		//Calculate the highest MakeSpan among all machines
		for(int i = 0; i< 16; i++){
			if(makeSpan[i]> highest){
				Indiv.set_makeSpanMachine(i);
				highest = makeSpan[i];
			}
		}
		//First step => fitness = 1/highest
		//Using 10000.. instead of 1 to get the fitness as whole numbers.
		double fitness = (1000000000/highest);
		Indiv.set_fitness(fitness);
		return fitness;
	}
	
}

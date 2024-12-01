package org.gmdev.genetic;

import lombok.Getter;

import java.util.List;

import static org.gmdev.utils.GeneticUtils.getByteArrayFromBytesString;

@Getter
public class FitnessCalculator {
	
    public static final int SOLUTION_SIZE = 64;

	private byte[] candidateSolution;

    public FitnessCalculator(String solutionString) {
        this.setCandidateSolution(solutionString);
    }

    /**
     * Set a candidate candidateSolution as a byte array of '0' and '1'
     */
    private void setCandidateSolution(String solutionString) {
        if (solutionString == null || solutionString.isEmpty())
            throw new IllegalArgumentException("solutionString cannot be empty");
        if (solutionString.length() != SOLUTION_SIZE)
            throw new IllegalArgumentException(String.format(
                    "solutionString size must be equal to: %s, but it is %s", SOLUTION_SIZE, solutionString.length()));

        solutionString.chars().forEach(it -> {
            if (!List.of("0", "1").contains(Character.toString(it)))
                throw new IllegalArgumentException(String.format("Value %s cannot be accepted", Character.toString(it)));
        });

        this.candidateSolution = getByteArrayFromBytesString(solutionString, SOLUTION_SIZE);
    }

    public int getFitness(Individual individual) {
        if (this.candidateSolution == null)
            throw new IllegalStateException("candidateSolution cannot be null");

        int fitness = 0;
        for (int i = 0; i < individual.getGenes().length; i++) {
            if (individual.getGene(i) == this.candidateSolution[i]) {
                fitness++;
            }
        }
        
        return fitness;
    }

    public int getMaxFitness() {
        return SOLUTION_SIZE;
    }

}

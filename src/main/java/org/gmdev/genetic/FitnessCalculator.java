package org.gmdev.genetic;

import lombok.Getter;

import java.util.List;

@Getter
public class FitnessCalculator {
	
    public static final int SOLUTION_SIZE = 64;
    private static FitnessCalculator INSTANCE;

	private byte[] candidateSolution;

    private FitnessCalculator() {}

    public synchronized static FitnessCalculator getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new FitnessCalculator();
        }

        return INSTANCE;
    }

    /**
     * Set a candidate candidateSolution as a byte array of '0' and '1'
     */
    public void setCandidateSolution(String solutionString) {
        if (solutionString == null)
            throw new IllegalArgumentException("solutionString cannot be null");
        if (solutionString.length() != SOLUTION_SIZE)
            throw new IllegalArgumentException(
                    String.format("solutionString size must be equal to: %s, but it is %s", SOLUTION_SIZE, solutionString.length()));

        this.candidateSolution = fromBytesString(solutionString, SOLUTION_SIZE);
    }

    public byte[] fromBytesString(String bytesString, int size) {
        byte[] byteArray = new byte[size];

        for (int i = 0; i < size; i++) {
            String stringChar = Character.toString(bytesString.charAt(i));

            if (!List.of("0", "1").contains(stringChar))
                throw new IllegalArgumentException(String.format("Value %s cannot be accepted", stringChar));

            byteArray[i] = Byte.parseByte(stringChar, 2);
        }

        return byteArray;
    }

    public int getFitness(Individual individual) {
        if (this.candidateSolution == null)
            throw new IllegalStateException("candidateSolution cannot be null");

        int fitness = 0;
        for (int i = 0; (i < individual.getGenes().length && i < this.candidateSolution.length); i++) {
            if (individual.getGene(i) == this.candidateSolution[i]) {
                fitness++;
            }
        }
        
        return fitness;
    }

    public int getMaxFitness() {
        return this.candidateSolution.length;
    }

    public void resetCandidateSolution() {
        this.candidateSolution = null;
    }

}

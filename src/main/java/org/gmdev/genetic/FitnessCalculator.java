package org.gmdev.genetic;

public class FitnessCalculator {
	
    public static final int SOLUTION_SIZE = 64;
    private static FitnessCalculator INSTANCE;

	private byte[] solution;

    private FitnessCalculator() {}

    public synchronized static FitnessCalculator getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new FitnessCalculator();
        }

        return INSTANCE;
    }

    /**
     * Set a candidate solution as a String of 0s and 1s
     */
    public void setSolutionFromString(String solution) {
        if (solution == null)
            throw new IllegalArgumentException("String solution cannot be null");
        if (solution.length() != SOLUTION_SIZE)
            throw new IllegalArgumentException(
                    String.format("String solution size must be equal to: %s, but it is %s", SOLUTION_SIZE, solution.length()));

        this.solution = new byte[SOLUTION_SIZE];

        for (int i = 0; i < solution.length(); i++) {
            String character =  Character.toString(solution.charAt(i));
            
            if (character.contains("0") || character.contains("1"))
                this.solution[i] = Byte.parseByte(character, 2);
            else
                throw new IllegalArgumentException(String.format("Value %s cannot be accepted", character));
        }
    }

    public int getFitness(Individual individual) {
        if (solution == null)
            throw new IllegalArgumentException("String solution cannot be null");

        int fitness = 0;
        for (int i = 0; (i < individual.size() && i < solution.length); i++) {
            if (individual.getGene(i) == solution[i]) {
                fitness++;
            }
        }
        
        return fitness;
    }

    public int getMaxFitness() {
        return solution.length;
    }

    public byte[] getSolution() {
        return solution;
    }

}

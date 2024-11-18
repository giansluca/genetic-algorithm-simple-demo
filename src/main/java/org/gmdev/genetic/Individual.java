package org.gmdev.genetic;

import lombok.Getter;

import java.util.stream.IntStream;

@Getter
public class Individual {
	
	private static final int GENES_SIZE = FitnessCalculator.SOLUTION_SIZE;

    private final FitnessCalculator fitnessCalculator;
    private final byte[] genes;
    private int fitness = 0;

    public Individual(byte[] genes, FitnessCalculator fitnessCalculator) {
        if (genes.length != GENES_SIZE)
            throw new IllegalArgumentException(String.format(
                    "genes size must be equal to: %s, but it is %s", GENES_SIZE, genes.length));

        this.genes = genes;
        this.fitnessCalculator = fitnessCalculator;
    }

    public Individual(FitnessCalculator fitnessCalculator) {
        this.genes = generateIndividualGenes();
        this.fitnessCalculator = fitnessCalculator;
    }

    private byte[] generateIndividualGenes() {
        byte[] genes = new byte[GENES_SIZE];
        IntStream.range(0, GENES_SIZE)
                .forEach(i -> genes[i] = (byte) Math.round(Math.random()));

        return genes;
    }
    
    public int getFitness() {
    	if (this.fitness == 0) {
            this.fitness = this.fitnessCalculator.getFitness(this);
    	}
    	
    	return this.fitness;
    }

    public byte getGene(int index) {
        return this.genes[index];
    }

    public void setGene(int index, byte value) {
        this.genes[index] = value;
        this.fitness = 0;
    }

    @Override
    public String toString() {
        StringBuilder geneString = new StringBuilder();
        IntStream.range(0, this.genes.length)
                .forEach(i -> geneString.append(getGene(i)));
        
        return geneString.toString();
    }
    
}

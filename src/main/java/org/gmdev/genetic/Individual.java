package org.gmdev.genetic;

import lombok.Getter;

@Getter
public class Individual {
	
	private static final int DEFAULT_GENE_LENGTH = 64;
	
    private final byte[] genes;
    private int fitness = 0;

    public Individual(byte[] genes) {
        this.genes = genes;
    }

    public Individual() {
        this.genes = new byte[DEFAULT_GENE_LENGTH];
    }

    public void generateIndividual() {
        for (int i = 0; i < this.genes.length; i++) {
            byte gene = (byte) Math.round(Math.random());
            this.genes[i] = gene;
        }
    }
    
    public int getFitness() {
    	if (this.fitness == 0) {
            var fitnessCalculator = FitnessCalculator.getInstance();
            this.fitness = fitnessCalculator.getFitness(this);
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
        
        for (int i = 0; i < this.genes.length; i++) {
            geneString.append(getGene(i));
        }
        
        return geneString.toString();
    }
    
}

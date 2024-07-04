package org.gmdev.genetic;

public class Individual {
	
	private static final int DEFAULT_GENE_LENGTH = 64;
	
    private final byte[] genes = new byte[DEFAULT_GENE_LENGTH];
    private int fitness = 0;

    public void generateIndividual() {
        for (int i = 0; i < this.size(); i++) {
            byte gene = (byte) Math.round(Math.random());
            genes[i] = gene;
        }
    }
    
    public int size() {
        return genes.length;
    }
    
    public int getFitness() {
    	if (fitness == 0) {
            var fitnessCalculator = FitnessCalculator.getInstance();
    		fitness = fitnessCalculator.getFitness(this);
    	}
    	
    	return fitness;
    }

    public byte getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, byte value) {
        this.genes[index] = value;
        this.fitness = 0;
    }
    
    @Override
    public String toString() {
        StringBuilder geneString = new StringBuilder();
        
        for (int i = 0; i < this.size(); i++) {
            geneString.append(getGene(i));
        }
        
        return geneString.toString();
    }
    
}

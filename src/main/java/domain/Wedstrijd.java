package domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import util.ArrayConverter;

@Entity
@Table(name = "wedstrijd")
public class Wedstrijd implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //unieke sleutel

	@Convert(converter = ArrayConverter.class)
    private String[] landen; //2 landen van de wedstrijd

    private int dag; //dag van de wedstrijd

    private int uur; //uur van de wedstrijd

 
    public Wedstrijd(String[] landen, int dag, int uur) {
        this.landen = landen;
        this.dag = dag;
        this.uur = uur;
    }
    
    protected Wedstrijd() {}

    
    
 

	public int getId() {
        return id;
    }

    public String[] getLanden() {
        return landen;
    }

    public int getDag() {
        return dag;
    }

    public int getUur() {
        return uur;
    }
    
    @Override
    public String toString()
    {
        return String.format("%s vs %s op %d-11", landen[0], landen[1], dag); 
    }
    
    @Override
 	public int hashCode() {
 		final int prime = 31;
 		int result = 1;
 		result = prime * result + Arrays.hashCode(landen);
 		result = prime * result + Objects.hash(dag, uur);
 		return result;
 	}

 	@Override
 	public boolean equals(Object obj) {
 		if (this == obj)
 			return true;
 		if (obj == null)
 			return false;
 		if (getClass() != obj.getClass())
 			return false;
 		Wedstrijd other = (Wedstrijd) obj;
 		return dag == other.dag && Arrays.equals(landen, other.landen) && uur == other.uur;
 	}
}

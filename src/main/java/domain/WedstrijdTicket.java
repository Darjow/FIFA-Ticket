package domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "wedstrijdticket")
@NamedQueries({
	@NamedQuery(name = "WedstrijdTicket.getWedstrijdenByStadium", query = "select wt from WedstrijdTicket wt where wt.stadium.id = :id")
})
public class WedstrijdTicket implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	@ManyToOne(fetch = FetchType.LAZY)
    private Wedstrijd wedstrijd; 
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "stadium_id")
	private Stadium stadium;
	

    private int tickets; //aantal tickets beschikbaar

    public WedstrijdTicket(Wedstrijd wedstrijd, int tickets) {
        this.wedstrijd = wedstrijd;
        this.tickets = tickets;
    }

    protected WedstrijdTicket() {
    	
    }
    
    public int getTickets() {
        return tickets;
    }
    
    public Wedstrijd getWedstrijd() {
        return wedstrijd;
    }
    
    public int ticketsKopen(int aantal) {
        if (aantal <= 0) {
            return -1;
        }
        
        if (tickets >= aantal) {
            tickets -= aantal;
            return aantal;
        }

        int gekocht = tickets;
        tickets = 0;
        return gekocht;
    }

    public boolean uitverkocht() {
        return tickets == 0;
    }

	@Override
	public int hashCode() {
		return Objects.hash(tickets, wedstrijd);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WedstrijdTicket other = (WedstrijdTicket) obj;
		return tickets == other.tickets && Objects.equals(wedstrijd, other.wedstrijd);
	}
}

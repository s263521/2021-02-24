package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	private Graph<Player, DefaultWeightedEdge > grafo;
	private List<Match> listaAllMatches;
	private List<Player> vertici;
	private Map<Integer, Player> verticiIdMap;
	
	
	public List<Match> getAllMatches() {
		PremierLeagueDAO plDao = new PremierLeagueDAO();
		listaAllMatches = plDao.listAllMatches();
		return listaAllMatches;
	}
	
	public String creaGrafo(Match m) {
		this.grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		PremierLeagueDAO plDao = new PremierLeagueDAO();
		this.vertici = plDao.listAllPlayersByMatches(m.getMatchID());
		this.verticiIdMap = new HashMap<>();
		for(Player p : vertici) {
			this.verticiIdMap.put(p.getPlayerID(), p);
		}
		
		Graphs.addAllVertices(this.grafo, this.vertici);
		
		List<Efficenze> efficenze = plDao.listAllValueForEfficenze(m.getMatchID());
		double peso = 0.0;
		double eff1 = 0.0;
		double eff2 = 0.0;
		
		for(Efficenze e1 : efficenze) {
			for(Efficenze e2 : efficenze) {
				if(e1.getPlayerId()!=e2.getPlayerId()) {
					eff1 = (e1.getTotalSuccessfulPassesAll() + e1.getAssists())/ e1.getTimePlayed();
					eff2 = (e2.getTotalSuccessfulPassesAll() + e2.getAssists())/ e2.getTimePlayed();
					peso = Math.abs(eff1-eff2);
					
					if(eff1>eff2) {
						Graphs.addEdgeWithVertices(this.grafo, this.verticiIdMap.get(e1.getPlayerId()), this.verticiIdMap.get(e2.getPlayerId()), peso);
					} else if(eff2>eff1) {
						Graphs.addEdgeWithVertices(this.grafo, this.verticiIdMap.get(e2.getPlayerId()), this.verticiIdMap.get(e1.getPlayerId()), peso);
					}
				}
			}
		}
		
		return String.format("Grafo creato con %d vertici e %d archi\n" , this.grafo.vertexSet().size(), this.grafo.edgeSet().size() );
	}
	
	
}

package it.polito.tdp.PremierLeague.model;

public class Efficenze {

	private int totalSuccessfulPassesAll;
	private int assists;
	private int timePlayed;
	private String playerId;
	
	public Efficenze(int totalSuccessfulPassesAll, int assists, int timePlayed, String playerId) {
		super();
		this.totalSuccessfulPassesAll = totalSuccessfulPassesAll;
		this.assists = assists;
		this.timePlayed = timePlayed;
		this.playerId=playerId;
	}

	public int getTotalSuccessfulPassesAll() {
		return totalSuccessfulPassesAll;
	}

	public void setTotalSuccessfulPassesAll(int totalSuccessfulPassesAll) {
		this.totalSuccessfulPassesAll = totalSuccessfulPassesAll;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	public int getTimePlayed() {
		return timePlayed;
	}

	public void setTimePlayed(int timePlayed) {
		this.timePlayed = timePlayed;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	
	
	
}

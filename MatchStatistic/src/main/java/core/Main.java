package core;

import afterPlaying.againtTeamTotal.OpponentGoalsFinder;
import afterPlaying.bestPlayers.PlayersLeaderBoard;
import afterPlaying.bestPlayers.PlayersLeaderBoardPrinter;
import afterPlaying.bestTeams.TeamsLeaderBoard;
import afterPlaying.bestTeams.TeamsLeaderBoardPrinter;
import afterPlaying.findEventsByTeam.EventByTypeFinder;
import afterPlaying.findEventsByTeam.GoalsFinder;
import afterPlaying.findMatchesInfoByLocation.HomeOrAwayMatchesFinder;
import afterPlaying.resultatFilter.TeamResults;
import afterPlaying.table.LigueTable;
import afterPlaying.table.LigueTablePrinter;
import afterPlaying.teamByPlayers.TeamByPlayerLeaderBoard;
import afterPlaying.teamByPlayers.TeamByPlayerLeaderBoardPrinter;
import afterPlaying.teamTotal.TeamTotalStatisticsPrinterNew;
import afterPlaying.teamTotal.TotalTeamStats;
import beforePlaying.Schedule;
import beforePlaying.Team;
import beforePlaying.TeamRepo;
import beforePlaying.Teams;
import playing.HistoryMatchesRepo;
import playing.PlayingMatches;

public class Main {

	public static void main(String[] args) {
		// CREATE TEAMS FOR LIGUE
		Teams allTeams = new Teams(new TeamRepo());

		// PREPARE SCHEDULE FOR CHOSEN TEAMS
		Schedule schedule = new Schedule(allTeams);

		// PLAY MATCHES FROM SCHEDULE
		PlayingMatches playingMatches = new PlayingMatches(schedule, new HistoryMatchesRepo());

		// PRINT INFO ABOUT LIGUE AND PLAYED MATCHES DETAILS
		printHeader(1, "AFTER PLAYING");
		System.out.println("Played ligue lines: " + schedule.getTotalLines() + "\n");
		System.out.println("FINAL RESULTATS:" + "\n");
		playingMatches.printPlayedMatchesGroupingByLigueLine();
		
		HistoryMatchesRepo allPlayedMatches = playingMatches.getAllPlayedMatches();
		
		Team selectedTeam = allTeams.getTeamById(1);
//		Team selectedTeam2 = allTeams.getTeamById(2);
//		Team selectedTeam3 = allTeams.getTeamById(3);
//		Team selectedTeam4 = allTeams.getTeamById(4);
		
		printHeader(2, "LIGUE TABLE");
		TeamResults teamResults = new TeamResults(new HomeOrAwayMatchesFinder(allPlayedMatches.getAllHistory()));
		LigueTable ligueTable = new LigueTable(
				allTeams.getAll(), 
				teamResults,
				new GoalsFinder(allPlayedMatches),
				new OpponentGoalsFinder(allPlayedMatches));
		LigueTablePrinter ligueTablePrinter = new LigueTablePrinter(ligueTable);
		ligueTablePrinter.print();
		System.out.println();
		
		// PRINT TOTAL GOALS AND TOTAL YELLOW CARDS FOR CHOSEN TEAM
		printHeader(3, "TEAM TOTAL");
		printSingleTotal(new GoalsFinder(allPlayedMatches), selectedTeam);
//		printSingleTotal(new GoalsFinder(allPlayedMatches), selectedTeam2);
//		printSingleTotal(new GoalsFinder(allPlayedMatches), selectedTeam3);
//		printSingleTotal(new GoalsFinder(allPlayedMatches), selectedTeam4);
//		printSingleTotal(new YellowCardsFinders(allPlayedMatches), selectedTeam);
		
		// PRINT TEAMS LEADERBOARDS
		printHeader(4, "TEAMS LEADERBOARD");
		printTeamsLeaderBoard(new GoalsFinder(allPlayedMatches), allTeams);
//		printTeamsLeaderBoard(new YellowCardsFinders(allPlayedMatches), allTeams);
		
		// PRINT TEAM BY PLAYER LEADERBOARDS
		printHeader(5, "PLAYERS IN TEAM LEADERBOARD");
		printTeamByPlayerLeaderBoard(new GoalsFinder(allPlayedMatches), selectedTeam);
//		printTeamByPlayerLeaderBoard(new YellowCardsFinders(allPlayedMatches), selectedTeam);
		
		// PRINT PLAYERS LEADERBOARDS
		printHeader(6, "PLAYERS LEADERBOARD");
		printPayersLeaderBoard(new GoalsFinder(allPlayedMatches),allTeams);
		System.out.println();
//		printPayersLeaderBoard(new YellowCardsFinders(allPlayedMatches),allTeams);
	}

	private static void printPayersLeaderBoard(EventByTypeFinder whichLeaderBoard, Teams allTeams)  {
		TeamByPlayerLeaderBoard teamByPlayerLeaderBoard = new TeamByPlayerLeaderBoard(whichLeaderBoard);
		PlayersLeaderBoard playersLeaderBoard = new PlayersLeaderBoard(teamByPlayerLeaderBoard, allTeams.getAll());
		PlayersLeaderBoardPrinter playersLeaderBoardPrinter = new PlayersLeaderBoardPrinter(playersLeaderBoard);
		playersLeaderBoardPrinter.printAll();
	}

	private static void printTeamByPlayerLeaderBoard(EventByTypeFinder whichLeaderBoard, Team selectedTeam) {
		TeamByPlayerLeaderBoard teamByPlayerLeaderBoard = new TeamByPlayerLeaderBoard(whichLeaderBoard);
		TeamByPlayerLeaderBoardPrinter teamByPlayerLeaderBoardPrinter = new TeamByPlayerLeaderBoardPrinter(teamByPlayerLeaderBoard);
		teamByPlayerLeaderBoardPrinter.printAll(selectedTeam);
	}

	private static void printTeamsLeaderBoard(EventByTypeFinder whichLeaderBoard, Teams allTeams) {
		TotalTeamStats totalTeamStats = new TotalTeamStats(whichLeaderBoard);
		TeamsLeaderBoard teamsLeaderBoard = new TeamsLeaderBoard(totalTeamStats, allTeams.getAll());
		TeamsLeaderBoardPrinter teamsLeaderBoardPrinter = new TeamsLeaderBoardPrinter(teamsLeaderBoard);
		teamsLeaderBoardPrinter.printAll();
	}

	private static void printSingleTotal(EventByTypeFinder whichStats, Team selectedTeam) {
		TotalTeamStats totalTeamStats = new TotalTeamStats(whichStats);
		TeamTotalStatisticsPrinterNew pr = new TeamTotalStatisticsPrinterNew(totalTeamStats);
		pr.printAllTeamStatistics(selectedTeam);
	}

	private static void printHeader(int moduleNumber, String message) {
		System.out.print("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.print("MODULE: " + moduleNumber + " - " + message);
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println();
	}

}

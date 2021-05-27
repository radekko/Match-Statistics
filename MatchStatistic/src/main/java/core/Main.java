package core;

import java.util.List;

import afterPlaying.bestPlayers.IPlayersLeaderBoard;
import afterPlaying.bestPlayers.infrastructure.PlayersLeaderBoardFactory;
import afterPlaying.bestPlayers.infrastructure.PlayersLeaderBoardPrinter;
import afterPlaying.bestPlayers.infrastructure.PlayersLeaderBoardFactory.PlayerLeaderBoardType;
import afterPlaying.bestTeams.ITeamsLeaderBoard;
import afterPlaying.bestTeams.infrastructure.TeamsLeaderBoardFactory;
import afterPlaying.bestTeams.infrastructure.TeamsLeaderBoardPrinter;
import afterPlaying.bestTeams.infrastructure.TeamsLeaderBoardFactory.LeaderBoardType;
import afterPlaying.ligueTable.ILigueTable;
import afterPlaying.ligueTable.infrastructure.LigueTableFactory;
import afterPlaying.ligueTable.infrastructure.LigueTablePrinter;
import afterPlaying.ligueTable.infrastructure.LigueTableFactory.LigueTableType;
import afterPlaying.teamStatsByPlayers.ITeamByPlayerLeaderBoard;
import afterPlaying.teamStatsByPlayers.infrastructure.TeamByPlayerLeaderBoardFactory;
import afterPlaying.teamStatsByPlayers.infrastructure.TeamByPlayerLeaderBoardPrinter;
import afterPlaying.teamStatsByPlayers.infrastructure.TeamByPlayerLeaderBoardFactory.TeamByPlayerLeaderBoardType;
import afterPlaying.teamStatsTotal.IDetailStats;
import afterPlaying.teamStatsTotal.core.EventType;
import afterPlaying.teamStatsTotal.core.Localization;
import afterPlaying.teamStatsTotal.infrastructure.TeamDetailStatsFactory;
import afterPlaying.teamStatsTotal.infrastructure.TeamTotalStatisticsPrinter;
import beforePlaying.core.ISchedule;
import beforePlaying.core.model.Team;
import beforePlaying.infrastructure.ScheduleFactory;
import beforePlaying.infrastructure.TeamDatabase;
import beforePlaying.infrastructure.TeamsRepo;
import playing.core.IPlayingMatches;
import playing.core.model.MatchPlayedInfo;
import playing.infrastructure.PlayingMatchesFactory;

public class Main {

	public static void main(String[] args) {
		// CREATE TEAMS FOR LIGUE
		TeamsRepo allTeams = new TeamsRepo(new TeamDatabase());

		// PREPARE SCHEDULE FOR CHOSEN TEAMS
		ISchedule schedule = ScheduleFactory.getInstance(allTeams);

		// PLAY MATCHES FROM SCHEDULE
		IPlayingMatches playingMatches = PlayingMatchesFactory.getPlayingMatchesInstance(schedule);
		List<MatchPlayedInfo> playedMatches = playingMatches.getPlayedMatches();
		
		// PRINT INFO ABOUT LIGUE AND PLAYED MATCHES DETAILS
		printHeader(1, "AFTER PLAYING");
		System.out.println("Played ligue lines: " + schedule.getTotalLines() + "\n");
		System.out.println("FINAL RESULTATS:" + "\n");
		playingMatches.printPlayedMatchesGroupingByLigueLine();
		
		Team selectedTeam = allTeams.getTeamById(1);
		Team selectedTeam2 = allTeams.getTeamById(2);
//		Team selectedTeam3 = allTeams.getTeamById(3);
//		Team selectedTeam4 = allTeams.getTeamById(4);
		
		printHeader(2, "LIGUE TABLE");
		ILigueTable ligueTable = LigueTableFactory.getInstance(LigueTableType.TOTAL, playedMatches);
		LigueTablePrinter ltp = new LigueTablePrinter(ligueTable);
		ltp.print(allTeams.getAll());
		
		// PRINT TOTAL GOALS AND TOTAL YELLOW CARDS FOR CHOSEN TEAM
		printHeader(3, "TEAM TOTAL");
		IDetailStats homeAndAwayGoals = TeamDetailStatsFactory.getInstance(EventType.GOALS_GAINED, Localization.BOTH, playedMatches);
		TeamTotalStatisticsPrinter ttsp = new TeamTotalStatisticsPrinter(homeAndAwayGoals, "home and away goals");
		ttsp.print(selectedTeam);
		ttsp.print(selectedTeam2);

		// PRINT TEAMS LEADERBOARDS
		printHeader(4, "TEAMS LEADERBOARD");
		ITeamsLeaderBoard teamsLeaderBoard = TeamsLeaderBoardFactory.getTeamsLeaderBoard(LeaderBoardType.GOALS_TOTAL, playedMatches);
		TeamsLeaderBoardPrinter tlp = new TeamsLeaderBoardPrinter(teamsLeaderBoard, "goals total");
		tlp.printAll(allTeams.getAll());
		
		// PRINT TEAM BY PLAYER LEADERBOARDS
		printHeader(5, "PLAYERS IN TEAM LEADERBOARD");
		ITeamByPlayerLeaderBoard teamByPlayerLeaderBoard = TeamByPlayerLeaderBoardFactory.getInstance(TeamByPlayerLeaderBoardType.BOTH_GOALS, playedMatches);
		TeamByPlayerLeaderBoardPrinter tbpl = new TeamByPlayerLeaderBoardPrinter(teamByPlayerLeaderBoard,"total goals");
		tbpl.printAll(selectedTeam, playedMatches);
		
		// PRINT PLAYERS LEADERBOARDS
		printHeader(6, "PLAYERS LEADERBOARD");
		IPlayersLeaderBoard playersLeaderBoard = PlayersLeaderBoardFactory.getInstance(PlayerLeaderBoardType.PLAYER_BOTH_GOALS, playedMatches);
		PlayersLeaderBoardPrinter plp = new PlayersLeaderBoardPrinter(playersLeaderBoard,"total goals");
		plp.printAll(allTeams.getAll(), playedMatches);
	}

	private static void printHeader(int moduleNumber, String message) {
		System.out.print("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.print("MODULE: " + moduleNumber + " - " + message);
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println();
	}
}

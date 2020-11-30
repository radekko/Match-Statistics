package core;

import java.util.List;

import afterPlaying.bestPlayers.PlayersLeaderBoard;
import afterPlaying.bestPlayers.PlayersLeaderBoardFactory;
import afterPlaying.bestPlayers.PlayersLeaderBoardFactory.PlayerLeaderBoardType;
import afterPlaying.bestPlayers.PlayersLeaderBoardPrinter;
import afterPlaying.bestTeams.TeamsLeaderBoard;
import afterPlaying.bestTeams.TeamsLeaderBoardFactory;
import afterPlaying.bestTeams.TeamsLeaderBoardFactory.LeaderBoardType;
import afterPlaying.bestTeams.TeamsLeaderBoardPrinter;
import afterPlaying.ligueTable.LigueTable;
import afterPlaying.ligueTable.LigueTableFactory;
import afterPlaying.ligueTable.LigueTableFactory.LigueTableType;
import afterPlaying.ligueTable.LigueTablePrinter;
import afterPlaying.teamStatsByPlayers.TeamByPlayerLeaderBoard;
import afterPlaying.teamStatsByPlayers.TeamByPlayerLeaderBoardFactory;
import afterPlaying.teamStatsByPlayers.TeamByPlayerLeaderBoardFactory.TeamByPlayerLeaderBoardType;
import afterPlaying.teamStatsByPlayers.TeamByPlayerLeaderBoardPrinter;
import afterPlaying.teamStatsTotal.DetailStats;
import afterPlaying.teamStatsTotal.TeamDetailStatsStatsFactory;
import afterPlaying.teamStatsTotal.TeamDetailStatsStatsFactory.EventType;
import afterPlaying.teamStatsTotal.TeamDetailStatsStatsFactory.Localization;
import afterPlaying.teamStatsTotal.TeamTotalStatisticsPrinter;
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
		LigueTable ligueTable = LigueTableFactory.getInstance(LigueTableType.TOTAL, playedMatches);
		LigueTablePrinter ltp = new LigueTablePrinter(ligueTable);
		ltp.print(allTeams.getAll());
		
		// PRINT TOTAL GOALS AND TOTAL YELLOW CARDS FOR CHOSEN TEAM
		printHeader(3, "TEAM TOTAL");
		DetailStats detailStats = TeamDetailStatsStatsFactory.getInstance(EventType.GOALS, Localization.BOTH);
		TeamTotalStatisticsPrinter ttsp = new TeamTotalStatisticsPrinter(detailStats, playedMatches, "home and away goals");
		ttsp.print(selectedTeam);
		ttsp.print(selectedTeam2);

		// PRINT TEAMS LEADERBOARDS
		printHeader(4, "TEAMS LEADERBOARD");
		TeamsLeaderBoard teamsLeaderBoard = TeamsLeaderBoardFactory.getTeamsLeaderBoard(LeaderBoardType.GOALS_TOTAL);
		TeamsLeaderBoardPrinter tlp = new TeamsLeaderBoardPrinter(teamsLeaderBoard, "goals total");
		tlp.printAll(allTeams.getAll(), playedMatches);
		
		// PRINT TEAM BY PLAYER LEADERBOARDS
		printHeader(5, "PLAYERS IN TEAM LEADERBOARD");
		TeamByPlayerLeaderBoard teamByPlayerLeaderBoard = TeamByPlayerLeaderBoardFactory.getInstance(TeamByPlayerLeaderBoardType.BOTH_GOALS);
		TeamByPlayerLeaderBoardPrinter tbpl = new TeamByPlayerLeaderBoardPrinter(teamByPlayerLeaderBoard,"total goals");
		tbpl.printAll(selectedTeam, playedMatches);
		
		// PRINT PLAYERS LEADERBOARDS
		printHeader(6, "PLAYERS LEADERBOARD");
		PlayersLeaderBoard playersLeaderBoard = PlayersLeaderBoardFactory.getInstance(PlayerLeaderBoardType.PLAYER_BOTH_GOALS);
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

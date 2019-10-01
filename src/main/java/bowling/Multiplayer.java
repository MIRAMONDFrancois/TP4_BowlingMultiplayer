/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowling;

/**
 *
 * @author pedago
 */
public class Multiplayer implements MultiPlayerGame {

    String[] players;
    SinglePlayerGame[] games;
    int i_player;
    int i_tour;
    
    @Override
    public String startNewGame(String[] playerName) throws Exception {
        i_tour = 0;
        i_player = 0;
        players = playerName;
        
        games = new SinglePlayerGame[players.length];
        for (int i = 0; i < games.length; i++) {
            games[i] = new SinglePlayerGame();
        }
        
        return "Prochain tir: joueur " + players[i_player]
                + ", tour " + games[i_player].getFrameNumber()
                + ", boule " + games[i_player].getNextBallNumber();
        
    }

    @Override
    public String lancer(int nombreDeQuillesAbattues) throws Exception {
        games[i_player].lancer(nombreDeQuillesAbattues);
        if (games[i_player].getFrameNumber() != i_tour+1 && i_player == players.length-1) {
            i_tour++;
            i_player = 0;
            //System.out.println("[?] changement de tour");
        } else {
            if (games[i_player].getFrameNumber() != i_tour+1) {
                i_player++;
                //System.out.println("[?] changement de joueurs");
            } else {
                //System.out.println("[?] le joueur rejoue");
            }
        }
        return "Prochain tir: joueur " + players[i_player]
                + ", tour " + games[i_player].getFrameNumber()
                + ", boule " + games[i_player].getNextBallNumber();
    }

    @Override
    public int scoreFor(String playerName) throws Exception {
        int i = 0;
        while (i < players.length) {
            if (players[i].equals(playerName)) {
                return games[i].score();
            }
            i++;
        }
        return -1;
    }
    
    public static void main (String[] args) throws Exception {
        String[] players = { "John", "Paul", "Georges", "Ringo" };
        MultiPlayerGame game = new Multiplayer();
        
        System.out.println(game.startNewGame(players));
        System.out.println(game.lancer(10)); // Strike for John
        System.out.println(game.lancer(3));
        System.out.println(game.lancer(7)); // Spare for Paul
        System.out.println(game.lancer(0));
        System.out.println(game.lancer(0)); // 0 for Georges
        System.out.println(game.lancer(0));
        System.out.println(game.lancer(0)); // 0 for Ringo
        System.out.println(game.lancer(6));
        System.out.println(game.lancer(3)); // 9 for john, + strike bonus
        System.out.println(game.lancer(5));
        System.out.println(game.lancer(0)); // 5 for Paul, + spare bonus
        
        for (String playerName : players)
	System.out.printf("Player: %s, score: %d %n",
		playerName,
		game.scoreFor(playerName));
        
    }
    
}

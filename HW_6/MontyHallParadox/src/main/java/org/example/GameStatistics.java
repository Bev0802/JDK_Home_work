package org.example;

public class GameStatistics {
    private int switchWins = 0;
    private int stayWins = 0;

    public void updateStatistics(GameRound gameRound, int initialChoiceIndex, int remainingChoiceIndex) {        
        String initialChoiceResult = gameRound.getDoors().get(initialChoiceIndex);
        String remainingChoiceResult = gameRound.getDoors().get(remainingChoiceIndex);

        if (remainingChoiceResult.equals("car")) {
           switchWins++;
        } else if (initialChoiceResult.equals("car")) {
                stayWins++;
        }
    }
    

    public void displayStatistics() {
        System.out.println("\nСтатистика в случае изменения выбора:");
        System.out.println("Побед: " + switchWins);

        System.out.println("\nСтатистика в случае, если не менял выбор:");
        System.out.println("Побед: " + stayWins);
    }
}


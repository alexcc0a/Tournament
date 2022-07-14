package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

public class Tests {
    Collection<Player> tests = new ArrayList<>();
    Game game = new Game();
    Player player1 = new Player(1, "Valeria", 6);
    Player player2 = new Player(2, "Alexsander", 8);
    Player player3 = new Player(3, "Tatiana", 7);
    Player player4 = new Player(4, "Dmitry", 4);
    Player player5 = new Player(5, "Andrew", 4);

    @Test
    public void shouldFindByName() {
        game.register(player1);
        game.register(player2);
        int actual = game.findByName("Alexsander");
        int expected = 1;
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void ShouldFindByNotRegisteredName() {
        game.register(player1);
        game.register(player2);
        int actual = game.findByName("player4");
        int expected = -1;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void ExceptionWithNotRegisteredPlayer() {
        game.register(player1);
        game.register(player2);
        int expected = -1;
        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Alexsander", "Tatiana");
        });
    }

    @Test
    public void ExceptionWithNotRegisteredPlayers() {
        game.register(player1);
        game.register(player2);
        int expected = -1;
        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Dmitry", "Tatiana");
        });
    }

    @Test
    public void ExceptionWhenEmptyRegisteredField() {
        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Ilya", "Jane");
        });
    }


    @Test
    public void WhenPlayer1IsWinner() {
        game.register(player2);
        game.register(player3);
        int actual = game.round("Alexsander", "Tatiana");
        int expected = 1;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void WhenPlayer2IsWinner() {
        game.register(player1);
        game.register(player2);
        int actual = game.round("Valeria", "Alexsander");
        int expected = 2;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void whenStrengthSame() {
        game.register(player4);
        game.register(player5);
        int actual = game.round("Dmitry", "Andrew");
        int expected = 0;
        Assertions.assertEquals(expected, actual);
    }
}


package sudoku.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import sudoku.problemdomain.IStorage;
import sudoku.problemdomain.SudokuGame;

public class LocalStorageImpl implements IStorage {

    private static File GAME_DATA = new File(
            System.getProperty("user.home"),
            "gamedata.txt");

    @Override
    public SudokuGame getGameData() throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(GAME_DATA);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            SudokuGame gamestate = (SudokuGame) objectInputStream.readObject();
            objectInputStream.close();
            return gamestate;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IOException("Arquivo não encontrado!");
        }
    }

    @Override
    public void updateGameData(SudokuGame game) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(GAME_DATA);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(game);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new IOException("Game Data não encontrado!");
        }
    }

}

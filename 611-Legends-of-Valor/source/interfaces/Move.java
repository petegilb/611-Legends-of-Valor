package lmh.interfaces;

public interface Move {
    void makeMovement(int row, int col);

    void teleport();

    void backToNexus();

    int[] getCurrPosition();

    void printNeighborInfo();
}

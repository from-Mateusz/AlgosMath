package cz.mateusz.recursion;

public class Handshaker {

    public int makeHandshakes(int guests) {
        if(guests <= 1) return 0;
        return (guests - 1) + makeHandshakes(guests - 1);
    }
}

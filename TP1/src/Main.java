public class Main {
    public static void main(String[] args){
        GenImpl g = new GenImpl();

        for(int i = 0; i < 4; i++) {
            Afficheur aff = new Afficheur();
            Canal c = new Canal(g);
            g.addObserver(c);
            c.addObserver(aff);
        }

        g.generate();
        g.generate();
        g.generate();
        g.generate();
        g.generate();
        g.generate();
        g.generate();
    }
}

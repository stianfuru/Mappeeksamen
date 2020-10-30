package no.oslomet.cs.algdat.Eksamen;

import java.util.*;

public class EksamenSBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public EksamenSBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null;
        int cmp = 0;

        while (p != null)
        {
            q = p;
            cmp = comp.compare(verdi,p.verdi);
            p = cmp < 0 ? p.venstre : p.høyre;
        }

        p = new Node(verdi,q);

        if (q == null){
            rot = p;
            p.forelder = null;
        }
        else if (cmp < 0){
            q.venstre = p;
        }
        else{
            q.høyre = p;
        }

        antall++;
        return true;
    }

    public boolean fjern(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot, q = null;

        while (p != null)
        {
            int cmp = comp.compare(verdi,p.verdi);
            if (cmp < 0) {
                q = p;
                p = p.venstre;
            }

            else if (cmp > 0) {
                q = p;
                p = p.høyre;
            }
            else break;
        }
        if (p == null) return false;

        if (p.venstre == null || p.høyre == null)
        {
            Node<T> b = p.venstre != null ? p.venstre : p.høyre;

            if (p == rot){
                rot = b;
            }
            else if (p == q.venstre){
                q.venstre = b;
            }
            else {
                q.høyre = b;
            }
            if(b != null){
                b.forelder = q;
            }
        }
        else
        {
            Node<T> s = p, r = p.høyre;
            while (r.venstre != null)
            {
                s = r;
                r = r.venstre;

            }

            p.verdi = r.verdi;

            if (s != p){
                s.venstre = r.høyre;
                if(s.venstre != null){
                    s.venstre.forelder = s;
                }

            }
            else{
                s.høyre = r.høyre;
                if(s.høyre != null){
                    s.høyre.forelder = s;
                }

            }
        }
        antall--;
        return true;
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int antall(T verdi) {
        if (verdi == null) return 0;

        Node<T> p = rot;
        int cmp = 0;
        int teller = 0;


        while (p != null){
            cmp = comp.compare(verdi, p.verdi);

            if(cmp < 0){
                p = p.venstre;
            }
            else if(p.verdi == verdi){
                teller++;
                p = p.høyre;
            }
            else{
                p = p.høyre;
            }
        }

        return teller;
    }

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {
        while (p.forelder != null){
            p = p.forelder;
        }

        while (true) {
            if (p.venstre != null){
                p = p.venstre;
            }
            else if (p.høyre != null){
                p = p.høyre;
            }
            else{
                return p;
            }
        }
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
       while(true){
            if(p.forelder == null){
                return null;
            }
            else if(p == p.forelder.høyre){
                p = p.forelder;
                return p;
            }
            else{
                if(p.forelder.høyre == null){
                    p = p.forelder;
                    return p;
                }
                else{
                    p = p.forelder.høyre;
                    while(p.høyre != null || p.venstre != null){
                        if(p.venstre == null){
                            p = p.høyre;
                        }
                        else{
                            p = p.venstre;
                        }
                    }
                    return p;
                }
            }
        }
    }

    public void postorden(Oppgave<? super T> oppgave) {
        Node<T> p = førstePostorden(rot);
        oppgave.utførOppgave(p.verdi);

        while(nestePostorden(p) != null){
            p = nestePostorden(p);
            oppgave.utførOppgave(p.verdi);
        }

    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        if(p == null){
            return;
        }
        postordenRecursive(p.venstre,oppgave);
        postordenRecursive(p.høyre,oppgave);

        oppgave.utførOppgave(p.verdi);
    }

    public ArrayList<T> serialize() {
        ArrayList<T> serie = new ArrayList<>();
        ArrayDeque<Node> kø = new ArrayDeque<>();

        kø.addLast(rot);
        serie.add(rot.verdi);

        while(!kø.isEmpty()){

            Node<T> current = kø.removeFirst();

            if (current.venstre != null){
                kø.addLast(current.venstre);
                serie.add(current.venstre.verdi);
            }
            if(current.høyre != null){
                kø.addLast(current.høyre);
                serie.add(current.høyre.verdi);
            }

        }

        return serie;
    }

    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


} // ObligSBinTre

class main{
    public static void main(String[] args){
        /*Integer[] a = {5,3,7,2,4,6,8};
        EksamenSBinTre<Integer> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre.leggInn(verdi);

        System.out.println(tre.toStringPostOrder());*/


        int[] b = {4,3,2,6,7,5,8,10,41};
        EksamenSBinTre<Integer> tre2 = new EksamenSBinTre<>(Comparator.naturalOrder());
        for (int verdi : b) tre2.leggInn(verdi);

        tre2.fjern(10);
        System.out.println(tre2.antall()); // 7
        System.out.println(tre2.toStringPostOrder());

    }
}

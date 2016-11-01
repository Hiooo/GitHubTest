package Skole;

/*
 * @author fredrikjensen
 */ 

 import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class DobbeltLenketListe<T> implements Liste<T>
{
     
    
  private static final class Node<T>   // en indre nodeklasse
  {
    // instansvariabler
    private T verdi;
    private Node<T> forrige, neste;

    private Node(T verdi, Node<T> forrige, Node<T> neste)  // konstruktør
    {
      this.verdi = verdi;
      this.forrige = forrige;
      this.neste = neste;
    }

    protected Node(T verdi)  // konstruktør
    {
      this(verdi, null, null);
    }

  } // Node

  // instansvariabler
  private Node<T> hode;          // peker til den første i listen
  private Node<T> hale;          // peker til den siste i listen
  private int antall;            // antall noder i listen
  private int endringer;   // antall endringer i listen

  // hjelpemetode
  private Node<T> finnNode(int indeks)
  {
    if(indeks<antall/2){
      Node<T> p = hode;
      for (int i = 0; i < indeks; i++) p = p.neste;
      return p;
    }
    else{
      Node<T> p = hale;
      for(int i = antall-1; i > indeks; i--) p = p.forrige;
      return p;
    }
    
    
  }
private static void fratilKontroll(int antall, int fra, int til)
  {
    if (fra < 0)                                  // fra er negativ
      throw new IndexOutOfBoundsException
        ("fra(" + fra + ") er negativ!");

    if (til > antall)                          // til er utenfor tabellen
      throw new IndexOutOfBoundsException
        ("til(" + til + ") > tablengde(" + antall + ")");

    if (fra > til)                                // fra er større enn til
      throw new IllegalArgumentException
        ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
  }
  
  
  // konstruktør
  public DobbeltLenketListe()
  {
    hode = hale = null;
    antall = 0;
    endringer = 0;
  }

  // konstruktør
  public DobbeltLenketListe(T[] a)
  {
     {
    if(a==null){
        throw new NullPointerException("Tabellen a er null!");
    }
    else if(a.length==0){
        
    }
    else{
     int i = 0;
     for (; i < a.length; i++) {
            if(a[i]!=null){
                hode = hale = new Node<>(a[i]);
                antall++;
                break;
            }
        }
        for (int j = i+1; j < a.length; j++) {
            if(a[j]!=null){
                hale = hale.neste = new Node<>(a[j], hale, null);
                antall++;
            }
        }
    }
  }
    }

  public Liste<T> subliste(int fra, int til)
  {
  if (fra > til)                                  // fra er negativ
      throw new IllegalArgumentException("fra(" + fra + ") er negativ!");
       
   Liste<T> nyListe = new DobbeltLenketListe<>();
      if(fra == til){
          return nyListe;
      }
      for(int i = fra; i < til; i++){
          nyListe.leggInn(hent(i));
      }
      return nyListe;
  
  } 
      
  @Override
  public int antall()
  {
    return antall;
  }

  @Override
  public boolean tom()
  {
    return antall == 0;
  }

  @Override
  public boolean leggInn(T verdi)
  {
     Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");

    if (antall == 0)  hode = hale = new Node<>(verdi, null, null);  // tom liste
    else hale = hale.neste = new Node<T>(verdi, hale, null);         // legges bakerst

    antall++;                  // en mer i listen
    endringer++;
    return true;               // vellykket innlegging
  }

  @Override
  public void leggInn(int indeks, T verdi)
  {
    Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");
    indeksKontroll(indeks, true);
    
    if (tom())
            hale = hode = new Node<>(verdi, null, null);

    else if (indeks == 0)
        {
            hode = new Node<>(verdi, null, hode);
            hode.neste.forrige = hode;
        }
    else if (indeks == antall)
        {
          hale = hale.neste = new Node<>(verdi, hale,null); 
        }
    else
        {
      Node<T> p = hode;
      for (int i = 1; i < indeks; i++) p = p.neste;
      p.neste = new Node<>(verdi, p, p.neste);
      
      Node<T> p2 = hale;
      for (int i = antall-1; i > indeks; i--) p2 = p2.forrige;
      p2.forrige = new Node<>(verdi, p2.forrige, p2);
    }


    endringer ++;
    antall++;
  }

  @Override
  public boolean inneholder(T verdi)
  {
    return indeksTil(verdi) != -1;
  }

  @Override
  public T hent(int indeks)
  {
    indeksKontroll(indeks, false);
    return finnNode(indeks).verdi;
  
  }

  @Override
  public int indeksTil(T verdi)
  {
    if (verdi == null) return -1;

    Node<T> p = hode;

    for (int indeks = 0; indeks < antall ; indeks++)
    {
      if (p.verdi.equals(verdi)) return indeks;
      p = p.neste;
    }
    return -1;
  }

  @Override
  public T oppdater(int indeks, T nyverdi)
  {
     Objects.requireNonNull(nyverdi, "Ikke tillatt med null-verdier!");

    indeksKontroll(indeks, false);  

    Node<T> p = finnNode(indeks);
    T gammelVerdi = p.verdi;

    p.verdi = nyverdi;
    endringer++;
    return gammelVerdi;
  }

  @Override

  public boolean fjern(T verdi)
  {
      if(tom()){
          return false;
      }
      Node<T> start = hode;
      
      while(start != null){
          if(start.verdi .equals(verdi)){
              
              Node<T> slettes = start;
              
              if(start .equals(hode) && hode.neste != null){
                  hode = hode.neste;
                  hode.forrige = null;
                  
                  
              }else if(start .equals(hode) && hode.neste == null){
                  
                  hode = hale = null;
                  
              }
              else if(start .equals(hale)){
                  
                 hale.forrige.neste = null;
                 hale = hale.forrige;
                  
              }else{
                  
              start.neste.forrige = start.forrige;
              start.forrige.neste = start.neste;
              }
              
              antall--;
              endringer++;
              return true;
          }
          start = start.neste;
      }
      
      return false;
  }

  @Override
 
  public T fjern(int indeks)
  {
      indeksKontroll(indeks, true);
       if(indeks < 0 || indeks>=antall){
        throw new IndexOutOfBoundsException();
    }
      
      Node<T> slettes = finnNode(indeks);
      
      if(tom()){
          return null;
      }
      else if(indeks == 0 && hode.neste != null){
          hode.neste.forrige = null;
          hode = hode.neste;
      }else if(indeks == 0 && hode.neste == null){
          hode = hale = null;
      }else if(indeks == antall -1){
          hale = hale.forrige;
          hale.neste = null;
      }else{
          
        Node<T> venstre = slettes.forrige;
        Node<T> høyre = slettes.neste;
        venstre.neste = høyre;
        høyre.forrige = venstre; 
      }
      
      antall --;
      endringer ++;
      return slettes.verdi;
  }

  @Override
  public void nullstill()
  {
    Node<T> p = hode, q = null;

    while (p != null)
    {
      q = p.neste;
      p.neste = null;
      p.verdi = null;
      p = q;
    }

    hode = hale = null;
    endringer++;
    antall = 0;
  }

  @Override
  public String toString()
  {
    StringJoiner s = new StringJoiner(", ", "[", "]");
    Node<T>p = hode; 
    if (p == null) s.add("");
    else{
        while(p!=hale.neste){
            s.add(p.verdi.toString());
            p=p.neste;
            
        }
    }
    return s.toString();
  }

  public String omvendtString()
  {
    StringJoiner s = new StringJoiner(", ", "[", "]");
    Node<T>p = hale; 
    if (p == null) s.add("");
    else{
        while(p!=hale.neste){
            s.add(p.verdi.toString());
            p=p.forrige;
  }   
    }
    return s.toString();
  }

  public static <T> void sorter(Liste<T> liste, Comparator<? super T> c)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public Iterator<T> iterator()
  {
    return new DobbeltLenketListeIterator();
  }

  public Iterator<T> iterator(int indeks)
  {
    indeksKontroll(indeks, false);
    return new DobbeltLenketListeIterator(indeks);
  }

  private class DobbeltLenketListeIterator implements Iterator<T>
  {
    private Node<T> denne;
    private boolean fjernOK;
    private int iteratorendringer;

    private DobbeltLenketListeIterator()
    {
      denne = hode;     // denne starter på den første i listen
      fjernOK = false;  // blir sann når next() kalles
      iteratorendringer = endringer;  // teller endringer
    }

    private DobbeltLenketListeIterator(int indeks)
    {
      denne = finnNode(indeks);
      fjernOK = false;  // blir sann når next() kalles
      iteratorendringer = endringer;  // teller endringer
    }

    @Override
    public boolean hasNext()
    {
      return denne != null;  // denne koden skal ikke endres!
    }

    @Override
    public T next()
    {
        if (iteratorendringer != endringer){
            throw new ConcurrentModificationException();
            }
      if (!hasNext()) throw new NoSuchElementException("Ingen verdier!");
      
    fjernOK = true;            // nå kan remove() kalles
    T temp = null;
    temp =  denne.verdi;
    denne = denne.neste;// flytter p til den neste noden

    return temp;         // returnerer verdien
    }

    @Override
    public void remove()
    {
       if (!fjernOK) 
           throw new IllegalStateException("Ulovlig tilstand!");
       if (iteratorendringer != endringer) 
           throw new ConcurrentModificationException();
       
    fjernOK = false;               // remove() kan ikke kalles på nytt
   

    if (antall == 1){
        hode = hale = null;
    }
    else if (denne == null ){
        hale = hale.forrige;
        hale.neste=null;
    }
    else if (denne.forrige == hode){
        hode = hode.neste;
        hode.forrige=null;
    }
    else{
        denne.forrige = denne.forrige.forrige;
        denne.forrige.neste = denne;
    }
    
    endringer++;
    iteratorendringer++;
    antall--;
    }

  }  

} 

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oblig2;

/**
 *
 * @author Hio
 */
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

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
      if(indeks < antall/2){
          Node<T> kopi = hode.neste;
          for(int i=0;i<indeks;i++){
              kopi = kopi.neste;
          }
          return kopi;
      }else{
          Node<T> kopi = hale.forrige;
            for(int i = antall-1;i>indeks ;i--){
              kopi = kopi.forrige;
          }
          return kopi;
      }
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
      if(a == null){
          throw new NullPointerException("Tabellen a er null");
      }
      else{
          hode = new Node<T>(null, null, hale);
          hale = new Node<T>(null, hode, null);
          for(int i = 0; i < a.length; i++){
              if(a[i] != null){
              Node<T> nyNode = new Node<T> (a[i], hale.forrige, hale);
              hale.forrige.neste = nyNode;
              hale.forrige = nyNode;
              antall++;
              }
          }
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
  // subliste
  public Liste<T> subliste(int fra, int til)
  {
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
      return antall() == 0;
  }

  @Override
  public boolean leggInn(T verdi)
  {
      if(verdi == null){
          return false;
      }else{
           if(hode == null){
             hode = new Node<T>(null, null, hale);
             hale = new Node<T>(null, hode, null);
           }
           Node<T> nyNode = new Node<T> (verdi, hale.forrige, hale);
              hale.forrige.neste = nyNode;
              hale.forrige = nyNode;
              antall++;
          return true;
      }
  }

  @Override
  public void leggInn(int indeks, T verdi)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public boolean inneholder(T verdi)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public T hent(int indeks)
  {   
      
      indeksKontroll(indeks, false);
      return finnNode(indeks).verdi;
      
    //throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public int indeksTil(T verdi)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public T oppdater(int indeks, T nyverdi)
  {
   Node<T> kopi = finnNode(indeks);
   
   finnNode(indeks).verdi = nyverdi;
   
   return kopi.verdi;
   
  }

  @Override
  public boolean fjern(T verdi)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public T fjern(int indeks)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public void nullstill()
  {
    for (Iterator<T> i = iterator(); i.hasNext(); )
    {
      i.next();
      i.remove();
    }
  }

  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    
    sb.append("[");
    
    
      if(antall != 0){
         Node<T> kopi = hode.neste;
         while(!kopi .equals (hale)){
      
         sb.append(kopi.verdi.toString());
         sb.append(",");
         
          kopi = kopi.neste;
        }
         
        sb.deleteCharAt(sb.toString().length() - 1); 
      }
      
      sb.append("]");
      return sb.toString();
      
  }

  public String omvendtString()
  {
    StringBuilder sb = new StringBuilder();
    
    sb.append("[");
    
    
      if(antall != 0){
          Node<T> kopi = hale.forrige;
         while(!kopi .equals (hode)){
      
         sb.append(kopi.verdi.toString());
         sb.append(",");
         
          kopi = kopi.forrige;
        }
         
        sb.deleteCharAt(sb.toString().length() - 1);

      }
      
      sb.append("]");
      return sb.toString();
      
  }

  public static <T> void sorter(Liste<T> liste, Comparator<? super T> c)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public Iterator<T> iterator()
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  public Iterator<T> iterator(int indeks)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
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
      throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    @Override
    public boolean hasNext()
    {
      return denne != null;  // denne koden skal ikke endres!
    }

    @Override
    public T next()
    {
      throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    @Override
    public void remove()
    {
      throw new UnsupportedOperationException("Ikke laget ennå!");
    }

  } // DobbeltLenketListeIterator  

  
} // DobbeltLenketListe  


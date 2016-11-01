/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Skole;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 *
 * @author Gud
 */
public class Oblig1 {
    
        private static int parter(int[] a, int v, int h, int skilleverdi)
    {
        while(true)
        {
            while(v <= h && a[v] < skilleverdi) v++;
            
            while(v <= h && a[h] >= skilleverdi) h--;
            
            if(v<h) bytt(a,v++,h--); else return v;
            
        }
    }
    
        private static int sParter(int[] a, int v, int h, int indeks)
    {
        bytt(a, indeks, h);
        v = parter(a,v,h - 1, a[h]);
        bytt(a,v,h);
        return v;
    }
    
      public static int[] randPerm(int n)
  {
    int[] a = new int[n]; // fyller tabellen med 1, 2, . . , n
    for (int i = 0; i < n; i++) a[i] = i+1;

    Random r = new Random();  // hentes fra java.util

    for (int k = n-1; k > 0; k--)
    {
      int i = r.nextInt(k+1);  // tilfeldig tall fra [0,k]
      bytt(a,k,i);
    }

    return a; // tabellen med permutasjonen returneres

  } // randPerm

  public static void randPerm(int[] a) // stokker om a
  {
    Random r = new Random();  // hentes fra java.util

    for (int k = a.length-1; k > 0; k--)
    {
      int i = r.nextInt(k+1);  // tilfeldig tall fra [0,k]
      bytt(a,k,i);
    }
  }
  
        public static boolean erSortert(int[] a)  // legges i samleklassen Tabell
  {
    for (int i = 1; i < a.length; i++)      // starter med i = 1
      if (a[i-1] > a[i]) return false;      // en inversjon

    return true;
  }
    
    public static void bytt(int[] a, int i, int j){
   
    int temp = a[i]; 
        a[i] = a[j]; 
               a[j] = temp; 
      
    }
    
        public static void fratilKontroll(int tablengde, int fra, int til)
  {
    if (fra < 0)                             // fra er negativ
      throw new ArrayIndexOutOfBoundsException
        ("fra(" + fra + ") er negativ!");

    if (til > tablengde)                     // til er utenfor tabellen
      throw new ArrayIndexOutOfBoundsException
        ("til(" + til + ") > tablengde(" + tablengde + ")");

    if (fra > til)                           // fra er større enn til
      throw new IllegalArgumentException
        ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
  }
    
      public static int maks1(int[] a, int fra, int til)
  {
    if (a == null) throw new NullPointerException
    ("parametertabellen a er null!");
      
    fratilKontroll(a.length,fra,til);
    
    if (fra == til)
    throw new NoSuchElementException
    ("fra(" + fra + ") = til(" + til + ") - tomt tabellintervall!");

    int m = fra;             // indeks til største verdi i a[fra:til>
    int maksverdi = a[fra];  // største verdi i a[fra:til>

    for (int i = fra + 1; i < til; i++) 
        if (a[i] > maksverdi)
    {
      m = i;               // indeks til største verdi oppdateres
      maksverdi = a[m];    // største verdi oppdateres
    }

    return m;  // posisjonen til største verdi i a[fra:til>
  }

  public static int maks1(int[] a)  // bruker hele tabellen
  {
    return maks1(a,0,a.length);  // kaller metoden over
  }
  
  
      private static void kvikksortering1(int[] a, int v, int h)  // en privat metode
  {
    if (v >= h) return;  // a[v:h] er tomt eller har maks ett element
    int k = sParter(a, v, h, (v + h)/2);  // bruker midtverdien
    kvikksortering1(a, v, k - 1);     // sorterer intervallet a[v:k-1]
    kvikksortering1(a, k + 1, h);     // sorterer intervallet a[k+1:h]
  }
    
    
          public static void sortering(int[] a)
  {
    for (int i = a.length; i > 1; i--)
    {
      int m = maks1(a,0,i);
      bytt(a,i-1,m);
    }
    
  }
    //oppgave 1
    public static int maks(int[] a)
    {
        if (a.length == 0) throw new NoSuchElementException
         ("Tabellen a er tom!");
        
    for (int i = 1; i < a.length; i++){ 
        
        if (a[i-1] > a[i])
            {
            bytt(a, i-1, i);
            }
        }
        
    return a[a.length - 1];
        
    }
    
    public static int ombyttinger(int[] a){
        int antallBytt = 0; //antall ombyttinger
        
          for (int i = 1; i < a.length; i++){ 
        
        if (a[i-1] > a[i])
            {
            bytt(a, i-1, i); 
            antallBytt++;
            }
        }
        
        return antallBytt;
    }
    //oppgave2
    public static int antallUlikeSortert(int[] a){
        
        int antallulike = 0;
        
                    
        if (a.length == 0)
             {
                 antallulike = 0;
             }
             
        else if (erSortert(a) == true)
        {
            
//            if (a.length == 0) throw new IllegalStateException
//             ("Tabellen er tom, altså 0 forskjellige verdier.");
            
          
            
//            if (a.length == 1 && a[0] <= 1) throw new IllegalStateException
//             ("Tabellen er tom, altså 0 forskjellige verdier.");

            if(a[0] != a[a.length-1] || a[0] >= 1){
                         antallulike++;
                        }
            
            for(int i = 1; i < a.length; i++){

                if(a[i-1] != a[i]){
                    antallulike++;
                }
            }
        }
        else if(erSortert(a) == false)
        {
            throw new IllegalStateException ("Tabellen er ikke sortert");
        }
         
        return antallulike;
    }
    
    public static boolean finnesbak(int[] a, int tallsjekkes, int indeks){
        
        for(int i=0; i < indeks; i++){
            
            if(tallsjekkes == a[i]){
                return true;
            }
        }
        return false;
    
    }
            //oppgave3
    public static int antallUlikeUsortert(int[] a){

        int ulike = 0;
        
        for(int i=0; i < a.length; i++){
            
            if(!finnesbak(a, a[i], i)){              
                ulike++;
            }
            
        }
        
        return ulike;
    }
    
    
    //oppgave 4
    public static void delsortering(int[] a){
        
            int h = a.length-1;
            int v = 0;
            
        while(h-v > 2){
            
            System.out.println(Arrays.toString(a));
            System.out.println(v+ " " + (h-v) +" " +h);
            if( a[v]%2 == 1 && a[h]%2 == 0 || a[v]%2 == -1 && a[h]%2 == 0){
                v++;
                h--;
                 }
            if(a[v] % 2 == 0 && a[h] % 2 == 0){
                h--;
                 }
            if(a[v] % 2 == 1 && a[h] % 2 == 1||a[v] % 2 == -1 && a[h] % 2 == -1||a[v] % 2 == 1 && a[h] % 2 == -1||a[v] % 2 == -1 && a[h] % 2 == 1){
                v++;
                }
            if(a[v] % 2 == 0 && a[h] % 2 == 1||a[v] % 2 == 0 && a[h] % 2 == -1){
                bytt(a,v,h);
                v++;
                h--;
                }
            }
                 int venstre = v;
                 
//        Arrays.sort();
//        sortering1(a, 0, venstre);
            
       
        //sorterskille(a, h/v, a.length-1);
        //hvis h == v, sorter fra 0 til h eller v ,  og h/v + 1 til a.length-1
         System.out.println(Arrays.toString(a));
            System.out.println(v + "    " + h);
            System.out.println(" " + (h-v) +"");
            
            
            
            
    }
        
//
            public static void bytt1(char[] a, char i, char j){
   
    char temp = a[i]; 
        a[i] = a[j]; 
               a[j] = temp; 
      
    }

            //oppgave 5
   public static void rotasjon(char[] a)           
  {
    int d = 1;
    int n = a.length; 
    
    if (n < 2) 
        return;                 
    
    if ((d %= n) < 0) d += n;                             

    char[] b = Arrays.copyOfRange(a, n - d, n);           
    for (int i = n - 1; i >= d; i--) a[i] = a[i - d];     
    System.arraycopy(b, 0, a, 0, d);                      
  }
    
}//oppgave 6
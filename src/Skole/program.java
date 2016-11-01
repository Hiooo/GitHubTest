/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Skole;

import java.util.Arrays;

/**
 *
 * @author Gud
 */
public class program {

    public static void main(String[] args) {

        String[] s1 = {"A", "B", "C", "D"};
        DobbeltLenketListe<String> l1 = new DobbeltLenketListe<>(s1);

        System.out.println(l1.toString());
        
        l1.fjern(3);
        
        //System.out.println(l1.fjern(0));
        System.out.println(l1.toString());
    }

}

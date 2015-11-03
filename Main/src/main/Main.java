/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

/**
 * Testo rotina da classe CheckStrength, alimentando-a com uma string específica.
 * Onde meu programa roda/inicia.
 * 
 * @author alexsander
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String password = "2hAj5#mne-ix.86H21";
	System.out.println(CheckStrength.getPasswordLevel(password));
    }
    
}

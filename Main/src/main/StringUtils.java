/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 * Classe que trata casos específico em uma String
 *
 * @author alexsander
 */
public class StringUtils {

    private final static int[] SIZE_TABLE = {9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE};

    /**
     * Calcula o tamanho de um número inteiro
     */
    protected static int sizeOfInt(int integer) {
        for (int i = 0;; i++) {
            if (integer <= SIZE_TABLE[i]) {
                return i + 1;
            }
        }
    }

    /**
     * Verifica se todos os caracteres de uma string são iguais
     */
    protected static boolean isCharEqual(String character) {
        return character.replace(character.charAt(0), ' ').trim().length() == 0;
    }

    /**
     * Verifica se uma string é formada apenas por dígitos numéricos
     */
    protected static boolean isNumeric(String character) {
        for (int i = character.length(); --i >= 0;) {
            if (!Character.isDigit(character.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Verifica se uma string contém apenas espaços em branco, é vazia ou nula
     */
    protected static boolean equalsNull(String character) {
        int characterLength;

        if (character == null || (characterLength = character.length()) == 0 || character.equalsIgnoreCase("null")) {
            return true;
        }

        for (int i = 0; i < characterLength; i++) {
            if (!Character.isWhitespace(character.charAt(i))) {
                return false;
            }
        }

        return true;
    }

}

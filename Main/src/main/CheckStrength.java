/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 * Classe que irá analisar uma String(senha) para definir sua força de
 * nivelamento.
 *
 * @author alexsander
 */
public class CheckStrength {

    /**
     * Níveis de força da senha
     */
    public enum LEVEL {

        WEAK, MEDIUM, STRONG, VERY_STRONG, EXTREMELY_STRONG
    }

    private static final int NUMBER = 1;
    private static final int SMALL_LETTER = 2;
    private static final int CAPITAL_LETTER = 3;
    private static final int OTHER_CHAR = 4;

    /**
     * Dicionário de senhas simples
     */
    private final static String[] dictionarySimplePasswords
            = {
                "password",
                "abc123",
                "iloveyou",
                "adobe123",
                "123123",
                "sunshine",
                "1314520",
                "a1b2c3",
                "123qwe",
                "aaa111",
                "qweasd",
                "admin",
                "passwd"
            };

    /**
     * Verifica o tipo de um caractere (maiúsculo, minúsculo, numérico, caracter
     * especial)
     */
    private static int checkCharacterType(char character) {
        if ((character >= 48) && (character <= 57)) {
            return NUMBER;
        }
        if ((character >= 65) && (character <= 90)) {
            return CAPITAL_LETTER;
        }
        if ((character >= 97) && (character <= 122)) {
            return SMALL_LETTER;
        }
        return OTHER_CHAR;
    }

    /**
     * Conta o número de caracteres de um determinado tipo em uma senha
     */
    private static int counterLetter(String password, int type) {
        int counter = 0;

        if ((null != password) && (password.length() > 0)) {
            for (char character : password.toCharArray()) {
                if (checkCharacterType(character) == type) {
                    counter++;
                }
            }
        }
        return counter;
    }
    /**
     * Quebro uma String em partes
     */
    private static int breakString(String password, int part, int level, int length) {
        // Quebro minha String em 2 partes iguais
        if (length % 2 == 0 && part == 2) {
            String firstPartString = password.substring(0, length / 2);
            String secondPartString = password.substring(length / 2);
            if (firstPartString.equals(secondPartString)) {
                level--;
            }
            if ((StringUtils.isCharEqual(firstPartString)) && (StringUtils.isCharEqual(secondPartString))) {
                level--;
            }
        }
        // Quebro minha String em três partes iguais
        if (length % 3 == 0 && part == 3) {
            String firstPartString = password.substring(0, length / 3);
            String secondPartString = password.substring(length / 3, length / 3 * 2);
            String thirdPartString = password.substring(length / 3 * 2);
            if ((firstPartString.equals(secondPartString)) && (secondPartString.equals(thirdPartString))) {
                level--;
            }
        }
        return level;
    }
    /**
     * Verifico a data e meu dicionário
     */
    private static int checkDateAndDictionary(String password, int length, int level){
        // Verifica data dia/ano/mes ou mes/ano
        if ((StringUtils.isNumeric(password)) && (length >= 6)) {
            int year = 0;
            if ((length == 8) || (length == 6)) {
                year = Integer.parseInt(password.substring(0, length - 4));
            }
            int size = StringUtils.sizeOfInt(year);
            int month = Integer.parseInt(password.substring(size, size + 2));
            int day = Integer.parseInt(password.substring(size + 2, length));
            if ((year >= 1950) && (year < 2050) && (month >= 1) && (month <= 12) && (day >= 1) && (day <= 31)) {
                level--;
            }
        }
        // Trato casos que envolvam o meu dicionário de palavras simples
        if ((null != dictionarySimplePasswords) && (dictionarySimplePasswords.length > 0)) {
            for (String DICTIONARY : dictionarySimplePasswords) {
                if ((password.equals(DICTIONARY)) || (DICTIONARY.contains(password))) {
                    level--;
                    break;
                }
            }
        }
        if (length <= 6) {
            level--;
            if (length <= 4) {
                level--;
                if (length <= 3) {
                    level = 0;
                }
            }
        }
        return level;
    }

    /**
     * Verifica a força de uma senha pontuando segundo regras impostas pela
     * rotina
     *
     * @param password senha que será nivelada
     */
    private static int checkPasswordStrength(String password) {
        //trato excessão caso a String esteja vazia, seja nula ou tenha espaços em branco
        if (StringUtils.equalsNull(password)) {
            System.out.print("password is empty");
        }

        int length = password.length();
        int level = 0;

        // adiciona pontos
        if (counterLetter(password, NUMBER) > 0) {
            level++;
        }
        if (counterLetter(password, SMALL_LETTER) > 0) {
            level++;
        }
        if ((length > 4) && (counterLetter(password, CAPITAL_LETTER) > 0)) {
            level++;
        }
        if ((length > 6) && (counterLetter(password, OTHER_CHAR) > 0)) {
            level++;
        }
        if ((length > 4) && (counterLetter(password, NUMBER) > 0) && (counterLetter(password, SMALL_LETTER) > 0) || (counterLetter(password, NUMBER) > 0) && (counterLetter(password, CAPITAL_LETTER) > 0)
                || (counterLetter(password, NUMBER) > 0) && (counterLetter(password, OTHER_CHAR) > 0) || (counterLetter(password, SMALL_LETTER) > 0) && (counterLetter(password, CAPITAL_LETTER) > 0)
                || (counterLetter(password, SMALL_LETTER) > 0) && (counterLetter(password, OTHER_CHAR) > 0) || (counterLetter(password, CAPITAL_LETTER) > 0) && (counterLetter(password, OTHER_CHAR) > 0)) {
            level++;
        }
        if ((length > 6) && (counterLetter(password, NUMBER) > 0) && (counterLetter(password, SMALL_LETTER) > 0) && (counterLetter(password, CAPITAL_LETTER) > 0)
                || (counterLetter(password, NUMBER) > 0) && (counterLetter(password, SMALL_LETTER) > 0) && (counterLetter(password, OTHER_CHAR) > 0)
                || (counterLetter(password, NUMBER) > 0) && (counterLetter(password, CAPITAL_LETTER) > 0) && (counterLetter(password, OTHER_CHAR) > 0)
                || (counterLetter(password, SMALL_LETTER) > 0) && (counterLetter(password, CAPITAL_LETTER) > 0) && (counterLetter(password, OTHER_CHAR) > 0)) {
            level++;
        }
        if ((length > 8) && (counterLetter(password, NUMBER) > 0) && (counterLetter(password, SMALL_LETTER) > 0) && (counterLetter(password, CAPITAL_LETTER) > 0) && (counterLetter(password, OTHER_CHAR) > 0)) {
            level++;
        }
        if ((length > 6) && (counterLetter(password, NUMBER) >= 3) && (counterLetter(password, SMALL_LETTER) >= 3) || (counterLetter(password, NUMBER) >= 3) && (counterLetter(password, CAPITAL_LETTER) >= 3)
                || (counterLetter(password, NUMBER) >= 3) && (counterLetter(password, OTHER_CHAR) >= 2) || (counterLetter(password, SMALL_LETTER) >= 3) && (counterLetter(password, CAPITAL_LETTER) >= 3)
                || (counterLetter(password, SMALL_LETTER) >= 3) && (counterLetter(password, OTHER_CHAR) >= 2) || (counterLetter(password, CAPITAL_LETTER) >= 3) && (counterLetter(password, OTHER_CHAR) >= 2)) {
            level++;
        }
        if ((length > 8) && (counterLetter(password, NUMBER) >= 2) && (counterLetter(password, SMALL_LETTER) >= 2) && (counterLetter(password, CAPITAL_LETTER) >= 2)
                || (counterLetter(password, NUMBER) >= 2) && (counterLetter(password, SMALL_LETTER) >= 2) && (counterLetter(password, OTHER_CHAR) >= 2)
                || (counterLetter(password, NUMBER) >= 2) && (counterLetter(password, CAPITAL_LETTER) >= 2) && (counterLetter(password, OTHER_CHAR) >= 2)
                || (counterLetter(password, SMALL_LETTER) >= 2) && (counterLetter(password, CAPITAL_LETTER) >= 2) && (counterLetter(password, OTHER_CHAR) >= 2)) {
            level++;
        }
        if ((length > 10) && (counterLetter(password, NUMBER) >= 2) && (counterLetter(password, SMALL_LETTER) >= 2) && (counterLetter(password, CAPITAL_LETTER) >= 2) && (counterLetter(password, OTHER_CHAR) >= 2)) {
            level++;
        }
        if (counterLetter(password, OTHER_CHAR) >= 3) {
            level++;
        }
        if (counterLetter(password, OTHER_CHAR) >= 6) {
            level++;
        }
        if (length > 12) {
            level++;

            if (length >= 16) {
                level++;
            }
        }
        // desconta pontos
        if (("abcdefghijklmnopqrstuvwxyz".indexOf(password) > 0) || ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(password) > 0)) {
            level--;
        }
        if (("qwertyuiop".indexOf(password) > 0) || ("asdfghjkl".indexOf(password) > 0) || ("zxcvbnm".indexOf(password) > 0)) {
            level--;
        }
        if ((StringUtils.isNumeric(password)) && (("01234567890".indexOf(password) > 0) || ("09876543210".indexOf(password) > 0))) {
            level--;
        }
        if ((counterLetter(password, NUMBER) == length) || (counterLetter(password, SMALL_LETTER) == length) || (counterLetter(password, CAPITAL_LETTER) == length)) {
            level--;
        }
        //aplico regras em métodos p/ descontar pontos
        breakString(password, 2, level, length);
        breakString(password, 3, level, length);
        checkDateAndDictionary(password, length, level);
        
        //Verifico se todas os caracteres da string são iguais.
        if (StringUtils.isCharEqual(password)) {
            level = 0;
        }
        if (level < 0) {
            level = 0;
        }
        return level;
    }

    /**
     * Retorna o nível de segurança de uma senha
     * 
     * @param password              minha senha
     */
    public static LEVEL getPasswordLevel(String password) {
        int level = checkPasswordStrength(password);

        switch (level) {
            case 0:
            case 1:
            case 2:
            case 3:
                return LEVEL.WEAK;

            case 4:
            case 5:
            case 6:
                return LEVEL.MEDIUM;

            case 7:
            case 8:
            case 9:
                return LEVEL.STRONG;

            case 10:
            case 11:
            case 12:
                return LEVEL.VERY_STRONG;

            default:
                return LEVEL.EXTREMELY_STRONG;
        }
    }

}

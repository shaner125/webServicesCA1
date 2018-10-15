/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jerseytutorial;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/RomanConverter")
public class RomanConverterApp {

  @GET
  @Path("/{param}")
  public Response ConvertResponse(@PathParam("param") String message) {
    String output = message+" converted to roman numerals = ";
    if (isNumeric(message)) {
        output += (StringToRomanNumerals(Integer.parseInt(message)) + "!");
    } else {
        output += (RomanNumeralsToString(message) + "!");
    }
    return Response.status(200).entity(output).build();
  }

  public static boolean isNumeric(String strNum) {
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
  }

  public static int getIntegerFromRomanNumeral(String numeral) {
      numeral = numeral.toLowerCase();
      int intVal = 0;
      switch (numeral) {
          case "i":  intVal = 1;
              break;
          case "iv":  intVal = 4;
              break;
          case "v":  intVal = 5;
              break;
          case "ix":  intVal = 9;
              break;
          case "x":  intVal = 10;
              break;
          case "xl":  intVal = 40;
              break;
          case "l":  intVal = 50;
              break;
          case "xc":  intVal = 90;
              break;
          case "c":  intVal = 100;
              break;
          case "cd":  intVal = 400;
              break;
          case "d":  intVal = 500;
              break;
          case "cm":  intVal = 900;
              break;
          case "m":  intVal = 1000;
              break;
          default: intVal = 0;
              break;
      }
      return intVal;
  }

  public static String StringToRomanNumerals(int userValue) {
    String res = "";
    LinkedHashMap<String, Integer> roman_numerals = new LinkedHashMap<String, Integer>();
      roman_numerals.put("M", 1000);
      roman_numerals.put("CM", 900);
      roman_numerals.put("D", 500);
      roman_numerals.put("CD", 400);
      roman_numerals.put("C", 100);
      roman_numerals.put("XC", 90);
      roman_numerals.put("L", 50);
      roman_numerals.put("XL", 40);
      roman_numerals.put("X", 10);
      roman_numerals.put("IX", 9);
      roman_numerals.put("V", 5);
      roman_numerals.put("IV", 4);
      roman_numerals.put("I", 1);

    // Work our way from the biggest roman numeral down and find how many of them are in the int
    for(Map.Entry<String, Integer> entry : roman_numerals.entrySet()){
        
      int matches = userValue/entry.getValue();
      res += repeat(entry.getKey(), matches);
      userValue = userValue % entry.getValue();
      
    }
    return res;
  }

    public static String RomanNumeralsToString(String userValue) {
        int res = 0;

        for (int i = 0; i < userValue.length(); i += 1) {
            String currentNumeral = String.valueOf(userValue.charAt(i));
            int currentInt = getIntegerFromRomanNumeral(currentNumeral);
            if (i < userValue.length() - 1) {
                String nextNumeral = String.valueOf(userValue.charAt(i+1));
                int nextInt = getIntegerFromRomanNumeral(nextNumeral);
                if (currentInt < nextInt) {
                    res += (nextInt - currentInt);
                    i += 1;
                } else {
                    res += currentInt;
                }
            } else {
                res += currentInt;
            }
        }
        return String.valueOf(res);
    }


  public static String repeat(String s, int n) {
    if(s == null) {
        return null;
    }
    final StringBuilder sb = new StringBuilder();
    for(int i = 0; i < n; i++) {
        sb.append(s);
    }
    return sb.toString();
  }

}
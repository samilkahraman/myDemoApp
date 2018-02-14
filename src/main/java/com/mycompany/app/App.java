package com.mycompany.app;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

public class App
{
    public static boolean search(ArrayList<Integer> array, int e) {
        System.out.println("inside search");
        if (array == null) return false;

        for (int elt : array) {
            if (elt == e) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Hello, World");

        post("/compute", (req, res) -> {
            //System.out.println(req.queryParams("input1"));
            //System.out.println(req.queryParams("input2"));
            String input1 = req.queryParams("input1");
            java.util.Scanner sc1 = new java.util.Scanner(input1);
            sc1.useDelimiter("[;\r\n]+");

            java.util.ArrayList<Integer> inputList = new java.util.ArrayList<>();
            while (sc1.hasNext())
            {
                int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
                inputList.add(value);
            }
            System.out.println(inputList);


            String input2 = req.queryParams("input2");
            java.util.Scanner sc2 = new java.util.Scanner(input2);
            sc2.useDelimiter("[;\r\n]+");

            java.util.ArrayList<Integer> inputList2 = new java.util.ArrayList<>();
            while (sc2.hasNext())
            {
                int value = Integer.parseInt(sc2.next().replaceAll("\\s",""));
                inputList2.add(value);
            }
            System.out.println(inputList2);


            String input3 = req.queryParams("input3").replaceAll("\\s","");
            System.out.println(input3);







            String input4 = req.queryParams("input4").replaceAll("\\s","");


            String sifreliMetin = sifrele(inputList,inputList2,input3,input4);

           System.out.println(sifreliMetin);

            Map map = new HashMap();
            map.put("result", sifreliMetin);
            return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());


        get("/compute",
                (rq, rs) -> {
                    Map map = new HashMap();
                    map.put("result", "not computed yet!");
                    return new ModelAndView(map, "compute.mustache");
                },
                new MustacheTemplateEngine());
    }

    public static String sifrele(ArrayList<Integer> bir, ArrayList<Integer> iki, String mesaj, String sonuc){
        sonuc="";
        //birinci listeyi al
        //ikinci listeyi al
        // mesajın karakterlerine sırasıyla ilk birincinin birinci değerini topla sonra ikincinin birinci değerini topla
        //bu şekilde devam ederek çoklu kaydırma ile şifrele sonuca yaz
        // ornek vermek gerekirse birinci listede 1,2,3 var ikinci listede 4,5,6 var mesajımızda gerede ise sifreli mesaj
        // g + 1, e + 4, r + 2, e + 5, d + 3, e + 6

        int birinciArrayUzunlugu=bir.size();
        int ikinciArrayUzunlugu=iki.size();
        int counter1=0;
        int counter2=0;


        char c;
        for(int i=0;i<mesaj.length();i++){
            c=mesaj.charAt(i);
            if(c%2==0){
                if(counter1==birinciArrayUzunlugu){
                    counter1=0;
                }
                int deneme=bir.get(counter1);
                c += deneme;

                sonuc+=c ;
                counter1++;

            }else{
                if(counter2==ikinciArrayUzunlugu){
                    counter2=0;
                }
                int deneme=bir.get(counter2);
                c += deneme;

                sonuc+=c ;
                counter2++;
            }
        }

        return sonuc;
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
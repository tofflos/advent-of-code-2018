package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Traditional {

    public static void main(String[] args) {

        var examples1 = List.of("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab");
        var examples2 = List.of("abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz");

        var boxes = List.of("ayitmcjvlhedbsyoqfzukjpxwt", "agirmcjvlheybsyogfzuknpxxt", "wgirmcjvlvedbsyoqfzujnpxwt", "agizmcjvlhedbsyoqfzuenlxwt", "aryrmcjvlheebsyoqfzuknpxwt", "agirmcjelhedbsyoqfzukosxwt", "azirmcjvlhedbsooqfzuknpxvt", "agirmcjvffedbsyoqfzudnpxwt", "agilmcjvlhedbsyrqfzuknpxrt", "agirmcjvlhndbsyoofzukcpxwt", "awirmcjvlhedbsyoqfzuknpxlz", "aghrmcjmlhewbsyoqfzuknpxwt", "apirmcjvlmedbsyoqfzcknpxwt", "jgiricjvlhedbsyrqfzuknpxwt", "abirmcjvlbedbsyoqfzuknpxwo", "agirmcjvlhedbsyojfzuknpgkt", "agicmclvlhedbmyoqfzuknpxwt", "aslrzcjvlhedbsyoqfzuknpxwt", "agiqmcjvlhedbsymqfzurnpxwt", "agirmcjvlwedbsyoqfzuknfxmt", "agiumcjvlhedbsyoqfzuknpbyt", "xgirxcjvlwedbsyoqfzuknpxwt", "bgtrvcjvlhedbsyoqfzuknpxwt", "afirmcjvlpedbsyoqvzuknpxwt", "agirmcjjvhedbsyoqfzukmpxwt", "ggirmcjvlhedbsyoqfzukypxvt", "agirmdjulhekbsyoqfzuknpxwt", "agirmcjqlhedbsyoqfztknixwt", "agirmcjvjhedbsyomfduknpxwt", "agirmcjvlhedbgyoqfzuknpxtq", "agirmvjvlhbdbsyfqfzuknpxwt", "agirmcjvlhedbsyatfzbknpxwt", "agirmcjvlrlybsyoqfzuknpxwt", "agirmajvlhedbsqovfzuknpxwt", "abinmcrvlhedbsyoqfzuknpxwt", "agnrmcjvlhedbsyurfzuknpxwt", "agirmpjvlhedbsyoqezuknpxct", "agirmxjvlhedbsgoqjzuknpxwt", "agirmcjvlhehbstoqfzuknpxht", "qgirmcjvlhepcsyoqfzuknpxwt", "tgirmcjvlhkdbsyoqszuknpxwt", "agirmcjvdhedbscoqftuknpxwt", "agbrmcjvlhedbsyoqfzukqpxwj", "agurmcjvlhedbsyaqfzuknpxmt", "agirmcjvohudbsyoqfmuknpxwt", "agirmcjvlhekbsyoqfbuktpxwt", "agirmcjvlhedhsyoqfzugnnxwt", "agirmcjvlhedbsyjqyzuknpxft", "agirmcjvlhedbsymufznknpxwt", "agirmcjhlheubsyoqfzuknpxmt", "agirmcjvlhwdbsywqfzwknpxwt", "agirmcjvljedbsgqqfzuknpxwt", "aglrmcjelhedbsyoqfzuknpxkt", "agxrmcjvlhxdbsyoqfquknpxwt", "agirmcjvnhedbsyoqfzuenfxwt", "agirmcjvlhedbsyoqfzatnqxwt", "agirmcvvlhedbsboqfzuknuxwt", "agirncjvlhezbsyoqfzulnpxwt", "agiamcjvdiedbsyoqfzuknpxwt", "agirmcjvwhedbskoqfzhknpxwt", "agiwmcjflhedbsyoqfzulnpxwt", "agirmcjvlhedboyoqfzuknpjwl", "agivmcjslhedbsyoqfzdknpxwt", "agirmcjvlcedbsyoqfzukepxyt", "akirmcjvlhjdbssoqfzuknpxwt", "agvrmcjvldedmsyoqfzuknpxwt", "agirecjvlhidbsyoqfzukbpxwt", "abirmcjvlhjdbsyoqfkuknpxwt", "agirmcjelhedbfyoqfzuknpxwj", "agirmcjvlhedbbyoqrzukwpxwt", "akirmcjvlhedbsyoyfzuknplwt", "agirmcjvlhedbsydsfzuknpxwq", "agirrcjvlhedbsyoqazuknpmwt", "aeirmcjvlhedbsyoqfvuknpwwt", "akirmcjvlhedbsyoqpzudnpxwt", "agijmcjvlhedbsyuqfzunnpxwt", "agirmcjilhedasyoqizuknpxwt", "agirmczvlhzdbsyoqfzuknpxwx", "agirmcjvlhehbsyoifzuknpxwo", "agirwcjvlhedbsyoqfzuenpxst", "agirmcjvlhedbsyoquzuknhxft", "agirmcqvlkedbsyoqfzrknpxwt", "agirmcqvlhenbsyoqfzuknpuwt", "agirmcjvleedbsyoqfzhhnpxwt", "agirmcjvlhembsyrqfauknpxwt", "agirmcjvlhedbssoqflcknpxwt", "aqirmcjvlnedbsyoqfzuknpxpt", "agirmcjqlhedbxpoqfzuknpxwt", "fgirmcjvlhedbsyoqfzukqpqwt", "aggrmcjvlhpdbsyoqfzuknpxjt", "agirmwjvlhedbsywqfzuknpzwt", "agirmcailhembsyoqfzuknpxwt", "aglrmcjvlhxdbsyoqfzuknpxet", "xgirmcjvlhzdbsyoqfzukrpxwt", "agvrmcjvuhedbsyoqfzuknpxgt", "agikmcjvlhecbsyoqfzuknpxwr", "agyrmcjvlhezbsyoqfouknpxwt", "agirmcjvfhjdbsyokfzuknpxwt", "agkrmjjvlhedtsyoqfzuknpxwt", "agirmgjvlhedbiyoqfzuknpxwv", "wcirmcjvlhedbsyoqfzuknpxwo", "aairmcjvlhedbstoqfguknpxwt", "hgirmcjvlhedwfyoqfzuknpxwt", "agirmcjvmhfdbmyoqfzuknpxwt", "agirmcjvlhvdbsioqfzuanpxwt", "agrrmcjvgsedbsyoqfzuknpxwt", "agirmcjvlqetbsysqfzuknpxwt", "agirccjvlhedbsyoqfzuknkcwt", "agirmqjvlhedbsdoqfzkknpxwt", "agirmcjvlheobsyopfzuknpxwg", "agirmcjolhedbsyofpzuknpxwt", "agirmcjnlhedbsyoqkzukfpxwt", "agiumcjvlheabsyoqfzuknpxbt", "agipmcjvlhedbsyoqfzukupxwz", "atirmcrvlhedbsyoqfnuknpxwt", "agirmcjvnhedfkyoqfzuknpxwt", "agirmrjvlhedboyoqfzvknpxwt", "abhrmcjvlhedbtyoqfzuknpxwt", "cbirmcjvlhedbfyoqfzuknpxwt", "agirmcjvlhedbsyoqfmwknjxwt", "ahirmcjvlhedbsloqfzuknpfwt", "agarmjjvlhedbsyoqfzyknpxwt", "ajirmcjvlhevjsyoqfzuknpxwt", "agirmcjvlhpdbstoqfzuknpewt", "agirmcsvlhedbsyoqfbupnpxwt", "agirmcjvlhexbsyodfzukqpxwt", "auiymcjblhedbsyoqfzuknpxwt", "azirmcjvchedbsyoqfziknpxwt", "aeirmcjvlhedvsyoqfzuonpxwt", "agirmcjvlhedbfyoqfbukjpxwt", "ygirmcjvlhidbsyoqfzukncxwt", "agirmxpvlhedbsyoqffuknpxwt", "ztirmcjvlhedosyoqfzuknpxwt", "agirmcjvlhepbsyoqfzuenppwt", "agirmcjvshedbsyoqnzaknpxwt", "awirmcjvlhydbsyoqfzuknoxwt", "ucirmcjvlhedbsyoqfjuknpxwt", "agirmwjvlhkbbsyoqfzuknpxwt", "agirmcjvldedbsyohfzuknpxzt", "agirmcjvwhedbsyoqfznknpxgt", "agiricjvlhedxqyoqfzuknpxwt", "agirmcjvlhzdbjyoqfzukapxwt", "agirmcgvlhedbsyoqfzuknaowt", "agidmcjvlhedbsyoqayuknpxwt", "agirmcjvlhedisnoqfzuknpxnt", "wkjrmcjvlhedbsyoqfzuknpxwt", "agirmcjvlhedbuyojfzukxpxwt", "agkrmcjvlhedbsybqfzurnpxwt", "agirmcjvghedbsyoqfzuknexwj", "agirmcjvnhedbsyoqfzuznpxit", "agirmcjvlbedbsyoqfiukwpxwt", "agirlctvlheabsyoqfzuknpxwt", "agirmcjzzhedbsyoqfzcknpxwt", "akirmcjvlnedbsyoqfzlknpxwt", "agirmdjvlhedpsyoqfzuknpjwt", "agiyjcuvlhedbsyoqfzuknpxwt", "agirmcbvltedysyoqfzuknpxwt", "agirmcjvlhedfdyoqfzubnpxwt", "agidmcjvlhedesfoqfzuknpxwt", "aeirmcjvlhedqsyoqfxuknpxwt", "agifmcjvlhedbsyoqfquknptwt", "agidmcjvlhedbsyfqfzuknpxwb", "agirvcjvlhedbsroqfzuknjxwt", "agirmcqvlhddbsyoqfzuknpxwj", "agirmcjvlhmdqsyoqizuknpxwt", "atirmcjvltedbsyoqfzuknpxwz", "agirxnjvlhedbsyoqfzuknpxkt", "agihmcjvlhedbsyoqfzukepxqt", "agirmcjvlhedbsmoqzsuknpxwt", "agirycjvlhedbuyoqfwuknpxwt", "agirmcjvlhedbsyoqfzfkrfxwt", "agirzcjvlhedbsyoqfhuknpxnt", "agigmcjvlhedbsqnqfzuknpxwt", "agirmgzvlhedbsyoqfzuonpxwt", "agirmcjvqhedbqyoqfzukqpxwt", "anarmcjvlhedbsyocfzuknpxwt", "agirmcjuihedbshoqfzuknpxwt", "agirdckvlhedbsyoqfzxknpxwt", "ugirmujvlhwdbsyoqfzuknpxwt", "mgirmcjvlheobsyovfzuknpxwt", "agirmcjvghedbsyoqfzufxpxwt", "agirmcjvlhedbsyoinzuknuxwt", "agirmzjvlhbdbsyoqfzlknpxwt", "agivmcjvlhedbsconfzuknpxwt", "agirmwfvlhedtsyoqfzuknpxwt", "agirmcjvlhedbbyoqrzukncxwt", "agirmcjvlhelbsyoqfzupnlxwt", "agirmmjvluedqsyoqfzuknpxwt", "agjrmcjvlhedbsyaqfcuknpxwt", "agiwmcjvlhedbsyoqzzuknpswt", "agirxcjvlhedbsyoqfyvknpxwt", "agirmljvlhedbsyoqkzuknpxjt", "agirmcjvchedbsyoqfzmknyxwt", "agirmcjvlhedbsyovfzuynpxwl", "agtrmcjvlhedysyoqfzuknexwt", "agirmcjvmhedbslonfzuknpxwt", "agirmcjfshedbsyoqfziknpxwt", "agirmcjvlhedbsygqfzkknpbwt", "agyrmcivlhedbsyovfzuknpxwt", "agirmcjvghedbsyoqjzuknkxwt", "agirmcjvlhedqsyoqfzukspxmt", "ayirmcjvhhedbsyomfzuknpxwt", "agirmcjvlnembsypqfzuknpxwt", "agirmcjqlhedbsyuvfzuknpxwt", "agirmcjvlhembsybqfzuknpxwa", "agirjcfvlhedbsyoqfuuknpxwt", "agirmcjvohedbsyowfzuknxxwt", "agirmcjvlhedroyoqfzukncxwt", "agrrmijvlhedbsyoqfnuknpxwt", "agirmjjvlhsdbsyoqfzumnpxwt", "agirrcjvnhedbsyoqfzuktpxwt", "agirmcjvlzedjsyoqfzuknpdwt", "agirmkjvlhedbsyoqfzxinpxwt", "agirmcjvlhedbzyojfzuknpvwt", "arirmcjvlheddsyoqfzuknrxwt", "agirmcjvlhedbsyoqhzuanpxmt", "agirmcjvluedbsyoqozuknwxwt", "afirmcjwlhedxsyoqfzuknpxwt", "agirmcjvlhefbsyoqfkuinpxwt", "agirycjvltedbsypqfzuknpxwt", "agirmrxvlhedbsyoqfzeknpxwt", "agfrmcqvlhedbsyoqxzuknpxwt", "agormcjvuhexbsyoqfzuknpxwt", "agyrmcjvehddbsyoqfzuknpxwt", "agirmcjvlheqbsynqfzgknpxwt", "agirmcjvlhedbsloufwuknpxwt", "tgirmcjvlwedbsyoqfzuknpqwt", "agirmcjvlhesbzyogfzuknpxwt", "agitmdjvlhedpsyoqfzuknpjwt", "bgirmejvlhtdbsyoqfzuknpxwt", "aginmcjvlhedzsyoqfzuknoxwt", "agvrzcjvlhedbsuoqfzuknpxwt", "agormcjvlhedbsyoqfzuknpodt", "agirmcevlhedbgyojfzuknpxwt", "agirmcjblhedboytqfzuknpxwt", "qgibmcjvlhedbsyoqfzuknbxwt", "agirmcjvlhedbsyoafzutnnxwt", "agiamcjvchkdbsyoqfzuknpxwt", "agirmcjvehedblyoqwzuknpxwt", "agirmcpvlhwdbsyoafzuknpxwt", "agirmcjvlhtdbsyoqfzumnpxtt", "agirmcjalhegtsyoqfzuknpxwt", "agirdijvlhedbsyoqfzutnpxwt", "agirmckvlhgdbsyovfzuknpxwt", "qgmrmcjvlkedbsyoqfzuknpxwt", "agirjcjvlhodbsyoqfzuanpxwt", "ajirmcjvlhedbpyoqftuknpxwt", "cgirmcjvlhedbsyoqfiuonpxwt", "ayirmcjvlhedbsyaqfzuknwxwt", "agirmcjvlhedbdyoqbzwknpxwt");

        // Part one
        var counters = new ArrayList<Map<String, Integer>>();
        for (var s : boxes) {
            counters.add(countLetters(s));
        }

        var twos = 0;
        var threes = 0;
        for (var map : counters) {
            if (map.values().contains(2)) {
                twos++;
            }
            if (map.values().contains(3)) {
                threes++;
            }
        }

        var checksum = twos * threes;

        System.out.println("Part one: " + checksum);

        //Part two
        String box1 = null;
        String box2 = null;

        outer:
        for (int i = 0; i < boxes.size(); i++) {
            for (int j = i + 1; j < boxes.size(); j++) {
                if (matchBoxes(boxes.get(i), boxes.get(j))) {
                    box1 = boxes.get(i);
                    box2 = boxes.get(j);
                    break outer;
                }
            }
        }

        System.out.println("Part two: " + removeDifferences(box1, box2));
    }

    private static Map<String, Integer> countLetters(String letters) {
        var counters = new HashMap<String, Integer>();

        for (int i = 0; i < letters.length(); i++) {
            var letter = letters.substring(i, i + 1);
            var count = counters.getOrDefault(letter, 0);

            counters.put(letter, ++count);
        }

        return counters;
    }

    private static boolean matchBoxes(String box1, String box2) {
        var differences = 0;

        for (int i = 0; i < box1.length(); i++) {
            if (box1.charAt(i) != box2.charAt(i)) {
                differences++;
            }

            if (differences > 1) {
                return false;
            }

        }

        return true;
    }

    private static String removeDifferences(String box1, String box2) {
        var sb = new StringBuffer();
        
        for (int i = 0; i < box1.length(); i++) {
            if (box1.charAt(i) == box2.charAt(i)) {
                sb.append(box1.charAt(i));
            }
        }
        
        return sb.toString();
    }
}
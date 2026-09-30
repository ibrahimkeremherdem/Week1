package week1project;
import java.util.Scanner;

public class Week1_Project {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        
        String[] takimlar = {"Team A", "Team B", "Team C", "Team D"};

        
        int[] oynananMac = new int[4];
        int[] galibiyet = new int[4];
        int[] beraberlik = new int[4];
        int[] maglubiyet = new int[4];
        int[] atilanGol = new int[4];
        int[] yenilenGol = new int[4];
        int[] puan = new int[4];
        int[] averaj = new int[4];

        
        int[][] fikstur = {
            {0, 1}, // Team A vs Team B
            {2, 3}, // Team C vs Team D
            {0, 2}, // Team A vs Team C
            {1, 3}, // Team B vs Team D
            {0, 3}, // Team A vs Team D
            {1, 2}  // Team B vs Team C
        };

        
        System.out.println("--- FIKSTUR ---");
        for (int i = 0; i < fikstur.length; i++) {
            System.out.println("Mac " + (i + 1) + ": " + takimlar[fikstur[i][0]] + " vs " + takimlar[fikstur[i][1]]);
        }
        System.out.println("----------------\n");

        
        int[] evSahibiGolleri = new int[6];
        int[] deplasmanGolleri = new int[6];

        
        for (int i = 0; i < fikstur.length; i++) {
            int evSahibiIndeks = fikstur[i][0];
            int deplasmanIndeks = fikstur[i][1];

            System.out.println("Mac " + (i + 1) + ": " + takimlar[evSahibiIndeks] + " vs " + takimlar[deplasmanIndeks]);
            
            System.out.print(takimlar[evSahibiIndeks] + " gol sayisi: ");
            int evGol = input.nextInt();

            System.out.print(takimlar[deplasmanIndeks] + " gol sayisi: ");
            int depGol = input.nextInt();

            evSahibiGolleri[i] = evGol;
            deplasmanGolleri[i] = depGol;

           
            oynananMac[evSahibiIndeks]++;
            oynananMac[deplasmanIndeks]++;

           
            atilanGol[evSahibiIndeks] += evGol;
            yenilenGol[evSahibiIndeks] += depGol;
            atilanGol[deplasmanIndeks] += depGol;
            yenilenGol[deplasmanIndeks] += evGol;

            
            if (evGol > depGol) {
                galibiyet[evSahibiIndeks]++;
                maglubiyet[deplasmanIndeks]++;
                puan[evSahibiIndeks] += 3;
            } else if (depGol > evGol) {
                galibiyet[deplasmanIndeks]++;
                maglubiyet[evSahibiIndeks]++;
                puan[deplasmanIndeks] += 3;
            } else {
                beraberlik[evSahibiIndeks]++;
                beraberlik[deplasmanIndeks]++;
                puan[evSahibiIndeks] += 1;
                puan[deplasmanIndeks] += 1;
            }
            System.out.println();
        }

        
        System.out.println("--- OYNANAN MAC SONUCLARI ---");
        for (int i = 0; i < fikstur.length; i++) {
            System.out.println(takimlar[fikstur[i][0]] + " " + evSahibiGolleri[i] + " - " + deplasmanGolleri[i] + " " + takimlar[fikstur[i][1]]);
        }

        
        for (int i = 0; i < 4; i++) {
            averaj[i] = atilanGol[i] - yenilenGol[i];
        }

        
        System.out.println("\n--- PUAN DURUMU ---");
        System.out.println("Takim\t\tOM\tG\tB\tM\tPuan\tAvr");
        for (int i = 0; i < 4; i++) {
            System.out.println(takimlar[i] + "\t\t" + oynananMac[i] + "\t" + galibiyet[i] + "\t" + beraberlik[i] + "\t" + maglubiyet[i] + "\t" + puan[i] + "\t" + averaj[i]);
        }

        
        int sampiyonIndeks = 0;
        for (int i = 1; i < 4; i++) {
            if (puan[i] > puan[sampiyonIndeks]) {
                sampiyonIndeks = i;
            } else if (puan[i] == puan[sampiyonIndeks]) {
                if (averaj[i] > averaj[sampiyonIndeks]) {
                    sampiyonIndeks = i;
                }
            }
        }

        System.out.println("\nTournament Champion: " + takimlar[sampiyonIndeks]);

        input.close();
    }
}
package busroute;

import java.util.ArrayList;
import java.util.Scanner;

public class BusRoute {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ArrayList<String> duraklar = new ArrayList<>();
        ArrayList<Integer> binenler = new ArrayList<>();
        ArrayList<Integer> inenler = new ArrayList<>();
        ArrayList<Integer> durakYolcuSayilari = new ArrayList<>(); 

        System.out.println("Yolda kac adet durak oldugunu girin!");
        int durakSayisi = input.nextInt();

        System.out.println("Otobuste kac koltuk var?");
        int koltukKapasite = input.nextInt();
        input.nextLine(); 

        int mevcutYolcu = 0;
        int kapasiteAsimSayisi = 0;

        
        for (int i = 1; i <= durakSayisi; i++) {
            System.out.println("\n--- " + i + ". Durak Bilgileri ---");
            System.out.print("Durak ismi girin: ");
            String durakIsim = input.nextLine();
            duraklar.add(durakIsim);

            System.out.print("Bu durakta binen kisi sayisini girin: ");
            int binen = input.nextInt();
            binenler.add(binen);

            System.out.print("Bu durakta inen kisi sayisini girin: ");
            int inen = input.nextInt();
            input.nextLine(); 
            inenler.add(inen);

            
            mevcutYolcu += binen;

            
            if (inen > mevcutYolcu) {
                System.out.println("Hata inen kişi sayısı mevcut yolcu sayısından fazla olamaz mevcut yolcu sayısı 0'a eşitlendi!");
                mevcutYolcu = 0;
            } else {
                mevcutYolcu -= inen;
            }

            
            durakYolcuSayilari.add(mevcutYolcu);
            System.out.println("Durak sonrasi otobusteki yolcu sayisi: " + mevcutYolcu);

            
            if (mevcutYolcu > koltukKapasite) {
                System.out.println("Uyarı " + durakIsim + "durağında yolcu kapasitesine ulaşıldı!");
                kapasiteAsimSayisi++;
            }
        }

        
        System.out.println("\n--------- GUZERGAH OZETI ---------");
        for (int i = 0; i < durakSayisi; i++) {
            System.out.println("Durak: " + duraklar.get(i) 
                    + " | Binen: " + binenler.get(i) 
                    + " | Inen: " + inenler.get(i) 
                    + " | Mevcut Yolcu: " + durakYolcuSayilari.get(i));
        }

        
        System.out.println("\n--------- ISTATISTIKLER ----------");

        
        int enCokBinen = binenler.get(0);
        String enYogunDurak = duraklar.get(0);
        for (int i = 1; i < durakSayisi; i++) {
            if (binenler.get(i) > enCokBinen) {
                enCokBinen = binenler.get(i);
                enYogunDurak = duraklar.get(i);
            }
        }
        System.out.println("En cok yolcu binen durak: " + enYogunDurak + " (" + enCokBinen + " kisi)");

        
        int toplamYolcuHacmi = 0;
        for (int sayi : durakYolcuSayilari) {
            toplamYolcuHacmi += sayi;
        }
        double ortalamaYolcu = (double) toplamYolcuHacmi / durakSayisi;
        System.out.println("Guzergah boyunca ortalama yolcu sayisi: " + ortalamaYolcu);

       
        System.out.println("Kapasitenin asildigi toplam durak sayisi: " + kapasiteAsimSayisi);

        
        int sonDurakYolcu = durakYolcuSayilari.get(durakSayisi - 1);
        if (sonDurakYolcu != 0) {
            System.out.println("Uyarı " + sonDurakYolcu + " Son duraktan sonra hala otobüste yolcu var,girilen bilgileri kontrol edin!");
        } else {
            System.out.println("Tum yolcular son durakta basariyla inmistir.");
        }

        
    }
}
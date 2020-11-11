package yazlab12;

import static java.lang.Thread.currentThread;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainServer extends Thread{
    private String sunucuAdi;
    private int kapasite;
    private  int istek_s;
    public static int main_istek_s;
    private long istekAlmaSuresi;
    private long istekYanitSuresi;
    
    public static ArrayList<SubServer> YardimciSunucular=new ArrayList<>();
    Random random=new Random();
    long baslangic =System.currentTimeMillis();

    public MainServer() {
     
        this.sunucuAdi="Ana Sunucu";
        this.kapasite =10000;
        this.istek_s =1+random.nextInt(350);
        this.main_istek_s=this.istek_s;
        this.istekAlmaSuresi = 300;
        this.istekYanitSuresi = 200;
     
    }

    @Override
    public void run() {
        
          istek_th.start();
          yanit_th.start();
          
        try {
            istek_th.join();
             yanit_th.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MainServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }
    
    Thread istek_th=new Thread(new Runnable() {
        @Override
        public void run() {
            while(!interrupted()){
          istek_al();
                   
                try {
                    
                    Thread.sleep(istekAlmaSuresi);
                    
                } catch (InterruptedException ex) {
                        currentThread().interrupt();
                }
         
           }
        }
    });
     Thread yanit_th=new Thread(new Runnable() {
        @Override
        public void run() {
             while(!interrupted()){
                yanit_ver();
                 try {
                     
                     
                     Thread.sleep(istekYanitSuresi);
                    
                 } catch (InterruptedException ex) {
                     currentThread().interrupt();
                 }
                 
                
             }
        }
    });

    public synchronized static int getMain_istek_s() {
        return main_istek_s;
    }

    public static void setMain_istek_s(int main_istek_s) {
        MainServer.main_istek_s = main_istek_s;
    }
     
     
    public synchronized void istek_al(){
         
             
         this.istek_s=this.main_istek_s;
        int eklenecek=1+random.nextInt(199);
        long simdiki =System.currentTimeMillis();
       // System.out.println(sunucuAdi+":  -İstek Alındı:"+(getIstek_s()+eklenecek)+" süre:"+(simdiki-baslangic));
            this.istek_s +=eklenecek;
            this.main_istek_s=this.istek_s;
        
    }
    public synchronized void yanit_ver(){
        
           
        this.istek_s=this.main_istek_s;
        int yanit=1+random.nextInt(50);
         long simdiki =System.currentTimeMillis();
        if(this.istek_s==0 || (this.istek_s-yanit)<=0){
          //  this.istek_s=0;
         //  System.out.println(sunucuAdi+":  -İstek Cevaplanamadı:"+(getIstek_s()-yanit)+" süre:"+(simdiki-baslangic));
        }else{
            
       // System.out.println(sunucuAdi+":  -İstek Cevaplandı:"+(getIstek_s()-yanit)+" süre:"+(simdiki-baslangic));
        this.istek_s -=yanit;
        this.main_istek_s=this.istek_s;
        }
     
    }
    
    public synchronized int getKapasite() {
        return kapasite;
    }

    public String getSunucuAdi() {
        return sunucuAdi;
    }

    public void setSunucuAdi(String sunucuAdi) {
        this.sunucuAdi = sunucuAdi;
    }

    public void setKapasite(int kapasite) {
        this.kapasite = kapasite;
    }

    public synchronized int getIstek_s() {
        return istek_s;
    }

    public synchronized void setIstek_s(int istek_s) {
        this.istek_s = istek_s;
    }

    public long getIstekAlmaSuresi() {
        return istekAlmaSuresi;
    }

    public void setIstekAlmaSuresi(long istekAlmaSuresi) {
        this.istekAlmaSuresi = istekAlmaSuresi;
    }

    public long getIstekYanitSuresi() {
        return istekYanitSuresi;
    }

    public void setIstekYanitSuresi(long istekYanitSuresi) {
        this.istekYanitSuresi = istekYanitSuresi;
    }

    public synchronized Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }
    
    
    
    
}

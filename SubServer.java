
package yazlab12;

import java.util.logging.Level;
import java.util.logging.Logger;


public class SubServer extends MainServer{

    public SubServer(String name) {
        setSunucuAdi(name);
        setKapasite(5000);
        setIstekAlmaSuresi(400);
        setIstekYanitSuresi(300);
        setIstek_s(1);
    }
    public SubServer(String name,int istek_sayisi) {
         setKapasite(5000);
        setSunucuAdi(name);
        setIstekAlmaSuresi(500);
         
        setIstekYanitSuresi(300);
        setIstek_s(istek_sayisi);
        
    }



    @Override
    public synchronized void istek_al() {
     
        int eklenecek;
        if(main_istek_s<50 && main_istek_s>0){
            
        eklenecek=random.nextInt(main_istek_s);
        }else{
        eklenecek=random.nextInt(150);
        }
        if(main_istek_s-eklenecek>=0){
        main_istek_s-=eklenecek;
        long simdiki =System.currentTimeMillis();
       
         sunucu_oluşturucu();
      //  System.out.println(getSunucuAdi()+":İstek Alındı:"+(getIstek_s()+eklenecek)+" süre:"+(simdiki-baslangic));
     
         setIstek_s(getIstek_s()+eklenecek);
         }
    
    }

    @Override
    public synchronized void yanit_ver() {
      
          
          int yanit=1+random.nextInt(50);
         long simdiki =System.currentTimeMillis();
        if(getIstek_s()==0 || (getIstek_s()-yanit)<=0){
            setIstek_s(0);
         //  System.out.println(getSunucuAdi()+":  -İstek Cevaplanamadı:"+(getIstek_s()-yanit)+" süre:"+(simdiki-baslangic));
        }else{
            
       //  System.out.println(getSunucuAdi()+":  -İstek Cevaplandı:"+(getIstek_s()-yanit)+" süre:"+(simdiki-baslangic));
        
        setIstek_s(getIstek_s()-yanit);
        
        }
        
    }
    
    
    
    public synchronized int sunucu_dolu_mu(){
        if(getIstek_s() >= (getKapasite()*50)/100){
         return 1;
        }else{
        return -1;
        }
        
    }
    public synchronized void sunucu_oluşturucu(){
        if(sunucu_dolu_mu()==1){
        
       
      
         int sayi=YardimciSunucular.size()+3;
         String sunucu_adi="AltServer-"+sayi;
         YardimciSunucular.add(new SubServer(sunucu_adi,getIstek_s()/2));
       
            setIstek_s(getIstek_s()/2); 
  
               YardimciSunucular.get(YardimciSunucular.size()-1).start();
       
        }
    
    }
    
    @Override
    public void run() {
        
       
            try {
            istek_th.start();
            istek_th.sleep(getIstekAlmaSuresi());
        } catch (InterruptedException ex) {
            Logger.getLogger(MainServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            yanit_th.start();
            yanit_th.sleep(getIstekYanitSuresi());
        } catch (InterruptedException ex) {
            Logger.getLogger(MainServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(Thread.currentThread().isInterrupted()){
        return;
        }
    }
    
    
}

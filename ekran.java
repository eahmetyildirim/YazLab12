
package yazlab12;
/*
- SubServer isteği ana sunucudan alıcak.
- Oluşturulan SubServer lardan istek sayısı 0 olanlar silinecek.
*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class ekran extends javax.swing.JFrame implements ActionListener{
            MainServer mainServer =new MainServer();
            SubServer subServer1=new SubServer("AltServer-1");
            SubServer subServer2=new SubServer("AltServer-2");
            DefaultTableModel server_model;
          //   ArrayList<SubServer> YardimciSunucular=new ArrayList<>();
    Timer timer =new Timer(200,this);
    public ekran() {
         
        initComponents();
            
            timer.start();
            mainServer.start();
            subServer1.start();
            subServer2.start();
               
            server_model=(DefaultTableModel) server_table.getModel();
    }
    public void setSunucuSayisi(){
       int alt_sunucu=2;
    if(mainServer.YardimciSunucular!=null){
        alt_sunucu+=mainServer.YardimciSunucular.size();        
    }    
    String  sayi_S="Alt Sunucu Sayısı:"+alt_sunucu;
    sunucu_sayisi.setText(sayi_S);
    }
    
    
    public void updateServerTable(){
 
       server_model.setRowCount(0);
    
           ArrayList<SubServer> listele=new ArrayList<>();
           
             int main_istek_yuzde=(100*mainServer.getIstek_s())/mainServer.getKapasite();
             anaSunucu.setValue(main_istek_yuzde);
                        Object[] main_eklenecek ={mainServer.getSunucuAdi(),mainServer.getKapasite(),mainServer.getIstek_s(),main_istek_yuzde,anaSunucu};
                        server_model.addRow(main_eklenecek);
             altSunucu1.setValue((100*subServer1.getIstek_s())/subServer1.getKapasite());
             altSunucu2.setValue((100*subServer2.getIstek_s())/subServer2.getKapasite());
                    listele.add(subServer1);
                    listele.add(subServer2);
        
                if(mainServer.YardimciSunucular!=null){
                    for(int i=0;i<mainServer.YardimciSunucular.size();i++){
                        mainServer.YardimciSunucular.get(i).setSunucuAdi("AltServer-"+(i+3));
                        if(mainServer.YardimciSunucular.get(i).getIstek_s()==0){
                            mainServer.YardimciSunucular.get(i).interrupt();
                            mainServer.YardimciSunucular.get(i).istek_th.interrupt();
                            mainServer.YardimciSunucular.get(i).yanit_th.interrupt();
                           mainServer.YardimciSunucular.remove(i);
                        }else{
                        listele.add(mainServer.YardimciSunucular.get(i));
                        }
                     //listele.add(mainServer.YardimciSunucular.get(i));
                       
                       
                    }
                }
    
                if(listele!=null){
                    for(SubServer i:listele){
                        int istek_yuzde=(100*i.getIstek_s())/i.getKapasite();
                        Object[] eklenecek ={i.getSunucuAdi(),i.getKapasite(),i.getIstek_s(),istek_yuzde};
                        server_model.addRow(eklenecek);
                    }
                }
                                    
    }  
    
     

     @Override
    public void actionPerformed(ActionEvent e) {
      //  AltSunucuOlusturucu();
        setSunucuSayisi();
        updateServerTable();
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu1 = new java.awt.PopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        sunucu_sayisi = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        server_table = new javax.swing.JTable();
        anaSunucu = new javax.swing.JProgressBar();
        altSunucu1 = new javax.swing.JProgressBar();
        altSunucu2 = new javax.swing.JProgressBar();

        popupMenu1.setLabel("popupMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("SUNUCULAR");

        sunucu_sayisi.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        sunucu_sayisi.setText("Sunucu Sayısı:0");

        server_table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        server_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Sunucu Adı", "Kapasite", "İstek Sayısı", "Doluluk Yüzdesi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(server_table);
        if (server_table.getColumnModel().getColumnCount() > 0) {
            server_table.getColumnModel().getColumn(0).setResizable(false);
            server_table.getColumnModel().getColumn(1).setResizable(false);
            server_table.getColumnModel().getColumn(2).setResizable(false);
            server_table.getColumnModel().getColumn(3).setResizable(false);
        }

        anaSunucu.setValue(50);
        anaSunucu.setAlignmentX(1.0F);
        anaSunucu.setAlignmentY(1.0F);
        anaSunucu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        anaSunucu.setEnabled(false);
        anaSunucu.setNextFocusableComponent(server_table);
        anaSunucu.setStringPainted(true);

        altSunucu1.setValue(50);
        altSunucu1.setAlignmentX(1.0F);
        altSunucu1.setAlignmentY(1.0F);
        altSunucu1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        altSunucu1.setEnabled(false);
        altSunucu1.setNextFocusableComponent(server_table);
        altSunucu1.setStringPainted(true);

        altSunucu2.setValue(50);
        altSunucu2.setAlignmentX(1.0F);
        altSunucu2.setAlignmentY(1.0F);
        altSunucu2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        altSunucu2.setEnabled(false);
        altSunucu2.setNextFocusableComponent(server_table);
        altSunucu2.setStringPainted(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(anaSunucu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(altSunucu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(altSunucu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(anaSunucu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(altSunucu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(altSunucu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(157, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(170, 170, 170)
                                .addComponent(jLabel1))
                            .addComponent(sunucu_sayisi, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sunucu_sayisi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    public static void main(String args[]) {
        
       
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ekran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ekran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ekran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ekran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ekran Ekran= new ekran();
                Ekran.setVisible(true);
                
            }
        });
    }
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar altSunucu1;
    private javax.swing.JProgressBar altSunucu2;
    private javax.swing.JProgressBar anaSunucu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.PopupMenu popupMenu1;
    private javax.swing.JTable server_table;
    private javax.swing.JLabel sunucu_sayisi;
    // End of variables declaration//GEN-END:variables

  
}

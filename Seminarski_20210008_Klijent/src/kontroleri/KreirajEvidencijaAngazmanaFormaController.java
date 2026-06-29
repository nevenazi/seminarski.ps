/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.KreirajEvidencijaAngazmanaForma;
import forme.VrstaForme;
import static forme.VrstaForme.PROMENI;
import forme.model.ModelTabeleStavkaAngazmana;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import komunikacija.Komunikacija;
import model.Dizajner;
import model.EvidencijaAngazmana;
import model.MarketingMenadzer;
import model.StavkaAngazmana;
import model.TipVizuala;

/**
 *
 * @author N
 */
public class KreirajEvidencijaAngazmanaFormaController {
    
    public final KreirajEvidencijaAngazmanaForma keaf;
    private EvidencijaAngazmana evidencijaForme;
    private final SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd.MM.yyyy");

    public KreirajEvidencijaAngazmanaFormaController(KreirajEvidencijaAngazmanaForma keaf) {
        this.keaf = keaf;
    }
    public void otvoriFormu(VrstaForme vrstaForme){
        keaf.setVisible(true);
        popuniSveComboBox();
        
        switch (vrstaForme){
            case KREIRAJ:
                keaf.setTitle("Kreiraj evidenciju angažmana");
                keaf.getjButtonPromeni().setVisible(false);
                keaf.getjButtonSacuvaj().setVisible(true);
                keaf.getjButtonSacuvaj().setEnabled(true);
                evidencijaForme=new EvidencijaAngazmana();
                break;
            case PROMENI:
                keaf.setTitle("Promeni evidenciju angažmana");
                keaf.getjButtonSacuvaj().setVisible(false);
                keaf.getjButtonPromeni().setVisible(true);
                keaf.getjButtonPromeni().setEnabled(true);
                evidencijaForme=(EvidencijaAngazmana)Koordinator.getInstance().vratiParametar("evidencija angažmana");
                popuniFormu(evidencijaForme);
                break;
            case PRIKAZI:
                keaf.setTitle("Prikaži evidenciju angažmana");
                keaf.getjButtonSacuvaj().setVisible(false);
                keaf.getjButtonPromeni().setVisible(false);
                evidencijaForme=(EvidencijaAngazmana)Koordinator.getInstance().vratiParametar("evidencija angažmana");
                popuniFormu(evidencijaForme);
                onemoguciFormu();
                break;
            default:
                JOptionPane.showMessageDialog(keaf, "Nije izabrana vrsta forme", "Greška", JOptionPane.ERROR_MESSAGE);
                keaf.dispose();
                
        }
        popuniTabeluStavki();
        addActionListeners();
    }
    public void addActionListeners(){
        
        keaf.addButtonDodajStavkuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                izracunaj();
                TipVizuala tipVizuala=(TipVizuala) keaf.getjComboBoxTipVizuala().getSelectedItem();
                double cena=tipVizuala.getOsnovnaCena();
                int kolicina = 0;
                try {
                    kolicina = Integer.parseInt(keaf.getjTextFieldKolicina().getText().trim());
                } catch (NumberFormatException numberFormatException) {
                    Logger.getLogger(KreirajEvidencijaAngazmanaFormaController.class.getName()).log(Level.SEVERE, "Količina nije uneta kao celi broj", numberFormatException);
                    return;
                }
                double nekorigovan=(double)cena*kolicina;
                double korekcija;
                if (keaf.getjTextFieldKorekcijaIznosa().getText().trim().equals("")){
                    korekcija=0;
                }else{
                    try {
                        korekcija = Double.parseDouble(keaf.getjTextFieldKorekcijaIznosa().getText().trim());
                        } catch (NumberFormatException numberFormatException) {
                            Logger.getLogger(KreirajEvidencijaAngazmanaFormaController.class.getName()).log(Level.SEVERE, "Korekcija nije uneta kao broj", numberFormatException);
                            return;
                        }
                }
                
                double korigovan=Math.max(0,nekorigovan+korekcija);
                boolean zavrsena=keaf.getjCheckBoxZavrsena().isSelected();
                String opis=keaf.getjTextAreaOpis().getText().trim();
                
                if (kolicina>0 && opis!=null && !opis.isEmpty()){
                    List<StavkaAngazmana> stavke=((ModelTabeleStavkaAngazmana)keaf.getjTableStavkeAngazmana().getModel()).getLista();
                    int najveciRB=0;
                    for (StavkaAngazmana stavkaZaRb : stavke) {
                        if (stavkaZaRb.getRb()>najveciRB)najveciRB=stavkaZaRb.getRb();
                    }
                    StavkaAngazmana stavka=new StavkaAngazmana(evidencijaForme, najveciRB+1, kolicina, opis, cena, nekorigovan, korekcija, korigovan, zavrsena, tipVizuala);
                    boolean vecPostoji=false;
                    for (StavkaAngazmana stavkaAngazmana : stavke) {
                        if (stavkaAngazmana.getOpis().equals(opis)
                                && stavkaAngazmana.getCena()==cena
                                && stavkaAngazmana.isZavrsena()==zavrsena
                                && stavkaAngazmana.getTipVizuala().equals(tipVizuala)           
                                ){  //samo treba povecati kolicinu i prilagoditi iznose i korekciju iznosa
                            stavkaAngazmana.setKolicina(stavkaAngazmana.getKolicina()+kolicina);
                            stavkaAngazmana.setKorekcijaIznosa(stavkaAngazmana.getKorekcijaIznosa()+korekcija);
                            stavkaAngazmana.setNekorigovanIznos(stavkaAngazmana.getCena()*stavkaAngazmana.getKolicina());
                            stavkaAngazmana.setKorigovanIznos(stavkaAngazmana.getNekorigovanIznos()+stavkaAngazmana.getKorekcijaIznosa());
                            vecPostoji=true;
                        }
                    }
                    if (!vecPostoji){
                        stavke.add(stavka);
                    }
                    double zbir=0;
                    for(int i=0;i<stavke.size();i++){
                        zbir=zbir+stavke.get(i).getKorigovanIznos();
                    }
                    ModelTabeleStavkaAngazmana msa=new ModelTabeleStavkaAngazmana(stavke);
                    keaf.getjTableStavkeAngazmana().setModel(msa);
                    keaf.getjTextFieldUkupanIznos().setText(zbir+"");
                }
            }
        });
        
        keaf.addButtonIzbaciStavkuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int red=keaf.getjTableStavkeAngazmana().getSelectedRow();
                if (red==-1) {
                    JOptionPane.showMessageDialog(keaf, "Sistem ne može da nađe stavku angažmana.", "Greška", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(keaf, "Sistem je pronašao stavku angažmana za brisanje.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                List<StavkaAngazmana> stavke=((ModelTabeleStavkaAngazmana)keaf.getjTableStavkeAngazmana().getModel()).getLista();
                
                double ukupaniznos=Double.parseDouble(keaf.getjTextFieldUkupanIznos().getText());
                ukupaniznos-=stavke.get(red).getKorigovanIznos();
                keaf.getjTextFieldUkupanIznos().setText(ukupaniznos+"");
                
                stavke.remove(red);
                
                ModelTabeleStavkaAngazmana msa=new ModelTabeleStavkaAngazmana(stavke);
                keaf.getjTableStavkeAngazmana().setModel(msa);
            }
        });
        
        keaf.addButtonIzracunajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izracunaj();
            }
        });
        
        
        keaf.addButtonSacuvajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                EvidencijaAngazmana ea=pokupiEvidencijaAngazmana();
                if (ea.getStavkeAngazmana().isEmpty()){
                    JOptionPane.showMessageDialog(keaf, "Sistem ne može da zapamti evidenciju angažmana.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    Komunikacija.getInstance().kreirajEvidencijaAngazmana(ea);
                    String ispis="Sistem je kreirao evidenciju angažmana:\n"+"dizajner: "+ea.getDizajner().toString()
                            +"\nmarketing menadžer: "+ea.getMarketingMenadzer().toString()+"\nrok: "+simpleDateFormat.format(ea.getRok())
                            +"\nukupan iznos: "+ea.getUkupanIznos()+"\nzavršen: "+ea.isZavrsen()+"\nbroj stavki:"+ea.getStavkeAngazmana().size();
                    JOptionPane.showMessageDialog(keaf, ispis, "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    keaf.dispose();
                    Koordinator.getInstance().getEvidencijaAngazmanaFormaController().pripremiFormu();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(keaf, "Sistem ne može da kreira evidenciju angažmana.", "Greška", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(KreirajEvidencijaAngazmanaFormaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        keaf.addButtonPromeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                EvidencijaAngazmana ea=pokupiEvidencijaAngazmana();
                
                ea.setIdEvidencijaAngazmana(evidencijaForme.getIdEvidencijaAngazmana());
                try {    
                    Komunikacija.getInstance().promeniEvidencijaAngazmana(ea);
                    String ispis="Sistem je zapamtio evidenciju angažmana:\n"+"dizajner: "+ea.getDizajner().toString()
                            +"\nmarketing menadžer: "+ea.getMarketingMenadzer().toString()+"\nrok: "+simpleDateFormat.format(ea.getRok())
                            +"\nukupan iznos: "+ea.getUkupanIznos()+"\nzavršen: "+ea.isZavrsen()+"\nbroj stavki:"+ea.getStavkeAngazmana().size();
                    JOptionPane.showMessageDialog(keaf, ispis, "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    
                    keaf.dispose();
                    Koordinator.getInstance().getEvidencijaAngazmanaFormaController().pripremiFormu();
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(keaf, "Sistem ne može da zapamti evidenciju angažmana.", "Greška", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(KreirajEvidencijaAngazmanaFormaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }

            
        });
        
        keaf.addButtonZatvoriActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keaf.dispose();
            }
        });
        
        keaf.addTableStavkeMouseClickListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JTable table=(JTable) evt.getSource();
                Point point=evt.getPoint();
                int red=table.rowAtPoint(point);

                StavkaAngazmana stavka=((ModelTabeleStavkaAngazmana)keaf.getjTableStavkeAngazmana().getModel()).getLista().get(red);
                String ispis="Sistem je našao stavku angažmana.";
                JOptionPane.showMessageDialog(keaf, ispis, "Uspeh", JOptionPane.INFORMATION_MESSAGE);

                keaf.getjComboBoxTipVizuala().setSelectedItem(stavka.getTipVizuala());
                keaf.getjTextAreaOpis().setText(stavka.getOpis());
                keaf.getjTextFieldKolicina().setText(stavka.getKolicina()+"");
                keaf.getjTextFieldKorekcijaIznosa().setText(stavka.getKorekcijaIznosa()+"");
                keaf.getjCheckBoxZavrsena().setSelected(stavka.isZavrsena());
                izracunaj();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
    }
    
    private void onemoguciFormu(){
        keaf.getjButtonDodajStavku().setVisible(false);
        keaf.getjButtonIzbaciStavku().setVisible(false);
        keaf.getjButtonIzracunaj().setVisible(false);
        keaf.getjComboBoxDizajner1().setEnabled(false);
        keaf.getjComboBoxTVizuala().setEnabled(false);
        keaf.getjComboBoxMarketingMenadzer1().setEnabled(false);
        keaf.getjTextAreaOpis().setEnabled(false);
        keaf.getjTextFieldCena().setEnabled(false);
        keaf.getjTextFieldKolicina().setEnabled(false);
        keaf.getjTextFieldKorekcijaIznosa().setEnabled(false);
        keaf.getjTextFieldKorigovaniIznos().setEnabled(false);
        keaf.getjTextFieldNekorigovaniIznos().setEnabled(false);
        keaf.getjTextFieldRok().setEnabled(false);
        keaf.getjTextFieldUkupanIznos().setEnabled(false);
        keaf.getjCheckBoxZavrsena().setEnabled(false);
    }
    
    private EvidencijaAngazmana pokupiEvidencijaAngazmana() {
        
        Dizajner dizajner=(Dizajner) keaf.getjComboBoxDizajner1().getSelectedItem();
        MarketingMenadzer men=(MarketingMenadzer) keaf.getjComboBoxMarketingMenadzer1().getSelectedItem();
        java.util.Date rok;
        java.sql.Date sqlrok;
        try {
            rok = simpleDateFormat.parse(keaf.getjTextFieldRok().getText().trim());
            sqlrok=new java.sql.Date(rok.getTime());
        } catch (ParseException ex) {
            sqlrok=null;
            Logger.getLogger(KreirajEvidencijaAngazmanaFormaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        double ukupanIznos;
        try {
            ukupanIznos=Double.parseDouble(keaf.getjTextFieldUkupanIznos().getText());
        } catch (NumberFormatException ex) {
            ukupanIznos=0;
            Logger.getLogger(KreirajEvidencijaAngazmanaFormaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<StavkaAngazmana> stavke=((ModelTabeleStavkaAngazmana)keaf.getjTableStavkeAngazmana().getModel()).getLista();
        boolean zavrsen=true;
        if (!stavke.isEmpty()){
            for (int i=0;i<stavke.size();i++){
                zavrsen=zavrsen&&stavke.get(i).isZavrsena();
            }
        }

        EvidencijaAngazmana evidencija=new EvidencijaAngazmana(-1, ukupanIznos, sqlrok, zavrsen, stavke, dizajner, men);
        return evidencija;
            
    
                
    }

    private void izracunaj(){
        double cena=((TipVizuala)keaf.getjComboBoxTipVizuala().getSelectedItem()).getOsnovnaCena();
        int kolicina = 0;
        try {
            kolicina = Integer.parseInt(keaf.getjTextFieldKolicina().getText().trim());
        } catch (NumberFormatException numberFormatException) {
        }
        double nekorigovan=(double)cena*kolicina;
        double korekcija = 0;
        try {
            korekcija = Double.parseDouble(keaf.getjTextFieldKorekcijaIznosa().getText().trim());
        } catch (NumberFormatException numberFormatException) {
            Logger.getLogger(KreirajEvidencijaAngazmanaFormaController.class.getName()).log(Level.SEVERE, "Korekcija nije uneta kao broj.", numberFormatException);
        }
        double korigovan=Math.max(0,nekorigovan+korekcija);

        keaf.getjTextFieldCena().setText(cena+"");
        keaf.getjTextFieldNekorigovaniIznos().setText(nekorigovan+"");
        keaf.getjTextFieldKorigovaniIznos().setText(korigovan+"");
    }
    
    private void popuniFormu(EvidencijaAngazmana evidencija) {
        keaf.getjComboBoxDizajner1().setSelectedItem(evidencija.getDizajner());
        keaf.getjComboBoxMarketingMenadzer1().setSelectedItem(evidencija.getMarketingMenadzer());
        keaf.getjTextFieldRok().setText(simpleDateFormat.format(evidencija.getRok()));
        keaf.getjTextFieldUkupanIznos().setText(evidencija.getUkupanIznos()+"");
        
        
    }

    

    private void popuniSveComboBox() {
        List<TipVizuala> vizuali=new ArrayList<>();
        try {
            vizuali=Komunikacija.getInstance().vratiSveTipVizuala();
        } catch (Exception ex) {
            Logger.getLogger(KreirajEvidencijaAngazmanaFormaController.class.getName()).log(Level.SEVERE, "Neispunjeni preduslovi. Sistem ne može da nađe tipove vizuala.", ex);
            Koordinator.getInstance().otvoriEvidencijaAngazmanaFormu();
            keaf.dispose();
        }
        keaf.getjComboBoxTipVizuala().removeAllItems();
        for (TipVizuala vizual : vizuali) {
            System.out.println(vizual.toString());
            keaf.getjComboBoxTVizuala().addItem(vizual);
        }
        
        
        List<Dizajner> dizajneri=new ArrayList<>();
        try {
            dizajneri=Komunikacija.getInstance().vratiSveDizajner();
        } catch (Exception ex) {
            Logger.getLogger(KreirajEvidencijaAngazmanaFormaController.class.getName()).log(Level.SEVERE, "Neispunjeni preduslovi. Sistem ne može da nađe dizajnere.", ex);
            Koordinator.getInstance().otvoriEvidencijaAngazmanaFormu();
            keaf.dispose();
        }
        keaf.getjComboBoxDizajner1().removeAllItems();
        for (Dizajner dizajner : dizajneri) {
            keaf.getjComboBoxDizajner1().addItem(dizajner);
        }
        
        List<MarketingMenadzer> menadzeri=new ArrayList<>();
        try {
            menadzeri=Komunikacija.getInstance().vratiSveMarketingMenadzer();
        } catch (Exception ex) {
            Logger.getLogger(KreirajEvidencijaAngazmanaFormaController.class.getName()).log(Level.SEVERE, "Neispunjeni preduslovi. Sistem ne može da nađe marketing menadžere.", ex);
            Koordinator.getInstance().otvoriEvidencijaAngazmanaFormu();
            keaf.dispose();
        }
        keaf.getjComboBoxMarketingMenadzer1().removeAllItems();
        for (MarketingMenadzer menadzer : menadzeri) {
            keaf.getjComboBoxMarketingMenadzer1().addItem(menadzer);
        }
    }

    private void popuniTabeluStavki() {
        List<StavkaAngazmana> stavke=new ArrayList<>();
        if (evidencijaForme.getIdEvidencijaAngazmana()>0)
            stavke=evidencijaForme.getStavkeAngazmana();
        
        ModelTabeleStavkaAngazmana mtsa=new ModelTabeleStavkaAngazmana(stavke);
        keaf.getjTableStavkeAngazmana().setModel(mtsa);
    }

    


}

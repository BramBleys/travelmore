package be.thomasmore.travelmore.controller;

import be.thomasmore.travelmore.domain.Boeking;
import be.thomasmore.travelmore.domain.Reis;
import be.thomasmore.travelmore.service.BoekingService;
import be.thomasmore.travelmore.service.ReisService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@ManagedBean
@ViewScoped
public class BoekingController implements Serializable {

    private Boeking boeking = new Boeking();
    public Boeking newBoeking = new Boeking();
    public Reis reis;
    private List<Boeking> boekingen;


    @Inject
    private BoekingService boekingService;

    @Inject
    private ReisController reisController;

    public Boeking getBoeking(int id) {
        return this.boekingService.findBoekingById(id);
    }

    public void addBoeking(Reis reis, double prijs) {
        Date datum = new Date();
        this.newBoeking.setPrijs(prijs*(this.newBoeking.getAantalKinderen()+this.newBoeking.getAantalVolwassenen()));
        this.newBoeking.setDatum(datum);
        this.newBoeking.setBetaald(false);
        this.newBoeking.setReis(reis);
        this.boekingService.addBoeking(newBoeking);
    }


    public List<Boeking> getBoekingen() {
        boekingen = this.boekingService.findAll();
        return boekingen;
    }

    public Boeking getNewBoeking() {
        return newBoeking;
    }

    public void setNewBoeking(Boeking newBoeking) {
        this.newBoeking = newBoeking;
    }

}
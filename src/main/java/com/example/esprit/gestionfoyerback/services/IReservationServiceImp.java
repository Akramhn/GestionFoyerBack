package com.example.esprit.gestionfoyerback.services;

import com.example.esprit.gestionfoyerback.entities.Bloc;
import com.example.esprit.gestionfoyerback.entities.Chambre;
import com.example.esprit.gestionfoyerback.entities.Etudiant;
import com.example.esprit.gestionfoyerback.entities.Reservation;
import com.example.esprit.gestionfoyerback.enums.TypeChambre;
import com.example.esprit.gestionfoyerback.repository.BlocRepository;
import com.example.esprit.gestionfoyerback.repository.ChambreRepository;
import com.example.esprit.gestionfoyerback.repository.EtudiantRepository;
import com.example.esprit.gestionfoyerback.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
@Service
@AllArgsConstructor
public class IReservationServiceImp implements IReservationService {

    final ReservationRepository reservationRepository;
    final ChambreRepository chambreRepository ;
    final EtudiantRepository etudiantRepository ;
    final BlocRepository blocRepository ;
    @Override
    public List<Reservation> retrieveAllReservation() {
        return (List<Reservation>) reservationRepository.findAll();
    }

    @Override
    public Reservation updateReservation(Reservation res) {
        return reservationRepository.save(res);
    }

    @Override
    public Reservation retrieveReservation(String idReservation) {

        return reservationRepository.findById(idReservation).orElseThrow(() -> new IllegalArgumentException("No Reservation Found with this id"));

    }

    @Override
    public Reservation ajouterReservation(long idChambre, long cinEtudiant) {

            Chambre chambre = chambreRepository.findById(idChambre).orElseThrow(null);
            Etudiant etudiant = etudiantRepository.findEtudiantByCin(cinEtudiant);


            Bloc bloc = blocRepository.findById(chambre.getBloc().getIdBloc()).orElseThrow();
            Reservation chambreReserve = null ;
            chambreReserve = reservationRepository.findByChambre(idChambre) ;

        Reservation res =reservationRepository.findById(   String.valueOf(chambre.getNumeroChambre())+"-"+ bloc.getIdBloc() +"-"+ LocalDate.now().getYear()).orElse(null);
            if(res==null) {
                res = new Reservation();

                res.setAnneeUnivirsitaire(LocalDate.of(LocalDate.now().getYear(), 8, 15));
                res.setIdReservation(
                        String.valueOf(chambre.getNumeroChambre()) + "-" + bloc.getIdBloc() + "-" + LocalDate.now().getYear()
                );

            }




            if(chambreReserve == null || chambreReserve.getAnneeUnivirsitaire().getYear() < LocalDate.now().getYear() ){
                if(chambre.getTypeC() == TypeChambre.SIMPLE){
                    res.setEstValide(false);
                }
                else {
                    res.setEstValide(true);
                }
                chambre.getReservations().add(res);
                if (res.getEtudiants()==null)
                {
                    List<Etudiant>etudiants=new ArrayList<>();
                    etudiants.add(etudiant);
                    res.setEtudiants(etudiants);
                }
                else {
                    res.getEtudiants().add(etudiant);
                }


                reservationRepository.save(res);
                chambreRepository.save(chambre);

            } else if (res.getAnneeUnivirsitaire().getYear() < LocalDate.now().getYear()) {
                if(chambre.getTypeC() == TypeChambre.SIMPLE){
                    res.setEstValide(false);
                } else {
                    res.setEstValide(true);
                }
                reservationRepository.save(res);


            } else {
                if(res.isEstValide() ){
                    if(reservationRepository.getNumberReservation(res.getIdReservation()) ==  1 && chambre.getTypeC() == TypeChambre.DOUBLE){
                        res.setEstValide(false);
                        reservationRepository.save(res);

                    } else if(reservationRepository.getNumberReservation(res.getIdReservation()) == 1 && chambre.getTypeC() == TypeChambre.TRIPLE){
                        res.setEstValide(true);
                        reservationRepository.save(res);
                    }else{
                        res.setEstValide(false);
                    }
                    if (res.getEtudiants()==null)
                    {
                        List<Etudiant>etudiants=new ArrayList<>();
                        etudiants.add(etudiant);
                        res.setEtudiants(etudiants);
                    }
                    else {
                        res.getEtudiants().add(etudiant);
                    }


                    reservationRepository.save(res);
                }
            }


    return  res ;
    }

}

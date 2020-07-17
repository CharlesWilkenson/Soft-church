package com.example.demo.services;

import java.util.List;
import java.util.Map;

import com.example.demo.entities.Cash;
import com.example.demo.entities.Cheque;
import com.example.demo.entities.Culte;
import com.example.demo.entities.Dime;
import com.example.demo.entities.Don;
import com.example.demo.entities.Espece;
import com.example.demo.entities.Membre;
import com.example.demo.entities.Nature;
import com.example.demo.entities.Offrande;
import com.example.demo.entities.Role;
import com.example.demo.entities.TraceSysteme;
import com.example.demo.entities.Utilisateur;


public interface Services {
public void saveMembre(Membre membre);
public Membre findMembre(String code);
public void updateMembre(Membre membre);
public List<Membre> listerMembre();
public List<Membre> listerMembre(String sexe);

public void createUser(Utilisateur u);
public Utilisateur findUser(String email);
public Utilisateur updateUser(Utilisateur u);
public List<Utilisateur>listerUser();
public List<Utilisateur>listerUser(String email);
public void changePassword(Utilisateur u);
public void resetPassword(String email);
public void desactiverUser(String email);
public void reactiverUser(String email);
public void enleverRole(String email,String role);





public void addRole(Role r);
public Role findRole(String role);
public List<Role>listerRole();
public void attribuerRole(Utilisateur u,String newRole);

public void addTrace(TraceSysteme t);
public List<TraceSysteme>listerTrace();

public void enregistrerCulte(Culte c,Map<String,Membre>mb);
public List<Culte> listerCultes();
public Culte getCulte(Long id);
public List<Culte> listerCultes(Long id);

public void enregistrerDimeCash(Cash cash);
public void enregistrerDimeCheque(Cheque cheque);
public List<Cash>listerCashes();
public List<Cheque>listerCheques();

public void enregistrerOffrande(Offrande of);
public List<Offrande>listerOffrandes();


public void enregistrerDon_nature(Nature nature);
public void enregistrerDon_espece(Espece espece );
public List<Nature>listerNatures();
public List<Espece>listerEspeces();

















}

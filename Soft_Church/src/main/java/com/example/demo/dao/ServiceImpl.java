package com.example.demo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.*;
import com.example.demo.services.CashRepository;
import com.example.demo.services.ChequeRepository;
import com.example.demo.services.CulteRepository;
import com.example.demo.services.DimeRepository;
import com.example.demo.services.EspeceRepository;
import com.example.demo.services.MembreRepository;
import com.example.demo.services.NatureRepository;
import com.example.demo.services.OffrandesRepository;
import com.example.demo.services.RoleRepository;
import com.example.demo.services.Services;
import com.example.demo.services.TraceRepository;
import com.example.demo.services.UtilisateurRepository;


@Service
@Transactional
public class ServiceImpl implements Services{
@Autowired
private MembreRepository membreRepository;
@Autowired
private RoleRepository roleRepository;
@Autowired
private UtilisateurRepository userRepository;
@Autowired
private TraceRepository traceRepository;

@Autowired
private CulteRepository culteRepository;
@Autowired
private CashRepository cashRepository;
@Autowired
 private ChequeRepository chequeRepository;
@Autowired
private DimeRepository  dimeRepository;
@Autowired
private OffrandesRepository offrandesRepository;
@Autowired
private EspeceRepository especeRepository;
@Autowired
private NatureRepository natureRepository;

	public void saveMembre(Membre membre) {
	String password=password();
	List<Utilisateur>utilisateurs =new ArrayList<Utilisateur>();
	List<Role> roles=new ArrayList<Role>();
	
	membre.setCode(code());
	Utilisateur utilisateur=new Utilisateur(membre.getEmail(), encrytePassword(password), 
			new Membre(membre.getCode()),true);
	
	Role role1=new Role("MEMBRE");

	roles.add(role1);
		
	utilisateurs.add(utilisateur);
		
	utilisateur.setRoles(roles);
	role1.setUsers(utilisateurs);
	membre.setDateAjout(new Date());
	

	membreRepository.save(membre);
	userRepository.save(utilisateur);
	
	SendEmail.sendEmail(password, membre.getEmail());
	}

	
	
	
	
	public Membre findMembre(String code) {
	
		try {
		Optional<Membre> membre = membreRepository.findById(code);
		if (membre.isPresent()){
		return membre.get();
		}
	
		}catch(Exception e) {
			throw new RuntimeException("Code invalide");
			
		}
		return null;
		
		}
	
	

	public void updateMembre(Membre membre) {
		Membre m=membreRepository.getOne(membre.getCode());
		membre.setDateAjout(m.getDateAjout());
	    membreRepository.save(membre);
		
	}

	public List<Membre> listerMembre() {
		List<Membre> list=membreRepository.listerMembre();
		return list;
	}


	public List<Membre> listerMembre(String sexe) {
		List<Membre> list=membreRepository.listerMembre(sexe);
		return list;
	}


	public void createUser(Utilisateur u) {
		userRepository.save(u);
		
	}

	
	public Utilisateur findUser(String email) {
Utilisateur utilisateur=userRepository.getOne(email);
		return utilisateur;
	}

	
	public Utilisateur updateUser(Utilisateur u) {
		userRepository.saveAndFlush(u);
		return null;
	}

	
	public List<Utilisateur> listerUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}


	public void addRole(Role r) {
	roleRepository.save(r);
		
	}

	
	public Role findRole(String role) {
		Role rol=roleRepository.getOne(role);
		return rol;
	}

	
	public List<Role> listerRole() {
		List<Role> list=roleRepository.findAll();
		return list;
	}


	


	public List<Utilisateur> listerUser(String email) {
		//List<Utilisateur> listerUser=userRepository.listerUser(email);
		return  null;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public  String code(){
		Random rd=new Random();

	String id=String.format("MB%s-%s-%s-%s", rd.nextInt(9999), 
			rd.nextInt(9999), rd.nextInt(9999), rd.nextInt(9999));
	return	id;
	}
	
	  public static String encrytePassword(String password) {
    	  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	        return encoder.encode(password);
    	    }
	
	public  String password(){
		Random rd=new Random();

	String id=String.format("%s-%s-%s-%s", rd.nextInt(9999), 
			rd.nextInt(9999), rd.nextInt(9999), rd.nextInt(9999));
	return	id;
	}

	
	
	public void resetPassword(String email) {
	String password=password();
	Utilisateur utilisateur=userRepository.getOne(email);

	utilisateur.setPassword(encrytePassword(password));
	
    userRepository.save(utilisateur);
    SendEmail.sendEmail(password, utilisateur.getEmail());
	}
	
	
	
	public void changePassword(Utilisateur u) {
		String newPassword=encrytePassword(u.getPassword());
	
	
		Utilisateur ut=userRepository.getOne(u.getEmail());
	 
		
		ut.setPassword(newPassword);
		userRepository.save(ut);
	
		
	}

	public void attribuerRole(Utilisateur utilisateur, String newRole) {
		
	List<Utilisateur>utilisateurs =new ArrayList<Utilisateur>();
	List<Role> roles=new ArrayList<Role>();
	
	Role role1=new Role(newRole);
	Role role2=new Role("MEMBRE");
	
	roles.add(role1);
	roles.add(role2);

    utilisateurs.add(utilisateur);
	
	utilisateur.setRoles(roles);
	role1.setUsers(utilisateurs);
	role2.setUsers(utilisateurs);

   userRepository.save(utilisateur);
	}

	
	
	public void addTrace(TraceSysteme t) {
		
		traceRepository.save(t);
	}

	public List<TraceSysteme> listerTrace() {
	
		return traceRepository.findAll();
	}

	
	public void desactiverUser(String email) {
		Utilisateur u=userRepository.getOne(email);
		
		u.setEtat(false);
	
		userRepository.save(u);
	}
	
	
	
	public void reactiverUser(String email) {
		Utilisateur u=userRepository.getOne(email);
		
		u.setEtat(true);
		
		userRepository.save(u);
	}

	public void enregistrerCulte(Culte c,Map<String,Membre>map) {

	List<Culte>cultes =new ArrayList<Culte>();
	List<Membre> membres=new ArrayList<Membre>();
	List<Activite>activities=new ArrayList<Activite>();
	  
		c.setDate(new Date());
	    cultes.add(c);	    
	    
	    Membre mb=new Membre();
	   for (Membre m:map.values()){
		    mb.setCode(m.getCode());
	    	membres.add(new Membre(mb.getCode())); 
	    	
	    }
	
	  c.setMembres(membres);  
	
	System.out.println("Size of the map Second "+map.size());
     culteRepository.save(c);
 	
		
 	
 	  Offrande of=new Offrande(c.getOffrande().getMontant(),
 			  c.getOffrande().getDevise(), new Culte(c.getId()));
 			 
 			  System.out.println("Montant "+c.getOffrande().getMontant());
 			  System.out.println("Devise "+c.getOffrande().getDevise());
 			  System.out.println("ID "+c.getId());
 		offrandesRepository.save(of);
 	 System.out.println("Size of the map Third "+map.size());				
	}

	public List<Culte> listerCultes() {

		return culteRepository.findAll();
	}

	public void enregistrerDime(Dime dime) {
	dimeRepository.save(dime);
		
	}

	public List<Dime> listerDimes() {
		// TODO Auto-generated method stub
		return dimeRepository.findAll();
	}

	public void enregistrerDimeCash(Cash cash) {
		
		cash.setDate(new Date());
    cashRepository.save(cash);
		
	}

	public void enregistrerDimeCheque(Cheque cheque) {
		cheque.setDate(new Date());
    chequeRepository.save(cheque);
		
	}





	public void enregistrerOffrande(Offrande of) {
		// TODO Auto-generated method stub
		
	}





	public List<Offrande> listerOffrandes() {	
		return offrandesRepository.findAll() ;
	}





	public List<Culte> listerCultes(Long id) {

		return culteRepository.listerCultes((long) 18);
	
	}





	public Culte getCulte(Long id) {
		return culteRepository.getOne(id);
	}


	public List<Cash> listerCashes() {
		
		return cashRepository.findAll();
	}



	public List<Cheque> listerCheques() {
		return chequeRepository.findAll();
	}


	public void enleverRole(String email,String role) {
		System.out.println("role " +role);
		//List<Utilisateur>utilisateurs =new ArrayList<Utilisateur>();
		
		
		
		//Role role2=new Role("MEMBRE");
		
		//roles.add(role1);
		//roles.add(role2);
		
	    Utilisateur utilisateur= userRepository.getOne(email);
	    List<Role> roles=utilisateur.getRoles();
	    for(Role r:roles) {
	    
	    	if(r.getRoleName().equals("PAS")) {
	    		System.out.println("ok " );
	    		System.out.println(r.getRoleName() );
	    		System.out.println(role );
	    		Role role1=new Role(r.getRoleName());
	    		 roleRepository.delete(role1);	
	    	}
	    	
	    }
	    
	    //utilisateurs.add(utilisateur);
		
		//utilisateur.setRoles(roles);
		//role1.setUsers(utilisateurs);
		//role2.setUsers(utilisateurs);

	  
	}





	public void enregistrerDon_nature(Nature nature) {
		nature.setDate(new Date());
	natureRepository.save(nature);
		
	}

	public void enregistrerDon_espece(Espece espece) {
		espece.setDate(new Date());
		especeRepository.save(espece);
		
	}


	public List<Nature> listerNatures() {
		
		return natureRepository.findAll();
	}

	public List<Espece> listerEspeces() {
		
		return especeRepository.findAll();
	}
	
	
	/*
	 * String name = SecurityContextHolder.getContext().getAuthentication();
	 * 
	 * try { authenticationManager.authenticate(new
	 * UsernamePasswordAuthenticationToken(name, oldPassword)); // Update password
	 * here with your dao } catch (AuthenticationException e) { // Old password was
	 * wrong }
	 */
}

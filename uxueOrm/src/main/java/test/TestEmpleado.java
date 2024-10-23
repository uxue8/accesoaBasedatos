package test;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.uxueOrm.model.Empleado;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

public class TestEmpleado {
	
	//@PersistenceContext(unitName= "Persistencia")
	private static EntityManager manager;
	
	private static EntityManagerFactory emf;

	public static void main(String[] args) {
	
		
		emf = Persistence.createEntityManagerFactory("Persistencia");
		
		manager =emf.createEntityManager();
		
		
		//Insert
		
		
		Empleado e= new Empleado(1L,"rodriguez","Loki",new GregorianCalendar(1991,6,6).getTime());
		
		manager.getTransaction().begin();
		manager.persist(e);
		manager.getTransaction().commit();
		
		//En el from hay que poner el nombre de la clase NO el nombre de la tabla de la base de datos
		
		List <Empleado> empleados =(List<Empleado>) manager.createQuery("FROM Empleado").getResultList();
		
		System.out.println("En esta base de datos hay" +empleados.size());
		
		

	}

}

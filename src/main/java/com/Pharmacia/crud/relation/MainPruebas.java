package com.Pharmacia.crud.relation;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.Pharmacia.crud.model.Product;
import com.Pharmacia.crud.model.ProductDetails;
import com.Pharmacia.crud.model.Category;
import com.Pharmacia.crud.model.User;
import com.Pharmacia.crud.config.HibernateUtil;

public class MainPruebas {
	
	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;
		// EN UN TRY CATCH FINALLY
		try {
			// ABRIMOS SESIÓN
			session = HibernateUtil.getSessionFactory().openSession();
			//OBTENEMOS LOS DATOS DE LA TRANSACCIÓN
			transaction = session.getTransaction();
			//INICIALIZAMOS EL PROCESO
			transaction.begin();

			//GUARDAMOS LOS DATOS EN LA BASE
			//OneToOne.executeRelation1To1(session);
			//OneToMany.executeRelation1ToMany(session);
			//ManyToMany.executeRelationManyToMany(session);
			//Remove
			//Remove.executeRemoveAction(session);
			//CONFIRMAMOS LA OPERACIÓN
			transaction.commit();
			//SACAMOS INFORMACIÓN POR CONSOLA
			System.out.println("Operación realizada con éxito");

		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("Transacción anulada.");
				//ALGO HA IDO MAL! HACEMOS UN ROOLBACK (ANULAMOS)
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				// CERRAMOS SESIÓN
				session.close();
			}
		}
		// DESACTIVAMOS NUESTRO OBJETO HIBERNATEUTIL
		HibernateUtil.shutdown();
	}

	

	

	
}

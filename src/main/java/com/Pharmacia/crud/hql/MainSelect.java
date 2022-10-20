package com.Pharmacia.crud.hql;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.Pharmacia.crud.config.HibernateUtil;
import com.Pharmacia.crud.model.Product;

public class MainSelect {

	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;
		// EN UN TRY CATCH FINALLY
		try {

			/* PARTE QUE ESTABA EN EL MÉTODO LIST DEL BOOKDAOIMPL */

			// Con el fin de utilizar la referencia book en minusculas
			// en otra parte de la petición
			// superior a un determinado año y usando "as book"
			// sentencia = "from Book as book where
			// book.bookDetails.publication_year>:year";
			// System.out.println(sentencia);
			// query = sessionFactory.getCurrentSession().createQuery(sentencia);
			// query.setParameter("year", 1880);
			// y inferior a usando Book book que es lo mismo que as book

//			sentencia = "from Book book where book.bookDetails.publication_year < :year";
//			System.out.println(sentencia);	
//			query = sessionFactory.getCurrentSession().createQuery(sentencia);
//			query.setParameter("year", 1880);

			// Que empieza por: Les

//			sentencia = "from Book book where book.title like :cadena ";
//			System.out.println(sentencia);	
//			query = sessionFactory.getCurrentSession().createQuery(sentencia);
//			query.setParameter("cadena", "Les%");

			// Que acaba por: es

//			sentencia = "from Book book where book.title like :cadena ";
//			System.out.println(sentencia);	
//			query = sessionFactory.getCurrentSession().createQuery(sentencia);
//			query.setParameter("cadena", "%es");

			// Que contenga las letras "de"

//			sentencia = "from Book book where book.title like :cadena ";
//			System.out.println(sentencia);	
//			query = sessionFactory.getCurrentSession().createQuery(sentencia);
//			query.setParameter("cadena", "%de%");

			// Estrictamente igual a
			// sentencia = "from Book book where book.author=:name ";
			// System.out.println(sentencia);
			// query = sessionFactory.getCurrentSession().createQuery(sentencia);
			// query.setParameter("name", "François Rabelais");

			// ABRIMOS SESIÓN
			session = HibernateUtil.getSessionFactory().openSession();
			// OBTENEMOS LOS DATOS DE LA TRANSACCIÓN
			transaction = session.beginTransaction();
			Query<?> query;
			List<?> list;

			// FUENTES:
			// https://www.javatutoriales.com/2010/05/hibernate-parte-8-hql-segunda-parte.html

			/* CASE SENSITIVITY */
			// SACAMOS INFORMACIÓN POR CONSOLA
			System.out.println("REGISTROS FILTRADOS CON SELECT");
			System.out.println("**Todos los libros de François Rabelais**");
			query = session.createQuery("from Book book where book.author=:name ");
			query.setParameter("name", "François Rabelais");
			list = query.list();
			sacaLista(list);
			System.out.println("**************ALGO MÁS DE ELEGANCIA***************************");
			/* USO DE SELECT */
			// Una foma más elegante de hacer la consulta:
			query = session.createQuery("select b from Book b where b.title like :cadena").setParameter("cadena",
					"%es%");
			list = query.list();
			sacaLista(list);
			System.out.println("****************PASAR UN SELECT A MAYUSCULAS*************************");

			query = session.createQuery("select upper(b.title) from Book b where b.title like :cadena")
					.setParameter("cadena", "%es%");
			list = query.list();
			sacaListaSelect(list);
			System.out.println("****************PASAR UN SELECT CON VARIAS COLUMNAS*************************");

			query = session.createQuery("select new List(b.title, b.author, b.id) from Book b");
			list = query.list();
			sacaListaSelect(list);
			/* USO DE IN() */
			System.out.println(
					"****************SACAR UNA SERIE DE TÍTULOS SEGÚN UNA LISTA DE AUTORES*************************");
			query = session.createQuery("from Book b where b.author in('George Sand','François Rabelais')");
			list = query.list();
			sacaLista(list);
			/* ORDER BY */
			System.out.println(
					"****************ORDENAR LA SERIE DE TÍTULOS SEGÚN UNA LISTA DE AUTORES*************************");
			query = session
					.createQuery("from Book b where b.author in('George Sand','François Rabelais') ORDER BY b.title");
			list = query.list();
			sacaLista(list);
			/* NOT NULL Y ASÍ INCLUIR LA CLASE CATEGORY */
			System.out.println("*****************SACAR LOS IS NOT NULL************************");
			query = session.createQuery("from Book b where b.category IS NOT NULL");
			list = query.list();
			sacaLista(list);
			/* USO DE GROUP BY */
			System.out.println("****************AGRUPAR la consulta*************************");
			query = session.createQuery("from Book b where b.category IS NOT NULL GROUP BY b.category");
			list = query.list();
			sacaGrupo(list);
			/* FUNCIONES DE AGREGACIÓN */
			System.out.println("********************MEDIA DE AÑO DE PUBLICACIÓN*********************");
			double mediaPublicacion = (double) session.createQuery("select AVG(publication_year) from BookDetails bd")
					.uniqueResult();
			System.out.println(mediaPublicacion + " de media");
			System.out.println("***************NÚMERO DE PÁGINAS**************************");
			long sumPages = (long) session.createQuery("select SUM(page_number) from BookDetails bd").uniqueResult();
			System.out.println(sumPages + " páginas");
			System.out.println("*****************NÚMERO DE LIBROS************************");
			long countLibros = (long) session.createQuery("select count(*) from BookDetails bd").uniqueResult();
			System.out.println(countLibros + " libros");
			System.out.println("********************NÚMERO DE AUTORES*********************");
			long autoresNum = (long) session.createQuery("select count(distinct b.author) from Book b").uniqueResult();
			System.out.println(autoresNum + " autores");
			/* PRUEBA CON CONSULTAS COMPUESTAS */
			System.out.println("********************LOS LIBROS COMPRADO HACE POCO*********************");
			int recien = (int) session.createQuery("select max(bd.purchase_year) FROM BookDetails bd").uniqueResult();

			System.out.println(recien + " es le último año durante el cual se han comprado libros");
			System.out.println("********************LOS LIBROS MÁS RECIENTES*********************");
			query = session.createQuery("from Book b where b.bookDetails.purchase_year = :ultimo");
			query.setParameter("ultimo", recien);
			list = query.list();
			sacaLista(list);
			/* PRUEBA CON SUBCONSULTAS */
			System.out.println("********************TODO EN UNO*********************");
			query = session.createQuery(
					"from Book b where b.bookDetails.purchase_year = (select max(bd.purchase_year) FROM BookDetails bd)");
			list = query.list();
			sacaLista(list);

			// INNER JOIN

			/*
			 * OJO CON LO QUE SIGUE:
			 * 
			 * 
			 * INNER JOIN: Devuelve todas las filas cuando hay al menos una coincidencia en
			 * ambas tablas. LEFT JOIN: Devuelve todas las filas de la tabla de la
			 * izquierda, y las filas coincidentes de la tabla de la derecha.
			 */
			System.out.println("********************LOS LIBROS DE LUDO*********************");
			query = session.createQuery("SELECT book FROM Book book inner join book.users user ORDER BY user.id asc");
			list = query.list();
			sacaLista(list);

			session.getTransaction().commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("Consulta imposible.");
				// ALGO HA IDO MAL! HACEMOS UN ROOLBACK (ANULAMOS)
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

	private static void sacaLista(List<?> list) {
		for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();

			System.out.println("");
			System.out.println("ID: " + product.getId());
			System.out.println("Título: " + product.getTitle());
			System.out.println("Autor: " + product.getCode());
			System.out.println("");
		}
	}

	private static void sacaGrupo(List<?> list) {
		for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {

			Product product = (Product) iterator.next();

			System.out.println("");
			System.out.println("Categoría: " + product.getCategory().getCategory_name());
			System.out.println("");

		}
	}

	private static void sacaListaSelect(List<?> list) {
		for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
			System.out.println(iterator.next());
		}
	}

}

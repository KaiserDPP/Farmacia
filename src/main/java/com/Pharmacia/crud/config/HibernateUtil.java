package com.Pharmacia.crud.config;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.Pharmacia.crud.model.Product;
import com.Pharmacia.crud.model.ProductDetails;
import com.Pharmacia.crud.model.Category;
import com.Pharmacia.crud.model.User;

import static org.hibernate.cfg.Environment.*;

public class HibernateUtil {
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;

	// https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/3-ways-to-build-a-Hibernate-SessionFactory-in-Java-by-example
	public static SessionFactory getSessionFactory() {

		/*
		 * El objetivo es la creación de una instancia de sesión. 
		 * Por lo general, la aplicación tiene una sola instancia de SessionFactory y las
		 * solicitudes de cliente de servicios de subprocesos obtienen instancias de
		 * sesión de esta fábrica.
		 * 
		 * El estado interno de un SessionFactory es inmutable. Una vez creado, se
		 * establece este estado interno. Este estado interno incluye todos los
		 * metadatos encargados de realizar el Mapeo Objecto Relational.
		 * 
		 */
		if (sessionFactory == null) {
			try {
				StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

				Map<String, String> settings = new HashMap<>();
				settings.put(DRIVER, "com.mysql.cj.jdbc.Driver");
				settings.put(URL,
						"jdbc:mysql://localhost:3306/springbdd?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
				settings.put(USER, "root");
				settings.put(PASS, "");
				settings.put(SHOW_SQL, "true");
				settings.put(HBM2DDL_AUTO, "update");

				registry = registryBuilder.applySettings(settings).build();

				MetadataSources sources = new MetadataSources(registry).addAnnotatedClass(Product.class)
						.addAnnotatedClass(ProductDetails.class).addAnnotatedClass(Category.class)
						.addAnnotatedClass(User.class);

				/*
				 * Punto de entrada para trabajar con fuentes de metadatos Damos una fuente a la
				 * clase Hibernateabout y luego llamamos al método getMetadataBuilder() para
				 * customizar las fuentes procesadas.
				 */
				Metadata metadata = sources.getMetadataBuilder().build();

				sessionFactory = metadata.getSessionFactoryBuilder().build();
			} catch (Exception e) {
				System.out.println("Ha habido un problema durante la creación de la SessionFactory");
				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);
				}
			}
		}
		return sessionFactory;
	}

	public static void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
}

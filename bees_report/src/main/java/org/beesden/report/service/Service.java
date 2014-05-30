package org.beesden.report.service;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class Service<T> {

	@Autowired
	protected SessionFactory sessionFactory;

	public String tableName;

	public Service() {
	}

	public Service(String tableName) {
		this.tableName = tableName;
	}

	public Long count(String dbQuery) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT COUNT(*) FROM " + tableName + " p " + dbQuery);
		return (Long) query.uniqueResult();
	}

	public List<T> findAll(String dbQuery) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM " + tableName + " p " + dbQuery);
		return query.list();
	}

	public T findOne(String dbQuery) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM " + tableName + " p " + dbQuery);
		return (T) query.setMaxResults(1).uniqueResult();
	}

	public List<T> findPaged(String dbQuery, Map<String, Integer> pagination) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM " + tableName + " p " + dbQuery);
		return query.setFirstResult(pagination.get("first")).setMaxResults(pagination.get("results")).list();
	}

	public String getQuery(String id, String name, Integer status, String sort) {
		// Generate dbQuery string
		String dbSearch = " WHERE " + getStatus("p", status);
		if (id != null && !id.isEmpty()) {
			dbSearch += " AND p.id = " + id;
		}
		if (name != null && !name.isEmpty()) {
			dbSearch += " AND p.name = '" + name + "'";
		}
		if (sort != null && !sort.isEmpty()) {
			dbSearch += " ORDER BY p." + sort.replaceAll("_", " ");
		}
		return dbSearch;
	}

	public String getQueryPaged(String link, String id, Integer status, String sort) {
		String dbQuery = "  JOIN p." + link + " c WHERE c.id = " + id + " AND " + getStatus("p", status);
		if (sort != null && !sort.isEmpty()) {
			dbQuery += " ORDER BY p." + sort.replaceAll("_", " ");
		}
		return dbQuery;
	}

	protected String getStatus(String s, Integer status) {
		s += ".status";
		status = status == null ? -1 : status;
		if (status == 0) {
			s = s + " = 1 OR " + s + " = 2";
		} else if (status > 0) {
			s = s + " = " + status;
		} else {
			s = s + " > 0";
		}
		return " (" + s + ")";
	}

	public Boolean isNameAvailable(String name, Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM " + tableName + " p WHERE name = '" + name + "'" + (id != null ? " AND id != '" + id.toString() + "'" : ""));
		return query.list().isEmpty();
	}

	public T objectCreate(T o) {
		// Create new object
		Session session = sessionFactory.getCurrentSession();
		session.save(tableName, o);
		session.flush();
		return o;
	}

	public T objectRemove(T o) {
		// Delete existing object
		Session session = sessionFactory.getCurrentSession();
		session.delete(tableName, o);
		return o;
	}

	public T objectUpdate(T o) {
		// Update existing object
		Session session = sessionFactory.getCurrentSession();
		session.merge(tableName, o);
		session.flush();
		return o;
	}
}

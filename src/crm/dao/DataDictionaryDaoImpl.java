package crm.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import crm.domain.DataDictionary;

public class DataDictionaryDaoImpl extends HibernateDaoSupport implements
		DataDictionaryDao {

	@Override
	public List<DataDictionary> findAllDataDictionary() {
		List<DataDictionary> dataDictionaryList = (List<DataDictionary>) getHibernateTemplate().find("from DataDictionary", null);
		return dataDictionaryList;
	}

}

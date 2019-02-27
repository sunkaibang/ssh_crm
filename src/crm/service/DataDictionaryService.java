package crm.service;

import java.util.List;

import crm.dao.DataDictionaryDao;
import crm.domain.DataDictionary;

public class DataDictionaryService {
	private DataDictionaryDao dataDictionaryDao;

	public void setDataDictionaryDao(DataDictionaryDao dataDictionaryDao) {
		this.dataDictionaryDao = dataDictionaryDao;
	}

	public List<DataDictionary> findAllDataDictionary() {
		return dataDictionaryDao.findAllDataDictionary();
	}

	
}

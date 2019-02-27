package crm.dao;

import java.util.List;

import crm.domain.Visit;

public interface VisitDao {

	void addVisit(Visit visit);

	Integer findVisitSize();

	List<Visit> findVisitByPage(Integer begin, Integer pageCount);

	void deleteVisit(Visit visit);

	Visit findVisitById(Integer vid);

	void modifyVisit(Visit visit);

	Integer findVisitSizeByCondition(Visit visit);

	List<Visit> findVisitByPageAndCondition(Visit visit, int begin, int pageCount);

}

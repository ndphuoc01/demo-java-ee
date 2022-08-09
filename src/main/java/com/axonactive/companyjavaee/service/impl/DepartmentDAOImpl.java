package com.axonactive.companyjavaee.service.impl;

import com.axonactive.companyjavaee.entity.Department;
import com.axonactive.companyjavaee.rest.request.DepartmentRequest;
import com.axonactive.companyjavaee.service.dao.DepartmentDAO;
import com.axonactive.companyjavaee.service.mapper.DepartmentMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import java.util.List;

@Stateless
public class DepartmentDAOImpl implements DepartmentDAO {
    @PersistenceContext(name = "Company")
    EntityManager em;

    @Inject
    DepartmentMapper departmentMapper;

    @Override
    public List<Department> findAll() {
        Query allDepartmentQuery = em.createQuery("SELECT d FROM Department d", Department.class);
        return allDepartmentQuery.getResultList();
    }

    @Override
    public Department save(DepartmentRequest departmentRequest) {

//        Department department = new Department();
//        department.setDepartmentName(departmentRequest.getDepartmentName());
//        department.setStartDate(departmentRequest.getStartDate());
//        this.em.persist(department);
//        this.em.flush();
//        return findByDepartmentName(departmentRequest.getDepartmentName());

        Department department = new Department();
        department.setDepartmentName(departmentRequest.getDepartmentName());
        department.setStartDate(departmentRequest.getStartDate());
        department = this.em.merge(department);
        return department;

    }

    @Override
    public Department update(Integer id, DepartmentRequest departmentRequest) {
        Department updateDepartment = findById(id);
        updateDepartment.setDepartmentName(departmentRequest.getDepartmentName());
        updateDepartment.setStartDate(departmentRequest.getStartDate());
        updateDepartment = this.em.merge(updateDepartment);
        return updateDepartment;
    }

    @Override
    public void flush() {
        this.em.flush();
    }

    @Override
    public void removeEntity(Department department) {
        this.em.remove(department);
    }

    @Override
    public Department findById(Integer deptId) {
        List<Department> findByIdQuery = em.createQuery(
                        "SELECT d FROM Department d " +
                                "WHERE d.departmentId = :deptid", Department.class)
                .setParameter("deptid", deptId).getResultList();
        if (findByIdQuery.isEmpty())
            throw new NoResultException("No department found");
        else return findByIdQuery.get(0);

    }


    @Override
    public void remove(Integer id) {
        Department department = findById(id);
        if (null != department) {
            this.em.remove(department);
        }
    }

    @Override
    public Department findByDepartmentName(String name) {
        List<Department> findByIdQuery = em.createQuery(
                        "SELECT d FROM Department d " +
                                "WHERE d.departmentName = :deptname", Department.class)
                .setParameter("deptname", name).getResultList();
        if (findByIdQuery.isEmpty())
            throw new NoResultException("No department found");
        else return findByIdQuery.get(0);
    }


}

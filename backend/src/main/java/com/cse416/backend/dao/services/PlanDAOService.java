
package com.cse416.backend.dao.services;
import com.cse416.backend.dao.repositories.PlanRepository;
import org.springframework.stereotype.*;

import com.cse416.backend.model.plan.Plan;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.lang.Integer;



@Service
public class PlanDAOService{

   @Autowired
   private PlanRepository planRepository;

   public Optional<Plan> getPlanById(Integer Id){
       return planRepository.findById(Id);
   }

   public List<Plan> getPlansByJobId(Integer jobId){
        return planRepository.findByJobId(jobId);
    }

   public void addPlan(Plan plan){
       planRepository.save(plan);
   }

   public void updatePlan(Plan plan){
       planRepository.save(plan);
   }

   public void deletePlan(Plan plan){
       planRepository.delete(plan);
   }

   public void deletePlanById(Integer Id){
       planRepository.deleteById(Id);
   }

   public Long numberPlanEntities(){
       return planRepository.count();
   }
}
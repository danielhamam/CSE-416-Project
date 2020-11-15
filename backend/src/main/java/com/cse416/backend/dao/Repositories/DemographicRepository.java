package com.cse416.backend.dao;

import com.cse416.backend.model.regions.Demographic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.*;
import java.lang.Integer;

@Repository
public interface DemographicRepository extends CrudRepository<Demographic, Integer>{}
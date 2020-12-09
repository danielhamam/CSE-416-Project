package com.cse416.backend.model.regions.district.comparators;

import com.cse416.backend.model.regions.district.District;

import java.util.Comparator;

public class WhitePopulationCompare implements Comparator<District> {
    public int compare(District d1, District d2)
    {
        if (d1.getDemographic().getWhitePopulation() < d2.getDemographic().getWhitePopulation())
            return 1;
        if (d1.getDemographic().getWhitePopulation() > d2.getDemographic().getWhitePopulation())
            return -1;
        else return 0;
    }
}

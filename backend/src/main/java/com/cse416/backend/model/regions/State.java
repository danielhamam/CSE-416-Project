package com.cse416.backend.model.regions;

import com.cse416.backend.model.Plan;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.geojson.FeatureCollection;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.io.File;


public class State {
    private String stateName;
    private String stateAbbreviation;
    private int stateFIPSCode;
    private int totalPopulation;
    private Plan enactedPlan;
    @JsonIgnore
    private Precinct [] statePrecincts;
    @JsonIgnore
    private Boundary boundary;
    @JsonIgnore
    private File precinctFile;
    @JsonIgnore
    private File stateFile;
    @JsonIgnore
    private FeatureCollection precinctsGeoJson;
    @JsonIgnore
    private FeatureCollection stateGeoJson;

    public State(String stateName, String stateAbbreviation, int stateFIPSCode, int totalPopulation){
        this.stateName = stateName;
        this.stateAbbreviation = stateAbbreviation;
        this.stateFIPSCode = stateFIPSCode;
        this.totalPopulation = totalPopulation;
        String precinctFilePath = "src/main/resources/system/states/" +
                stateAbbreviation.toLowerCase() + "/Precincts.json";
        String boundaryFilePath = "src/main/resources/system/states/" +
                stateAbbreviation.toLowerCase() + "/StateBoundaries.json";
        this.precinctFile = new File(new File(precinctFilePath).getAbsolutePath());
        this.stateFile = new File(new File(boundaryFilePath).getAbsolutePath());
        this.enactedPlan = new Plan(stateAbbreviation,"Enacted","0",57,true);
        try{
            this.precinctsGeoJson = createPrecinctFeatureCollection();
        }
        catch(IOException error){
            error.printStackTrace();
        }
    }

    public FeatureCollection createPrecinctFeatureCollection()throws IOException {
        return new ObjectMapper().readValue(this.precinctFile, FeatureCollection.class);
    }
    
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public int getStateFIPSCode() {
        return stateFIPSCode;
    }

    public void setStateFIPSCode(int stateFIPSCode) {
        this.stateFIPSCode = stateFIPSCode;
    }

    public Plan getEnactedPlan() {
        return enactedPlan;
    }

    public void setEnactedPlan(Plan enactedPlan) {
        this.enactedPlan = enactedPlan;
    }

    public Precinct[] getStatePrecincts() {
        return statePrecincts;
    }

    public void setStatePrecincts(Precinct[] statePrecincts) {
        this.statePrecincts = statePrecincts;
    }

    public Boundary getBoundary() {
        return boundary;
    }

    public void setBoundary(Boundary boundary) {
        this.boundary = boundary;
    }

    // public Map<String, Object>  getClientInitialData(){
    //     Map<String, Object> clientState = new HashMap<>();
    //     clientState.put("stateName", this.stateName);
    //     clientState.put("stateAbbreviation", this.stateAbbreviation);
    //     clientState.put("stateFIPSCode", this.stateFIPSCode);
    //     clientState.put("numOfPrecincts", 10);
    //     clientState.put("numOfCounties", 10);
    //     clientState.put("enactedPlan", this.enactedPlan.getClientInitialData());
    //     clientState.put("demographic", this.demographic);
    //     return clientState;
    // }

    @Override
    public String toString() {
        return "State{" +
                "stateName='" + stateName + '\'' +
                ", stateAbbreviation='" + stateAbbreviation + '\'' +
                ", stateFIPSCode=" + stateFIPSCode +
                ", enactedPlan=" + enactedPlan +
                ", statePrecincts=" + Arrays.toString(statePrecincts) +
                ", boundary=" + boundary +
                '}';
    }
}
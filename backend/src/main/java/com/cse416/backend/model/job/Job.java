package com.cse416.backend.model.job;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.cse416.backend.model.demographic.CensusEthnicity;
import com.cse416.backend.model.enums.CensusCatagories;
import com.cse416.backend.model.enums.ClientCompactness;
import com.cse416.backend.model.enums.JobStatus;
import com.cse416.backend.model.job.boxnwhisker.BoxWhisker;
import com.cse416.backend.model.regions.district.District;
import com.cse416.backend.model.regions.state.State;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.cse416.backend.model.plan.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;


import javax.persistence.*;

import java.lang.*;
import java.util.*;

@Entity
@Table(name = "Jobs")
public class Job{

    @Id
    @GeneratedValue
    @Column(name = "jobId")
    @JsonProperty("jobID")
    private Integer generatedId;

    @Column(nullable=true)
    @JsonProperty("jobName")
    private String jobName;

    @Column(name = "compactness")
    @JsonProperty("compactness")
    private ClientCompactness clientCompactness;

    @JsonProperty("populationDifference")
    private double populationDifference;

    @Column(name = "numberOfPlans")
    @JsonProperty("plansAmount")
    private int numDistrictingPlan;

    @Column(name = "numberOfDistricts")
    @JsonProperty("districtsAmount")
    private int numOfDistricts;

    @Column(name = "jobStatus")
    @JsonProperty("status")
    private JobStatus status;

    @Transient
    @JsonProperty("minorityAnalyzed")
    private List<CensusCatagories> minorityAnalyzedEnumration;

    @Transient
    @JsonProperty("districtPlans")
    private List<Plan> clientDistrictingPlans;

    @ManyToMany(targetEntity=CensusEthnicity.class,cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, mappedBy="job")
    @JoinTable(
            name = "JobMinorityGroups",
            joinColumns = @JoinColumn(name = "jobId"),
            inverseJoinColumns = @JoinColumn(name = "censusEthnicityId")
    )
    @JsonIgnore
    private List<CensusEthnicity> minorityAnalyzed;

    @ManyToOne(targetEntity=State.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="stateId")
    @JsonIgnore
    private State state;

    @Transient
    @JsonIgnore
    private Plan averageDistrictPlan;

    @Transient
    @JsonIgnore
    private Plan extremeDistrictPlan;

    @Transient
    @JsonIgnore
    private Plan randomDistrictPlan;

    @Transient
    @JsonIgnore
    private int stateFIPSCode;

    @Transient
    @JsonIgnore
    private int averagePlanPopulation;

    @Transient
    @JsonIgnore
    private int averagePlanCompactness;

    @Transient
    @JsonIgnore
    private String seawulfJobID;

    @Transient
    @JsonIgnore
    private String jobSummary;

    
    @JsonIgnore
    @OneToMany(targetEntity=Plan.class,cascade = CascadeType.ALL,
    fetch = FetchType.LAZY, orphanRemoval = true, mappedBy ="job")
    private List <Plan> allPlans;

    @Transient
    @JsonIgnore
    private BoxWhisker boxWhisker;

    
    protected Job (){}

    public Job (@JsonProperty("jobName")String jobName, 
                @JsonProperty("districtsAmount")int numOfDistricts, 
                @JsonProperty("plansAmount")int numDistrictingPlan, 
                @JsonProperty("populationDifference")double populationDifference, 
                @JsonProperty("compactness")ClientCompactness clientCompactness, 
                @JsonProperty("minorityAnalyzed") List<CensusCatagories> minorityAnalyzedEnumration){
        //TODO: Format the information to be consistant with frontend and database
        System.out.println("Job spring");
        this.jobName = jobName;
        this.seawulfJobID = "0";
        this.numOfDistricts = numOfDistricts;
        this.numDistrictingPlan = numDistrictingPlan;
        this.clientCompactness = clientCompactness;
        this.populationDifference = populationDifference;
        this.minorityAnalyzedEnumration = minorityAnalyzedEnumration;
        this.status = JobStatus.PENDING;
       // this.clientStatus = status.getStringRepresentation();
//        for (CensusCatagories censusCatagories : minorityAnalyzed) {
//            this.clientMinorityAnalyzed.add(censusCatagories.getStringRepresentation());
//        }
    }

    public Integer getJobID() {
        return generatedId;
    }

    public void setJobID(Integer jobID) {
        this.generatedId = jobID;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public String getSeawulfJobID() {
        return seawulfJobID;
    }

    public void setSeawulfJobID(String seawulfJobID) {
        this.seawulfJobID = seawulfJobID;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getNumOfDistricts() {
        return numOfDistricts;
    }

    public void setNumOfDistricts(int numOfDistricts) {
        this.numOfDistricts = numOfDistricts;
    }

    public int getNumDistrictingPlan() {
        return numDistrictingPlan;
    }

    public void setNumDistrictingPlan(int numDistrictingPlan) {
        this.numDistrictingPlan = numDistrictingPlan;
    }

    public List<CensusCatagories> getMinorityAnalyzedEnumration(){
        return this.minorityAnalyzedEnumration;
    }

    public void setMinorityAnalyzed(List<CensusEthnicity> minorityAnalyzed){
        this.minorityAnalyzed = minorityAnalyzed;
    }

    public ClientCompactness getClientCompactness() {
        return clientCompactness;
    }

    public void setClientCompactness(ClientCompactness clientCompactness) {
        this.clientCompactness = clientCompactness;
    }

    public double getPopulationDifference() {
        return populationDifference;
    }

    public void setPopulationDifference(double populationDifference) {
        this.populationDifference = populationDifference;
    }

    @JsonIgnore
    public Plan getPlanByID(String planID){
        if(planID.equals(averageDistrictPlan.getPlanID())){
            return averageDistrictPlan;
        }
        else if(planID.equals(extremeDistrictPlan.getPlanID())) {
            return extremeDistrictPlan;
        }
        else if(planID.equals(randomDistrictPlan.getPlanID())) {
            return randomDistrictPlan;
        }
        else{

        }
        return null;

    }

    public void processAlgorithmOutput(File algorithmOutputFile){
        //Create ObjectMapper object
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(algorithmOutputFile);
            JsonNode plansNode = rootNode.get("plans");
            for(JsonNode node : plansNode){

                //Create plan
                int averageDistrictPopulation = node.get("averageDistrictPopulation").asInt();
                int averageDistrictCompactness = node.get("averageDistrictPopulation").asInt();
                Plan plan = new Plan(this, numOfDistricts,
                        averageDistrictPopulation, averageDistrictCompactness);


                //Create districts for the plan
                List <District> districts = new ArrayList<>();
                JsonNode algorithmNode = plansNode.get("algorithmData");
                int districtElement = 0;
                for(JsonNode nodeE : algorithmNode){
                    //TODO: Figure out find counties and precints. How would you do that?
                    // How would the Job object access the database?
                    District tempDistricts =  new District(districtElement, state, plan, null, null);
                    districts.add(tempDistricts);
                    districtElement++;
                }
                plan.setDistricts(districts);

                //








            }
        }
        catch (IOException error){

        }

        //TODO:39 Generate a summary file of each job (required)
    }

    @JsonIgnore
    public Map<String, Object> getClientPlans(){
        Map<String, Object> clientJob = new HashMap<>();
        clientJob.put("averageDistrictPlan", this.averageDistrictPlan);
        clientJob.put("extremeDistrictPlan", this.extremeDistrictPlan);
        clientJob.put("randomDistrictPlan", this.randomDistrictPlan);
        return clientJob;
    }

    @Override
    public String toString() {
        return "Job{" +
                ", jobName='" + jobName + '\'' +
                ", state='" + state.getStateAbbreviation() + " Object" + '\'' +
                ", jobID=" + generatedId +
                ", seawulfJobID=" + seawulfJobID +
                ", status=" + status +
                ", clientCompactness=" + clientCompactness +
                ", populationDifference=" + populationDifference +
                ", numOfDistricts=" + numOfDistricts +
                ", numDistrictingPlan=" + numDistrictingPlan +
                ", jobSummary='" + jobSummary + '\'' +
                ", allPlans=" + allPlans +
                ", averageDistrictPlan=" + averageDistrictPlan +
                ", extremeDistrictPlan=" + extremeDistrictPlan +
                ", randomDistrictPlan=" + randomDistrictPlan +
                ", averagePlanPopulation=" + averagePlanPopulation +
                ", averagePlanCompactness=" + averagePlanCompactness +
                ", minorityAnalyzedEnumaration=" + minorityAnalyzedEnumration +
                ", minorityAnalyzed=" + minorityAnalyzed +
                '}';
    }
}


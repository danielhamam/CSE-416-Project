package com.cse416.backend.model.job.summary;

import java.lang.Integer;

// @Entity
// @Table(name="File")
public class File {

    // @Id
    // @GeneratedValue
    private Integer fileId;

    // @Column(nullable=false, length=1000)
    private String filePath;

    //Necessary for JPA
    // protected File(){}

    public File(String filePath){
        this.filePath = filePath;
    }

    public String getFilePath(){return this.filePath;}

    public Integer getId(){return this.fileId;}

    public String toString(){ return "summary id: " + this.fileId.toString() + " summary path: " + this.filePath;}

}
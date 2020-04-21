package com.example.revalform;

import com.google.firebase.firestore.Exclude;

public class RevalData {
    private String documentId;
    private String usn;
    private String name;
    private String[] courses;

    public RevalData() {
        //public no-arg constructor needed
    }

    @Exclude
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public RevalData(String usn, String name,String[] courses) {
        this.usn = usn;
        this.name = name;
        this.courses=courses;
    }

    public String getUsn() {
        return usn;
    }

    public String getName() {
        return name;
    }

    public String[] getCourses(){
        return courses;
    }
}

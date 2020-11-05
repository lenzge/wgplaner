package de.hdm_stuttgart_mi.CleaningShedule;

public class Task {

    private String content;
    private boolean finished;
    //private currentRoommate responsible;


    public void setContent(String content){
        this.content = content;
    }

    public String getContent(){
        return content;
    }

    public void setFinished(boolean finished){
        this.finished = finished;
    }

    public boolean getFinished(){
        return finished;
    }

    //public void setResponsible(currentRoommate responsible){ this.responsible = responsible}

    //public CurrentRoommate getResponsible(){ return responsible; }


    public Task(String content) {
        this.content = content;
        finished = false;

    }
}

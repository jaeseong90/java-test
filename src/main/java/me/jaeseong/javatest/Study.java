package me.jaeseong.javatest;
public class Study {

    private StudyStatus studyStatus;
    private int limit;

    public Study(){
    }

    public Study(int limit) {
        if(limit < 0){
            throw new IllegalArgumentException("limit 은 0보다 작을 수 없습니다.");
        }
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public StudyStatus getStudyStatus() {
        return studyStatus;
    }
}


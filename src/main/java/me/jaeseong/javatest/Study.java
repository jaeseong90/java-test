package me.jaeseong.javatest;
public class Study {

    private StudyStatus studyStatus;
    private String title;
    private int limit;

    public Study(){
    }

    public String getTitle() {
        return title;
    }

    public Study(String title, int limit) {
        this.title = title;
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "Study{" +
                "studyStatus=" + studyStatus +
                ", title='" + title + '\'' +
                ", limit=" + limit +
                '}';
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


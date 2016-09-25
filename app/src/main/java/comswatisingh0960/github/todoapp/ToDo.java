package comswatisingh0960.github.todoapp;

/**
 * Created by Swati on 9/22/2016.
 */
import com.orm.SugarRecord;


public class ToDo extends SugarRecord {
    private String taskName;
    private String status;
    private int idno;

    public ToDo()
    {
        this.taskName=null;
        this.status=null;
    }
    public ToDo(String taskName, String status,int idno) {
        super();
        this.taskName = taskName;
        this.status = status;
        this.idno = idno;
    }

    public int getIdno() {
        return idno;
    }
    public void setIdno(int idno) {
        this.idno = idno;
    }
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}

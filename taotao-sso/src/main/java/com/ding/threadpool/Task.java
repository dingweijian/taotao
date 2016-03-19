package com.ding.threadpool;

import java.util.ArrayList;
import java.util.List;

/* 所有任务的接口 */
public class Task implements Runnable {

	public long _taskID = 0;
    public Task(){
        
    }
    
    public Task(long task_id){
        _taskID = task_id;
        List<String> list = new ArrayList<>();
    }
    
    public void setTaskId(long task_id){
        _taskID = task_id;
    }
    
    public long getTaskId(){
        return _taskID;
    }
    
    public void run(){
        System.out.printf("test\r\n");
    }
}

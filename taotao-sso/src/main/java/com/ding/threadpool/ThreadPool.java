package com.ding.threadpool;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

public class ThreadPool {

	public static long _next_task_id = 0;
    public static final int DEFAULT_THREAD_NUM = 0xa;//10
    public static final int MAX_THREAD_NUM = 20;
    public int _cur_thread_num = 0;
    public boolean _is_closed = true;
    public List<Task> taskQueue = Collections.synchronizedList(new LinkedList<Task>());
    public WorkThread[] threads;
    
    public static ThreadPool _instance = null;
    
    static Logger logger = Logger.getLogger(ThreadPool.class);
    
    public ThreadPool(){
        _cur_thread_num = DEFAULT_THREAD_NUM;
        threads = new WorkThread[_cur_thread_num];
        for(int i = 0; i < _cur_thread_num; ++ i){
            threads[i] = new WorkThread(i);
        }
    }
    
    public ThreadPool(int thread_num){
        _cur_thread_num = thread_num;
        threads = new WorkThread[_cur_thread_num];
        for(int i = 0; i < _cur_thread_num; ++ i){
            threads[i] = new WorkThread(i); 
        }
    }
    
    /* singleton */
    public static ThreadPool getInstance(){
        if(_instance == null){
            synchronized(ThreadPool.class){
                if(_instance == null){
                    _instance = new ThreadPool();
                }
            }
        }
        return _instance;
    }
    
    public static long generateTaskId(){
        _next_task_id += (_next_task_id + 1) / 1000000;
        if(_next_task_id == 0) _next_task_id ++;
        return _next_task_id;
    }
    
    public void start(){
        _is_closed = false;
        for(int i = 0; i < _cur_thread_num; ++ i){
            threads[i].start();
            logger.info(String.format("thread [%d] start!", i));
        }
    }
    
    public void close(){
        if(!_is_closed){
            waitforfinish();
            _is_closed = true;
            taskQueue.clear();
        }
        logger.info("Thread pool close!");
    }
    
    public void waitforfinish(){
        synchronized(this){
            _is_closed = true;
            notifyAll();
        }
        for(int i = 0; i < _cur_thread_num; ++ i){
            threads[i].stopThread();
            logger.info(String.format("Thread [%d] stop!", i));
        }
    }
   
    public void addTask(Task new_task){
        if(_is_closed){
            throw new IllegalStateException();
        }
        synchronized(taskQueue){
            if(new_task != null){
                taskQueue.add(new_task);
                taskQueue.notifyAll();
                //taskQueue.notify();

            }
        }
    }
    
    public int getTaskCount(){
        return taskQueue.size();
    }
    
    public class WorkThread extends Thread{
        private int _index;
        private boolean _is_running = true;
        public WorkThread(int index){
            _index = index;
        }
        
        public void run(){
            while(_is_running){
                Task t = getTask();
                if(t != null){
                    t.run();
                }else{
                    // 结束线程

                    logger.info(String.format("thread [%d] exit", _index));
                    return;
                }
            }
        }
        
        public Task getTask(){
            if(_is_closed) return null;
            Task r = null;
            synchronized (taskQueue) {
                while (taskQueue.isEmpty()) {
                    try {
                        /* 任务队列为空，则等待有新任务加入从而被唤醒 */
                        taskQueue.wait();
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
                
                /* 取出任务执行 */
                r = (Task) taskQueue.remove(0);
                return r;
            }
        }
        
        public void stopThread(){
            _is_running = false;
            try{
                join();
            }catch(InterruptedException ex){
               ex.printStackTrace();
            }
        }
    }
}

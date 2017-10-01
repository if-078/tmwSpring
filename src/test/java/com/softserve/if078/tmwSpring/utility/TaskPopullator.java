package com.softserve.if078.tmwSpring.utility;

import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class TaskPopullator {

    public Task createDefaultTask (Task task){
        task.getId();
        //waiting Sveta task dao and entity
    }

    public Task getTask (){
        return null;
    }

    public class Task {
        int id;

        public int getId() {
            return id;
        }
    }
}

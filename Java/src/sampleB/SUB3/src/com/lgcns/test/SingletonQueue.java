package com.lgcns.test;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class SingletonQueue {
	private Queue<String> q = new LinkedList<>();
    private SingletonQueue(){
    }
    public static SingletonQueue getInstance(){
        return LazyHolder.INSTANCE;
    }
    private static class LazyHolder{
        private static final SingletonQueue INSTANCE = new SingletonQueue();
    }

    public Queue<String> getQueue() {
        return q;
    }
    public void remove() {
    	if(!q.isEmpty()) {
    		q.poll();
    	}
    }
    public void add(String a) {
    	q.add(a);
    }
}

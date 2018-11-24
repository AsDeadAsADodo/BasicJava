package em.lock.demo;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class WriteLockDemo {
	
	public static void main(String[] args) {
		Resource r = new Resource();
		ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
		WriteLock writeLock = readWriteLock.writeLock();
		ReadLock readLock = readWriteLock.readLock();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					writeLock.lock();
					try {
						r.setR("开炮");
					}finally {
						writeLock.unlock();
					}
					readLock.lock();
					try {
						System.out.println(r.getR());
					}finally {
						readLock.unlock();
					}
				}
			}
		},"线程1").start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					writeLock.lock();
					try {
						r.setR("开枪");
					}finally {
						writeLock.unlock();
					}
					readLock.lock();
					try {
						System.out.println("-----"+r.getR());
					}finally {
						readLock.unlock();
					}
				}
			}
		},"线程2").start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					readLock.lock();
					try {
						System.out.println("------------------"+r.getR());
					}finally {
						readLock.unlock();
					}
				}
			}
		},"线程3").start();
	}
}

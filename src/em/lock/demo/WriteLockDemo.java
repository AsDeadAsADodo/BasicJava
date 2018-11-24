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
						r.setR("����");
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
		},"�߳�1").start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					writeLock.lock();
					try {
						r.setR("��ǹ");
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
		},"�߳�2").start();
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
		},"�߳�3").start();
	}
}

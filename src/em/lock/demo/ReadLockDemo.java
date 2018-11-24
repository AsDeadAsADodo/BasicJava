package em.lock.demo;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

public class ReadLockDemo {
	public static void main(String[] args) {
		Resource r = new Resource("����Ӱ");
		ReadLock readLock = new ReentrantReadWriteLock().readLock();
		new Thread(new Runnable() {
			@Override
			public void run() {
				readLock.lock();
				try {
					System.out.println(Thread.currentThread().getName()+"-----------------------�õ��˶���");
					try {
						Thread.sleep(1000);
						System.out.println(Thread.currentThread().getName()+"==============================="+r.getR());
						throw new RuntimeException();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}finally {
					readLock.unlock();
				}
			}
		},"�߳�1").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				long start = System.currentTimeMillis();
				while(true) {
					readLock.lock();
					try {
						System.out.println(Thread.currentThread().getName()+"===="+r.getR());
						long end = System.currentTimeMillis();
						if(end-start>2000) {
							System.out.println("�߳�2����");
							break;
						}
					}finally {
						readLock.unlock();
					}
				}
			}
		},"�߳�2").start();
	}

}

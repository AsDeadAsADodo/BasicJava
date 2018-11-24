package em.lock.demo;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

public class ReadLockDemo {
	public static void main(String[] args) {
		Resource r = new Resource("看电影");
		ReadLock readLock = new ReentrantReadWriteLock().readLock();
		new Thread(new Runnable() {
			@Override
			public void run() {
				readLock.lock();
				try {
					System.out.println(Thread.currentThread().getName()+"-----------------------拿到了读锁");
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
		},"线程1").start();
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
							System.out.println("线程2结束");
							break;
						}
					}finally {
						readLock.unlock();
					}
				}
			}
		},"线程2").start();
	}

}

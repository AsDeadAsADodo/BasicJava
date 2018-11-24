package em.string;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Singleton {
	public static void main(String[] args) {
		Cpu cpu = new Cpu();
		Lock lock = new ReentrantReadWriteLock().writeLock();
		Condition condition = lock.newCondition();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					lock.lock();
					System.out.print(Thread.currentThread().getName());
					cpu.doSomething("放电影");
					lock.unlock();
				}
			}
		},"电影cpu").start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					lock.lock();
					System.out.print("--------------"+Thread.currentThread().getName());
					cpu.doSomething("--------------放歌");
					lock.unlock();
				}
			}
		},"放歌cpu").start();
		
	}
}
class Cpu{
	String dothing;
	public Cpu() {
	}
	
	public void doSomething(String something) {
		dothing = something;
		System.out.println(dothing);
	}
}
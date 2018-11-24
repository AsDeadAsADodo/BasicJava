package em.lock.demo;

public class NoLockDemo {
	public static void main(String[] args) {
		Resource r = new Resource();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					r.setR("111");
					System.out.println(r.getR());
				}
			}
		}).start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					r.setR("222");
					System.out.println(r.getR());
				}
			}
		}).start();
	}
}

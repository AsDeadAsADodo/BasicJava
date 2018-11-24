package list;

import org.junit.Test;

@SuppressWarnings("unchecked")
public class MyLinkedList {
	@Test
	public void addTest() {
		MyList<String> list = new MyList();
		System.out.println(list.get(0));
		list.add("1");
		System.out.println(list.get(0));
	}

	@Test
	public void addIndexTest() {
		MyList<String> list = new MyList();
		list.add("1");
		list.add("2");
		list.add("3");
		for (int i = 0; i < 3; i++) {
			System.out.println(list.get(i));
		}
		System.out.println("______________________________");
		list.add("4", 1);
		for (int i = 0; i < 4; i++) {
			System.out.println(list.get(i));
		}
		System.out.println("______________________________");
		list.add("500", 500);
		for (int i = 0; i < 5; i++) {
			System.out.println(list.get(i));
		}
		System.out.println("______________________________");
	}

	@Test
	public void removeTest() {
		MyList<String> list = new MyList();
		list.add("1");					
		list.add("2");
		list.add("3");
		list.add("4", 1);
		list.add("500", 500);
		//1 4 2 3 500
		list.remove(4);
		System.out.println(list.get(4));
	}
	@Test
	public void removeFirstTest() {
		MyList<String> list = new MyList();
		list.add("1");					
		list.remove(0);
		System.out.println(list.get(0));
	}

	class MyList<T> {
		private int size;
		private Node<T> first;
		private Node<T> last;

		public MyList() {
		}

//							 container     5      newNode 3
		// first 1 node 2 ... last 5
		void add(T t) {
			Node<T> container = last;
			Node<T> newNode = new Node<>(t, container, null);
			last = newNode;
			if (container == null) {
				first = newNode;
			} else {
				container.setNext(last);
			}
			size++;
		}

		@SuppressWarnings("unchecked")
		void add(T t, int index) {
			if (index > size) {
				add(t);
				size--;
			} else {
				Node<T> indexNode = indexOf(index);
				Node<T> newNode = new Node<>(t, indexNode.getPrev(), indexNode);
				indexNode.getPrev().setNext(newNode);
				indexNode.setPrev(newNode);
			}
			size++;
		}

		private Node<T> indexOf(int index) {
			Node<T> l = null;
			if (index > (size >> 1)) {
				l = last;
				for (int i = size - 1; i > index; i--) {
					l = l.getPrev();
				}
			} else {
				l = first;
				for (int i = 0; i < index; i++) {
					l = l.getNext();
				}
			}
			return l;
		}

		void remove(int index) {
			Node<T> indexOf = indexOf(index);
			Node<T> prev = indexOf.getPrev();
			Node<T> next = indexOf.getNext();
			if (next == null) {
				if(prev==first) {
					first = null;
				}
				last = prev;
			}else {
				prev.setNext(next);
				next.setPrev(prev);
			}
			size--;
			indexOf = null;
		}

		public String get(int index) {
			Node<T> indexOf = indexOf(index);
			return indexOf == null ? null : indexOf.toString();
		}
	}
}
@SuppressWarnings("unused")
class Node<T>{
	private T data;
	private Node<T> prev; 
	private Node<T> next;
	public Node(T data, Node<T> prev, Node<T> next) {
		super();
		this.data = data;
		this.prev = prev;
		this.next = next;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Node<T> getPrev() {
		return prev;
	}
	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}
	public Node<T> getNext() {
		return next;
	}
	public void setNext(Node<T> next) {
		this.next = next;
	}
	@Override
	public String toString() {
		return ""+data;
	}
}
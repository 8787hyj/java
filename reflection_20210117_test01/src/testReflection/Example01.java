package testReflection;

import java.lang.reflect.Field;

class Node01 {
// private 타입입니다.
	private int data = 0;

// 멤버 변수 설정
	public void setData(int data) {
		this.data = data;
	}

// 멤버 변수 리턴
	public int getData() {
		return this.data;
	}
}

public class Example01 {
	// 실행 함수
	public static void main(String... args) {
		try {
			// 인스턴스 생성
			Node01 node = new Node01();
			// Reflection으로 data 변수를 취득한다.
			Field field = Node01.class.getDeclaredField("data");
			// setAccessible는 private, protected도 접근 가능하게 한다.
			field.setAccessible(true);
			// data 필드에 100를 넣는다.
			field.set(node, 100);
			// 결과는 100이 나온다.
			System.out.println(node.getData());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}

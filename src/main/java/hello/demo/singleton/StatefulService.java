package hello.demo.singleton;

public class StatefulService {
    private int price; // 상태(state)를 유지하는 필드

    public void order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 이 부분이 문제가 된다.
    }

    public int getPrice(){
        return price;
    }
}

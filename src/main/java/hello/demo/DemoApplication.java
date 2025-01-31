package hello.demo;

import hello.demo.member.Grade;
import hello.demo.member.Member;
import hello.demo.member.MemberService;
import hello.demo.member.MemberServiceImpl;
import hello.demo.order.Order;
import hello.demo.order.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		MemberService memberService = ac.getBean("memberService",MemberService.class);
		OrderService orderService = ac.getBean("orderService",OrderService.class);
		
		long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member);

		Order order = orderService.createOrder(memberId, "ItemA", 10000);

		System.out.println("order = " + order);

	}
}

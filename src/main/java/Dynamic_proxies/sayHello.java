package Dynamic_proxies;

public class sayHello implements Hello{

    @Override
    public void sayHello() {
        System.out.println("Dynamic_proxies.Hello World");
    }
}

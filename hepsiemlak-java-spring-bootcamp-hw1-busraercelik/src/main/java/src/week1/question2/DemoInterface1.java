package src.week1.question2;

interface DemoInterface1 {
    default void print() {
        System.out.println("In DemoInterface1");
    }
}

interface DemoInterface2 {
    default void print() {
        System.out.println("In DemoInterface2");
    }
}

class DemoClass implements DemoInterface1, DemoInterface2 {
    @Override
    public void print() {
        DemoInterface1.super.print();
        DemoInterface2.super.print();
    }

    public static void main(String[] args) {
        DemoClass demoClass = new DemoClass();
        demoClass.print();
    }
}
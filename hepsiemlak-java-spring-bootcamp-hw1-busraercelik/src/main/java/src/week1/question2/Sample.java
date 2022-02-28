package src.week1.question2;

public abstract class Sample {
    abstract void print();
}

class SuperClass1 extends Sample {
    @Override
    void print() {
        System.out.println("Super1 Class");
    }
}

class SuperClass2 extends Sample {
    @Override
    void print() {
        System.out.println("Super2 Class");
    }
}

/*
class SubClass extends SuperClass1, SuperClass2 {
    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        subClass.print();
    }
}*/

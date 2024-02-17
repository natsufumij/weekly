public class SynchronizedDemo {
    public void method(){
        synchronized(this){
            System.out.println("synchronized 代码块...");
        }
    }
    public synchronized void method2(){
        System.out.println("synchronized 方法");
    }

    public static synchronized void method3(){
        System.out.println("静态 synchronized 方法");
    }
}

package mthreading;

// TODO: 8/26/2017 resolve deadlock. Deleting 'synchronized' is forbidden
public class DeadLockTask {

  private static final Object lock1 = new Object();
  private static final Object lock2 = new Object();

  private static void f1() {   // option 1: add synchronized
    synchronized (lock1) {
      try {
        Thread.sleep(1000);
        // option 3: lock1.wait();
      } catch (InterruptedException e) {}
      synchronized (lock2){
        //option 4: lock2.notify();
      }
    }
  }

  private static void f2() {  // option 1: add synchronized
    synchronized (lock2) {
      try {
        Thread.sleep(2000);
        // option 4: lock2.wait();
      } catch (InterruptedException e) {}

      synchronized (lock1){
        // option 3: lock1.notify();
      }
    }
  }


  public static void main(String[] args) { //option 2: add throws InterruptedException

    Runnable acs = new DeadLockThreadASC();
    Runnable desc = new DeadLockThreadDESC();

    Thread t1 = new Thread(acs);

    Thread t2 = new Thread(desc);

    t1.start();
    //option 2: insert t1.join();
    t2.start();
  }

  static class DeadLockThreadASC implements Runnable{

    @Override
    public void run() {
      f1();
      f2();
    }
  }

  static class DeadLockThreadDESC implements Runnable{

    @Override
    public void run() {
      f2();
      f1();
    }
  }


}


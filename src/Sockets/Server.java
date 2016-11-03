public Class Server extends Thread {

        public static final int NTHREADS = 5;
        ExecutorService executor = new ExecutorService(NTHREADS);

        public static void main( String[] args ){
                System.out.println("Dafuq");
                System.exit(1);
        }
}
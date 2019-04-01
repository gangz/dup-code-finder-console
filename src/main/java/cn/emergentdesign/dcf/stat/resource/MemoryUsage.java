package cn.emergentdesign.dcf.stat.resource;

public class MemoryUsage {
	public static void outputMemoryUsage() {
		// Get the Java runtime
	    Runtime runtime = Runtime.getRuntime();
	    // Run the garbage collector
	    runtime.gc();
	    // Calculate the used memory
	    long memory = runtime.totalMemory() - runtime.freeMemory();
	    System.out.println("Used memory in bytes: " + memory);
	    System.out.println("Used memory in megabytes: "
	                    + bytesToMegabytes(memory));
	}
	
	private static final long MEGABYTE = 1024L * 1024L;
	
	public static long bytesToMegabytes(long bytes) {
	        return bytes / MEGABYTE;
	}
}


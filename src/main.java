import java.io.IOException;
import java.util.List;

import org.jfree.ui.RefineryUtilities;

import com.gantt.Output;
import com.generator.Generator;
import com.job.Job;
import com.machine.Machine;

public class main {

	public static void main(String[] args) throws IOException {
		Generator gen = new Generator();

		List<Job> jobs = gen.generateSimple();
		gen.generateFile();
		gen.generateWithDependencies();
		
		System.out.println("spt");
		Machine mach = new Machine(0, jobs);
		mach.schedule();
		mach.print();
		
		System.out.println("edd");
		mach = new Machine(1, jobs);
		mach.schedule();
		mach.print();
		
		System.out.println("HM");
		mach = new Machine(2, jobs);
		mach.schedule();
		mach.print();
		

		System.out.println("wspt");
		mach = new Machine(3, jobs);
		mach.schedule();
		mach.print();
		
		
		System.out.println("OK!!!");
	
	
	
	 System.out.println("ok");
     
     final Output demo = new Output("Gantt");
     demo.pack();
     RefineryUtilities.centerFrameOnScreen(demo);
     demo.setVisible(true);
	
	}
}

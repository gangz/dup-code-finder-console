package cn.emergentdesign.dcf.cli;

import java.util.Collection;
import java.util.List;

import cn.emergentdesign.dcf.core.IndexCloneDetector;
import cn.emergentdesign.dcf.core.InvertedIndexTable.IndexEntry;
import cn.emergentdesign.dcf.data.CloneClass;
import cn.emergentdesign.dcf.data.CloneInstance;
import cn.emergentdesign.dcf.data.Segment;
import cn.emergentdesign.dcf.output.json.CloneData;
import cn.emergentdesign.dcf.output.json.JsonDumper;
import cn.emergentdesign.dcf.patterns.NullFrequentPatterns;
import cn.emergentdesign.dcf.stat.resource.MemoryUsage;
import cn.emergentdesign.dcf.uniform.Type1JavaUniformer;
import cn.emergentdesign.dcf.uniform.Type2JavaUniformer;
import cn.emergentdesign.dcf.uniform.Type2cJavaUniformer;
import cn.emergentdesign.dcf.uniform.Uniformer;

public class App {

	private CliParameters params;

	public App(CliParameters params) {
		this.params = params;
	}

	public void run() {
		Uniformer uniformer = buildUniformer(params.getType());
		IndexCloneDetector detector = new IndexCloneDetector(uniformer,params.getMinimumLines(),new NullFrequentPatterns());
		detector.addDir(params.getSrc());
		for (String dir:params.getDirs()) {
			detector.addDir(dir);
		}
		
		CloneData data = buildCloneData(detector);
		data.setParameters(params);

		JsonDumper dumper =new JsonDumper();
		dumper.output(params.getOutput()+".json", data);
		MemoryUsage.outputMemoryUsage();
	}

	private CloneData buildCloneData(IndexCloneDetector detector) {
		CloneData data = new CloneData();
		Collection<CloneClass> cloneGroups = detector.getCloneGroupBeforeAggregate();
		data.addGroup(cloneGroups);
		data.buildSummary();
		data.setFileCount(detector.getFileCount());
		data.setLineCount(detector.getLineCount());
		if (detector.getErrorMessage()!=null) {
			data.setMessage(detector.getErrorMessage());
			System.err.println(detector.getErrorMessage());
		}
		return data;
	}

	private Uniformer buildUniformer(String type) {
		switch(type){
		case "type1":
			return new Type1JavaUniformer();
		case "type2":
			return new Type2JavaUniformer();
		case "type2c":
			return new Type2cJavaUniformer();
		default:
			return new Type2JavaUniformer();
		}
	}
}

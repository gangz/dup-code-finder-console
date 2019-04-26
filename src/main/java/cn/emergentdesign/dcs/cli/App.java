package cn.emergentdesign.dcs.cli;

import java.util.Collection;
import java.util.List;

import cn.emergentdesign.dcs.core.IndexCloneDetector;
import cn.emergentdesign.dcs.core.InvertedIndexTable.IndexEntry;
import cn.emergentdesign.dcs.data.CloneClass;
import cn.emergentdesign.dcs.data.CloneInstance;
import cn.emergentdesign.dcs.data.Segment;
import cn.emergentdesign.dcs.output.json.CloneData;
import cn.emergentdesign.dcs.output.json.JsonDumper;
import cn.emergentdesign.dcs.patterns.NullFrequentPatterns;
import cn.emergentdesign.dcs.stat.resource.MemoryUsage;
import cn.emergentdesign.dcs.uniform.Type1JavaUniformer;
import cn.emergentdesign.dcs.uniform.Type2JavaUniformer;
import cn.emergentdesign.dcs.uniform.Type2cJavaUniformer;
import cn.emergentdesign.dcs.uniform.Uniformer;

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
		Collection<CloneClass> cloneGroups = null;
		if (params.isNoAdjacentJoin()) {
			cloneGroups = detector.getCloneGroupBeforeAggregate();
		}else {
			cloneGroups = detector.getCloneGroupOfAdjacent();
		}
		data.addGroup(cloneGroups);
		data.buildSummary();
		data.setFileCount(detector.getFileCount());
		data.setLineCount(detector.getLineCount());
		if (detector.getErrorMessage()!=null) {
			data.setMessage(detector.getErrorMessage());
			System.err.println(detector.getErrorMessage());
		}
		cloneGroups = detector.getCloneGroupOfAdjacent();

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


package cn.emergentdesign.dcs.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "dupcode-cli")
public class CliParameters {
	
	@Parameters(index = "0", description = "The directory to be analyzed")
    private String src;
	@Parameters(index = "1",  description = "The output file name")
	private String output;
	@Option(names = {"-t", "--type"},  description = "The duplication type (type1, type2(default), type2c, type3)")
	private String type="type2";
	@Option(names = {"-g", "--granularity"},  description = "Granularity of duplication(file, class, method(default), fragment)")
    private String granularity="method";
	@Option(names = {"--min-lines"}, description = "The minimum lines of code duplication")
    private Integer minimumLines = new Integer(5);
    @Option(names = {"-h","--help"}, usageHelp = true, description = "display this help and exit")
    boolean help;
    @Option(names = {"--no-adjacent-join"}, description = "do not join adjacent segments")
    boolean noAdjacentJoin=false;

	@Option(names = {"-s","--sources"},split=",", description = "The additional directories to be analyzed")
    private String[] dirs = new String[] {};

    public String[] getDirs() {
		return dirs;
	}
	public void setDirs(String[] dirs) {
		this.dirs = dirs;
	}
	public CliParameters() {
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGranularity() {
		return granularity;
	}
	public void setGranularity(String granularity) {
		this.granularity = granularity;
	}
	public Integer getMinimumLines() {
		return minimumLines;
	}
	public void setMinimumLines(Integer minimumLines) {
		this.minimumLines = minimumLines;
	}
	public boolean isHelp() {
		return help;
	}
	public void setHelp(boolean help) {
		this.help = help;
	}
	
    public boolean isNoAdjacentJoin() {
		return noAdjacentJoin;
	}
    
	public void setNoAdjacentJoin(boolean noAdjacentJoin) {
		this.noAdjacentJoin = noAdjacentJoin;
	}
}

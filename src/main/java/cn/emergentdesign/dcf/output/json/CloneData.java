/*
MIT License

Copyright (c) 2018-2019 Gang ZHANG

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package cn.emergentdesign.dcf.output.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import cn.emergentdesign.dcf.cli.CliParameters;
import cn.emergentdesign.dcf.data.CloneClass;

@XmlRootElement(name = "code-clones")
public class CloneData {
    private CliParameters parameters;
    private String message = "success";
    private Integer groupCount = 0;
    private Long fileCount  = 0L;
    private Long lineCount = 0L;
    private ArrayList<CloneClass> cloneGroup = new ArrayList<>();
    
	public ArrayList<CloneClass> getCloneGroup() {
		return cloneGroup;
	}

	public void setCloneGroup(ArrayList<CloneClass> cloneGroup) {
		this.cloneGroup = cloneGroup;
	}

	public CloneClass addGroup() {
		CloneClass group = new CloneClass();
		this.cloneGroup.add(group);
		return group;
	}


	public CliParameters getParameters() {
		return parameters;
	}

	public void setParameters(CliParameters parameters) {
		this.parameters = parameters;
	}

	public Integer getGroupCount() {
		return groupCount;
	}

	public void setGroupCount(Integer groupCount) {
		this.groupCount = groupCount;
	}

	public void buildSummary() {
		this.groupCount = this.cloneGroup.size();
	}

	public Long getLineCount() {
		return lineCount;
	}

	public void setLineCount(Long lineCount) {
		this.lineCount = lineCount;
	}

	public Long getFileCount() {
		return fileCount;
	}

	public void setFileCount(Long fileCount) {
		this.fileCount = fileCount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void addGroup(Collection<CloneClass> cloneGroups) {
		this.cloneGroup.addAll(cloneGroups);
	}
}


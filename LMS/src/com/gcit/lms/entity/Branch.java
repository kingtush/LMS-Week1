/**
 * 
 */
package com.gcit.lms.entity;

import java.util.List;

/**
 * @author TUSH
 *
 */
public class Branch {

	private Integer branchId;
	private String branchName;
	private String branchAddress;

	/**
	 * @return the BranchId
	 */
	public Integer getBranchId() {
		return branchId;
	}

	/**
	 * @param BranchId the BranchId to set
	 */
	public void setBranchId(Integer LibraryBranchId) {
		this.branchId = LibraryBranchId;
	}

	/**
	 * @return the BranchName
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * @param BranchName the authorName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String address) {
		this.branchAddress = address;
	}

}

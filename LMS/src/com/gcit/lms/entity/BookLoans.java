/**
 * 
 */
package com.gcit.lms.entity;

import java.sql.Date;

/**
 * @author ppradhan
 *
 */
public class BookLoans {

	private Integer bookId;
	private Integer branchId;
	private Integer borrowerId;
	private Date dateOut;
	private Date dateDue;
	private Date dateIn;

	/**
	 * @return the bookId
	 */
	public Integer getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the branchId
	 */
	public Integer getBranchId() {
		return branchId;
	}

	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	/**
	 * @return the borrowerId
	 */
	public Integer getBorrowerId() {
		return borrowerId;
	}

	/**
	 * @param borrowerId the borrowerId to set
	 */
	public void setBorrowerId(Integer borrowerId) {
		this.borrowerId = borrowerId;
	}

	/**
	 * @return the dateOut
	 */
	public Date getDateOut() {
		return dateOut;
	}

	/**
	 * @param dateOut the dateOut to set
	 */
	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	/**
	 * @return the dateDue
	 */
	public Date getDateDue() {
		return dateDue;
	}

	/**
	 * @param dateDue the dateDue to set
	 */
	public void setDateDue(Date dateDue) {
		this.dateDue = dateDue;
	}

	/**
	 * @return the dateIn
	 */
	public Date getDateIn() {
		return dateIn;
	}

	/**
	 * @param dateIn the dateIn to set
	 */
	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

}

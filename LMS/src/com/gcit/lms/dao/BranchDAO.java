package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;

public class BranchDAO extends BaseDAO<Branch> {

	public BranchDAO(Connection conn) {
		super(conn);
	}

	public void addLibraryBranch(Branch branch) throws ClassNotFoundException, SQLException {
		save("insert into tbl_library_branch (branchName) values (?)", new Object[] { branch.getBranchName() });
	}

	public void updateLibraryBranch(Branch branch) throws ClassNotFoundException, SQLException {
		save("update tbl_library_branch set branchName = ? where branchId = ?",
				new Object[] { branch.getBranchName(), branch.getBranchId() });
	}

	public void deleteLibraryBranch(Branch branch) throws ClassNotFoundException, SQLException {
		save("delete from tbl_library_branch where branchId = ?", new Object[] { branch.getBranchId() });
	}

	public List<Branch> readAllBranches() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_library_branch", null);
	}

	public boolean validateBranch(Branch branch) throws ClassNotFoundException, SQLException {
		ResultSet rs = validateRecord("select * from tbl_library_branch where branchId = ?",
				new Object[] { branch.getBranchId() });
		// System.out.println("made it here for validation");
		boolean notempty = false;
		while (rs.next()) {
			// ResultSet processing here
			notempty = true;
		}

		return notempty;
	}

	public List<Branch> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Branch> branches = new ArrayList<>();
		BranchDAO bdao = new BranchDAO(conn);
		while (rs.next()) {
			Branch branch = new Branch();
			branch.setBranchId(rs.getInt("branchId"));
			branch.setBranchName(rs.getString("branchName"));
			branch.setBranchAddress(rs.getString("branchAddress"));
			// populate my books
			// LibraryBranch.setBooks(bdao.readFirstLevel("select * from tbl_book where
			// bookId IN ( select bookId from tbl_book_authors where authorId = ?)", new
			// Object[]{LibraryBranch.getLibraryBranchId()}));
			// LibraryBranchs.add(LibraryBranch);
			branches.add(branch);
		}
		return branches;
	}

	public List<Branch> extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Branch> branches = new ArrayList<>();
		while (rs.next()) {
			Branch branch = new Branch();
			branch.setBranchId(rs.getInt("branchId"));
			branch.setBranchName(rs.getString("branchName"));
			branches.add(branch);
		}
		return branches;
	}

}
//}

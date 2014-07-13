package in.weighpro.dataComponents;

import java.sql.SQLException;

import in.weighpro.databaseQueryHandler.IncompleteEntriesRetriever;

public class IncompleteEntries {
	private Entry[] incompleteEntries;
	public IncompleteEntries() throws SQLException{
		IncompleteEntriesRetriever incompRet = new IncompleteEntriesRetriever();
		int[] inRet = incompRet.getIncompleteEntries();
		incompleteEntries = new Entry[inRet.length];
		for(int i=0;i<inRet.length;i++)
			incompleteEntries[i] = new Entry(inRet[i]);
		
	}
	
	public Entry[] getIncompleteEntries(){
		return this.incompleteEntries;
	}
}

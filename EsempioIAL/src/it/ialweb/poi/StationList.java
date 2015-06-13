package it.ialweb.poi;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class StationList {
	@SerializedName("results")
	private ArrayList<Station> list;

	public ArrayList<Station> getList() {
		return list;
	}
}

package it.ialweb.poi;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class StationList {
	@SerializedName("results")
	private List<Station> list;

	public List<Station> getList() {
		return list;
	}
}

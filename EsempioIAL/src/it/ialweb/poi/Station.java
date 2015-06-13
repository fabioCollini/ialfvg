package it.ialweb.poi;

import android.os.Parcel;
import android.os.Parcelable;

public class Station implements Parcelable {
	private double lat;

	private double lon;

	private String name;

	Station() {
	}

	public Station(double lat, double lon, String name) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.name = name;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}

	public String getName() {
		return name;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeDouble(lat);
		dest.writeDouble(lon);
		dest.writeString(name);
	}

	public final static Parcelable.Creator<Station> CREATOR = new Parcelable.Creator<Station>() {
		@Override
		public Station createFromParcel(Parcel source) {
			return new Station(source.readDouble(), source.readDouble(), source.readString());
		}

		@Override
		public Station[] newArray(int size) {
			return new Station[size];
		}
	};

	public String getImageUrl() {
		return "https://maps.googleapis.com/maps/api/staticmap?zoom=15&size=600x300&maptype=roadmap&markers=color:blue%7Clabel:S%7C"
				+ getLat() + "," + getLon();
	}
}

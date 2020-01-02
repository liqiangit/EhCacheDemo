package com.liqiangit;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

/**
 * 
 * @author liqiang
 *
 */
@Data
public class Row implements Serializable{
	private String featureId;
	private String[] libraryName;

	private Long faceId;
	private Integer[] address;

	private String peopleId;
	private long updatedOn;
	private int bioSubtype;

	public Row() {
	}

	public Row(String[] libraryName, String featureId) {
		this.libraryName = libraryName;
		this.featureId = featureId;
	}

	

	private boolean equals(String[] libraryName, String[] libraryName2) {
		if (libraryName == null && libraryName2 == null) {
			return true;
		}
		if (libraryName == null || libraryName2 == null) {
			return false;
		}
		if (libraryName.length != libraryName2.length) {
			return false;
		}
		boolean e = true;
		for (int i = 0; i < libraryName.length; i++) {
			if (!StringUtils.equals(libraryName[i], libraryName2[i])) {
				e = false;
				break;
			}
		}
		return e;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;// 地址相等
		}

		if (obj == null) {
			return false;// 非空性：对于任意非空引用x，x.equals(null)应该返回false。
		}

		if (obj instanceof Row) {
			Row other = (Row) obj;
			// 需要比较的字段相等，则这两个对象相等
			if (StringUtils.equals(this.getFeatureId(), other.getFeatureId())
					&& equals(this.getLibraryName(), other.getLibraryName())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + (featureId == null ? 0 : featureId.hashCode());
		for (int i = 0; i < libraryName.length; i++) {
			result = 31 * result + libraryName[i].hashCode();
		}
		return result;
	}
}

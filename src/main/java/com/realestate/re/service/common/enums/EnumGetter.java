package com.realestate.re.service.common.enums;

import java.util.ArrayList;
import java.util.List;

public class EnumGetter {

	public static List<String> getStatusForEdit() {

		Status[] arrayList = Status.values();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < arrayList.length; i++) {

			if (arrayList[i] != Status.DELETED) {
				list.add(arrayList[i].name());
			}

		}

		return list;

	}

}

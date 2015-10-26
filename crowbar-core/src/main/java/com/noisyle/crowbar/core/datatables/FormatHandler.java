package com.noisyle.crowbar.core.datatables;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.noisyle.crowbar.core.base.BaseModel;
import com.noisyle.crowbar.core.datatables.element.Column;
import com.noisyle.crowbar.core.util.ReflectionUtils;

public class FormatHandler {
	public static <T extends BaseModel> FormatedPage handle(Page<T> page, PageParam pageapram) {
		FormatedPage pf = new FormatedPage();
		pf.setDraw(page.getDraw());
		pf.setRecordsFiltered(page.getRecordsFiltered());
		pf.setRecordsTotal(page.getRecordsTotal());
		pf.setData(new LinkedList<Map<String, Object>>());

		for (int j = 0; j < page.getData().size(); j++) {
			T row = page.getData().get(j);
			Map<String, Object> formatedRow = new HashMap<String, Object>();
			for (int i = 0; i < pageapram.getColumns().length; i++) {
				Column column = pageapram.getColumns()[i];
				if(column.getData()==null) continue;
				try {
					Object value = ReflectionUtils.getFieldValue(row, column.getData());
					if (column.getFormatter() != null) {
						value = column.getFormatter().format(value);
					}
					formatedRow.put(column.getData(), value);
				} catch (Exception e) {
				}
			}
			pf.getData().add(formatedRow);
		}
		return pf;
	}
}

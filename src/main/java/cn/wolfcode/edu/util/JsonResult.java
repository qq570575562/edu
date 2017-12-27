package cn.wolfcode.edu.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JsonResult {
	private boolean success;
	private String msg;
	public JsonResult(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}
	public JsonResult(boolean success) {
		this.success = success;
	}
}
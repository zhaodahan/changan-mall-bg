package com.ec.changan.bg.controller.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ec.changan.bg.entities.CommonResult;


public class CustomerBlockHandler {
	public static CommonResult handlerException(BlockException exception) {
		return new CommonResult(444,"客户自定义全局处理-1");
	}

	public static CommonResult handlerException2(BlockException exception) {
		return new CommonResult(444,"客户自定义全局处理-2");
	}
}

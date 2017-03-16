package com.object.swagger.api;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Api(value = "order", description = "订单管理", produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
@RequestMapping("order")
public class OrderContoller {

	// 列出某个类目的所有规格
		@ApiOperation(value = "获得订单列表", notes = "订单列表信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		@RequestMapping(value = "list", method = RequestMethod.GET)
		public Result<Order> list(
				@ApiParam(value = "订单id", required = true) @RequestParam Long id,	
				@ApiParam(value = "订单名称", required = true) @RequestParam String orderName) {
			Result<Order> result = new Result<Order>();
			Order order = new Order();
			order.setOrderId(1);
			order.setOrderName("订单名称");
			order.setTime(new Date());
			result.setData(order);
			return result;
		}
		
		@ApiOperation(value="添加订单",notes="添加订单信息",httpMethod="POST",produces=MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		@RequestMapping(value="addOrder",method=RequestMethod.POST)
		public int addOrder(Order order){
			System.out.println("***************"+order.getOrderName());
			if(order.getOrderName()!=null){
				return 1;
			}
			return 0;
		};
}

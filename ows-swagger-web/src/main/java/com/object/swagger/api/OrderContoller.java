package com.object.swagger.api;

import com.object.swagger.api.model.Order;
import com.object.swagger.api.model.Result;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("order")
@Api(value = "order", description = "订单管理", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
public class OrderContoller {

		// 列出某个类目的所有规格
		@RequestMapping(value = "list", method = RequestMethod.GET)
		@ApiOperation(value = "获得订单列表", notes = "订单列表信息", httpMethod = "GET")
		@ApiImplicitParams({
				@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
				@ApiImplicitParam(name = "orderName", value = "订单名称", required = true, dataType = "String")
		})
		public Result<Order> list(@RequestBody Long id, @RequestBody String orderName) {
			Result<Order> result = new Result<Order>();
			Order order = new Order();
			order.setOrderId(1);
			order.setOrderName("订单名称");
			order.setTime(new Date());
			result.setData(order);
			return result;
		}
		@RequestMapping(value="addOrder", method=RequestMethod.POST)
		@ApiOperation(value="添加订单", notes="添加订单信息", httpMethod="POST")
		@ApiImplicitParams({
				@ApiImplicitParam(name = "order", value = "用户实体", required = true, dataType = "Order")
		})
		public int addOrder(@RequestBody Order order){
			System.out.println("***************"+order.getOrderName());
			if(order.getOrderName()!=null){
				return 1;
			}
			return 0;
		};
}

package cn.bxd.sip.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.bxd.sip.bxd.pay.BillDownService;
import cn.bxd.sip.bxd.util.JsonTools;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class QueryTransController {

    @Autowired
    private BillDownService billDownService;
	
	@RequestMapping(value = "query/queryTrans", method = RequestMethod.POST)
	@ResponseBody
	public String queryTrans(@RequestParam String operation, @RequestParam String hospitalCode, 
			@RequestParam String startTime, @RequestParam String endTime) {
		return JsonTools.obj2Json(billDownService.queryTrans(Integer.parseInt(hospitalCode), startTime, endTime));
	}
	
}

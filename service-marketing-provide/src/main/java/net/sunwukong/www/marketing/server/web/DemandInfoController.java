package net.sunwukong.www.marketing.server.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.api.util.ApiTool;
import net.sunwukong.www.marketing.bean.DemandInfo;
import net.sunwukong.www.marketing.server.domain.DemandParamPo;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * @Copyright (C), 2018, 成都孙悟空文化传媒有限公司
 * @FileName: DemandInfoController
 * @Author: kangdong
 * @Date: 2018/6/16 下午7:52
 * @Description: 用户需求信息控制层
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@RestController
@RequestMapping(value = "/demandinfo")
@Api(tags = "需求信息", description = "需求信息服务")
public class DemandInfoController extends BaseController {


    @RequestMapping(value="/getDemandList",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "获取我的全部需求列表", httpMethod = "POST", notes = "获取我的全部需求列表",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "页码",paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量",paramType = "query"),
    })
    public ResponseData getDemandList(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String, String> data = requestData.getData();
        DemandParamPo demandParamPo = new DemandParamPo();
        Integer start = ApiTool.getStartPage(Integer.parseInt(data.get("pageNo")), Integer.parseInt(data.get("pageSize")));
        demandParamPo.setUserNo(data.get("userNo"));
        demandParamPo.setStart(start);
        demandParamPo.setPageSize(Integer.parseInt(data.get("pageSize")));
        return iDemandInfoService.getDemandList(demandParamPo);
    }

    @RequestMapping(value="/getNotStartedList",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "未开始的列表", httpMethod = "POST", notes = "未开始的列表",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "页码",paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量",paramType = "query"),
    })
    public ResponseData getNotStartedList(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String, String> data = requestData.getData();
        DemandParamPo demandParamPo = new DemandParamPo();
        Integer start = ApiTool.getStartPage(Integer.parseInt(data.get("pageNo")), Integer.parseInt(data.get("pageSize")));
        demandParamPo.setUserNo(data.get("userNo"));
        demandParamPo.setStart(start);
        demandParamPo.setPageSize(Integer.parseInt(data.get("pageSize")));
        return iDemandInfoService.getNotStartedList(demandParamPo);
    }


    @RequestMapping(value="/getInServiceList",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "在服务中的列表", httpMethod = "POST", notes = "在服务中的列表",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "页码",paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量",paramType = "query"),
    })
    public ResponseData getInServiceList(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String, String> data = requestData.getData();
        DemandParamPo demandParamPo = new DemandParamPo();
        Integer start = ApiTool.getStartPage(Integer.parseInt(data.get("pageNo")), Integer.parseInt(data.get("pageSize")));
        demandParamPo.setUserNo(data.get("userNo"));
        demandParamPo.setStart(start);
        demandParamPo.setPageSize(Integer.valueOf(data.get("pageSize")));
        return iDemandInfoService.getInServiceList(demandParamPo);
    }


    @RequestMapping(value="/getWaitEndList",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "待结单的列表", httpMethod = "POST", notes = "待结单的列表",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "页码",paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量",paramType = "query"),
    })
    public ResponseData getWaitEndList(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String, String> data = requestData.getData();
        DemandParamPo demandParamPo = new DemandParamPo();
        Integer start = ApiTool.getStartPage(Integer.parseInt(data.get("pageNo")), Integer.parseInt(data.get("pageSize")));
        demandParamPo.setUserNo(data.get("userNo"));
        demandParamPo.setStart(start);
        demandParamPo.setPageSize(Integer.parseInt(data.get("pageSize")));
        return iDemandInfoService.getWaitEndList(demandParamPo);
    }

    @RequestMapping(value="/getFinishList",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "已结单的列表", httpMethod = "POST", notes = "已结单的列表",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "页码",paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量",paramType = "query"),
    })
    public ResponseData getFinishList(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String, String> data = requestData.getData();
        DemandParamPo demandParamPo = new DemandParamPo();
        Integer start = ApiTool.getStartPage(Integer.parseInt(data.get("pageNo")), Integer.parseInt(data.get("pageSize")));
        demandParamPo.setUserNo(data.get("userNo"));
        demandParamPo.setStart(start);
        demandParamPo.setPageSize(Integer.valueOf(data.get("pageSize")));
        return iDemandInfoService.getFinishList(demandParamPo);
    }

    @RequestMapping(value="/getDemandInfoDetail",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "获取我的需求信息详情", httpMethod = "POST", notes = "获取我的需求信息详情",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id编码",paramType = "query"),
    })
    public ResponseData getDemandInfoDetail(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String, String> data = requestData.getData();
        return iDemandInfoService.getDemandInfoDetail(data.get("id"));
    }


    @RequestMapping(value="/publishDemandInfo",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "发布需求信息", httpMethod = "POST", notes = "发布需求信息",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query",defaultValue = "yh201808281413036335"),
            @ApiImplicitParam(name = "phoneNo", value = "联系电话",paramType = "query",defaultValue = "18108099936"),
            @ApiImplicitParam(name = "categoryOneNo", value = "一级类目编码",paramType = "query",defaultValue = "10001"),
            @ApiImplicitParam(name = "categoryOneName", value = "一级类目名称",paramType = "query",defaultValue = "旅游服务"),
            @ApiImplicitParam(name = "categoryTwoNo", value = "二级类目编码",paramType = "query",defaultValue = "10001107"),
            @ApiImplicitParam(name = "categoryTwoName", value = "二级类目名称",paramType = "query",defaultValue = "租车"),
            @ApiImplicitParam(name = "cityNo", value = "城市编码",paramType = "query",defaultValue = "10001000"),
            @ApiImplicitParam(name = "cityName", value = "城市名称",paramType = "query",defaultValue = "北京"),
            @ApiImplicitParam(name = "payNum", value = "奖励币数",paramType = "query",defaultValue = "10"),
            @ApiImplicitParam(name = "specialRemark", value = "需求特别说明",paramType = "query",defaultValue = "特别说明"),
            @ApiImplicitParam(name = "scopeOneNo", value = "一级服务范围编码",paramType = "query",defaultValue = "10001"),
            @ApiImplicitParam(name = "scopeOneName", value = "一级服务范围名称",paramType = "query",defaultValue = "亚洲"),
            @ApiImplicitParam(name = "scopeTwoNo", value = "二级服务范围编码",paramType = "query",defaultValue = "10001103"),
            @ApiImplicitParam(name = "scopeTwoName", value = "二级服务范围名称",paramType = "query",defaultValue = "马来西亚"),
    })
    public ResponseData insertDemandInfo(@RequestBody(required = false) @Valid DemandInfo demandInfo, BindingResult result) {
        if (result.hasErrors()){
            throw new GrilException(result.getFieldError().getDefaultMessage());
        }
        return iDemandInfoService.insertDemandInfo(demandInfo);
    }

    //拆销订单
    @RequestMapping(value="/cancelDemandInfo",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "拆销订单", httpMethod = "POST", notes = "拆销订单",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id标识",paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
    })
    public ResponseData cancelDemandInfo(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String, String> data = requestData.getData();
        return iDemandInfoService.updateCancelDemandInfo(data.get("id"),data.get("userNo"));
    }

    @RequestMapping(value="/confirmFinish",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "确认结单", httpMethod = "POST", notes = "确认结单",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "demandNo", value = "需求单号",paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
    })
    public ResponseData confirmFinish(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String, String> data = requestData.getData();
        return iDemandInfoService.updateConfirmFinish(data.get("demandNo"),data.get("userNo"));
    }

    @RequestMapping(value="/gethomedemandinfo",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "获取首页需求列表", httpMethod = "POST", notes = "获取首页需求列表",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query",defaultValue = "yh201806241935359471"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
    })
    public ResponseData getHomeDemandinfo(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        return iDemandInfoService.getHomeDemandinfo(requestData.getData());
    }

    @RequestMapping(value="/grabsingledemandinfo",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "抢单", httpMethod = "POST", notes = "抢单",response = DemandInfo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "抢单用户编码",paramType = "query",defaultValue = "yh201806241935359471"),
            @ApiImplicitParam(name = "demandNo", value = "需求编码",paramType = "query"),
    })
    public ResponseData updateSingleDemandInfo(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String, String> data = requestData.getData();
        return iDemandInfoService.updateSingleDemandInfo(data.get("userNo"),data.get("demandNo"));
    }
}

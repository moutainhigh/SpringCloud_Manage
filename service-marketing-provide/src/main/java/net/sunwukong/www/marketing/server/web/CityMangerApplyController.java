package net.sunwukong.www.marketing.server.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sunwukong.www.api.entity.RequestData;
import net.sunwukong.www.api.entity.ResponseData;
import net.sunwukong.www.api.exception.GrilException;
import net.sunwukong.www.api.util.ApiTool;
import net.sunwukong.www.marketing.bean.CityMangerApply;
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
 * @FileName: CityMangerApplyController
 * @Author: kangdong
 * @Date: 2018/6/20 下午2:22
 * @Description: 城市管理者申请控制类(城市运营官)
 * @Version: 1.0
 * @History: <author>          <time>          <version>          <desc>
 *
 */
@RestController
@RequestMapping(value = "/citymangerapply")
@Api(tags = "城市管理者申请", description = "城市管理者申请服务")
public class CityMangerApplyController extends BaseController {

    @RequestMapping(value="/apply",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "申请成为城市管理者", httpMethod = "POST", notes = "城市管理者申请",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "age", value = "年龄",paramType = "query"),
            @ApiImplicitParam(name = "sexNo", value = "用户性别 1001",paramType = "query"),
            @ApiImplicitParam(name = "phoneNo", value = "手机号",paramType = "query"),
            @ApiImplicitParam(name = "selfAvaluation", value = "个人介绍",paramType = "query"),
            @ApiImplicitParam(name = "cityNo", value = "城市编码",paramType = "query"),
            @ApiImplicitParam(name = "cityName", value = "城市名称",paramType = "query"),
            @ApiImplicitParam(name = "contractAddress", value = "联系地址",paramType = "query"),
    })
    public ResponseData apply(@RequestBody(required = false) @Valid CityMangerApply cityMangerApply, BindingResult result){
        if (result.hasErrors()){
            throw new GrilException(result.getFieldError().getDefaultMessage());
        }
        return iCityMangerApplyService.applyCityMangerApply(cityMangerApply);
    }


    /**
     * PC审核城市运营官
     * @param requestData
     * @return
     */
    @RequestMapping(value="/updateAudit",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "PC审核城市运营官", httpMethod = "POST", notes = "PC审核城市运营官",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "auditState", value = "申请状态：10070002已审核，10070003已驳回",paramType = "query"),
    })
    public ResponseData updateAudit(@RequestBody(required = false) RequestData<Map<String,String>> requestData){
        Map<String,String> data = requestData.getData();
        return iCityMangerApplyService.updateAudit(data);
    }


    /**
     * 运营官下面的服务机构列表
     * 每个服务机构已完成总单数
     * @param requestData
     * @return
     */
    @RequestMapping(value="/getServiceList",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "服务机构列表", httpMethod = "POST", notes = "服务机构列表",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query"),
    })
    public ResponseData getServiceList(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String,String> data = requestData.getData();
        return iCityMangerApplyService.getServiceList(data.get("userNo"),data.get("pageNo"),data.get("pageSize"));
    }


    /**
     * 运营官管理界面接口
     * 1.运营下的服务机构的服务需求投诉列表
     * 2.运营官所在城市下未开始的需求列表
     */
    @RequestMapping(value="/getManagerList",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "管理列表", httpMethod = "POST", notes = "管理列表",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "运营官编码",paramType = "query"),
    })
    public ResponseData getManagerList(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String,String> data = requestData.getData();
        return iCityMangerApplyService.getManagerList(data.get("userNo"),data.get("pageNo"),data.get("pageSize"));
    }

    @RequestMapping(value="/getnotstartdemandlist",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "获取未开始的需求列表", httpMethod = "POST", notes = "获取未开始的需求列表",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "运营官编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页面",paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query"),
    })
    public ResponseData getNotStartDemandList(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String,String> data = requestData.getData();
        int pageNo = Integer.parseInt(data.get("pageNo"));
        int pageSize = Integer.parseInt(data.get("pageSize"));
        int start = ApiTool.getStartPage(pageNo, pageSize);
        return iCityMangerApplyService.getNotStartDemandList(data.get("userNo"),start,pageSize);
    }

    @RequestMapping(value="/getnotstartdemanddetail",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "获取未开始的需求详情", httpMethod = "POST", notes = "获取未开始的需求详情",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "demandNo", value = "需求编码",paramType = "query"),
    })
    public ResponseData getNotStartDemandDetail(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String,String> data = requestData.getData();
        return iCityMangerApplyService.getNotStartDemandDetail(data.get("demandNo"));
    }

    @RequestMapping(value="/sendorders",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "运营官派单", httpMethod = "POST", notes = "运营官派单",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "demandNo", value = "需求编码",paramType = "query",defaultValue = "xq201808061722507354"),
            @ApiImplicitParam(name = "userNo", value = "运营官编码",paramType = "query",defaultValue = ""),
    })
    public ResponseData sendOrders(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String,String> data = requestData.getData();
        return iCityMangerApplyService.getSendOrders(data.get("demandNo"),data.get("userNo"));
    }


    /**
     * 城市运营官待审核的服务机构申请列表
     */
    @RequestMapping(value="/getApplyList",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "待审核列表", httpMethod = "POST", notes = "待审核列表",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
    })
    public ResponseData getApplyList(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String,String> data = requestData.getData();
        return iCityMangerApplyService.getApplyList(data.get("userNo"),data.get("pageNo"),data.get("pageSize"));
    }


    /**
     * 城市运营官审核服务机构
     */
    @RequestMapping(value="/auditService",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "审核服务机构", httpMethod = "POST", notes = "审核服务机构",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id标识",paramType = "query"),
            @ApiImplicitParam(name = "auditState", value = "审核状态:10070002已审核,10070003已驳回",paramType = "query"),
    })
    public ResponseData auditService(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String,String> data = requestData.getData();
        return iCityMangerApplyService.updateAuditServiceAgency(data.get("id"), data.get("auditState"));
    }



    /**
     * 通过申请编码查看待审核的服务机构详情
     */
    @RequestMapping(value="/getOrganizationDetail",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "机构详情", httpMethod = "POST", notes = "机构详情",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyNo", value = "申请编码",paramType = "query"),
    })
    public ResponseData getOrganizationDetail(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String,String> data = requestData.getData();
        return iCityMangerApplyService.getOrganizationDetail(data);
    }


    /**
     * 运营官查看需求详情
     * 已结单的要关联需求服务表、服务机构表、评价表
     */
    @RequestMapping(value="/getDemandDetail",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "运营官查看需求详情", httpMethod = "POST", notes = "运营官查看需求详情",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "demandNo", value = "需求单号",paramType = "query"),
            @ApiImplicitParam(name = "demandState", value = "需求状态:10080001未接单,10280002投诉中",paramType = "query"),
    })
    public ResponseData getDemandDetail(@RequestBody(required = false) RequestData<Map<String,String>> requestData) {
        Map<String,String> data = requestData.getData();
        return iCityMangerApplyService.getDemandDetail(data.get("demandNo"), data.get("demandState"));
    }

}
